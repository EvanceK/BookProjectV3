package controller.employee;



import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import model.BookOrders;
import model.BookOrdersDetail;
import model.Employees;
import model.Member;
import model.MemberInfo;
import model.Products;
import service.impl.BookOrdersDetailServiceImpl;
import service.impl.BookOrdersServiceImpl;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.ProductsServiceImpl;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.ImageIcon;

public class CheckoutScreen2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static List<Products> shoppingCar;
	private static Connection conn = null;
	private static Member m;
	private ProductsServiceImpl psi = new ProductsServiceImpl();
	private static int finalAmount, pointsUsed;
	private MemberInfoServiceImpl misi = new MemberInfoServiceImpl();
	private MemberServiceImpl msi = new MemberServiceImpl();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String finaladdress;
	protected static BookOrdersDetail bookOrdersDetail;
	protected static BookOrders bookOrders;
	private BookOrdersServiceImpl bosi = new BookOrdersServiceImpl();
	private BookOrdersDetailServiceImpl bodsi = new BookOrdersDetailServiceImpl();
	protected static Employees emp;
	protected static List<BookOrdersDetail> bookOrdersDetails;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutScreen2 frame = new CheckoutScreen2(conn, shoppingCar, m, finalAmount, pointsUsed,bookOrders,bookOrdersDetails,emp);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CheckoutScreen2(Connection conn, List<Products> shoppingCar, Member m, int finalAmount, int pointsUsed,BookOrders bookOrders,List<BookOrdersDetail>  bookOrdersDetails, Employees emp) {
		this.shoppingCar = shoppingCar;
		this.m = m;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 獲取user的地址
		m.getUserId();
		MemberInfo mem = misi.getMemberInfoByUserID(conn, m.getUserId());

		JButton checkoutButton = new JButton("確認結帳");
		checkoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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
					//經手人 改成empid
					bookOrders.setEmployeesId(emp.getEmployeeId());
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
				CheckoutScreen3 cScreen3 = new CheckoutScreen3(conn, m, bookOrders, bookOrdersDetails);
				cScreen3.setVisible(true);
				dispose();

			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("要結帳了唷~~~~");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("標楷體", Font.PLAIN, 36));
		lblNewLabel_1.setBounds(196, 331, 257, 53);
		contentPane.add(lblNewLabel_1);
		checkoutButton.setBounds(249, 383, 100, 30);
		contentPane.add(checkoutButton);

		// 前一頁按鈕
		JButton btnNewButton = new JButton("上一步");
		btnNewButton.setBounds(470, 10, 100, 30);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckoutScreen cScreen = new CheckoutScreen(conn, shoppingCar, emp,m);
				cScreen.setVisible(true);
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

		JLabel lblNewLabel_6 = new JLabel("2.確認");
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CheckoutScreen2.class.getResource("/resources/DSC00188.JPG")));
		lblNewLabel.setBounds(10, 53, 566, 510);
		contentPane.add(lblNewLabel);

	}
}