package com.comp.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MenuHolder {
	private MenuTree menuTree;
	private Map<String, MenuTreeNode> menuPaths;
	private Map<String, Set<String>> menuFuncs;

	public MenuHolder(MenuTree menuTree, Map<String, Set<String>> menuFuncs) {
		this.menuTree = menuTree;
		this.menuFuncs = menuFuncs;
		this.menuPaths = new HashMap<>();
		if (menuTree != null) {
			for (MenuTreeNode node : menuTree.getNodeMap().values()) {
				if (!node.getMenu().isDir()) {
					String path = node.getMenu().getUrl();
					if (path == null || !path.startsWith("/")) {
						continue;
					}
					menuPaths.put(path, node);
				}
			}
		}
	}

	public MenuTree getMenuTree() {
		return menuTree;
	}

	public MenuTreeNode getMenuByPath(String path) {
		return menuPaths == null ? null : menuPaths.get(path);
	}

	public MenuTreeNode getMenuByKey(String menuKey) {
		return menuTree == null ? null : menuTree.getTreeNode(menuKey);
	}

	public Set<String> getMenuFuncs(String menuKey) {
		return menuFuncs == null ? null : menuFuncs.get(menuKey);
	}

}