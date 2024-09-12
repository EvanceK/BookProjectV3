package controller.employee;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.SystemUI;
import controller.product.ShoppingCarUI;
import model.Employees;
import model.Member;
import model.Products;
import model.VipLevel;
import service.impl.MemberServiceImpl;
import service.impl.ProductsServiceImpl;
import utils.Clock;
import utils.ImageDisplay;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Employeesystem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static MemberServiceImpl msi = new MemberServiceImpl();
	private static Connection conn;
	private static Member m;

	private int currentPage = 1;
	private List<Products> currentBooks;
	private ProductsServiceImpl psi = new ProductsServiceImpl();
	private JPanel forbooklist;
	protected JTextArea bookdetails, description;
	private Products selectedBook;
	private int shoppingcarcount = 0;
	protected List<Products> shoppingCar;
	private JLabel timelabel;
	protected static Employees emp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Employeesystem frame = new Employeesystem(conn, emp);
					 frame.setTitle("書店首頁");

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
	public Employeesystem(Connection conn, Employees emp) {
		setTitle("首頁");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 612);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 250, 154));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ImageIcon windowIcon = new ImageIcon(SystemUI.class.getResource("/resources/gary-1.png"));
		setIconImage(windowIcon.getImage());

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 購物車加入時增加筆數
		shoppingCar = psi.CreateShoppingCar();

		if (shoppingCar == null) {
			shoppingcarcount = 0;
		} else {
			shoppingcarcount = shoppingCar.size();

		}
		JLabel carcount = new JLabel(String.valueOf(shoppingcarcount));
		carcount.setFont(new Font("新細明體", Font.BOLD, 14));
		carcount.setHorizontalAlignment(SwingConstants.CENTER);
		carcount.setBounds(769, 3, 38, 46);
		contentPane.add(carcount);

		// 監聽car
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				carcount.setText(String.valueOf(shoppingcarcount));
				carcount.revalidate();
				carcount.repaint();
			}
		});

		JButton btnNewButton = new JButton("回登入頁面");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SystemUI sysui = new SystemUI();
				sysui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 3, 145, 46);
		contentPane.add(btnNewButton);

	
		forbooklist = new JPanel();
		forbooklist.setBackground(Color.white);
		forbooklist.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		forbooklist.setLayout(new GridLayout(3, 3, 10, 10));
		forbooklist.setBounds(10, 110, 431, 406);
		contentPane.add(forbooklist, BorderLayout.CENTER);

		loadBooks(conn, currentPage);

		JPanel paginationPanel = new JPanel();
		paginationPanel.setBackground(new Color(0, 250, 154));
		paginationPanel.setBounds(10, 526, 431, 39);
		contentPane.add(paginationPanel);

		JButton prevButton = new JButton("上頁");
		prevButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (currentPage > 1) {
					currentPage--;
					loadBooks(conn, currentPage);
				}

			}
		});
		paginationPanel.add(prevButton);

		JButton nextButton = new JButton("下頁");
		nextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentPage++;
				loadBooks(conn, currentPage);
			}
		});
		paginationPanel.add(nextButton);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 250, 154));
		panel.setBounds(451, 110, 501, 455);
		contentPane.add(panel);
		panel.setLayout(null);

		bookdetails = new JTextArea();
		bookdetails.setBackground(new Color(255, 255, 255));
		bookdetails.setBounds(10, 0, 326, 204);
		panel.add(bookdetails);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 207, 481, 248);
		panel.add(scrollPane);

		description = new JTextArea();
		description.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(description);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);

		JButton btnNewButton_4 = new JButton("直接購買");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedBook == null) {
					JOptionPane.showMessageDialog(null, "請先選擇一本書");
				} else {
					if (shoppingCar.contains(selectedBook)) {
						JOptionPane.showMessageDialog(null, "此書已在清單中");
					} else {
						psi.addShoppingCar(shoppingCar, selectedBook);
						shoppingcarcount = shoppingCar.size();
						carcount.setText(String.valueOf(shoppingcarcount));
						carcount.revalidate();
						carcount.repaint();
						CheckoutScreen cks = new CheckoutScreen(conn, shoppingCar, emp,m);
						cks.setVisible(true);
					}
				}
			}
		});
		btnNewButton_4.setBounds(374, 105, 91, 68);
		panel.add(btnNewButton_4);
	
		JButton btnNewButton_3 = new JButton("加入購物車");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(shoppingCar.contains(selectedBook)) {
					  JOptionPane.showMessageDialog(null, "此書已在清單中");
				}else {
					psi.addShoppingCar(shoppingCar, selectedBook);
					shoppingcarcount=shoppingCar.size();
					carcount.setText(String.valueOf(shoppingcarcount));
					carcount.revalidate();
					carcount.repaint();
				}
	
			}
		});
		btnNewButton_3.setBounds(374, 27, 93, 68);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("購物車");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				CheckoutScreen cks = new CheckoutScreen(conn, shoppingCar, emp,m);
				cks.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(662, 3, 109, 46);
		contentPane.add(btnNewButton_1);
		JLabel time = new JLabel("");
		time.setBounds(240, 10, 120, 23);

		Timer timer = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Clock.Clock(time);
		
	}
		} );
		timer.start();
		contentPane.add(time);
		timelabel = new JLabel("現在時間:");
		timelabel.setBounds(186, 14, 96, 15);
		contentPane.add(timelabel);
	
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(166, 59, 103, 41);
		contentPane.add(lblNewLabel);
			
			
	
	
		JButton btnNewButton_2 = new JButton("查詢會員");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        // 顯示輸入框並獲取用戶輸入的電話號碼
		        String phoneNumber = JOptionPane.showInputDialog(Employeesystem.this, "請輸入會員電話號碼:", "查詢會員", JOptionPane.QUESTION_MESSAGE);

		        // 檢查用戶是否輸入了電話號碼
		        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
		            // 使用輸入的電話號碼進行查詢
		            Member showMemberByPhone = msi.showMemberByPhone(conn, phoneNumber);
		            if(showMemberByPhone!=null) {
			            m=showMemberByPhone;
			        	lblNewLabel.setText(m.getUsername());
		            }else {
		            	 //無優惠進入
			            Member notmember = msi.showMemberByUserId(conn, "notmember");
			            m=notmember;
			            lblNewLabel.setText(m.getUsername());
			            m.setBalance(0);
			            m.setPoint(0);
			            m.setVipLevel(VipLevel.normal);
		            }
		            
		        } else {
		            // 用戶未輸入電話號碼或取消了輸入
		            JOptionPane.showMessageDialog(Employeesystem.this, "請輸入有效的電話號碼。", "錯誤", JOptionPane.ERROR_MESSAGE);
		          
		        }
		      
				
				
		        
		    }
		});
		btnNewButton_2.setBounds(10, 59, 145, 41);
		contentPane.add(btnNewButton_2);
		

	
	
	}

	protected void loadBooks(Connection conn, int pageNumber) {
		forbooklist.removeAll();
		currentBooks = psi.showbyLimit6(conn, 9, pageNumber);

		   // 檢查是否還有商品
	    if (currentBooks == null || currentBooks.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "已經是最後一頁了", "信息", JOptionPane.INFORMATION_MESSAGE);
	        // 如果沒有商品回到上一頁
	        if (currentPage > 1) {
	            currentPage--;
	        }
	        return;
	    }
		
		
		for (Products book : currentBooks) {
			JPanel booklist = new JPanel();
			booklist.setLayout(new BorderLayout());
			booklist.setBackground(Color.white);

			String imagePath = "/resources/" + book.getBookName() + ".png";
			ImageIcon imageIcon = ImageDisplay.loadImageIcon(imagePath, 90, 90);

			JLabel titleLabel = new JLabel(book.getBookName());
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel coverLabel = new JLabel(imageIcon);
			coverLabel.setPreferredSize(new Dimension(100, 100));
			JLabel priceLabel = new JLabel("價格: " + book.getBestOffer());
			priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			priceLabel.setForeground(Color.red);

			booklist.add(titleLabel, BorderLayout.NORTH);
			booklist.add(coverLabel, BorderLayout.CENTER);
			booklist.add(priceLabel, BorderLayout.SOUTH);

			forbooklist.add(booklist);

			coverLabel.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					// 當滑鼠進入封面時，將游標改變為手型
					coverLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					// 放大圖片
					ImageIcon largeIcon = ImageDisplay.loadImageIcon(imagePath, 100, 100); // 放大至 120x120
					coverLabel.setIcon(largeIcon);
					coverLabel.revalidate();
					coverLabel.repaint();

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// 當滑鼠離開封面時，將游標改回預設形狀
					coverLabel.setCursor(Cursor.getDefaultCursor());
					coverLabel.setIcon(imageIcon); // 回復至 100x100
					coverLabel.revalidate();
					coverLabel.repaint();

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// 滑鼠點擊封面 出現詳細資料
					StringBuilder sB = new StringBuilder();
					sB.append(String.format("書名: %s\n", book.getBookName()));
					sB.append(String.format("原文書名: %s\n", book.getBookNameEng()));
					sB.append(String.format("作者: %s\n", book.getAuthor()));
					sB.append(String.format("原文作者: %s\n", book.getOriginalAuthor()));
					sB.append(String.format("譯者: %s\n", book.getTranslated()));
					sB.append(String.format("出版社: %s\n", book.getPublisher()));
					sB.append(String.format("出版日期: %s\n", book.getPublicationDate()));
					sB.append(String.format("語言: %s\n", book.getLanguage()));
					sB.append(String.format("定價: %s\n", book.getListPrice()));
					sB.append(String.format("優惠價: %s\n", book.getBestOffer()));
					sB.append(String.format("ISBN: %s", book.getIsbn()));

					bookdetails.setText(sB.toString());
					description.setText("內容簡介: \n" + book.getDescription());
					selectedBook = book;
				}
			});
		}
		forbooklist.revalidate();
		forbooklist.repaint();
	}
}