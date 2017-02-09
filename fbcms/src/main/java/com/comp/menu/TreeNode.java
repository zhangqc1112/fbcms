package com.comp.menu;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.collections.IteratorUtils;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class TreeNode<T extends TreeNode<T>> implements Serializable {

    private static final long serialVersionUID = -3302676854582885340L;

    protected Map<Object, T> nodeMap = new HashMap<>();
    protected transient List<Object> childrenkeys = new ArrayList<>();
    protected transient T parent;
    protected transient T prev;
    protected transient T next;

    abstract protected Object getKey();

    abstract protected boolean noChild();

    @JSONField(ordinal = 999)
    public List<T> getChildren() {
        List<T> arrayList = new ArrayList<T>();
        for (Object key : childrenkeys) {
            arrayList.add(nodeMap.get(key));
        }
        return arrayList;
    }

    public T getParent() {
        return parent;
    }

    public T getPrev() {
        return prev;
    }

    public T getNext() {
        return next;
    }

    public boolean hasChildren() {
        return nodeMap != null && !nodeMap.isEmpty();
    }

    public void addChild(T child) {
        this.insertChild(childrenkeys.size(), child);
    }

    @SuppressWarnings("unchecked")
    public void insertChild(int idx, T child) {
        if (noChild()) {
            throw new IllegalStateException("Cannot add children to leaf");
        }
        if (child == null) {
            throw new IllegalArgumentException("Child cannot be null");
        }
        Object key = child.getKey();
        if (key == null) {
            throw new IllegalArgumentException("Child's key cannot be null");
        }
        if (childrenkeys.contains(key)) {
            throw new IllegalArgumentException("Duplicate child key: " + child.getKey());
        }
        /*        Set<Object> retains = new HashSet<>(nodeMap.keySet());
                retains.retainAll(child.nodeMap.keySet());
                if (!retains.isEmpty()) {
                    throw new IllegalStateException("Duplicate node key(s) found in target node tree: "
                            + Arrays.toString(retains.toArray()));
                nodeMap.putAll(child.nodeMap);
                }*/

        childrenkeys.add(idx, key);
        nodeMap.put(key, child);
        int size = childrenkeys.size();
        if (idx + 1 < size) {
            T childNext = nodeMap.get(childrenkeys.get(idx + 1));
            childNext.prev = child;
            child.next = childNext;
        }
        if (idx > 0) {
            T childPrev = nodeMap.get(childrenkeys.get(idx - 1));
            childPrev.next = child;
            child.prev = childPrev;
        }
        child.parent = (T) this;
    }

    public void removeChild(Object key) {
        if (noChild()) {
            return;
        }
        nodeMap.remove(key);
        childrenkeys.remove(key);
    }

    public T getChild(Object key) {
        if (noChild()) {
            return null;
        }
        return nodeMap.get(key);
    }

    @SuppressWarnings("unchecked")
    public Iterator<Object> childrenKeysIterator() {
        if (noChild()) {
            return IteratorUtils.EMPTY_ITERATOR;
        }
        return IteratorUtils.unmodifiableIterator(childrenkeys.iterator());
    }

    public T lastChild() {
        if (!childrenkeys.isEmpty()) {
            return this.getChild(childrenkeys.get(childrenkeys.size() - 1));
        }
        return null;
    }

    public int indexOf(Object key) {
        if (noChild()) {
            return -1;
        }
        return childrenkeys.indexOf(key);
    }

    public int indexOfParent() {
        if (parent == null) {
            return -1;
        }
        return parent.indexOf(getKey());
    }

    protected void clear() {
        this.childrenkeys.clear();
        this.nodeMap.clear();
    }

    public int size() {
        return nodeMap.size();
    }

}
