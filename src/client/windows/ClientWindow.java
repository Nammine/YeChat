package client.windows;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.Element;

import client.connect.ClientMainClass;
import client.panels.UserManage;
import client.windows.ClientWindow;
import entity.Request;
import entity.RequestType;
import entity.User;
import entity.Message;
import client.connect.ClientThread;
import client.panels.*;

public class ClientWindow extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1664152L;//��֤���Ψһ��
	
	private UserInfoPanel userInfoPanel;
	private MessageReceivedArea receivedmessageArea;
	private ToolsPanel toolsPanel;
	private MessageWritingArea sendingMessageArea;
	private SendButtonPanel sendButtonPanel;
	private PublicInfoPanel publicInfoPanel;
	private UserListPanel userListPanel;
	private MessageRecordArea recordArea;
	
	public ClientWindow() {
		super("Client");
		this.userInfoPanel = new UserInfoPanel(ClientMainClass.currentUser);
		this.receivedmessageArea = new MessageReceivedArea();
		this.toolsPanel = new ToolsPanel();
		this.sendingMessageArea=new MessageWritingArea();
		this.sendButtonPanel = new SendButtonPanel();
		this.publicInfoPanel = new PublicInfoPanel();
		this.userListPanel=new UserListPanel();
		this.recordArea=new MessageRecordArea();
		new ClientThread(receivedmessageArea.getTextPane(),recordArea.getTextPane(),
				publicInfoPanel.getTextpane(),userListPanel)
				.start();
		init();
		addHanderListener();
		sendingMessageArea.getTextPane().requestFocusInWindow();
	}

	public void init(){
		Container container = this.getContentPane();
		JPanel leftPanel = new JPanel();  //������
		JPanel rightPanel = new JPanel();    //�ұ����
		
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); //������box����  
		rightPanel.setLayout(new BorderLayout());                   //�ұ����borderLayout����
		
		leftPanel.add(userInfoPanel);//������������
		leftPanel.add(receivedmessageArea);
		leftPanel.add(toolsPanel);
		leftPanel.add(sendingMessageArea);
		leftPanel.add(sendButtonPanel);
 		
		rightPanel.add(publicInfoPanel, BorderLayout.NORTH);//�ұ����������
		rightPanel.add(userListPanel, BorderLayout.CENTER);
		
		container.add(leftPanel, BorderLayout.CENTER);
		container.add(rightPanel, BorderLayout.EAST);
	}
	
	public void addHanderListener(){
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow(); 
			}
		});
		userInfoPanel.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					new UserManage();
				}
			}
		});
		toolsPanel.getFontButton().addActionListener(this);
		toolsPanel.getColorButton().addActionListener(this);
		toolsPanel.getFaceButton().addActionListener(this);
		sendButtonPanel.getRecordButton().addActionListener(this);
		sendButtonPanel.getSentButton().addActionListener(this);
		sendButtonPanel.getCloseButton().addActionListener(this);
	}
	
	public void showMe(){
		ImageIcon tp1=new ImageIcon("images/bigheads/1.jpg");
		this.setIconImage(tp1.getImage());
		this.setSize(600,540);
		this.setLocation(250,100);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public static void main(String[] args){
		new ClientWindow().showMe();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Record")){
			recordArea.setVisible(true);
			recordArea.setLocation(this.getX(),this.getY()+540);
		}else if(e.getActionCommand().equals("Send")){
			Document doc = sendingMessageArea.getTextPane().getDocument();
			Element root = doc.getDefaultRootElement();//һ��
			Message message=new Message(sendingMessageArea.getTextPane().getParagraphAttributes());//AttributeSet��ȡ�ڵ�ǰ�������λ�õ���Ч�ַ����ԣ����� null��
			message.setMessage(root);
			message.setFrom(ClientMainClass.currentUser);
			try {
				ClientMainClass.oos.writeObject(new Request(RequestType.sendMessage));
				ClientMainClass.oos.writeObject(message);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			sendingMessageArea.getTextPane().setText("");
		}else if(e.getActionCommand().equals("Close")){
			closeWindow();
		}else if(e.getActionCommand().equals("Font")){
			Font   font  =   MyFontChooser.showDialog(this,null,new Font("����",Font.BOLD,12));
			sendingMessageArea.getTextPane().setFont(font);
			sendingMessageArea.getTextPane().requestFocusInWindow();
		}else if(e.getActionCommand().equals("Color")){
			Color c=JColorChooser.showDialog(this,"Please choose a Color",Color.BLACK);
			sendingMessageArea.getTextPane().setForeground(c);
			sendingMessageArea.getTextPane().requestFocusInWindow();
		}else if(e.getActionCommand().equals("Face")){
			new FacePanel(sendingMessageArea.getTextPane()).setLocation(this.getX()+20,this.getY()+200);
			sendingMessageArea.getTextPane().requestFocusInWindow();
		}
		
	}
	
	private void closeWindow(){
		int select=JOptionPane.showConfirmDialog(ClientWindow.this,"�˳������ж�������������ӣ�ȷ���˳���",
				"ȷ���˳�������",JOptionPane.YES_NO_OPTION);
		if(select==JOptionPane.YES_OPTION){
			try {
				User user=ClientMainClass.currentUser;
				Request req=new Request(RequestType.offline);
				ClientMainClass.oos.writeObject(req);
				ClientMainClass.oos.writeObject(user);
				ClientMainClass.oos.flush();
   				System.exit(0);	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}

