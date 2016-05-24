package client.panels;

import javax.swing.tree.DefaultTreeModel;

import entity.User;

import java.util.*;
/*
 * 因为产生一个JTree组件，要用到TreeModel参数，而TreeModel是一个接口，DefaultTreeModel
 * 是TreeModel的一个子类，因此我们继承此类，改造我们想写的方法
 */

public class MyTreeModel extends DefaultTreeModel {
	private static final long serialVersionUID = 1165421L;
	private Object root;
	
	public MyTreeModel(List<User> root) {
		super(null);
		this.root=root;
	}
	
	public Object getChild(Object parent, int index) {
		return ((List)parent).get(index);
	}
	
	public int getChildCount(Object parent) {
		return ((List)root).size();
	}
	
	public int getIndexOfChild(Object parent, Object child) {
		return ((List)parent).indexOf(child);
	}

	public Object getRoot() {
		return root;
	}

	public void setRoot(Object root){
		this.root=root;
	}
	
	public boolean isLeaf(Object node) {
		return  node instanceof User;
	}
}

