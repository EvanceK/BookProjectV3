package dao;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import utils.DbConnection;


public abstract class BaseDao {

	// 通用的增 刪 改 操作
	public void update(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		try {
			// 1.編譯sql語句
			ps = conn.prepareStatement(sql);
			// 2.填充佔位符//sql中佔位符數目等於可變形參長度
			for (int i = 0; i < args.length; i++) {
				
				 // 如果是 enum 類型，使用 name() 方法轉換為字串
	            if (args[i] instanceof Enum) {
	                ps.setString(i + 1, ((Enum<?>) args[i]).name());
	            } 
	            // 如果是 LocalDateTime 類型，轉換為 Timestamp
	            else if (args[i] instanceof LocalDateTime) {
	                LocalDateTime dateTime = (LocalDateTime) args[i];
	                ps.setTimestamp(i + 1, Timestamp.valueOf(dateTime));
	            }
                // 其他類型直接使用 setObject
                else {
                    ps.setObject(i + 1, args[i]);
                }
            }
			// 3.執行
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.關閉資源
			DbConnection.closeResource(null, ps);
		}

	}

	// 通用的 查詢 操作 2.0
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object... args) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			// 執行，獲取結果集
			rs = ps.executeQuery();
			// 獲取結果集元數據
			ResultSetMetaData rsmd = rs.getMetaData();
			// 獲取列數
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				T t = clazz.getDeclaredConstructor().newInstance();
				for (int j = 0; j < columnCount; j++) {
					// 獲取每個列的列值
					Object columnValue = rs.getObject(j + 1);
					
					
					// 獲取列的別名 getColumnLabel
					String columnLabel = rsmd.getColumnLabel(j + 1);
					// 通過反射指定的名columnName付給指定的columnValue
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);// 可能是私有的確保能訪問
					
					if (field.getType().isEnum()) {
			            // 假設欄位是 VipLevel 類型，將字串轉換為對應的 enum
			            Class<Enum> enumType = (Class<Enum>) field.getType();
			            if (columnValue != null) {
	                        // 將字串轉換為對應的 enum
	                        Object enumValue = Enum.valueOf(enumType, columnValue.toString());
	                        field.set(t, enumValue);
	                    } else {
	                        field.set(t, null); // 處理 null 值
	                    }
	                } else {
	                    // 非 enum 欄位直接賦值
	                    field.set(t, columnValue);
	                }
				}
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeResource(null, ps, rs);
		}
		return null;
	}

	// 通用的查詢操作 2.0 ver (考慮事務) 返回多個訊息的操作
	public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object... args) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			// 執行，獲取結果集
			rs = ps.executeQuery();
			// 獲取結果集元數據
			ResultSetMetaData rsmd = rs.getMetaData();
			// 獲取列數
			int columnCount = rsmd.getColumnCount();
			// 創建結合對象，用來存放多條紀錄
			ArrayList<T> list = new ArrayList<>();
			// 查詢多條紀錄
			while (rs.next()) {
				T t = clazz.getDeclaredConstructor().newInstance();
				for (int j = 0; j < columnCount; j++) {
					// 獲取每個列的列值
					Object columnValue = rs.getObject(j + 1);
					// 獲取列的別名 getColumnLabel
					// String columnLabel = rsmd.getColumnName(j + 1);
					String columnLabel = rsmd.getColumnLabel(j + 1);
					// 通過反射指定的名columnName付給指定的columnValue
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);// 可能是私有的確保能訪問
					
					
	        	    // 如果欄位是 enum 類型
	                if (field.getType().isEnum()) {
	                    @SuppressWarnings("unchecked")
	                    Class<? extends Enum<?>> enumType = (Class<? extends Enum<?>>) field.getType();
	                    // 將字符串值轉換為對應的 enum 類型值
	                    Enum<?> enumValue = Enum.valueOf((Class<Enum>) enumType, columnValue.toString());
	                    field.set(t, enumValue);
	                } 
	                // 如果欄位是 Timestamp 類型
	                else if (field.getType() == LocalDateTime.class && columnValue instanceof Timestamp) {
	                    LocalDateTime localDateTime = ((Timestamp) columnValue).toLocalDateTime();
	                    field.set(t, localDateTime);
	                } else {
		                    field.set(t, columnValue);
		                
		                }
				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeResource(null, ps, rs);
		}
		return null;
	}

	// 用於查詢特殊質的通用方法
	public <E> E getValue(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);

			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return (E) rs.getObject(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbConnection.closeResource(null, ps, rs);
		}
		return null;
	}
}
