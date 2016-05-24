package client.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

public class SendButtonPanel extends JPanel {
	
	private static final long serialVersionUID = 13245754L;
	JButton record;
	JButton sent;
	JButton close;
	
	public SendButtonPanel() {
		this.setBackground(Color.gray);
		this.setLayout(new FlowLayout(FlowLayout.LEADING,2,3));
		record=new JButton("Record");
		record.setForeground(Color.darkGray);
		sent=new JButton("Send");
		sent.setForeground(Color.darkGray);
		close=new JButton("Close");
		close.setForeground(Color.darkGray);
		JLabel label = new JLabel();
		label.setText("                                          ");
		this.add(record);
		this.add(label);
		this.add(sent);
		this.add(close);
		this.setPreferredSize(new Dimension(Short.MAX_VALUE,35));
	}

	public JButton getCloseButton() {
		return close;
	}

	public JButton getRecordButton() {
		return record;
	}

	public JButton getSentButton() {
		return sent;
	}
	
}

