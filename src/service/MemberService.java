package service;

import java.sql.Connection;
import java.util.List;

import model.Member;

public interface MemberService {

    void addMember(Connection conn, Member member);

    void deleteMemberByUserId(Connection conn,String userid);
    void deleteMemberById(Connection conn,Integer id);

    
    void updateMemberAll(Connection conn,Member member);
    void updateMember(Connection conn,Member member);
    void updateMemberBalance(Connection conn,Member member);
    void updateMemberPoint(Connection conn,Member member);
    void updateMemberAccumulatedAmount(Connection conn,Member member);
    void updateMemberVipLevel(Connection conn,Member member);
    void updateMemberPhone(Connection conn,Member member);

    boolean confirmUserId(Connection conn,  Member member);
    Member login(Connection conn,String userid,String password);
    
    String forgetpassword(Connection conn,String userid,String phone);
    
    List<Member> showAllMember(Connection conn);
    Member showMemberById (Connection conn, Integer Id);
    Member showMemberByUserId (Connection conn, String userId);
    Member showMemberByPhone (Connection conn, String phone);

}

