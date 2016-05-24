package client.panels;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyListCellRenderer extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = 15512L;

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value instanceof String) {
			ImageIcon icon = new ImageIcon("images/heads/" + value + ".jpg");
			if (icon != null) {
				this.setIcon(icon);
				this.setDisplayedMnemonic(Integer.parseInt(value.toString()));
			} 
		}
		return this;
	}

}
