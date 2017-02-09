package com.comp.menu;

import java.util.*;

public class MenuTree {

    private MenuTreeNode root;

    private Map<Object, MenuTreeNode> nodeMap;


    public MenuTree(MenuTreeNode treeRoot) {
        this.root = treeRoot;
        this.nodeMap = new LinkedHashMap<>();
        buildNodeMap(root);
    }

    public MenuTreeNode getTreeRoot() {
        return root;
    }

    public Map<Object, MenuTreeNode> getNodeMap() {
        return nodeMap;
    }

    public MenuTreeNode getTreeNode(Object key) {
        return nodeMap.get(key);
    }

    public Menu getMenu(Object key) {
        MenuTreeNode node = nodeMap.get(key);
        if (node != null) {
            return node.getMenu();
        }
        return null;
    }

    private void buildNodeMap(MenuTreeNode node) {
        Object key = node.getKey();
        if (nodeMap.containsKey(key)) {
            throw new IllegalStateException("Duplicate node key found in target node: " + node);
        }
        nodeMap.put(node.getKey(), node);
        for (MenuTreeNode child : node.getChildren()) {
            buildNodeMap(child);
        }
    }

    public MenuTree subTree(Object... nodeKeys) {
        nodeKeys = sortKeys(nodeKeys);
        Map<Object, MenuTreeNode> subNodeMap = new HashMap<>();
        for (Object key : nodeKeys) {
            buildSubTree(nodeMap.get(key), subNodeMap);
        }
        MenuTreeNode subTreeRoot = subNodeMap.get(root.getKey());
        return subTreeRoot == null ? null : new MenuTree(subTreeRoot);
    }

    private MenuTreeNode buildSubTree(MenuTreeNode sourceNode, Map<Object, MenuTreeNode> subNodeMap) {
        if (sourceNode == null) {
            return null;
        }
        MenuTreeNode node = subNodeMap.get(sourceNode.getKey());
        if (node != null) {
            return node;
        } else {
            if (sourceNode.getMenu() != null && !sourceNode.getMenu().isEnable()) {
                return null;
            }
            node = new MenuTreeNode(sourceNode.getMenu());
            subNodeMap.put(sourceNode.getKey(), node);
            MenuTreeNode parent = buildSubTree(sourceNode.getParent(), subNodeMap);
            if (parent != null) {
                parent.addChild(node);
            }
            return node;
        }
    }

    private Object[] sortKeys(Object[] nodeKeys) {
        if (nodeKeys == null || nodeKeys.length == 0) {
            return nodeKeys;
        }
        Set<Object> source = new HashSet<>(Arrays.asList(nodeKeys));
        List<Object> result = new ArrayList<>(nodeKeys.length);
        for (Object key : nodeMap.keySet()) {
            if (source.contains(key)) {
                result.add(key);
            }
        }
        return result.toArray();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MenuTree [nodeMap=").append(nodeMap).append("]");
        return builder.toString();
    }
}
