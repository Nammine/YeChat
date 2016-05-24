package client.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;

public class MessageRecordArea extends JDialog{
	
	private static final long serialVersionUID = 125531L;
	private JTextPane textPane;

	public MessageRecordArea(){
		Container container = this.getContentPane();
		Border line = BorderFactory.createLineBorder(Color.darkGray);
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		pan.setBorder(line);
		textPane = new JTextPane();
		textPane.setEditable(false);
		JScrollPane scro = new JScrollPane(textPane);  //¹ö¶¯Ìõ
		pan.add(scro, BorderLayout.CENTER);	
		container.add(pan);
		ImageIcon tp1=new ImageIcon("images/bigheads/1.jpg");
		this.setIconImage(tp1.getImage());
		this.setSize(400, 200);
		this.setVisible(false);
	}
	
	public JTextPane getTextPane() {
		return textPane;
	}
}
