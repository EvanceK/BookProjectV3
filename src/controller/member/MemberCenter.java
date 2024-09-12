package controller.member;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemUI;
import model.Member;
import model.MemberInfo;
import model.Products;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class MemberCenter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userIdtField;
	private JTextField passWordField_1;
	private JTextField userNameField;
	private JTextField balanceField;
	private JTextField pointField;
	private JTextField accumulatedField;
	private static Connection conn =null;
	private static Member m=null;
	private static MemberServiceImpl msi = new MemberServiceImpl();
	private static MemberInfoServiceImpl misi = new MemberInfoServiceImpl();

	private JTextField email;
	private JTextField address1;
	private JTextField address2;
	private JTextField birthday;
	private JLabel emailLabel;
	private JLabel addressLabel1;
	private JLabel addressLabel2;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel birthdayLabel;
	private JTextField vip;
	private JTextField phone;
	protected static List<Products> shoppingCar;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberCenter frame = new MemberCenter(conn,shoppingCar,m);
					 frame.setTitle("會員中心");

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
	public MemberCenter(Connection conn ,List<Products> shoppingCar, Member m) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
	    setIconImage(windowIcon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userIdtField = new JTextField();
		userIdtField.setBackground(new Color(192, 192, 192));
		userIdtField.setEditable(false);
		userIdtField.setBounds(105, 102, 96, 21);
		contentPane.add(userIdtField);
		userIdtField.setColumns(10);
		
		passWordField_1 = new JTextField();
		passWordField_1.setBounds(105, 143, 96, 21);
		contentPane.add(passWordField_1);
		passWordField_1.setColumns(10);
		
		userNameField = new JTextField();
		userNameField.setBounds(105, 188, 96, 21);
		contentPane.add(userNameField);
		userNameField.setColumns(10);
		
		balanceField = new JTextField();
		balanceField.setBackground(new Color(192, 192, 192));
		balanceField.setEditable(false);
		balanceField.setBounds(105, 310, 96, 21);
		contentPane.add(balanceField);
		balanceField.setColumns(10);
		
		pointField = new JTextField();
		pointField.setBackground(new Color(192, 192, 192));
		pointField.setEditable(false);
		pointField.setBounds(105, 360, 96, 21);
		contentPane.add(pointField);
		pointField.setColumns(10);
		
		accumulatedField = new JTextField();
		accumulatedField.setBackground(new Color(192, 192, 192));
		accumulatedField.setEditable(false);
		accumulatedField.setBounds(105, 413, 96, 21);
		contentPane.add(accumulatedField);
		accumulatedField.setColumns(10);
		
		vip = new JTextField();
		vip.setBackground(new Color(192, 192, 192));
		vip.setEditable(false);
		vip.setBounds(105, 70, 96, 21);
		contentPane.add(vip);
		vip.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(105, 269, 96, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("會員等級");
		lblNewLabel_6.setBounds(30, 73, 71, 15);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("電話");
		lblNewLabel_7.setBounds(30, 272, 46, 15);
		contentPane.add(lblNewLabel_7);
		
	
		
		JLabel lblNewLabel = new JLabel("帳號");
		lblNewLabel.setBounds(30, 105, 46, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(30, 146, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(30, 191, 46, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("儲值餘額");
		lblNewLabel_3.setBounds(30, 313, 65, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("點數");
		lblNewLabel_4.setBounds(30, 363, 46, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("累積消費");
		lblNewLabel_5.setBounds(30, 416, 65, 15);
		contentPane.add(lblNewLabel_5);
		
		userIdtField.setText(m.getUserId());
		passWordField_1.setText(m.getPassword());
		userNameField.setText(m.getUsername());
		balanceField.setText(m.getBalance().toString());
		pointField.setText(m.getPoint().toString());
		accumulatedField.setText(m.getAccumulatedAmount().toString());
		vip.setText(m.getVipLevel().toString());
		phone.setText(m.getPhone());
		
		JButton btnNewButton = new JButton("儲存變更");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//更新會員資料
					m.setUserId(userIdtField.getText());//userId 不給變更
					m.setPassword(passWordField_1.getText());
					m.setUsername(userNameField.getText());
					m.setPhone(phone.getText());
					
					msi.updateMember(conn,m);
					
					//測試更新儲值餘額 	//不給會員自行變更 之後給員工系統做處理 或消費時處理
					m.setBalance(Integer.valueOf(balanceField.getText()));
					msi.updateMemberBalance(conn, m);
					//測試更新點數 		//不給會員自行變更 之後給員工系統做處理 或消費時處理
					m.setPoint(Integer.valueOf(pointField.getText()));
					msi.updateMemberPoint(conn, m);
					//測試累積消費金額	//不給會員自行變更 之後給員工系統做處理 或消費時處理
					m.setAccumulatedAmount(Integer.valueOf(accumulatedField.getText()));
					msi.updateMemberAccumulatedAmount(conn, m);
					 // 顯示儲存成功的提示框
					JOptionPane.showMessageDialog(null, "已儲存變更", "儲存成功", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
			          ex.printStackTrace();
			            // 顯示錯誤提示框
			            JOptionPane.showMessageDialog(null, "儲存變更失敗: " + ex.getMessage(), "儲存失敗", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setVisible(false);
		panel.setBounds(235, 102, 638, 251);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnNewButton.setBounds(105, 444, 85, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("詳細資料");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MemberInfo memberInfo = misi.getMemberInfoByUserID(conn, m.getUserId());
				
				email.setText(memberInfo.getEmail() != null ? memberInfo.getEmail() : "尚未填寫");
				address1.setText(memberInfo.getAddress1() != null ? memberInfo.getAddress1() : "尚未填寫");
				address2.setText(memberInfo.getAddress2() != null ? memberInfo.getAddress2() : "尚未填寫");
				birthday.setText(memberInfo.getBirthday() != null ? memberInfo.getBirthday().toString(): "尚未填寫");

				panel.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(235, 69, 85, 23);
		contentPane.add(btnNewButton_1);
		

		email = new JTextField();
		email.setBounds(155, 33, 427, 21);
		panel.add(email);
		email.setColumns(10);
		
		address1 = new JTextField();
		address1.setBounds(155, 89, 427, 21);
		panel.add(address1);
		address1.setColumns(10);
		
		address2 = new JTextField();
		address2.setBounds(155, 141, 427, 21);
		panel.add(address2);
		address2.setColumns(10);
		
		birthday = new JTextField();
		birthday.setBounds(155, 194, 218, 21);
		panel.add(birthday);
		birthday.setColumns(10);
		
		emailLabel = new JLabel("email");
		emailLabel.setBounds(81, 36, 46, 15);
		panel.add(emailLabel);
		
		addressLabel1 = new JLabel("地址1");
		addressLabel1.setBounds(81, 92, 46, 15);
		panel.add(addressLabel1);
		
		addressLabel2 = new JLabel("地址2");
		addressLabel2.setBounds(81, 144, 46, 15);
		panel.add(addressLabel2);
		
		btnNewButton_2 = new JButton("變更詳細資料");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				try {
					MemberInfo memberInfo = misi.getMemberInfoByUserID(conn, m.getUserId());
					memberInfo.setEmail(email.getText());
					memberInfo.setAddress1(address1.getText());
					memberInfo.setAddress2(address2.getText());
					LocalDate localDate = LocalDate.parse(birthday.getText()); //"yyyy-MM-dd"
					java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
					memberInfo.setBirthday(sqlDate);
					misi.updateMemberInfo(conn, memberInfo);
					
					JOptionPane.showMessageDialog(null, "已儲存變更", "儲存成功", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					 ex.printStackTrace();
			            // 顯示錯誤提示框
			            JOptionPane.showMessageDialog(null, "儲存變更失敗: " + ex.getMessage(), "儲存失敗", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_2.setBounds(457, 218, 125, 23);
		panel.add(btnNewButton_2);
		
		birthdayLabel = new JLabel("生日");
		birthdayLabel.setBounds(81, 197, 46, 15);
		panel.add(birthdayLabel);
		
		JLabel lblNewLabel_8 = new JLabel("YYYY-MM-dd");
		lblNewLabel_8.setBounds(383, 197, 92, 15);
		panel.add(lblNewLabel_8);
		
		btnNewButton_3 = new JButton("回首頁");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Index l = new Index(conn, m);
				l.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(10, 10, 79, 53);
		contentPane.add(btnNewButton_3);
		
	
		

	}
}
