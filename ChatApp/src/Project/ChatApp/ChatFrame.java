package Project.ChatApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;

public class ChatFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel titleLabel = new JLabel();
	public JTextArea chatHistory = new JTextArea();
	public JScrollPane scrollBox = new JScrollPane();
	public JTextPane chatters = new JTextPane();
	public JTextArea chatText = new JTextArea();
	public JButton submit = new JButton();
	public String message;
	

	//JFrame - base of GUI
	
	public ChatFrame() {
	//Initial base frame
		
	
		
	Border grayline = BorderFactory.createLineBorder(new Color(0x3C4142), 5);
		
	this.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();	
	this.setTitle("Cognixia Chat");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(true);
	this.setSize(600,600);	
	ImageIcon image = new ImageIcon("/Java_Workspace/ChatApp/ChatApp/src/Project/ChatApp/resources/CognixiaChat Logo.png");
	this.setIconImage(image.getImage());
	this.getContentPane().setBackground(new Color(0xA4D3EE));
	
	//formatting title label	
	
	titleLabel.setBackground(new Color(0xD1EEEE));
	titleLabel.setText("Cognixia Chat");
	titleLabel.setFont(new Font("Helvetica", Font.ITALIC, 30));
	titleLabel.setForeground(new Color(0x3C4142));
	titleLabel.setOpaque(true);
	titleLabel.setVisible(true);
	titleLabel.setBorder(grayline);
	titleLabel.setHorizontalAlignment(JLabel.CENTER);
	titleLabel.setVerticalAlignment(JLabel.CENTER);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipadx = 40;
	c.ipady = 40;
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 3;
	c.gridheight = 1;
	c.weightx = 1.0;
	c.weighty = 0.167;
	
		
	this.add(titleLabel, c);
	
	chatHistory.setBackground(Color.white);
	chatHistory.setText("Initializing Chat Window...");
	chatHistory.setOpaque(true);
	chatHistory.setVisible(true);
	chatHistory.setBorder(grayline);
	chatHistory.setSize(400,300);
	
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 1;
	c.gridwidth = 2;
	c.gridheight = 4;
	c.weightx = 0.66;
	c.weighty = 0.66;
	c.ipady = 300;
	

	c.anchor = GridBagConstraints.FIRST_LINE_START;
	c.insets = new Insets(0,5,0,5);
	
	scrollBox.setBorder(grayline); 
	this.add(scrollBox, c);
	scrollBox.setViewportView(chatHistory);
	
	
	
	chatters.setBackground(Color.white);
	chatters.setOpaque(true);
	chatters.setVisible(true);
	chatters.setBorder(grayline);
	chatters.setSize(200, 300);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 2;
	c.gridy = 1;
	c.gridwidth = 1;
	c.gridheight = 4;
	c.weightx = 0.33;
	c.weighty = 0.66;
	c.ipady = 300;

	
	c.anchor = GridBagConstraints.FIRST_LINE_END;
	c.insets = new Insets(0,5,0,5);
	
	this.add(chatters, c);
	
	chatText.setBackground(Color.white);
	chatText.setForeground(Color.black);
	chatText.setText("Initializing Text Area...");
	chatText.setOpaque(true);
	chatText.setVisible(true);
	chatText.setBorder(grayline);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy =5;
	c.gridwidth = 2;
	c.gridheight = 1;
	c.weightx = 0.66;
	c.weighty = 0.167;
	c.ipady = 40;
	
	

	
	this.add(chatText, c);
	
	submit.setText("Submit");
	submit.setVisible(true);
	submit.addActionListener(this);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 2;
	c.gridy = 5;
	c.gridwidth = 1;
	c.gridheight = 1;
	c.weightx = 0.33;
	c.weighty = 0.167;
	c.ipady = 0;
	
	
	this.add(submit, c);
	
	this.revalidate();
	this.setVisible(true);
	
	

	
	
	
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			
			message = chatText.getText();
		}
		
	}

}
