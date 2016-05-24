package client.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.BorderFactory;
import java.util.*;

import entity.User;

/**
 * ÓÃ»§Ê÷µÄäÖÈ¾Æ÷
 * 
 */
public class MyTreeCellRenderer extends JLabel implements TreeCellRenderer {

	private static final long serialVersionUID = 118872265L;
	private Border line;
	private Border empty;
	private ImageIcon icon;

	public MyTreeCellRenderer() {
		line = BorderFactory.createLineBorder(Color.darkGray);
		empty = BorderFactory.createEmptyBorder();
		icon = null;
	}

	/**
	 * äÖÈ¾Æ÷
	 */
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if (value instanceof List) {
			this.setText("Online Users");
			if (expanded) {
				icon = new ImageIcon("images/tools/open.jpg");
			} else {
				icon = new ImageIcon("images/tools/many.jpg");
			}
			if (icon != null) {
				this.setIcon(icon);
			}
		} else if (value instanceof User) {
			User user = (User) value;
			this.setText(user.getName());
			this.setFont(new Font("Á¥Êé",Font.BOLD,16));
			this.setForeground(Color.darkGray);
			icon =user.getImageIcon();
			if (icon != null){
				this.setIcon(icon);
			}
		}
		if (selected) {
			this.setBorder(line);
		} else {
			this.setBorder(empty);
		}
		this.setPreferredSize(new Dimension(130, 40));
		return this;
	}
	
}

