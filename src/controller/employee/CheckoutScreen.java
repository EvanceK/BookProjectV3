package controller.employee;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.product.ShoppingCarUI;
import controller.product.ShoppingCarUI2;
import controller.product.ShoppingCarUI2_2;
import controller.product.ShoppingCarUI3;
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
import utils.DiscountService;

import java.util.ArrayList;
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

public class CheckoutScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static List<Products> shoppingCar = new ArrayList();
	private static Connection conn = null;
	private static Member m;
	private ProductsServiceImpl psi = new ProductsServiceImpl();
	private MemberServiceImpl misi = new MemberServiceImpl();

	private Container mainPanel;
	private JTextField point;
	private JLabel totalamount;
	private JLabel finalamount;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel Balance;
	private List<BookOrdersDetail> bookOrdersDetails;
	private static BookOrders bookOrders = new BookOrders();
	private BookOrdersServiceImpl bosi = new BookOrdersServiceImpl();
	private JLabel temp;
	protected static Employees emp;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutScreen frame = new CheckoutScreen(conn, shoppingCar, emp, m);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CheckoutScreen(Connection conn, List<Products> shoppingCar, Employees emp, Member m) {
		this.conn = conn;
		this.shoppingCar = shoppingCar;
		this.m = m;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 創建主面板
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // 設定為垂直佈局
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setBounds(10, 53, 560, 300);
		contentPane.add(scrollPane);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("使用儲值金支付");
		rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(142, 472, 143, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("現金");
		rdbtnNewRadioButton_1.setBackground(new Color(255, 255, 255));
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(313, 472, 105, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		// 結帳按鈕
		JButton checkoutButton = new JButton("下一步");
		checkoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ("notmember".equals(m.getUserId())) {
					javax.swing.JOptionPane.showMessageDialog(null, "非會員請選現金");
					return;
				} else {
					int totalAmount = Integer.valueOf(finalamount.getText()); // 計算總金額
					int pointsUsed = Integer.parseInt(point.getText()); // 使用的點數
					if (pointsUsed > m.getPoint()) {
						// 如果扣除點數後的金額大於餘額，彈出提示
						javax.swing.JOptionPane.showMessageDialog(null, "點數不足！");
						return;
					}
					int finalAmount = totalAmount - pointsUsed; // 扣除點數後的金額
					CheckoutScreen2 CScreen2 = new CheckoutScreen2(conn, shoppingCar, m, finalAmount, pointsUsed,
							bookOrders, bookOrdersDetails,emp);
					CScreen2.setVisible(true);
					dispose();
				}

			}
		});
		checkoutButton.setBounds(470, 507, 100, 30); // 設定 "結帳" 按鈕的位置與大小
		contentPane.add(checkoutButton);

		JLabel totalvalue = new JLabel("總金額");
		totalvalue.setBounds(360, 363, 100, 15);
		contentPane.add(totalvalue);

		JLabel lblNewLabel = new JLabel("點數折抵");
		lblNewLabel.setBounds(360, 388, 100, 15);
		contentPane.add(lblNewLabel);

		point = new JTextField("0");
		point.setBounds(474, 385, 96, 21);
		contentPane.add(point);
		point.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("現有餘額");
		lblNewLabel_1.setBounds(10, 363, 63, 15);
		contentPane.add(lblNewLabel_1);

		JLabel pppp = new JLabel("現有點數");
		pppp.setBounds(10, 399, 77, 15);
		contentPane.add(pppp);

		totalamount = new JLabel();
		totalamount.setBounds(474, 363, 96, 15);
		contentPane.add(totalamount);

		finalamount = new JLabel();
		finalamount.setBounds(474, 441, 96, 15);
		contentPane.add(finalamount);

		Balance = new JLabel(m.getBalance().toString());
		Balance.setBounds(83, 363, 96, 15);
		contentPane.add(Balance);

		JLabel lblNewLabel_5 = new JLabel(m.getPoint().toString());
		lblNewLabel_5.setBounds(83, 399, 96, 15);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_2 = new JLabel("會員折扣後");
		lblNewLabel_2.setBounds(360, 441, 78, 15);
		contentPane.add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(10, 10, 450, 33);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("1.付款明細確認");
		lblNewLabel_3.setForeground(new Color(0, 0, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("新細明體", Font.BOLD, 14));
		lblNewLabel_3.setBounds(13, 6, 130, 20);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_6 = new JLabel("2.確認");
		lblNewLabel_6.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(215, 6, 112, 20);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("3.訂單完成");
		lblNewLabel_7.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(378, 6, 130, 20);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel(">>>>>");
		lblNewLabel_8.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(156, 9, 46, 15);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel(">>>>>");
		lblNewLabel_9.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(322, 9, 46, 15);
		panel.add(lblNewLabel_9);

		JButton btnNewButton_1 = new JButton("儲值");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			private Component frame;

			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField inputField = new JTextField();
				Object[] message = { "我是用來模擬線上儲值，請輸入正整數金額:", inputField };

				int option = JOptionPane.showConfirmDialog(frame, message, "儲值金額", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					String input = inputField.getText();
					// 檢查是否是正整數
					if (input.matches("\\d+") && Integer.parseInt(input) > 0) {

						m.setBalance(m.getBalance() + Integer.parseInt(input));
						misi.updateMemberBalance(conn, m);
						JOptionPane.showMessageDialog(frame, "您已儲值金額: " + input);
						refreshBalance();// 更新餘額

					} else {
						JOptionPane.showMessageDialog(frame, "請輸入金額！");
					}
				} else {
					System.out.println("取消儲值");
				}

			}
		});
		btnNewButton_1.setBounds(189, 363, 85, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("結算金額");
		lblNewLabel_4.setBounds(360, 413, 78, 15);
		contentPane.add(lblNewLabel_4);

		temp = new JLabel("New label");
		temp.setBounds(473, 416, 97, 15);
		contentPane.add(temp);

		// 前一頁按鈕
		JButton btnNewButton = new JButton("前一頁");
		btnNewButton.setBounds(470, 10, 100, 30);
		contentPane.add(btnNewButton);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		// 初次載入購物車
		reloadShoppingCar();
		recalculateTotal();
	}

	/**
	 * 刷新購物車顯示內容
	 */
	public void reloadShoppingCar() {
		int totalAmount = 0;

		mainPanel.removeAll(); // 移除所有子元件
		mainPanel.revalidate(); // 重置面板佈局

		// 添加標題欄
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titlePanel.setBackground(new Color(224, 255, 255));
		titlePanel.setMaximumSize(new Dimension(550, 30));

		// 標題名稱
		JLabel itemNameTitle = new JLabel("商品名稱");
		itemNameTitle.setBackground(new Color(224, 255, 255));
		itemNameTitle.setPreferredSize(new Dimension(200, 30));
		titlePanel.add(itemNameTitle);

		JLabel itemPriceTitle = new JLabel("價格");
		itemPriceTitle.setPreferredSize(new Dimension(60, 30));
		titlePanel.add(itemPriceTitle);

		JLabel quantityTitle = new JLabel("數量");
		quantityTitle.setPreferredSize(new Dimension(40, 30));
		titlePanel.add(quantityTitle);

		JLabel totalTitle = new JLabel("小計");
		totalTitle.setPreferredSize(new Dimension(100, 30));
		titlePanel.add(totalTitle);

		JLabel actionTitle = new JLabel("操作");
		actionTitle.setPreferredSize(new Dimension(70, 30));
		titlePanel.add(actionTitle);

		mainPanel.add(titlePanel); // 將標題欄添加到主面板

		bookOrdersDetails = new ArrayList<>();
		// 遍歷商品列表，為每個商品添加一行
		for (Products book : shoppingCar) {
			JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 使用 FlowLayout 進行水平佈局
			itemPanel.setMaximumSize(new Dimension(550, 50)); // 限制每個 itemPanel 的寬度和高度

			// 商品名稱
			JLabel itemNameLabel = new JLabel(book.getBookName());
			itemNameLabel.setPreferredSize(new Dimension(200, 30));
			itemPanel.add(itemNameLabel);

			// 價格
			JLabel itemPriceLabel = new JLabel("$" + book.getBestOffer());
			itemPriceLabel.setPreferredSize(new Dimension(60, 30));
			itemPanel.add(itemPriceLabel);

			// 數量
			JTextField quantityField = new JTextField("1");
			quantityField.setPreferredSize(new Dimension(40, 30)); // 設置小一點的輸入框
			itemPanel.add(quantityField);

			// 小計
			JLabel itemTotalLabel = new JLabel("$" + book.getBestOffer()); // 每個 itemPanel 都有自己的小計標籤
			itemTotalLabel.setPreferredSize(new Dimension(100, 30));
			itemPanel.add(itemTotalLabel);

			BookOrdersDetail bookOrdersDetail = new BookOrdersDetail();
			bookOrdersDetail.setBookName(book.getBookName());
			bookOrdersDetail.setBookPrice(book.getBestOffer());
			bookOrdersDetail.setBookAmount(1); // 初始化數量為 1

			String getneworderno = bosi.getneworderno(conn);
			bookOrders.setOrderId(getneworderno);

			bookOrdersDetail.setBookOrdersId(getneworderno); // 設置訂單ID或其他唯一標識

			bookOrdersDetails.add(bookOrdersDetail);

			// 計算初始小計並更新總金額
			int itemSubtotal = book.getBestOffer(); // 計算每個商品的小計
			totalAmount += itemSubtotal; // 累加到總金額

			quantityField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					updateTotal();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					updateTotal();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					updateTotal();
				}

				// 根據新的數量更新小計
				private void updateTotal() {
					try {
						int quantity = Integer.parseInt(quantityField.getText());
						int newSubtotal = book.getBestOffer() * quantity;
						itemTotalLabel.setText("$" + newSubtotal);

						bookOrdersDetail.setBookAmount(quantity);

						recalculateTotal(); // 每次數量變更時，重新計算總金額
					} catch (NumberFormatException ex) {
						itemTotalLabel.setText("$0"); // 無效輸入時小計顯示為0 \
					}
				}
			});

			// 刪除按鈕
			JButton removeButton = new JButton("刪除");
			removeButton.setPreferredSize(new Dimension(70, 30));
			itemPanel.add(removeButton);

			// 刪除按鈕動作
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					shoppingCar.remove(book); // 從購物車列表中移除當前商品
					reloadShoppingCar(); // 刷新購物車顯示
					recalculateTotal(); // 刷新後重新計算總價
				}
			});

			// 將 itemPanel 添加到主面板
			mainPanel.add(itemPanel);
		}

		mainPanel.revalidate(); // 重新驗證面板
		mainPanel.repaint(); // 重繪面板
	}

	private void refreshBalance() {

		Balance.setText(String.valueOf(m.getBalance()));
		Balance.revalidate();
		Balance.repaint();
	}

	private void recalculateTotal() {
		int totalAmount = 0;
		for (Component component : mainPanel.getComponents()) {
			if (component instanceof JPanel) {
				JPanel itemPanel = (JPanel) component;
				if (itemPanel.getComponentCount() > 3) {
					JLabel itemTotalLabel = (JLabel) itemPanel.getComponent(3); // 獲取小計標籤
					String totalText = itemTotalLabel.getText().replace("$", ""); // 去掉$符號
					try {
						totalAmount += Integer.parseInt(totalText); // 累加所有小計
					} catch (NumberFormatException ex) {
						// 忽略無效數字
					}
				}
			}
		}

		// 更新總金額顯示
		totalamount.setText(String.valueOf(totalAmount));
		try {
			// 呼叫方法來計算總金額
			int discountedTotal = DiscountService.calculateDiscountedTotal(totalAmount, point.getText());
			// 更新 UI 顯示扣完點數後的總金額
			temp.setText(String.valueOf(discountedTotal));
			int calaftervip = DiscountService.calaftervip(discountedTotal, m);

			finalamount.setText(String.valueOf(calaftervip));

		} catch (IllegalArgumentException e) {
			// 當點數無效時，顯示錯誤訊息
			temp.setText("點數無效，請輸入數字。");
			finalamount.setText("點數無效，請輸入數字。");
		}
		// 添加 DocumentListener 監聽 point 欄位的變更
		point.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateFinalAmount();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateFinalAmount();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateFinalAmount();
			}

			// 更新扣完點數後的總金額
			private void updateFinalAmount() {
				try {
					// 呼叫 service 層的方法來計算總金額
					int discountedTotal = DiscountService
							.calculateDiscountedTotal(Integer.valueOf(totalamount.getText()), point.getText());

					// 更新 UI 顯示扣完點數後的總金額
					temp.setText(String.valueOf(discountedTotal));
					int calaftervip = DiscountService.calaftervip(discountedTotal, m);
					finalamount.setText(String.valueOf(calaftervip));
				} catch (IllegalArgumentException e) {
					// 當點數無效時，顯示錯誤訊息
					temp.setText("輸入點數有誤。");
					finalamount.setText("輸入點數有誤。");
				}
			}
		});
	}
}
