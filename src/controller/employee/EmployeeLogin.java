package controller.employee;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.SystemUI;
import controller.admin.AdminUI;
import model.Employees;
import service.impl.EmployeesServiceImpl;
import utils.DbConnection;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.Color;

public class EmployeeLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField employeeid;
	private JPasswordField password;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private EmployeesServiceImpl esi= new EmployeesServiceImpl();
	protected Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
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
	public EmployeeLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 250, 154));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
	    setIconImage(windowIcon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		employeeid = new JTextField();
		employeeid.setBounds(155, 85, 138, 21);
		contentPane.add(employeeid);
		employeeid.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(155, 116, 138, 21);
		contentPane.add(password);
		password.setColumns(10);
		
		JCheckBox checkAdmin = new JCheckBox("進入管理者介面");
		checkAdmin.setBounds(155, 147, 138, 23);
		contentPane.add(checkAdmin);
		
		
		JButton btnNewButton = new JButton("確定");
		btnNewButton.setBounds(155, 176, 138, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (checkAdmin.isSelected()) {
					//額外驗證管理權限
					String Employeeid = employeeid.getText();
					char[] passwordChars = password.getPassword();
					String Password = new String(passwordChars);
					conn = DbConnection.getDbConnection();
					Employees emp = esi.comfirmAdmin(conn, Employeeid, Password,1);
					if (emp != null) {
						AdminUI login = new AdminUI();
						login.setVisible(true);
						dispose();
					} else {
			            JOptionPane.showMessageDialog(null, "登入失敗 或 管理權限不足", "登入失敗", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
				
				String Employeeid = employeeid.getText();
				char[] passwordChars = password.getPassword();
				String Password = new String(passwordChars);
				conn = DbConnection.getDbConnection();
				 Employees emp = esi.login(conn, Employeeid, Password);
				
				if (emp != null) {
					Employeesystem login = new Employeesystem(conn, emp);
					login.setVisible(true);
					dispose();
				} else {
		            JOptionPane.showMessageDialog(null, "登入失敗", "登入失敗", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			}
			
		});
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("工號 或 姓名");
		lblNewLabel.setBounds(72, 88, 73, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(99, 119, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("我是新員工");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewEmployee newEmployee = new NewEmployee();
				newEmployee.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(155, 209, 138, 23);
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
		btnNewButton_2.setBounds(10, 10, 85, 23);

	}
}
