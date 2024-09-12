package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemUI;
import model.Member;
import service.impl.MemberServiceImpl;
import utils.Clock;
import utils.DbConnection;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

public class MemberLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userId;
	private JPasswordField password;
	private static MemberServiceImpl msi = new MemberServiceImpl();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	protected Member passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberLogin frame = new MemberLogin();
					 frame.setTitle("會員登入首頁");

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
	public MemberLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
	    setIconImage(windowIcon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userId = new JTextField();
		userId.setBounds(155, 85, 96, 21);
		contentPane.add(userId);
		userId.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(155, 116, 96, 21);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnNewButton = new JButton("登入");
		btnNewButton.setBounds(99, 168, 96, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String UerId = userId.getText();
				char[] passwordChars = password.getPassword();
				String password = new String(passwordChars);
				Connection conn = DbConnection.getDbConnection();
				Member Member = msi.login(conn, UerId, password);
				
				if (Member != null) {
					Index l = new Index(conn, Member);
					l.setVisible(true);
					dispose();
				} else {
					LoginError l = new LoginError();
					l.setVisible(true);
					dispose();

				}
				
				
			}
		});
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("帳號");
		lblNewLabel.setBounds(99, 88, 46, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(99, 119, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("註冊");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterMember registerMember = new RegisterMember();
				registerMember.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(219, 168, 85, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("回主畫面");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SystemUI systemUI = new SystemUI();
				systemUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(10, 10, 85, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("忘記密碼");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setForeground(new Color(0, 0, 255));
				lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setForeground(new Color(0, 0, 0));
				lblNewLabel_2.setCursor(Cursor.getDefaultCursor());

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Forgetpassword forgetpassword = new Forgetpassword();
				forgetpassword.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(278, 122, 65, 15);
		contentPane.add(lblNewLabel_2);
		
	}
}
