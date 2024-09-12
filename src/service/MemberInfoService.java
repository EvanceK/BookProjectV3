package service;

import model.Member;
import model.MemberInfo;

import java.sql.Connection;

/**
 * ClassName: MemberInfoService
 * ClassName: com.evance.hw3.service
 * Description:
 *
 * @Author: Evance
 * @Create: 2024/9/1-下午 04:21
 */
public interface MemberInfoService {

    void addMemberInfo(Connection conn, MemberInfo info);

    void deleteMemberInfoByUserId(Connection conn,String userid);
    void deleteMemberInfoById(Connection conn,Integer id);
    void updateMemberInfo(Connection conn, MemberInfo info);
    
    MemberInfo getMemberInfoByUserID(Connection conn, String userID);
    MemberInfo getMemberInfoByID(Connection conn, Integer id);

}
