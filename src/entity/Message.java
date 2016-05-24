package entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 11643154L;
	
	private User to;//������Ϣȥ�ĸ��û�
	private User from;//��Ϣ���ĸ��û�����
	private String time;//��Ϣ������ʱ��
	private StringBuffer message;//��Ϣ
	private AttributeSet as;
	
	public Message(AttributeSet as) {
		message=new StringBuffer();
		this.as=as;
		Calendar c = Calendar.getInstance();
		time=c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH)+" "+
				(c.get(Calendar.HOUR_OF_DAY)+8)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
	}

//	��ȡ�Ի�
	public void setMessage(Element e){
		if(!e.isLeaf()){
			for(int i = 0; i < e.getElementCount(); i++){
				setMessage(e.getElement(i));
			}
		}else{
			AttributeSet as = e.getAttributes().copyAttributes();
			if(e.getName().equals("content")){
				int start = e.getStartOffset();//�ĵ���ʼ������ʼƫ����
				int end = e.getEndOffset();//�ĵ�����������ʼƫ����
				try {
					String s = e.getDocument().getText(start, end-start);//���Ԫ�ع������ĵ�
					message.append(s);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}else if(e.getName().equals("icon")){
				Icon icon = StyleConstants.CharacterConstants.getIcon(as);
				String fileName = icon.toString();
				int index = fileName.lastIndexOf("/");
				String s = fileName.substring(index+1);
				message.append('\u001E'+s+'\u001F');//ͼƬ��ַ
			}
		}
	}
	//������������
	public void analysisMessage(JTextPane receive){
		String str = this.message.toString();
		int start = -1;
		int end = 0;
		int len = receive.getDocument().getLength();
		if ((start = str.indexOf('\u001E')) == -1){			
			try {
				receive.getDocument().insertString(len, str, as);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			//������ַ������б���		
		}else{
			String str4 = null;
			String str3 = null;
			while( (end = str.indexOf('\u001F'))!= -1 ){
				str3 = str.substring(start, end);
				str4 = str.substring(0, start);
				str = str.substring(end+1);
				if(start == 0){  //��һ���ַ����Ǳ���		
					ImageIcon icon = new ImageIcon("images/face/"+str3.trim());
					if(icon != null){
						receive.setCaretPosition(receive.getDocument().getLength());
						receive.insertIcon(icon);
					}
				}else{
					try {
						len = receive.getDocument().getLength();	
						receive.getDocument().insertString(len, str4, as);
						ImageIcon icon = new ImageIcon("images/face/"+str3.trim());
						if(icon != null){
							receive.setCaretPosition(receive.getDocument().getLength());
							receive.insertIcon(icon);
						}
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
				}
				len = receive.getDocument().getLength();
				start = str.indexOf('\u001E');
			}
			if(str != null){
				try {
					len = receive.getDocument().getLength();	
					receive.getDocument().insertString(len, str, as);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public StringBuffer getMessage() {
		return message;
	}

	public void setMessage(StringBuffer message) {
		this.message = message;
	}

}

