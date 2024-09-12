package controller.admin;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Member;
import model.MemberInfo;
import model.VipLevel;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import utils.DbConnection;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MemberInfoUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Userid1;
	private JTextField Password1;
	private JTextField Name1;
	private JTextField Balance1;
	private JTextField Point1;
	private JTextField Amount1;
	private JTextField Email1;
	private JTextField Address1;
	private JTextField Address2;
	private JTextField Birthday1;
	private MemberServiceImpl msi = new MemberServiceImpl();
	private MemberInfoServiceImpl misi = new MemberInfoServiceImpl();
	private Connection conn;
	private static Member member;
	private static MemberInfo memberInfo;
	private JTextField phone;
	private JList<VipLevel> vip;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberInfoUI frame = new MemberInfoUI(member,memberInfo);
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
	public MemberInfoUI(Member member,MemberInfo memberInfo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 622, 424);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		Userid1 = new JTextField();
		Userid1.setEditable(false);
		Userid1.setBounds(99, 29, 96, 21);
		panel.add(Userid1);
		Userid1.setColumns(10);
		
		Password1 = new JTextField();
		Password1.setEditable(false);
		Password1.setBounds(99, 79, 96, 21);
		panel.add(Password1);
		Password1.setColumns(10);
		
		Name1 = new JTextField();
		Name1.setEditable(false);
		Name1.setBounds(99, 129, 96, 21);
		panel.add(Name1);
		Name1.setColumns(10);
		
		Balance1 = new JTextField();
		Balance1.setEditable(false);
		Balance1.setBounds(99, 179, 96, 21);
		panel.add(Balance1);
		Balance1.setColumns(10);
		
		Point1 = new JTextField();
		Point1.setEditable(false);
		Point1.setBounds(99, 229, 96, 21);
		panel.add(Point1);
		Point1.setColumns(10);
		
		Amount1 = new JTextField();
		Amount1.setEditable(false);
		Amount1.setBounds(99, 279, 96, 21);
		panel.add(Amount1);
		Amount1.setColumns(10);
		
		phone = new JTextField();
		phone.setEditable(false);
		phone.setBounds(99, 373, 96, 21);
		panel.add(phone);
		phone.setColumns(10);
		
		
		Email1 = new JTextField();
		Email1.setEditable(false);
		Email1.setBounds(316, 79, 210, 21);
		panel.add(Email1);
		Email1.setColumns(10);
		
		Address1 = new JTextField();
		Address1.setEditable(false);
		Address1.setBounds(316, 129, 210, 21);
		panel.add(Address1);
		Address1.setColumns(10);
		
		Address2 = new JTextField();
		Address2.setEditable(false);
		Address2.setBounds(316, 179, 210, 21);
		panel.add(Address2);
		Address2.setColumns(10);
		
		Birthday1 = new JTextField();
		Birthday1.setEditable(false);
		Birthday1.setBounds(316, 29, 96, 21);
		panel.add(Birthday1);
		Birthday1.setColumns(10);
		
		VipLevel[] vipLevels = VipLevel.values();
		JComboBox<VipLevel> vipComboBox = new JComboBox<>(vipLevels);
		vipComboBox.setBounds(99, 321, 96, 21);
		panel.add(vipComboBox);
		vipComboBox.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("帳號");
		lblNewLabel.setBounds(27, 34, 73, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(27, 83, 73, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(27, 132, 73, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("儲值餘額");
		lblNewLabel_3.setBounds(27, 181, 73, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("點數");
		lblNewLabel_4.setBounds(27, 230, 73, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("累積消費");
		lblNewLabel_5.setBounds(27, 279, 73, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_10 = new JLabel("會員等級");
		lblNewLabel_10.setBounds(27, 324, 73, 15);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("電話");
		lblNewLabel_11.setBounds(27, 376, 73, 15);
		panel.add(lblNewLabel_11);

		
		JLabel lblNewLabel_6 = new JLabel("email");
		lblNewLabel_6.setBounds(256, 82, 63, 15);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("地址1");
		lblNewLabel_7.setBounds(256, 132, 63, 15);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("地址2");
		lblNewLabel_8.setBounds(256, 182, 63, 15);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("生日");
		lblNewLabel_9.setBounds(256, 34, 63, 15);
		panel.add(lblNewLabel_9);
		
		Userid1.setText(member.getUserId());
		Password1.setText(member.getPassword());
		Name1.setText(member.getUsername());
		Balance1.setText(String.valueOf(member.getBalance()));
		Point1.setText(String.valueOf(member.getPoint()));
		Amount1.setText(String.valueOf(member.getAccumulatedAmount()));
		vipComboBox.setSelectedItem(member.getVipLevel());
		phone.setText(member.getPhone());
		
		Email1.setText(memberInfo.getEmail() != null ? memberInfo.getEmail() : "");
		Address1.setText(memberInfo.getAddress1() != null ? memberInfo.getAddress1() : "");
		Address2.setText(memberInfo.getAddress2() != null ? memberInfo.getAddress2() : "");
		Birthday1.setText(memberInfo.getBirthday() != null ? memberInfo.getBirthday().toString(): "");

		JButton btnNewButton_2 = new JButton("回上頁");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(426, 372, 96, 23);
		panel.add(btnNewButton_2);
		

		
	}
}
