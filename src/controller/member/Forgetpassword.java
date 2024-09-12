package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.impl.MemberServiceImpl;
import utils.DbConnection;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Color;

public class Forgetpassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UserID;
	private JTextField PHONE;
	protected Connection conn;
	private static MemberServiceImpl msi = new MemberServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Forgetpassword frame = new Forgetpassword();
					 frame.setTitle("忘記密碼");

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
	public Forgetpassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UserID = new JTextField();
		UserID.setBounds(153, 33, 142, 21);
		contentPane.add(UserID);
		UserID.setColumns(10);
		
		PHONE = new JTextField();
		PHONE.setBounds(153, 64, 142, 21);
		contentPane.add(PHONE);
		PHONE.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("帳號");
		lblNewLabel.setBounds(97, 36, 46, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("電話");
		lblNewLabel_1.setBounds(97, 67, 46, 15);
		contentPane.add(lblNewLabel_1);
		

		JTextArea output = new JTextArea();
		output.setBounds(116, 146, 213, 28);
		contentPane.add(output);
		
		JButton btnNewButton = new JButton("確認");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				conn = DbConnection.getDbConnection();
				String userid = UserID.getText();
				String phone = PHONE.getText();
				
				String forgetpassword = msi.forgetpassword(conn, userid, phone);
				output.setText(forgetpassword);
			}
		});
		btnNewButton.setBounds(152, 95, 143, 23);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("回到登入頁面");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberLogin memberLogin = new MemberLogin();
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				};
				memberLogin.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(152, 207, 143, 23);
		contentPane.add(btnNewButton_1);
	}
}
