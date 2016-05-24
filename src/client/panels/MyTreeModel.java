package client.panels;

import javax.swing.tree.DefaultTreeModel;

import entity.User;

import java.util.*;
/*
 * ��Ϊ����һ��JTree�����Ҫ�õ�TreeModel��������TreeModel��һ���ӿڣ�DefaultTreeModel
 * ��TreeModel��һ�����࣬������Ǽ̳д��࣬����������д�ķ���
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

