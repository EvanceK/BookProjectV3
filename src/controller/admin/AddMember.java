package controller.admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import model.MemberInfo;
import model.VipLevel;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import utils.DbConnection;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.time.LocalDate;
import javax.swing.JComboBox;

public class AddMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Userid1;
	private JTextField Password1;
	private JTextField Name1;
	private JTextField Balance1;
	private JTextField Point1;
	private JTextField Amount1;
	private JTextField phone;
	private JTextField Email1;
	private JTextField Address1;
	private JTextField Address2;
	private JTextField Birthday1;
	private MemberServiceImpl msi = new MemberServiceImpl();
	private MemberInfoServiceImpl misi = new MemberInfoServiceImpl();
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
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
	public AddMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 622, 383);
		contentPane.add(panel);
		panel.setLayout(null);

		Userid1 = new JTextField();
		Userid1.setBounds(99, 29, 96, 21);
		panel.add(Userid1);
		Userid1.setColumns(10);

		Password1 = new JTextField();
		Password1.setBounds(99, 79, 96, 21);
		panel.add(Password1);
		Password1.setColumns(10);

		Name1 = new JTextField();
		Name1.setBounds(99, 129, 96, 21);
		panel.add(Name1);
		Name1.setColumns(10);

		Balance1 = new JTextField();
		Balance1.setBounds(99, 179, 96, 21);
		panel.add(Balance1);
		Balance1.setColumns(10);

		Point1 = new JTextField();
		Point1.setBounds(99, 229, 96, 21);
		panel.add(Point1);
		Point1.setColumns(10);

		Amount1 = new JTextField();
		Amount1.setBounds(99, 279, 96, 21);
		panel.add(Amount1);
		Amount1.setColumns(10);

		phone = new JTextField();
		phone.setBounds(100, 353, 96, 21);
		panel.add(phone);
		phone.setColumns(10);

		Email1 = new JTextField();
		Email1.setBounds(316, 129, 96, 21);
		panel.add(Email1);
		Email1.setColumns(10);

		Address1 = new JTextField();
		Address1.setBounds(316, 79, 96, 21);
		panel.add(Address1);
		Address1.setColumns(10);

		Address2 = new JTextField();
		Address2.setBounds(316, 179, 96, 21);
		panel.add(Address2);
		Address2.setColumns(10);

		Birthday1 = new JTextField();
		Birthday1.setBounds(316, 29, 96, 21);
		panel.add(Birthday1);
		Birthday1.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("YYYY-MM-dd");
		lblNewLabel_10.setBounds(422, 32, 83, 15);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("電話");
		lblNewLabel_11.setBounds(39, 355, 46, 15);
		panel.add(lblNewLabel_11);

		VipLevel[] vipLevels = VipLevel.values();
		JComboBox<VipLevel> vipComboBox = new JComboBox<>(vipLevels);
		vipComboBox.setBounds(99, 310, 96, 21);
		panel.add(vipComboBox);
		vipComboBox.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel = new JLabel("*帳號");
		lblNewLabel.setBounds(37, 34, 63, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(37, 83, 63, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(37, 132, 63, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("儲值餘額");
		lblNewLabel_3.setBounds(37, 181, 63, 15);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("點數");
		lblNewLabel_4.setBounds(37, 230, 63, 15);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("累積消費");
		lblNewLabel_5.setBounds(37, 279, 63, 15);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("email");
		lblNewLabel_6.setBounds(256, 132, 63, 15);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("電話");
		lblNewLabel_7.setBounds(256, 82, 63, 15);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("地址");
		lblNewLabel_8.setBounds(256, 182, 63, 15);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("生日");
		lblNewLabel_9.setBounds(256, 34, 63, 15);
		panel.add(lblNewLabel_9);

		JButton btnNewButton = new JButton("新增");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Member member = new Member();
				if (Userid1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "帳號不得空白", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				member.setUserId(Userid1.getText());
				member.setPassword(Password1.getText());
				member.setUsername(Name1.getText());
				member.setBalance(Integer.valueOf(Balance1.getText()));
				member.setPoint(Integer.valueOf(Point1.getText()));
				member.setAccumulatedAmount(Integer.valueOf(Amount1.getText()));
				member.setPhone(phone.getText());

				member.setVipLevel((VipLevel) vipComboBox.getSelectedItem());

				MemberInfo memberInfo = new MemberInfo();
				memberInfo.setUserId(Userid1.getText());
				memberInfo.setUsername(Name1.getText());
				memberInfo.setEmail(Email1.getText());
				memberInfo.setAddress1(Address1.getText());
				memberInfo.setAddress2(Address2.getText());
				if (Birthday1.getText().isEmpty()) {
					memberInfo.setBirthday(null);
				} else {
					LocalDate localDate = LocalDate.parse(Birthday1.getText());
					java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
					memberInfo.setBirthday(sqlDate);
				}
				conn = DbConnection.getDbConnection();
				msi.addMember(conn, member);
				misi.addMemberInfo(conn, memberInfo);
				dispose();

			}
		});
		btnNewButton.setBounds(316, 278, 96, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("重新填寫");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Userid1.setText("");
				Password1.setText("");
				Name1.setText("");
				Balance1.setText("");
				Point1.setText("");
				Amount1.setText("");
				phone.setText("");
				Email1.setText("");
				Address1.setText("");
				Address2.setText("");
				Birthday1.setText("");
			}
		});
		btnNewButton_1.setBounds(420, 278, 96, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(526, 278, 96, 23);
		panel.add(btnNewButton_2);

	}
}
