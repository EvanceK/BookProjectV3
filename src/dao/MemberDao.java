package dao;


import java.sql.Connection;
import java.util.List;

import model.Member;

public interface MemberDao {

    //新增 Create
    //將customer對象，添加到數據庫中
    public abstract void insert(Connection conn, Member mem);


    //刪除 Delete
    //將指定id的紀錄，從數據庫中刪除
    public abstract void deleteById(Connection conn, Integer id);
    //將指定userName的紀錄，從數據庫中刪除
    public abstract void deleteByUserId(Connection conn, String userId);


    //更新 Update
    //針對Member對象，修改數據表中的紀錄
    public abstract void updateMember(Connection conn, Member mem);//更新一般資訊 帳號 密碼 使用者名稱
    public abstract void updateBalance(Connection conn, Member mem);//更新餘額
    public abstract void updatePoint(Connection conn, Member mem);//更新點數
    public abstract void updateAccumulatedAmount(Connection conn, Member mem);//更新累積金額
    public abstract void updateVipLevel(Connection conn, Member mem);
    public abstract void updatePhone(Connection conn, Member mem);

    //查詢select  Read
    //查詢指定id，數據表中的紀錄
    public abstract Member getMemberById(Connection conn, Integer id);
    public abstract Member getMemberByUserID(Connection conn, String userID);
    public abstract Member getMemberByPhone(Connection conn, String phone);
    //查詢表中的所有紀錄
    public abstract List<Member> getAll(Connection conn);



    //返回數據表中 數據的條目數
    public abstract Long getCount(Connection conn);

}
