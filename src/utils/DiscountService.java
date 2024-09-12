package utils;

import model.Member;
import model.VipLevel;

public class DiscountService {
	
	   // 計算扣除點數後的總金額
    public static int calculateDiscountedTotal(int totalAmount, String points) throws IllegalArgumentException {
        int pointValue;

        // 如果用戶沒有輸入點數，默認為 0
        if (points == null || points.isEmpty()) {
            pointValue = 0;
        } else {
            try {
                pointValue = Integer.parseInt(points); // 取得用戶輸入的點數
                if (pointValue<0) {
                	  throw new IllegalArgumentException();
                };
                
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("輸入點數無效，請輸入數字。");
            }
        }

        // 扣除點數後的總金額
        int discountedTotal = totalAmount - pointValue;
        
        // 確保扣完點數後的總金額不會是負數
        if (discountedTotal < 0) {
            discountedTotal = 0;
        }
        return discountedTotal;
    }
    
	   // 計算扣除點數後的總金額
    public static int calaftervip(int totalAmount,Member m) throws IllegalArgumentException {

     int discountedTotal = totalAmount;  
     if(m.getVipLevel()==VipLevel.gold) {
     	discountedTotal=(int) (discountedTotal*0.90);
     }else if(m.getVipLevel()==VipLevel.silver) {
     	discountedTotal=(int) (discountedTotal*0.95);
     }
     return discountedTotal;
 }
}