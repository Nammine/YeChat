package client.panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class FacePanel extends JDialog {
	
	private static final long serialVersionUID = 1265464L;
	private JTextPane textPane;
	
	/**
	 * 表情面板构造
	 * @param textPane
	 */
	public FacePanel(JTextPane textPane){		
		this.textPane = textPane;
		init();
	}
	
	private void init(){
		Border line= BorderFactory.createLineBorder(new Color(244, 249, 254));
		Border empty = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		Border border = BorderFactory.createCompoundBorder(empty, line);
		
		Border in = BorderFactory.createLineBorder(Color.BLACK);
		Border out = BorderFactory.createEmptyBorder(6, 6, 6, 6);
		Border title = BorderFactory.createCompoundBorder(in, out);
		
		this.setUndecorated(true);
		this.setBackground(Color.WHITE);
		
		Container container = this.getContentPane();
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(7,15));
		pan.setBorder(title);
		
		JButton[] btn = new JButton[100];
		Listener listener = new Listener(this);
		for(int i = 0; i < 96; i++){
			btn[i] = new JButton();
			ImageIcon icon=new ImageIcon("images/face/"+i+".gif");
			if(icon != null){
				btn[i].setIcon(icon);
				btn[i].addActionListener(listener);
				btn[i].setMargin(new Insets(1,1,1,1));
				btn[i].setBorder(border);
				pan.add(btn[i]);
			}
		}
		container.add(pan);
		pack();
		this.setVisible(true);
		this.addWindowFocusListener(new MyWindowFocusListener(this));
	}
/**
 * 表情区加监听
 * @author student
 *
 */	
	public class Listener implements ActionListener{
		private JDialog  dialog;
		
		public Listener(JDialog  dialog){
			this.dialog = dialog;
		}
		public void actionPerformed(ActionEvent e) {
			textPane.insertIcon(((JButton)e.getSource()).getIcon());
			dialog.dispose();
		}
	}
	
	/**
	 * 表情窗体焦点监听
	 * @author student
	 *
	 */
	public class MyWindowFocusListener extends WindowAdapter{
		private JDialog  dialog;
		
		public MyWindowFocusListener(JDialog  dialog){
			this.dialog = dialog;
		}
	
		public void windowLostFocus(WindowEvent e) {  //没有焦点时
			dialog.dispose();
		}
	}
	
}
