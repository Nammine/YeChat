package client.panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * ��½����Ķ�����һ��ͼƬ
 */
public class LoginTopPanel extends JPanel {
	
	private static final long serialVersionUID = 1157482L;//��֤��Ķ�����

	public LoginTopPanel(){
		JLabel jl = new JLabel();
		ImageIcon icon = new ImageIcon("images/login//top.jpg");
		if(icon != null){
			jl.setIcon(icon);
		}
		this.add(jl);
		this.setSize(353, 46);
	}

}

