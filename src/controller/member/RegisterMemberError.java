package controller.member;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemUI;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class RegisterMemberError extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMemberError frame = new RegisterMemberError();
					 frame.setTitle("註冊失敗");

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
	public RegisterMemberError() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
	    setIconImage(windowIcon.getImage());
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("重新註冊");
		btnNewButton.setBounds(154, 210, 105, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterMember registerMember = new RegisterMember();
				registerMember.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("註冊失敗");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(170, 35, 131, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(RegisterMemberError.class.getResource("/resources/error.jpg")));
		lblNewLabel_1.setBounds(101, 63, 200, 137);
		contentPane.add(lblNewLabel_1);
	}

}
