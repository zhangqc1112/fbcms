package com.comp.menu;

import java.util.List;

/**
 * @author Administrator
 */
public class MenuTreeNode extends TreeNode<MenuTreeNode> {

	private static final long serialVersionUID = -1308421080414954582L;

	private Menu menu;

	public MenuTreeNode() {
	}

	public MenuTreeNode(Menu menu) {
		this.menu = menu;
	}

	@Override
	protected Object getKey() {
		return menu == null ? "0" : menu.getId();
	}

	@Override
	protected boolean noChild() {
		return menu == null ? false : !menu.isDir();
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setChildren(List<MenuTreeNode> children) {
		this.clear();
		for (MenuTreeNode child : children) {
			if (child != null) {
				this.addChild(child);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuTreeNode [key=").append(getKey()).append(", menu=").append(menu).append("]");
		return builder.toString();
	}

}
