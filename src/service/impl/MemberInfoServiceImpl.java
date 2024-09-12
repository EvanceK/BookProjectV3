package service.impl;

import dao.impl.MemberInfoDaoImpl;
import model.MemberInfo;
import service.MemberInfoService;

import java.sql.Connection;


public class MemberInfoServiceImpl implements MemberInfoService {
    private static MemberInfoDaoImpl midi = new MemberInfoDaoImpl();

    @Override
    public void addMemberInfo(Connection conn, MemberInfo info) {
        midi.insert(conn,info);
    }

    @Override
    public void deleteMemberInfoByUserId(Connection conn, String userid) {
        midi.deleteByUserId(conn,userid);
    }


    @Override
	public void deleteMemberInfoById(Connection conn, Integer id) {
    	midi.deleteById(conn,id);
		
	}

	@Override
    public void updateMemberInfo(Connection conn, MemberInfo info){
        midi.updateMemberInfo(conn, info);
    }

    @Override
    public MemberInfo getMemberInfoByUserID(Connection conn, String userID) {
        MemberInfo memberInfo = midi.getMemberByUserID(conn, userID);
        return memberInfo;
    }

	@Override
	public MemberInfo getMemberInfoByID(Connection conn, Integer id) {
	     MemberInfo memberInfo = midi.getMemberByID(conn, id);
	        return memberInfo;
	}
    
    
}
