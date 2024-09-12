package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.admin.AdminUI;
import controller.employee.EmployeeLogin;
import controller.member.MemberLogin;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SystemUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemUI frame = new SystemUI();
					 frame.setTitle("系統首頁");
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
	public SystemUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
	    setIconImage(windowIcon.getImage());
	    
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("員工專區");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeLogin employeeLogin = new EmployeeLogin();
				employeeLogin.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(SystemUI.class.getResource("/resources/evance.png")));
		lblNewLabel_1.setBounds(92, 56, 428, 143);
		contentPane.add(lblNewLabel_1);
		btnNewButton_1.setBounds(239, 250, 100, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("會員專區");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberLogin memberLogin = new MemberLogin();
				memberLogin.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(92, 250, 100, 40);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("離開");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(388, 250, 100, 40);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SystemUI.class.getResource("/resources/bg2.jpg")));
		lblNewLabel.setBounds(-239, -68, 825, 550);
		contentPane.add(lblNewLabel);
	}
}
