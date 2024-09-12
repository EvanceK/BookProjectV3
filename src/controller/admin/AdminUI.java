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

	private Connection conn = null;
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
				createMemberPanel();
			}
		});
		contentPane.setLayout(null);

		JPanel show = new JPanel();
		show.setBounds(161, 86, 715, 409);
		contentPane.add(show);
		show.setLayout(new CardLayout(0, 0));

		JPanel buttonlist = new JPanel();
		buttonlist.setBounds(10, 75, 138, 478);
		buttonlist.setLayout(new GridLayout(5, 1, 0, 0));
		contentPane.add(buttonlist);

		JButton btnNewButton = new JButton("會員名單管理");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPanel.setVisible(true);

				conn = DbConnection.getDbConnection();

				// 定義表頭
				String[] columnNames = { "id", "帳號", "密碼", "姓名", "餘額", "點數", "累積消費", "會員等級", "電話" };
				// 創建表格模型
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
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
				scrollPane.setBounds(0, 0, 715, 350); // 設置滾動面板的邊界
				show.removeAll(); // 清除之前的內容
				show.add(scrollPane); // 添加新的滾動面板
				show.revalidate(); // 刷新顯示
				show.repaint();

			}
		});
		btnNewButton.setBounds(25, 86, 118, 38);
		buttonlist.add(btnNewButton);

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

		buttonPanel = new JPanel();
		buttonPanel.setBounds(161, 505, 715, 37);
		buttonPanel.setVisible(false);
		contentPane.add(buttonPanel);

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

		JButton btnNewButton_7 = new JButton("修改");
		btnNewButton_7.addMouseListener(new MouseAdapter() {
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
		buttonPanel.add(btnNewButton_7);

		JButton btnNewButton_6 = new JButton("刪除");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
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
		buttonPanel.add(btnNewButton_6);

		JPanel showMemberPanel = new JPanel();
		showMemberPanel.setBounds(149, 75, 737, 478);
		contentPane.add(showMemberPanel);
		showMemberPanel.setLayout(new CardLayout(0, 0));

		JButton btnNewButton_1 = new JButton("員工名單管理");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPanel.setVisible(false);

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
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 715, 350); // 設置滾動面板的邊界
				show.removeAll(); // 清除之前的內容
				show.add(scrollPane); // 添加新的滾動面板
				show.revalidate(); // 刷新顯示
				show.repaint();

			}
		});

		btnNewButton_1.setBounds(25, 134, 118, 38);
		buttonlist.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("商品項目管理");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPanel.setVisible(false);

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
				scrollPane.setBounds(0, 0, 715, 350); // 設置滾動面板的邊界
				show.removeAll(); // 清除之前的內容
				show.add(scrollPane); // 添加新的滾動面板
				show.revalidate(); // 刷新顯示
				show.repaint();

			}
		});

		btnNewButton_2.setBounds(25, 182, 118, 38);
		buttonlist.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("員工出勤紀錄\n(待開發)");
		btnNewButton_3.setBounds(25, 230, 118, 38);
		buttonlist.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("訂單紀錄");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPanel.setVisible(false);

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
				show.removeAll(); // 清除之前的內容
				show.add(scrollPane); // 添加新的滾動面板
				show.revalidate(); // 刷新顯示
				show.repaint();
			}
		});
		btnNewButton_4.setBounds(25, 278, 118, 38);
		buttonlist.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(149, 32, 44, 44);
		lblNewLabel.setIcon(new ImageIcon(AdminUI.class.getResource("/resources/notfinished.jpg")));
		contentPane.add(lblNewLabel);

	}

	private void createMemberPanel() {
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
