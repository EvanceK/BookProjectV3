package dao.impl;

import dao.BaseDao;
import dao.MemberDao;
import model.Member;

import java.sql.Connection;
import java.util.List;

public class MemberDaoImpl extends BaseDao implements MemberDao{

	    @Override
	    public void insert(Connection conn, Member mem) {
	        String sql = "insert into member(userId,password, username,balance, point, accumulatedAmount,vipLevel,phone)values(?,?,?,?,?,?,?,?)";
	        update(conn, sql, mem.getUserId(), mem.getPassword(),mem.getUsername(),mem.getBalance(),mem.getPoint(),mem.getAccumulatedAmount(),mem.getVipLevel(),mem.getPhone());
	    }

	    @Override
	    public void deleteById(Connection conn, Integer id) {
	        String sql = "delete from member where id = ?";
	        update(conn, sql, id);
	    }

	    @Override
	    public void deleteByUserId(Connection conn, String userId) {
	        String sql = "delete from member where userId = ?";
	        update(conn, sql, userId);
	    }

	    @Override
	    public void updateMember(Connection conn, Member mem) {
	        String sql = "update member set userId=?,password=?,username=?,phone=? where id =?";
	        update(conn, sql, mem.getUserId(), mem.getPassword(), mem.getUsername(),mem.getPhone(),mem.getId());
	    }

	    @Override
	    public Member getMemberById(Connection conn, Integer id) {
	        String sql = "select id,userId,password,username,balance,point,accumulatedAmount,vipLevel,phone from member where id =?";
	        Member member = getInstance(conn, Member.class, sql, id);
	        return member;
	    }

	    @Override
	    public Member getMemberByUserID(Connection conn, String userID) {
	        String sql = "select id,userId,password,username,balance,point,accumulatedAmount,vipLevel,phone from member where userId =?";
	        Member member = getInstance(conn, Member.class, sql, userID);
	        return member;
	    }
	    
	    @Override
	    public Member getMemberByPhone(Connection conn, String phone) {
	        String sql = "select id,userId,password,username,balance,point,accumulatedAmount,vipLevel,phone from member where phone =?";
	        Member member = getInstance(conn, Member.class, sql, phone);
	        return member;
	    }

	    @Override
	    public List<Member> getAll(Connection conn) {
	        String sql = "select id,userId,password,username,balance,point,accumulatedAmount,vipLevel,phone from member";
	        List<Member> memberList = getForList(conn, Member.class, sql);
	        return memberList;
	    }

	    @Override
	    public Long getCount(Connection conn) {
	        return null;
	    }



	    @Override
	    public void updateBalance(Connection conn, Member mem) {
	        String sql = "update member set balance=? where id =?";
	        update(conn, sql, mem.getBalance(),mem.getId());
	    }

	    @Override
	    public void updatePoint(Connection conn, Member mem) {
	        String sql = "update member set point=? where id =?";
	        update(conn, sql, mem.getPoint(),mem.getId());
	    }

	    @Override
	    public void updateAccumulatedAmount(Connection conn, Member mem) {
	        String sql = "update member set accumulatedAmount=? where id =?";
	        update(conn, sql, mem.getAccumulatedAmount(),mem.getId());
	    }
	    
	    @Override
	    public void updateVipLevel(Connection conn, Member mem) {
	        String sql = "update member set vipLevel=? where id =?";
	        update(conn, sql, mem.getVipLevel(),mem.getId());
	    }

		@Override
		public void updatePhone(Connection conn, Member mem) {
	        String sql = "update member set phone=? where id =?";
	        update(conn, sql, mem.getPhone(),mem.getId());
			
		}
	    
	    
	}
