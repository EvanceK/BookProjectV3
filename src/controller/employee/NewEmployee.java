package controller.employee;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemUI;
import controller.admin.AddMember;
import model.Employees;
import model.Member;
import model.MemberInfo;
import model.VipLevel;
import service.impl.EmployeesServiceImpl;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import utils.Clock;
import utils.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.time.LocalDate;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class NewEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField employeeId;
	private JTextField password;
	private JTextField phone;
	private static EmployeesServiceImpl esi = new EmployeesServiceImpl();

	Connection conn = DbConnection.getDbConnection();
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEmployee frame = new NewEmployee();
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

	public NewEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 250, 154));
		panel.setBounds(10, 10, 400, 356);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextField Password1 = new JTextField();
		 Password1.setBounds(99, 79, 233, 21);
		panel.add(Password1);
		Password1.setColumns(10);
		
		JTextField Name1 = new JTextField();
		Name1.setBounds(99, 32, 233, 21);
		panel.add(Name1);
		Name1.setColumns(10);
		
		JTextField phone = new JTextField();
		phone.setBounds(99, 184, 233, 21);
		panel.add(phone);
		phone.setColumns(10);
		
		
		JTextField Email1 = new JTextField();
		Email1.setBounds(99, 235, 233, 21);
		panel.add(Email1);
		Email1.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("電話");
		lblNewLabel_11.setBounds(37, 187, 46, 15);
		panel.add(lblNewLabel_11);
		
		
		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(37, 83, 63, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(39, 35, 63, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("email");
		lblNewLabel_6.setBounds(37, 238, 63, 15);
		panel.add(lblNewLabel_6);
		

		JButton btnNewButton = new JButton("新增");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				  Employees employees = new Employees();
			    if (Name1.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "姓名不得空白", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
			    if (Password1.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "密碼不得空白", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
			    employees.setEmployeeName(Name1.getText());
			    employees.setPassword(Password1.getText());
			    employees.setPhone(phone.getText());
			    employees.setEmail(Email1.getText());
			
				conn = DbConnection.getDbConnection();
				String nextEmployeeId = esi.getNextEmployeeId(conn);
				employees.setEmployeeId(nextEmployeeId);
				esi.addEmployee(conn, employees);
				
				JOptionPane.showMessageDialog(null, "員工編號為:"+employees.getEmployeeId(),"註冊", JOptionPane.INFORMATION_MESSAGE);
				EmployeeLogin employeeLogin = new EmployeeLogin();
				employeeLogin.setVisible(true);
				dispose();

			}
		});
		btnNewButton.setBounds(58, 313, 96, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(290, 313, 96, 23);
		panel.add(btnNewButton_2);
		
	}
}