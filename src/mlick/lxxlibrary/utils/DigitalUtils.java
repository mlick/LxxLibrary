package mlick.lxxlibrary.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字工具类
 * 
 * @author yuegy
 * 
 */
public class DigitalUtils {

	/**
	 * 保留2位小数
	 */
	private static final int DEF_DIV_SCALE = 2;
	/**
	 * 保留1位小数
	 */
	private static final int DEF_DIV_ONE = 1;

	/**
	 * 四舍五入 保留2位
	 * 
	 * @param f
	 * @return
	 */
	public static double getRoundDef(double f) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		return f1;
	}

	/**
	 * 四舍五入 保留2位
	 * 
	 * @param f
	 * @return
	 */
	public static String getRoundDefStr(double f) {
		double f1 = getRoundDef(f);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(f1);
	}

	/**
	 * 四舍五入 保留1位
	 * 
	 * @param f
	 * @return
	 */
	public static String getRoundDefStr1(double f) {
		double f1 = getRoundDef(f);
		double f2 = getRoundDefPointOne(f1);
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(f2);
	}

	/**
	 * 四舍五入 保留整数
	 * 
	 * @param f
	 * @return
	 */
	public static String getRoundDefStr2(double f) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		DecimalFormat df = new DecimalFormat("0");
		return df.format(f1);
		// return Math.rint(f)
	}

	/**
	 * 字符串转int
	 * 
	 * @param inStr
	 * @return
	 */
	public static int strToInt(String inStr) {
		int retVal = 0;
		if (StringUtils.isBlank(inStr)) {
			return retVal;
		} else if (isNumberic(inStr)) {
			retVal = Integer.parseInt(inStr);
		}
		return retVal;
	}

	/**
	 * 字符串转float
	 * 
	 * @param inStr
	 * @return
	 */
	public static float strToFloat(String inStr) {
		float retVal = 0;
		if (StringUtils.isBlank(inStr)) {
			return retVal;
		} else if (isNumberic(inStr)) {
			retVal = Float.parseFloat(inStr);
		}
		return retVal;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isNumberic(String num) {
		return (null == num || num.length() <= 0 || num.matches("\\d{1,}")) ? true
				: false;
	}

	/**
	 * * 两个Double数相加 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.add(b2).doubleValue());
	}

	/**
	 * * 两个Double数相减 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.subtract(b2).doubleValue());
	}

	/**
	 * * 两个Double数相乘 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.multiply(b2).doubleValue());
	}

	/**
	 * * 两个Double数相除 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1
				.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue());
	}

	/**
	 * * 两个Double数相除，并保留scale位小数 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @param scale
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue());
	}

	/**
	 * 四舍五入 保留1位
	 * 
	 * @param f
	 * @return
	 */
	public static double getRoundDefPointOne(double f) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(DEF_DIV_ONE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		return f1;
	}

	/**
	 * 四舍五入 保留1位
	 * 
	 * @param f
	 * @return
	 */
	public static float getRoundDefPointOne(float f) {
		BigDecimal b = new BigDecimal(f);
		float f1 = b.setScale(DEF_DIV_ONE, BigDecimal.ROUND_HALF_UP)
				.floatValue();
		return f1;
	}

}
