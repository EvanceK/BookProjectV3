package controller.employee;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import controller.member.Index;
import model.BookOrders;
import model.BookOrdersDetail;
import model.Member;
import model.MemberInfo;
import model.Products;
import model.VipLevel;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.ProductsServiceImpl;
import utils.DiscountService;
import java.util.List;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.JTextArea;

public class CheckoutScreen3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static List<Products> shoppingCar;
	private static Connection conn =null;
	private static Member m;
	private ProductsServiceImpl psi = new ProductsServiceImpl();
	private static int finalAmount, pointsUsed;
	private MemberInfoServiceImpl misi = new MemberInfoServiceImpl();
	private MemberServiceImpl msi = new MemberServiceImpl();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	protected static BookOrders bookOrders;
	protected static BookOrdersDetail bookOrdersDetail;
	protected static List<BookOrdersDetail> bookOrdersDetails;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutScreen3 frame = new CheckoutScreen3(conn, m, bookOrders, bookOrdersDetails);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CheckoutScreen3(Connection conn,Member m, BookOrders bookOrders, List<BookOrdersDetail> bookOrdersDetails) {
		this.shoppingCar = shoppingCar;
		this.m = m;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 53, 560, 442);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea resultArea = new JTextArea();
		resultArea.setBounds(10, 10, 540, 422);
		panel_1.add(resultArea);
		
        StringBuilder sb = new StringBuilder();
        sb.append("\n----------------------------------------------------------------------------------------------");
        for (BookOrdersDetail book : bookOrdersDetails) {
              sb.append("\n書名: ").append(book.getBookName())
                        .append("\n數量: ").append(book.getBookAmount())
                        .append("\t\t價格: ").append(book.getBookPrice());
            }
        sb.append("\n----------------------------------------------------------------------------------------------");
        
        if("notmember".equals(m.getUserId())) {
            m.setBalance(0);
            m.setPoint(0);
            m.setAccumulatedAmount(0);
            m.setVipLevel(VipLevel.normal);

            bookOrders.setNewPoint(0);

        }

        	
		resultArea.setText(bookOrders.toString()+"\n"+m.toString()+sb.toString());
		
		
		m.getUserId();
		MemberInfo mem = misi.getMemberInfoByUserID(conn, m.getUserId());
		
	
		
		JButton checkoutButton = new JButton("離開系統");
		checkoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		checkoutButton.setBounds(470, 507, 100, 30);
		contentPane.add(checkoutButton);

		// 前一頁按鈕
		JButton btnNewButton = new JButton("繼續購物");
		btnNewButton.setBounds(360, 507, 100, 30);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(10, 10, 560, 33);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("1.付款明細確認");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(13, 6, 130, 20);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_6 = new JLabel("2.確認");
		lblNewLabel_6.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(215, 6, 130, 20);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("3.訂單完成");
		lblNewLabel_7.setForeground(new Color(0, 0, 255));
		lblNewLabel_7.setFont(new Font("新細明體", Font.BOLD, 14));
		lblNewLabel_7.setBounds(417, 6, 130, 20);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(">>>>>");
		lblNewLabel_8.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(156, 9, 46, 15);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(">>>>>");
		lblNewLabel_9.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(358, 9, 46, 15);
		panel.add(lblNewLabel_9);


	}
}
