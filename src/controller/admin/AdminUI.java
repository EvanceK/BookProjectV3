package controller.admin;

import java.awt.EventQueue;
import java.awt.GridLayout;

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
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MemberServiceImpl msi = new MemberServiceImpl();
	private MemberInfoServiceImpl misi = new MemberInfoServiceImpl();
	private ProductsServiceImpl psi = new ProductsServiceImpl();
	private EmployeesServiceImpl esi = new EmployeesServiceImpl();
	private BookOrdersServiceImpl bosi = new BookOrdersServiceImpl();
	private BookOrdersDetailServiceImpl bodsi = new BookOrdersDetailServiceImpl();

	private Connection conn;
	protected JTable table;
	protected DefaultTableModel tableModel;
	protected JPanel buttonPanel;
	protected DefaultTableModel employeeTableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
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
	public AdminUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				updateMemberPanel();
			}
		});
		contentPane.setLayout(null);

		JPanel Panel_cards = new JPanel();
		Panel_cards.setBounds(158, 62, 728, 491);
		Panel_cards.setBackground(new Color(238, 247, 255));
		contentPane.add(Panel_cards);
		Panel_cards.setLayout(new CardLayout(0, 0));
		
//============================================================================================
	
		JPanel mempage = new JPanel();
		mempage.setBackground(new Color(255, 255, 234));
	    mempage.setLayout(null);
	    Panel_cards.add(mempage,"會員名單管理");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(28, 429, 676, 35);
		mempage.add(buttonPanel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(28, 33, 676, 386);
		mempage.add(scrollPane_1);
	
		
		JPanel show1 = new JPanel();
		scrollPane_1.setViewportView(show1);
		show1.setBounds(28, 33, 676, 386);
		show1.setLayout(null);
		
		JPanel buttonlist = new JPanel();
		buttonlist.setBounds(10, 62, 138, 478);
		contentPane.add(buttonlist);
		buttonlist.setLayout(new GridLayout(5, 1, 0, 0));
		
		//show 內容
		
		JButton memButton = new JButton("會員名單管理");
		memButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		memButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        // 切換到會員頁面
		        CardLayout cardLayout = (CardLayout) Panel_cards.getLayout();
		        cardLayout.show(Panel_cards, "會員名單管理");
		        
				conn = DbConnection.getDbConnection();

				// 定義表頭
				String[] columnNames = { "id", "帳號", "密碼", "姓名", "餘額", "點數", "累積消費", "會員等級", "電話" };
				// 創建表格模型
				tableModel = new DefaultTableModel(columnNames, 0) {
					// 讓JTable內容不可編輯
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				List<Member> allMember = msi.showAllMember(conn);

				for (Member m : allMember) {
					Object[] rowData = { m.getId(), m.getUserId(), m.getPassword(), m.getUsername(), m.getBalance(),
							m.getPoint(), m.getAccumulatedAmount(), m.getVipLevel(), m.getPhone() };
					tableModel.addRow(rowData);
				}
				table = new JTable(tableModel);
				// 創建 JScrollPane 並添加表格到其中
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 676, 386); // 設置滾動面板的邊界
				show1.removeAll(); // 清除之前的內容
				show1.add(scrollPane); // 添加新的滾動面板
				show1.revalidate(); // 刷新顯示
				show1.repaint();

			}
		});
		memButton.setBounds(25, 86, 118, 38);
		buttonlist.add(memButton);
		
		//Member下方按鈕區
		JButton btnNewButton_8 = new JButton("新增");
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMember addMember = new AddMember();
				addMember.setVisible(true);
			}
		});
		JButton btnNewButton_9 = new JButton("詳細資料");
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int id = (int) tableModel.getValueAt(selectedRow, 0);
					Member member = msi.showMemberById(conn, id);
					MemberInfo memberInfo = misi.getMemberInfoByUserID(conn, member.getUserId());
					MemberInfoUI reviseMember = new MemberInfoUI(member, memberInfo);
					reviseMember.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "請選擇一筆資料再查詢");
				}
			}
		});
		buttonPanel.add(btnNewButton_9);
		buttonPanel.add(btnNewButton_8);

		JButton revbutton = new JButton("修改");
		revbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int id = (int) tableModel.getValueAt(selectedRow, 0);
					Member member = msi.showMemberById(conn, id);
					MemberInfo memberInfo = misi.getMemberInfoByUserID(conn, member.getUserId());
					ReviseMember reviseMember = new ReviseMember(member, memberInfo);
					reviseMember.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "請選擇一筆資料再修改");
				}
			}
		});
		buttonPanel.add(revbutton);

		JButton delbutton = new JButton("刪除");
		delbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int id = (int) tableModel.getValueAt(selectedRow, 0);

					int confirm = JOptionPane.showConfirmDialog(null, "你確定要刪除該筆資料?", "刪除確認", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						Member m = msi.showMemberById(conn, id);
						msi.deleteMemberById(conn, id); // 刪除數據庫資料
						MemberInfo memberInfo = misi.getMemberInfoByUserID(conn, m.getUserId());
						tableModel.removeRow(selectedRow); // 刪除表格資料
					}
				} else {
					JOptionPane.showMessageDialog(null, "請選擇一筆資料再刪除");
				}
			}
		});
		buttonPanel.add(delbutton);
		
		
