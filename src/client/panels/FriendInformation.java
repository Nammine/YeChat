package client.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import client.connect.ClientMainClass;
import entity.Request;
import entity.RequestType;
import entity.User;

public class FriendInformation extends JFrame{
	
	private static final long serialVersionUID = 21245271L;
	private JLabel[] lb = new JLabel[3];
	private JTextField id;
	private JTextField name;
	private JRadioButton male,female;
	private JButton sure;
	private JButton cancer;
	private JTextArea memo;
	private JComboBox face ;
	private User user;
	
	public FriendInformation(User user){
		this.user=user;
		JPanel manage = new JPanel();
		Border title = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border line = BorderFactory.createTitledBorder(title, "Information");
		manage.setBorder(line);
		manage.setLayout(new BorderLayout());
		String[] str = {"Id","Name","Sex"};
		JPanel p1 = new JPanel();
		lb[0] = new JLabel(str[0]);
		id = new JTextField(10);
		id.setEditable(false);
		p1.add(lb[0]);
		p1.add(id);
		
		JPanel p2 = new JPanel();
		lb[1] = new JLabel(str[1]);
		name = new JTextField(10);
		p2.add(lb[1]);
		p2.add(name);
		
		ButtonGroup gender=new ButtonGroup();
		male=new JRadioButton("Male",true);
		female=new JRadioButton("Female");
		gender.add(male);
		gender.add(female);
		JPanel p3 = new JPanel();
		lb[2] = new JLabel(str[2]);
		p3.add(lb[2]);
		p3.add(male);
		p3.add(female);
		
		JPanel p5 = new JPanel();
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		
		face = new JComboBox();
		face.addItem(user.getIcon());
		face.setRenderer(new MyListCellRenderer());
		face.setSelectedIndex(0);
		JPanel p7 = new JPanel();
		p7.setSize(50, 50);
		p7.add(face);
		
		JPanel p6 = new JPanel();
		p6.setLayout(new BoxLayout(p6, BoxLayout.X_AXIS));
		p6.add(p5);
		p6.add(p7);
		
		memo=new JTextArea(5,40);
		memo.setFont(new Font("����",Font.BOLD,14));
		JScrollPane js=new JScrollPane(memo);
		JLabel l=new JLabel("����ǩ��");
		JPanel p8 = new JPanel();
		p8.setLayout(new BoxLayout(p8, BoxLayout.X_AXIS));
		p8.add(l);
		p8.add(js);
		JPanel p4 = new JPanel();	
		cancer = new JButton("Cancel");
		cancer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		p4.add(cancer);
		
		manage.add(p6,BorderLayout.NORTH);
		manage.add(p8,BorderLayout.CENTER);
		manage.add(p4,BorderLayout.SOUTH);
		this.add(manage);
		init();
	}
	
	private void init(){
		id.setText(user.getId()+"");
		name.setText(user.getName());
		if(user.getSex()=="male"){
			male.setSelected(true);
		}else{
			female.setSelected(true);
		}		
		memo.setText(user.getMemo());
	}
	
	public void showMe(){
		ImageIcon tp1=new ImageIcon("images/bigheads/1.jpg");
		this.setIconImage(tp1.getImage());
		this.setLocation(250,100);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
