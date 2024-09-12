package dao.impl;

import dao.BaseDao;
import dao.MemberInfoDao;
import model.MemberInfo;

import java.sql.Connection;

/**
 * ClassName: MemberInfoImpl
 * ClassName: com.evance.hw3.dao.impl
 * Description:
 *
 * @Author: Evance
 * @Create: 2024/9/1-下午 04:01
 */
public class MemberInfoDaoImpl extends BaseDao implements MemberInfoDao {
    @Override
    public void insert(Connection conn, MemberInfo info) {
        String sql = "insert into memberinfo(userId,username,email, address1, address2,birthday )values(?,?,?,?,?,?)";
        update(conn, sql, info.getUserId(), info.getUsername(),info.getEmail(),info.getAddress1(),info.getAddress2(),info.getBirthday());

    }

    @Override
    public void updateMemberInfo(Connection conn, MemberInfo info) {
        String sql = "update memberinfo set email=?,address1=?,address2=?, birthday=? where userid =?";
        update(conn, sql, info.getEmail(), info.getAddress1(), info.getAddress2(),info.getBirthday(),info.getUserId());
    }

    @Override
    public void deleteByUserId(Connection conn, String userId) {
        String sql = "delete from memberinfo where userId = ?";
        update(conn, sql, userId);
    }
    

    @Override
	public void deleteById(Connection conn, Integer id) {
        String sql = "delete from memberinfo where id = ?";
        update(conn, sql, id);
    }

	@Override
    public MemberInfo getMemberByUserID(Connection conn, String userID) {
        String sql = "select id,userId,username,email,address1,address2,birthday from memberinfo where userId =?";
        MemberInfo memberInfo = getInstance(conn, MemberInfo.class, sql, userID);
        return memberInfo;   
        }

	@Override
	public MemberInfo getMemberByID(Connection conn, Integer id) {
        String sql = "select id,userId,username,email,address1,address2,birthday from memberinfo where id =?";
        MemberInfo memberInfo = getInstance(conn, MemberInfo.class, sql, id);
        return memberInfo;   
        }
	
	
}
