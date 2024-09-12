package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemUI;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Color;

public class RegisterMemberSuccess extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMemberSuccess frame = new RegisterMemberSuccess();
					 frame.setTitle("註冊成功");

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
	public RegisterMemberSuccess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
	    setIconImage(windowIcon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("註冊成功");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(161, 104, 115, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("回登入頁面");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MemberLogin memberLogin = new MemberLogin();
				memberLogin.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(131, 174, 165, 23);
		contentPane.add(btnNewButton);
	}
}
