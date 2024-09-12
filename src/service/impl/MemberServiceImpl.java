package service.impl;

import java.sql.Connection;
import java.util.List;

import dao.impl.MemberDaoImpl;
import model.Member;
import model.VipLevel;
import service.MemberService;

public class MemberServiceImpl implements MemberService {

    private static MemberDaoImpl mdi = new MemberDaoImpl();


    @Override
    public void addMember(Connection conn, Member member) {
        mdi.insert(conn, member);
    }

    @Override
    public void deleteMemberByUserId(Connection conn, String userid) {
        mdi.deleteByUserId(conn, userid);
    }

    @Override
    public void deleteMemberById(Connection conn, Integer id) {
        mdi.deleteById(conn, id);
    }

    @Override
	public void updateMember(Connection conn, Member member) {
		mdi.updateMember(conn, member);
	}

	@Override
	public void updateMemberBalance(Connection conn, Member member) {
		mdi.updateBalance(conn, member);
	}

	@Override
	public void updateMemberPoint(Connection conn, Member member) {
		mdi.updatePoint(conn, member);
	}

	@Override
	public void updateMemberAccumulatedAmount(Connection conn, Member member) {
		mdi.updateAccumulatedAmount(conn, member);
	}
	
	@Override
	public void updateMemberVipLevel(Connection conn, Member member) {
		if(member.getAccumulatedAmount()>=3000) {
			member.setVipLevel(VipLevel.gold);
		}else if(member.getAccumulatedAmount()>=1500) {
			member.setVipLevel(VipLevel.silver);
		}
		mdi.updateVipLevel(conn, member);
		
	}
	
	@Override
	public void updateMemberPhone(Connection conn, Member member) {
		mdi.updatePhone(conn, member);
		
	}
	
	


	@Override
	public void updateMemberAll(Connection conn, Member member) {
		this.updateMember(conn, member);
		this.updateMemberBalance(conn, member);
		this.updateMemberPoint(conn, member);
		this.updateMemberAccumulatedAmount(conn, member);
		this.updateMemberVipLevel(conn, member);
	}

	@Override
    public boolean confirmUserId(Connection conn, Member member) {
        Member member1 = mdi.getMemberByUserID(conn, member.getUserId());
        boolean x = false;
        if (member1 != null) {
            x = true;         //有人用
        }
        return x; //沒人用
    }
	
	  public boolean confirmPhone(Connection conn, Member member) {
	        Member member1 = mdi.getMemberByPhone(conn, member.getPhone());
	        boolean x = false;
	        if (member1 != null) {
	            x = true;         //有人用
	        }
	        return x; //沒人用
	    }
	

    @Override
    public Member login(Connection conn,String userid,String password) {
        Member member = mdi.getMemberByUserID(conn, userid);
        
        if (member == null){
            System.out.println("帳號錯誤");
        }else if(password.equals(member.getPassword())){
            System.out.println("登入成功");
        }else{
            System.out.println("密碼錯誤");
            member=null;
        }
        return member;
    }
    
    @Override
    public String forgetpassword(Connection conn,String userid,String phone) {
        Member member = mdi.getMemberByUserID(conn, userid);
        
        if (member == null){
        	return "帳號錯誤";
        }else if(phone.equals(member.getPhone())){
        	return "驗證正確，你的密碼是"+member.getPassword();
        }else{
        	return "電話錯誤";
        }
   
    }

    
    public List<Member> showAllMember(Connection conn){
    	List<Member> all = mdi.getAll(conn);
    	return all;
    }

	@Override
	public Member showMemberById(Connection conn, Integer id) {
		Member memberById = mdi.getMemberById(conn, id);
		return memberById;
	}

	@Override
	public Member showMemberByUserId(Connection conn, String userId) {
		Member memberByUserID = mdi.getMemberByUserID(conn, userId);
		return memberByUserID;
	}

	@Override
	public Member showMemberByPhone(Connection conn, String phone) {
		Member memberByPhone = mdi.getMemberByPhone(conn, phone);
		return memberByPhone;
	}


    
}
