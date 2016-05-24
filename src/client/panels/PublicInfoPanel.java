package client.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
public class PublicInfoPanel extends JPanel{
	
	private static final long serialVersionUID = 14257854L;
	private JTextPane textpane;
	
	public PublicInfoPanel(){
		Border line = BorderFactory.createLineBorder(Color.darkGray);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//公告栏
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.gray);
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEADING,2,0));
		ImageIcon icon=new ImageIcon("images/tools/message.gif");
		JLabel label = new JLabel("               Public        ",icon,SwingConstants .CENTER);
		panelTitle.add(label);
		panelTitle.setBorder(line);
		panelTitle.setPreferredSize(new Dimension(200,25));
		//最新公告内容
		
		textpane = new JTextPane();
		textpane.setText("Welcome to YeChat");
		textpane.setEditable(false);
		textpane.setBorder(line);
		this.setPreferredSize(new Dimension(200,150));
		this.add(panelTitle);
		this.add(textpane);
	}

	public JTextPane getTextpane() {
		return textpane;
	}

}