//============================================================================================
	    
		JPanel emppage = new JPanel();
		emppage.setBackground(new Color(255, 255, 234));
		emppage.setLayout(null);
	    Panel_cards.add(emppage,"員工名單管理");
	    
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(28, 33, 676, 386);
		emppage.add(scrollPane_2);
		
		JPanel show2 = new JPanel();
		scrollPane_2.setViewportView(show2);
		show2.setBounds(28, 33, 676, 386);
		show2.setLayout(null);
		
		JButton empButton = new JButton("員工名單管理");
		empButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		empButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        // 切換到員工頁面
		        CardLayout cardLayout = (CardLayout) Panel_cards.getLayout();
		        cardLayout.show(Panel_cards, "員工名單管理");
		        
				conn = DbConnection.getDbConnection();

				// 定義表頭
				String[] employeeColumnNames = { "ID", "工號", "姓名", "部門", "電話", "email" };
				// 創建表格模型
				DefaultTableModel employeeTableModel = new DefaultTableModel(employeeColumnNames, 0) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				List<Employees> allEmployees = esi.getAllEmployees(conn);
				for (Employees emp : allEmployees) {
					Object[] rowData = { emp.getId(), emp.getEmployeeId(), emp.getEmployeeName(), emp.getDepartment(),
							emp.getPhone(), emp.getEmail() };
					employeeTableModel.addRow(rowData);
				}

				table = new JTable(employeeTableModel);
				// 創建 JScrollPane 並添加表格到其中
				JScrollPane scrollPane2 = new JScrollPane(table);
				scrollPane2.setBounds(0, 0, 676, 386); // 設置滾動面板的邊界
				show2.removeAll(); // 清除之前的內容
				show2.add(scrollPane2); // 添加新的滾動面板
				show2.revalidate(); // 刷新顯示
				show2.repaint();

			}
		});
		
				empButton.setBounds(25, 134, 118, 38);
				buttonlist.add(empButton);
				
			
				//emp下方按鈕區
				JPanel buttonPanel2 = new JPanel();
				buttonPanel2.setBounds(28, 429, 676, 35);
				emppage.add(buttonPanel2);
				
				JButton empdetail = new JButton("詳細資料");				
				buttonPanel2.add(empdetail);

				JButton empadd = new JButton("新增");
				buttonPanel2.add(empadd);
				
	
				JButton emprevice = new JButton("修改");
				buttonPanel2.add(emprevice);

				JButton empdelete = new JButton("刪除");
				buttonPanel2.add(empdelete);
				
//============================================================================================

		JPanel bookpage = new JPanel();
		bookpage.setBackground(new Color(255, 255, 234));
		bookpage.setLayout(null);
	    Panel_cards.add(bookpage,"商品項目管理");
	    
		JPanel buttonPanel3 = new JPanel();
		buttonPanel3.setBounds(28, 429, 676, 35);
		bookpage.add(buttonPanel3);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(28, 33, 676, 386);
		bookpage.add(scrollPane_3);
		
		JPanel show3 = new JPanel();
		scrollPane_3.setViewportView(show3);
		show3.setBounds(28, 33, 676, 386);
		show3.setLayout(null);
		
		
		JButton bookbutton = new JButton("商品項目管理");
		bookbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			     // 切換到商品頁面
		        CardLayout cardLayout = (CardLayout) Panel_cards.getLayout();
		        cardLayout.show(Panel_cards, "商品項目管理");
		        

				conn = DbConnection.getDbConnection();

				String[] productColumnNames = { "ID", "ISBN", "書名", "作者", "原價", "特價", "出版日期" };
				DefaultTableModel productTableModel = new DefaultTableModel(productColumnNames, 0) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				List<Products> allProducts = psi.showAll(conn);
				for (Products p : allProducts) {
					Object[] rowData = { p.getId(), p.getIsbn(), p.getBookName(), p.getAuthor(), p.getListPrice(),
							p.getBestOffer(), p.getPublicationDate() };
					productTableModel.addRow(rowData);
				}

				table = new JTable(productTableModel);
				// 創建 JScrollPane 並添加表格到其中
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 676, 386); // 設置滾動面板的邊界
				show3.removeAll(); // 清除之前的內容
				show3.add(scrollPane); // 添加新的滾動面板
				show3.revalidate(); // 刷新顯示
				show3.repaint();

			}
		});
		
				bookbutton.setBounds(25, 182, 118, 38);
				buttonlist.add(bookbutton);
	    
		//book下方按鈕區
				
				JButton bookdetail = new JButton("詳細資料");				
				buttonPanel3.add(bookdetail);

				JButton bookadd = new JButton("新增");
				buttonPanel3.add(bookadd);
				
	
				JButton bookrevice = new JButton("修改");
				buttonPanel3.add(bookrevice);

				JButton bookdelete = new JButton("刪除");
				buttonPanel3.add(bookdelete);
	    
