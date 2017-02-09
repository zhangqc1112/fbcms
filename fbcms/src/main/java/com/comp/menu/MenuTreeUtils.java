package com.comp.menu;

import com.fbcms.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.Map;

public class MenuTreeUtils {
    private final static Logger logger = LoggerFactory.getLogger(MenuTreeUtils.class);

    public static MenuTree buildTree(String treeJson) {
        if (StringUtils.isBlank(treeJson)) {
            return null;
        }
        MenuTreeNode root;
        try {
            root = JSON.parseObject(treeJson, MenuTreeNode.class);
            return new MenuTree(root);
        } catch (Exception e) {
            logger.error("parse menu tree json error.", e);
            return null;
        }
    }

    public static String toTreeJson(MenuTreeNode node) {
        if (node == null) {
            return null;
        }
        return JSON.toJSONString(node);
    }

    public static MenuTreeNode getCurrTree(MenuTree menuTree,String url){
        Map<Object, MenuTreeNode> map = menuTree.getNodeMap();
        for (Object key : map.keySet()) {
            MenuTreeNode mtn = map.get(key);
            Menu menu = mtn.getMenu();
            if(!StringUtil.isEmptyOrNull(menu.getUrl()) && url.startsWith(menu.getUrl())){
                return mtn;
            }

        }
        return null;
    }



}
