package model;

import java.time.LocalDateTime;
public class BookOrders {
	 private Integer id;
	    private String orderId;
	    private String userId;
	    private Integer usePoint;
	    private Integer finalAmount;
	    private Integer newPoint;
	    private LocalDateTime  orderTime;
	    private String employeesId;

	    public BookOrders() {
	    }

	    public BookOrders(Integer id, String orderId, String userId,Integer usePoint, Integer finalAmount, Integer newPoint, LocalDateTime orderTime, String employeesId) {
	        this.id = id;
	        this.orderId = orderId;
	        this.userId = userId;
	        this.usePoint = usePoint;
	        this.finalAmount = finalAmount;
	        this.newPoint = newPoint;
	        this.orderTime = orderTime;
	        this.employeesId = employeesId;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	  
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
	    public Integer getUsePoint() {
	        return usePoint;
	    }

	    public void setUsePoint(Integer usePoint) {
	        this.usePoint = usePoint;
	    }

	    public Integer getFinalAmount() {
	        return finalAmount;
	    }

	    public void setFinalAmount(Integer finalAmount) {
	        this.finalAmount = finalAmount;
	    }

	    public Integer getNewPoint() {
	        return newPoint;
	    }

	    public void setNewPoint(Integer newPoint) {
	        this.newPoint = newPoint;
	    }

	    public LocalDateTime getOrderTime() {
	        return orderTime;
	    }

	    public void setOrderTime(LocalDateTime dateTimeToDatabase) {
	    	
	    	
	    	
	        this.orderTime = dateTimeToDatabase;
	    }

	    public String getEmployeesId() {
	        return employeesId;
	    }

	    public void setEmployeesId(String employeesId) {
	        this.employeesId = employeesId;
	    }

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		@Override
		public String toString() {
			return "\n購物明細資料"+
					"\n訂單編號\t"+ orderId + 
					"\n使用者名稱\t" + userId +
					"\n價格\t"+(usePoint+finalAmount)+
					"\n此次使用的點數折抵\t" + usePoint + 
					"\n最終價格\t"+ finalAmount + 
					"\n此次購入得到新點數為\t" + newPoint + 
					"\n下單時間\t" + orderTime + 
					"\n服務員工\t"+ employeesId;
		}

	
	    
}
