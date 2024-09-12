package model;

import java.io.Serializable;
import java.sql.Date;


public class MemberInfo  implements Serializable {
    private Integer id;
    private String userId;
    private String username;
    private String email;
    private String address1;
    private String address2;
    private Date birthday;


    public MemberInfo() {
    }

    public MemberInfo(String userId, String username, String email, String address1, String address2, Date birthday) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.birthday = birthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
