package controller.member;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.SystemUI;
import model.BookOrders;
import model.Employees;
import model.Member;
import model.MemberInfo;
import model.Products;
import service.impl.BookOrdersDetailServiceImpl;
import service.impl.BookOrdersServiceImpl;
import service.impl.EmployeesServiceImpl;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.ProductsServiceImpl;
import utils.DbConnection;


import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;


import java.awt.CardLayout;

public class ShoppingHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MemberServiceImpl msi = new MemberServiceImpl();
	private MemberInfoServiceImpl misi = new MemberInfoServiceImpl();
    private ProductsServiceImpl psi = new ProductsServiceImpl();
    private EmployeesServiceImpl esi = new EmployeesServiceImpl();
    private BookOrdersServiceImpl bosi = new BookOrdersServiceImpl();
    private BookOrdersDetailServiceImpl bodsi = new BookOrdersDetailServiceImpl();

	private static Connection conn;
	protected JTable table;
	protected DefaultTableModel tableModel;
	protected JPanel buttonPanel;
	protected DefaultTableModel employeeTableModel;
	protected static Member m;
	protected static List<Products> shoppingCar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingHistory frame = new ShoppingHistory(conn,shoppingCar,m);
					 frame.setTitle("購買紀錄");

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
	public ShoppingHistory(Connection conn ,List<Products> shoppingCar, Member m) {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    JPanel show = new JPanel();
	    show.setBounds(161, 86, 715, 409);
	    contentPane.add(show);
	    show.setLayout(new CardLayout(0, 0));
		
		JPanel buttonlist  = new JPanel();
		buttonlist.setBounds(10, 75, 138, 478);
		buttonlist.setLayout(new GridLayout(5, 1, 0, 0));
		contentPane.add(buttonlist);
	
		JButton btnNewButton_5 = new JButton("回主畫面");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_5.setBounds(10, 10, 133, 38);
		contentPane.add(btnNewButton_5);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(161, 505, 715, 37);
		buttonPanel.setVisible(false);
		contentPane.add(buttonPanel);
		
		JButton btnNewButton_9 = new JButton("詳細資料");
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		           int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) { 
	                    int id = (int) tableModel.getValueAt(selectedRow, 0);
//	                  	Member member = msi.showMemberById(conn, id);
//                    	MemberInfo memberInfo = misi.getMemberInfoByUserID(conn, member.getUserId());
//	                    MemberInfoUI reviseMember = new MemberInfoUI(member,memberInfo);
//	                    reviseMember.setVisible(true);
	                 
	                } else {
	                    JOptionPane.showMessageDialog(null, "請選擇一筆資料再查詢");
	                }
			}
		});
		buttonPanel.add(btnNewButton_9);
		
	
		JPanel showMemberPanel = new JPanel();
		showMemberPanel.setBounds(149, 75, 737, 478);
		contentPane.add(showMemberPanel);
		showMemberPanel.setLayout(new CardLayout(0, 0));
		
	
		JButton btnNewButton_4 = new JButton("訂單紀錄");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPanel.setVisible(false);
				
		        String[] bookorderColumnNames = {"id","訂單編號", "使用者", "金額", "下單時間", "使用點數", "獲得點數", "經手人"};
		        DefaultTableModel bookorderTableModel = new DefaultTableModel(bookorderColumnNames, 0) {
		            public boolean isCellEditable(int row, int column) {
		                return false;
		            }
		        };
		        
		        List<BookOrders> allOrders = bosi.getAllbyuserId(conn,m);
		        for (BookOrders o : allOrders) {
		            Object[] rowData = {
		                o.getId(),o.getOrderId(),o.getUserId(),o.getFinalAmount(),o.getOrderTime(),o.getUsePoint(),o.getNewPoint(),o.getEmployeesId()
		            };
		            bookorderTableModel.addRow(rowData);
		        }
		        table = new JTable(bookorderTableModel);
		        // 創建 JScrollPane 並添加表格到其中
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(0, 0, 715, 350); // 設置滾動面板的邊界
                show.removeAll(); // 清除之前的內容
                show.add(scrollPane); // 添加新的滾動面板
                show.revalidate(); // 刷新顯示
                show.repaint();
			}
		});
		btnNewButton_4.setBounds(25, 278, 118, 38);
		buttonlist.add(btnNewButton_4);
		
	}
}
