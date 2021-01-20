package com.wx.base.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: MathUtils  
* @Description: 运算Util
* @author Mr_dong  
* @date 2019年3月5日  
*
 */
public class MathUtils {

	private static Logger log = LoggerFactory.getLogger(MathUtils.class);
	
	/**
	 * 四舍五入,保留两位小数
	 * @param d
	 * @return
	 */
	 public static double formatDouble2(double d) {
		// 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
		 BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
		 return bg.doubleValue();
	 }
	 
	 
	 /**
	  * 	除法运算
	  * @param num
	  * @param num1
	  * @return
	  */
	 public static double dividerNum(Integer num,Integer num1) {
		 try {
			 return new BigDecimal((float)num/num1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 }catch (Exception e) {
			 return 0.00;
		}
	 }
	 
	 
	 /**
	  * 	除法运算
	  * @param num
	  * @param num1
	  * @return
	  */
	 public static double dividerNum(Double num,Double num1) {
		 try {
			 return formatDouble2(num/num1);
		 }catch (Exception e) {
			 return 0.00;
		}
	 }
	 
	 
	 /**
	  * 	除法运算
	  * @param num
	  * @param num1
	  * @return
	  */
	 public static double dividerNum(Integer num,Double num1) {
		 try {
			 return new BigDecimal((float)num/num1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 }catch (Exception e) {
			 return 0.00;
		}
	 }
	 
	 
	 /**
	  * 	除法运算
	  * @param num
	  * @param num1
	  * @return
	  */
	 public static double dividerNum(Double num,Integer num1) {
		 try {
			 return formatDouble2(num/num1);
		 }catch (Exception e) {
			 return 0.00;
		}
	 }
}
