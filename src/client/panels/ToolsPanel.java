package client.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.Border;
public class ToolsPanel extends JPanel{
	private static final long serialVersionUID = 1552162L;
	private JButton face;
	private JButton font;
	private JButton color;

	public ToolsPanel(){
		this.setLayout(new FlowLayout(FlowLayout.LEADING,20,2));
		this.setBackground(Color.gray);
		Border line = BorderFactory.createLineBorder(Color.darkGray);
		this.setBorder(line);
		ImageIcon icon=new ImageIcon("images/tools/font.jpg");
		font=new JButton(icon);
		font.setBorderPainted(false);
		font.setFocusPainted(false);
		font.setMargin(new Insets(0,0,0,0));
		font.setActionCommand("Font");
		
		icon=new ImageIcon("images/tools/color.jpg");
		color=new JButton(icon);
		color.setBorderPainted(false);
		color.setFocusPainted(false);
		color.setMargin(new Insets(0,0,0,0));
		color.setActionCommand("Color");
		
		icon=new ImageIcon("images/tools/face.jpg");
		face=new JButton(icon);
		face=new JButton(icon);
		face.setBorderPainted(false);
		face.setFocusPainted(false);
		face.setMargin(new Insets(0,0,0,0));
		face.setActionCommand("Face");
		
		this.add(font);
		this.add(color);
		this.add(face);
		this.setSize(new Dimension(5,5));
	}

	public JButton getColorButton() {
		return color;
	}

	public JButton getFaceButton() {
		return face;
	}

	public JButton getFontButton() {
		return font;
	}
	
}


