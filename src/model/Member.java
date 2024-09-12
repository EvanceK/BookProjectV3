package model;

import java.io.Serializable;


public class Member implements Serializable {

    private Integer id;
    private String userId;
    private String username;
    private String password;
    private Integer balance;
    private Integer point;

    private Integer accumulatedAmount;
    private VipLevel vipLevel = VipLevel.normal;
    private String phone;


    public Member() {
    	 
    }

    //註冊使用
    public Member(String userId, String password,String phone) {
        this.userId = userId;
        this.password = password;
        this.username = userId;		//初始化 username = userId for db (之後用戶再自己改)
        this.balance=0;
        this.point=0;
        this.accumulatedAmount=0;
        this.vipLevel = VipLevel.normal;
        this.phone=phone;			//之後提供忘記密碼使用
    }

    //一般資訊
    public Member(Integer id, String userId, String username, String password, Integer balance, Integer point, Integer accumulatedAmount,VipLevel vip, String phone) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.point = point;
        this.accumulatedAmount = accumulatedAmount;
        this.vipLevel = vip;
        this.phone=phone;
        
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getAccumulatedAmount() {
        return accumulatedAmount;
    }

    public void setAccumulatedAmount(Integer accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }
    
    

    public VipLevel getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(VipLevel vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "\n儲值餘額\t" + balance+ "\n點數餘額\t" + point + 
				"\n累積消費\t" + accumulatedAmount
				+"\n會員等級\t"+ this.getVipLevel();
	}


}