//================================================================================================

		JPanel orderspage = new JPanel();
		orderspage.setBackground(new Color(255, 255, 234));
		orderspage.setLayout(null);
	    Panel_cards.add(orderspage,"訂單管理");

		JPanel buttonPanel4 = new JPanel();
		buttonPanel4.setBounds(28, 429, 676, 35);
		orderspage.add(buttonPanel4);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(28, 33, 676, 386);
		orderspage.add(scrollPane_4);
		
		JPanel show4 = new JPanel();
		scrollPane_4.setViewportView(show4);
		show4.setBounds(28, 33, 676, 386);
		show4.setLayout(null);
		
		JButton orderbutton = new JButton("訂單管理");
		orderbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				  // 切換到訂單紀錄
		        CardLayout cardLayout = (CardLayout) Panel_cards.getLayout();
		        cardLayout.show(Panel_cards, "訂單管理");
				
				String[] bookorderColumnNames = { "id", "訂單編號", "使用者", "金額", "下單時間", "使用點數", "獲得點數", "經手人" };
				DefaultTableModel bookorderTableModel = new DefaultTableModel(bookorderColumnNames, 0) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				List<BookOrders> allOrders = bosi.showAll(conn);
				for (BookOrders o : allOrders) {
					Object[] rowData = { o.getId(), o.getOrderId(), o.getUserId(), o.getFinalAmount(), o.getOrderTime(),
							o.getUsePoint(), o.getNewPoint(), o.getEmployeesId() };
					bookorderTableModel.addRow(rowData);
				}
				table = new JTable(bookorderTableModel);
				// 創建 JScrollPane 並添加表格到其中
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 715, 350); // 設置滾動面板的邊界
				show4.removeAll(); // 清除之前的內容
				show4.add(scrollPane); // 添加新的滾動面板
				show4.revalidate(); // 刷新顯示
				show4.repaint();
			}
		});
		orderbutton.setBounds(25, 278, 118, 38);
		buttonlist.add(orderbutton);

		

		//================================================================================================
			    
				JPanel checkinoutpage = new JPanel();
				checkinoutpage.setBackground(new Color(255, 255, 234));
				checkinoutpage.setLayout(null);
			    Panel_cards.add(checkinoutpage,"員工出勤紀錄");
			    

				JButton checkbutton = new JButton("員工出勤紀錄");
				checkbutton.setBounds(25, 230, 118, 38);
				buttonlist.add(checkbutton);
			    
		//================================================================================================
			    
			    
	    
	    
		JButton btnNewButton_5 = new JButton("回主畫面");
		btnNewButton_5.setBounds(10, 10, 133, 38);
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				SystemUI systemUI = new SystemUI();
				systemUI.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_5);

	}
	
//	更新的監聽==============================================================================
	private void updateMemberPanel() {
		conn = DbConnection.getDbConnection();
		if (tableModel != null) {
			tableModel.setRowCount(0); // 或其他操作

			// 重新加載所有會員的數據
			List<Member> allMember = msi.showAllMember(conn);
			for (Member m : allMember) {
				Object[] rowData = { m.getId(), m.getUserId(), m.getPassword(), m.getUsername(), m.getBalance(),
						m.getPoint(), m.getAccumulatedAmount(), m.getVipLevel(), m.getPhone() };
				tableModel.addRow(rowData); // 加入新數據
			}
		} else {
			System.err.println("tableModel is null");
		}
	}
}
