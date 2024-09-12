package controller.product;

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

import model.BookOrders;
import model.BookOrdersDetail;
import model.Member;
import model.MemberInfo;
import model.Products;
import service.impl.BookOrdersDetailServiceImpl;
import service.impl.BookOrdersServiceImpl;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.ProductsServiceImpl;
import utils.DiscountService;

import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class ShoppingCarUI2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static List<Products> shoppingCar;
	private static Connection conn = null;
	private static Member m;
	private ProductsServiceImpl psi = new ProductsServiceImpl();
	private static int finalAmount, pointsUsed;
	private JTextField otheraddress;
	private MemberInfoServiceImpl misi = new MemberInfoServiceImpl();
	private MemberServiceImpl msi = new MemberServiceImpl();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String finaladdress;
	protected static BookOrdersDetail bookOrdersDetail;
	protected static BookOrders bookOrders;
	private BookOrdersServiceImpl bosi = new BookOrdersServiceImpl();
	private BookOrdersDetailServiceImpl bodsi = new BookOrdersDetailServiceImpl();
	protected static List<BookOrdersDetail> bookOrdersDetails;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingCarUI2 frame = new ShoppingCarUI2(conn, shoppingCar, m, finalAmount, pointsUsed,bookOrders,bookOrdersDetails);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ShoppingCarUI2(Connection conn, List<Products> shoppingCar, Member m, int finalAmount, int pointsUsed,BookOrders bookOrders,List<BookOrdersDetail>  bookOrdersDetails) {
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
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(10, 53, 560, 442);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JRadioButton radio1 = new JRadioButton("地址1(預設)");
		radio1.setBackground(new Color(224, 255, 255));
		radio1.setSelected(true);
		buttonGroup.add(radio1);
		radio1.setBounds(21, 60, 91, 23);
		panel_1.add(radio1);

		JRadioButton radio2 = new JRadioButton("地址2");
		radio2.setBackground(new Color(224, 255, 255));
		buttonGroup.add(radio2);
		radio2.setBounds(21, 120, 96, 23);
		panel_1.add(radio2);

		JRadioButton radio3 = new JRadioButton("其他");
		radio3.setBackground(new Color(224, 255, 255));
		buttonGroup.add(radio3);
		radio3.setBounds(21, 180, 92, 23);
		panel_1.add(radio3);

		// 獲取user的地址
		m.getUserId();
		MemberInfo mem = misi.getMemberInfoByUserID(conn, m.getUserId());

		JLabel address1 = new JLabel(mem.getAddress1());
		address1.setBounds(150, 64, 395, 15);
		panel_1.add(address1);

		JLabel address2 = new JLabel(mem.getAddress2());
		address2.setBounds(150, 124, 395, 15);
		panel_1.add(address2);

		otheraddress = new JTextField();
		otheraddress.setBounds(150, 181, 395, 21);
		panel_1.add(otheraddress);
		otheraddress.setColumns(10);

		JButton checkoutButton = new JButton("確認結帳");
		checkoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (radio1.isSelected()) {
					finaladdress = mem.getAddress1();
				} else if (radio2.isSelected()) {
					finaladdress = mem.getAddress2();
				} else if (radio3.isSelected()) {
					finaladdress = otheraddress.getText();
				}
				
				
				//進行支付 事務功能

				try {
					conn.setAutoCommit(false);//不自動提交
					 System.out.println("開始結帳流程...");
					m.setBalance(m.getBalance() - finalAmount); // 扣款
					m.setPoint(m.getPoint() - pointsUsed); // 扣除點數
					m.setAccumulatedAmount(m.getAccumulatedAmount()+finalAmount);//累積消費金額
					m.setPoint(m.getPoint() + finalAmount/20); // 消費金額點數回饋 20元一點
					m.setVipLevel(m.getVipLevel());
					msi.updateMemberAll(conn, m); //修改資料庫資訊
					 System.out.println("修改會員資料成功");
												//修改訂單資訊
					 
					bookOrders.setNewPoint(finalAmount/20);
					String getneworderno = bosi.getneworderno(conn);
				    bookOrders.setOrderId(getneworderno);
					bookOrders.setUserId(m.getUserId());
					bookOrders.setUsePoint(pointsUsed);
					bookOrders.setFinalAmount(finalAmount);
					bookOrders.setEmployeesId("By User");
					bookOrders.setNewPoint(finalAmount/20);
					bookOrders.setOrderTime(LocalDateTime.now());
					bosi.insert(conn, bookOrders);
					
					System.out.println("插入訂單成功");
					
					bodsi.insertAll(conn, bookOrdersDetails);
					System.out.println("插入訂單明細成功");
					
					javax.swing.JOptionPane.showMessageDialog(null, "結帳成功！");
					
					
					conn.commit();//提交數據
					System.out.println("提交成功");
					
				} catch (SQLException e1) {
					 System.out.println("捕捉到 SQL 異常，準備 rollback...");
					 try {
			                conn.rollback();	//有問題回朔到最初
			                System.out.println("Rollback 成功");
			                
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					e1.printStackTrace();
					
					
					
					
				}finally {
					 try {
			                conn.setAutoCommit(true);//改回自動提交
			                System.out.println("恢復自動提交");
			            } catch (SQLException er) {
			                er.printStackTrace();
			            }
				}
				ShoppingCarUI3 shoppingCarUI3 = new ShoppingCarUI3(conn, m, bookOrders, bookOrdersDetails);
				shoppingCarUI3.setVisible(true);
				dispose();

			}
		});
		checkoutButton.setBounds(470, 507, 100, 30);
		contentPane.add(checkoutButton);

		// 前一頁按鈕
		JButton btnNewButton = new JButton("上一步");
		btnNewButton.setBounds(470, 10, 100, 30);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShoppingCarUI shoppingCarUI = new ShoppingCarUI(conn, shoppingCar, m);
				shoppingCarUI.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(10, 10, 450, 33);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("1.付款明細確認");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(13, 6, 130, 20);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_6 = new JLabel("2.收貨地址確認");
		lblNewLabel_6.setForeground(new Color(0, 0, 255));
		lblNewLabel_6.setFont(new Font("新細明體", Font.BOLD, 14));
		lblNewLabel_6.setBounds(201, 6, 130, 20);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("3.訂單完成");
		lblNewLabel_7.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(369, 6, 130, 20);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel(">>>>>");
		lblNewLabel_8.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(139, 9, 46, 15);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel(">>>>>");
		lblNewLabel_9.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(318, 9, 46, 15);
		panel.add(lblNewLabel_9);

	}
}
