package dao;


import model.MemberInfo;

import java.sql.Connection;


public interface MemberInfoDao{

    //新增 Create
    public abstract void insert(Connection conn, MemberInfo info);

    //更新 Update
    public abstract void updateMemberInfo(Connection conn, MemberInfo info);//更新一般資訊 帳號 密碼 使用者名稱

    //刪除 Delete
    public abstract void deleteByUserId(Connection conn, String userId);
    
    public abstract void deleteById(Connection conn, Integer id);


    //查詢select  Read
    public abstract MemberInfo getMemberByUserID(Connection conn, String userID);

    public abstract MemberInfo getMemberByID(Connection conn, Integer id);
}
