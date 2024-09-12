package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemUI;
import model.Member;
import model.MemberInfo;
import model.VipLevel;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import utils.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class RegisterMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userid;
	private JTextField password;
	private JTextField phone;
	private static MemberServiceImpl msi = new MemberServiceImpl();
	private static MemberInfoServiceImpl misi = new MemberInfoServiceImpl();

	Connection conn = DbConnection.getDbConnection();
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMember frame = new RegisterMember();
					 frame.setTitle("註冊");

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
	    setIconImage(windowIcon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("*帳號");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(43, 58, 46, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" 密碼");
		lblNewLabel_1.setBounds(43, 106, 46, 15);
		contentPane.add(lblNewLabel_1);
		

		JLabel validationLabel = new JLabel("");
		validationLabel.setBounds(207, 58, 197, 15);
		contentPane.add(validationLabel);
		

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(207, 157, 197, 15);
		contentPane.add(lblNewLabel_3);
		
		
		textField = new JTextField();
		textField.setBounds(0, 0, 0, 0);
		contentPane.add(textField);
		textField.setColumns(10);
		
		phone = new JTextField();
		phone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
			@Override
			public void focusLost(FocusEvent e) {
				 if (phone.getText().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "電話不得空白", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
				String UserId = userid.getText();
				String Phone = phone.getText();

				Member temp = new Member(UserId,null,Phone);
				
				if (msi.confirmPhone(conn,temp)) {
					//有人使用
					lblNewLabel_3.setText("Phone already in use");
					lblNewLabel_3.setForeground(Color.RED);
					lblNewLabel_3.setVisible(true);
				}else{
					//無人使用
					lblNewLabel_3.setText("Phone available");
					lblNewLabel_3.setForeground(Color.green);
					lblNewLabel_3.setVisible(true);

				};
			}
		});
		phone.setBounds(79, 154, 96, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		
		userid = new JTextField();
		
		
		
		
		userid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
			@Override
			public void focusLost(FocusEvent e) {
				 if (userid.getText().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "帳號不得空白", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
				
				
				String UserId = userid.getText();
				String Phone = phone.getText();

				Member temp = new Member(UserId,null,Phone);
				
				if (msi.confirmUserId(conn,temp)) {
					//有人使用
					validationLabel.setText("Username already in use");
		            validationLabel.setForeground(Color.RED);
		            validationLabel.setVisible(true);
				}else{
					//無人使用
					validationLabel.setText("Username available");
		            validationLabel.setForeground(Color.green);
		            validationLabel.setVisible(true);

				};
				
			
				
			}
		});
		userid.setBounds(79, 54, 96, 21);
		contentPane.add(userid);
		userid.setColumns(10);
		
		password = new JTextField();
		password.setBounds(79, 103, 96, 21);
		contentPane.add(password);
		password.setColumns(10);
		
	
		
		
		JButton btnNewButton_1 = new JButton("新增成為會員");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String UserId = userid.getText();
				String PassWord = password.getText();
				String Phone = phone.getText();

				Member member = new Member(UserId,PassWord,Phone);
				
				if (msi.confirmUserId(conn,member)) {
					RegisterMemberError rMerror=new RegisterMemberError();
					rMerror.setVisible(true);
				}else {
					msi.addMember(conn, member);
					
					MemberInfo memberInfo = new MemberInfo();
					memberInfo.setUserId(member.getUserId());
					memberInfo.setUsername(member.getUsername());
					misi.addMemberInfo(conn, memberInfo);
					
					RegisterMemberSuccess rMSuccess = new RegisterMemberSuccess();
					rMSuccess.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(43, 185, 157, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("回上頁");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberLogin memberLogin = new MemberLogin();
				memberLogin.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(233, 185, 85, 46);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("*電話");
		lblNewLabel_2.setBounds(43, 157, 46, 15);
		contentPane.add(lblNewLabel_2);



	}
}
