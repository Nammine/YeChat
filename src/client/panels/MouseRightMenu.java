package client.panels;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;
import client.connect.*;
import client.windows.ChatWindow;
import entity.*;

public class MouseRightMenu extends JPopupMenu implements ActionListener {
	
	private static final long serialVersionUID = 1157615L;
	private User user;
	private JMenuItem information;
	private JMenuItem individual;
	private JMenuItem sendFile;
	private JMenuItem shield;
	
	public MouseRightMenu(User user) throws HeadlessException {
		super();
		this.user = user;
		this.information = new JMenuItem("Information");
		this.individual = new JMenuItem("Chat");
		this.shield=new JMenuItem("Shield");
		init();
	}

	private void init(){
		this.add(information);
		this.add(individual);
		this.add(shield);
		shield.addActionListener(this);
		information.addActionListener(this);
		individual.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==information) {
			new FriendInformation(user).showMe();
		} else if (e.getSource()==individual) {
			ChatWindow chat=new ChatWindow(user);
			ClientMainClass.individual.put(user.getId(), chat.getReceivedmessageArea().getTextPane());
			chat.showMe();
		} else if (e.getSource()==sendFile) {
			sendFile();
		}else{
			ClientMainClass.shield.add(user.getId());
		}
	}

	private void sendFile(){
	//ObjectOutputStream oos=ClientMainClass.oos;
		
	//try {
	//	oos.writeObject(new Request(RequestType.sendFile));
		
	//} catch (Exception e) {
		//	e.printStackTrace();		}
	}
}

