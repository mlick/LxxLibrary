package mlick.lxxlibrary.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.R.integer;

/**
 * String字符串的工具类
 * 
 * @author lxx
 * @date 2014-9-15
 * @time 下午2:39:35
 */
public class StrUtil {

	/**
	 * 判断str是否为空
	 * 
	 * @param str
	 * @return true表示为空 || false 表示不为空
	 */
	public static boolean isEmpty(String str) {
		if (str == null)
			return true;

		if (str.equals("")) {
			return true;
		}

		if (str.length() < 1)
			return true;

		return false;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return true表示不为空 || false 表示为空
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null)
			return false;
		if (str.equals(""))
			return false;
		if (str.length() < 1)
			return false;

		return true;
	}

	/**
	 * 判断字符串是否不为空 || 此种情况不区分大小写
	 * 
	 * @param str
	 * @return true表示为 null字符窜 || false 表示不为null字符窜
	 */
	public static boolean isNull(String str) {

		if (str.equalsIgnoreCase(str)) {
			return true;
		}

		return false;
	}

	/**
	 * String类型转为int类型
	 * 
	 * @param str
	 * @return
	 */
	public static int Str2Int(String str) {
		return Integer.parseInt(str);
	}

	/**
	 * int类型转为String类型
	 * 
	 * @param i
	 * @return
	 */
	public static String Int2Str(int i) {
		return String.valueOf(i);
	}

	/**
	 * URLEnCode 转成 UTF-8
	 * 
	 * @param res
	 * @return 异常返回null
	 */
	public static String URLEncode2UTF8(String res) {
		try {
			return URLEncoder.encode(res, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
