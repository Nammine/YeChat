package client.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.TreePath;

import client.connect.ClientMainClass;
import entity.User;

public class UserListPanel extends JPanel {

	private static final long serialVersionUID = 1252421L;
	private JTree tree ;
	private MyTreeModel m;
	
	public UserListPanel() {
		Border line = BorderFactory.createLineBorder(Color.darkGray);
		this.setLayout(new BorderLayout());
		this.setBorder(line);
		m=new MyTreeModel(ClientMainClass.onlineUsers);
		tree= new JTree(m);
		tree.setCellRenderer(new MyTreeCellRenderer());
		tree.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				//ÓÒ¼ü²Ëµ¥
				if(e.getButton()==MouseEvent.BUTTON3){
					TreePath pt=tree.getPathForLocation(e.getX(), e.getY());
					if(pt!=null){
						Object obj=pt.getLastPathComponent();
						if (obj instanceof User) {
							User user = (User) obj;
							new MouseRightMenu(user).show(tree, e.getX(), e.getY());
						}			
					}
				}
			}
		});
		JScrollPane jsp=new JScrollPane(tree); 
		this.add(jsp);
	}

	public void freash(List<User> onlineUsers){
		m.setRoot(onlineUsers);
		SwingUtilities.updateComponentTreeUI(tree);
		
	}
}
