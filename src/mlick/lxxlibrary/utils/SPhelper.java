package mlick.lxxlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences 工具类
 * 
 * @author lxx
 * @date 2014-9-15
 * @time 下午2:30:39
 */
public class SPhelper {

//	private static SPhelper sPhelper; // 暂时没有使用单例模式
//
//	public static SPhelper getInstance() {
//		if (sPhelper == null) {
//			sPhelper = new SPhelper();
//		}
//		return sPhelper;
//	}

	private static SharedPreferences sp;
	private static String SPNAME = "lxxsp"; // 缺省值

    private static void initial(Context mContext, String fileName) {
		sp = mContext.getSharedPreferences(fileName, 0);
	}

	/**
	 * 默认放置的文件为lxxsp
	 * 
	 * @param mContext
	 * @param key
	 * @param value
	 */
	public static void save(Context mContext, String key, Object value) {
		initial(mContext, SPNAME);
		if (value instanceof String) {
			sp.edit().putString(key, (String) value).commit();
		} else if (value instanceof Boolean) {
			sp.edit().putBoolean(key, (Boolean) value).commit();
		} else if (value instanceof Integer) {
			sp.edit().putInt(key, (Integer) value).commit();
		} else if (value instanceof Float) {
			sp.edit().putFloat(key, (Float) value).commit();
		} else if (value instanceof Long) {
			sp.edit().putLong(key, (Long) value).commit();
		}
	}

	/**
	 * 自定义文件
	 * 
	 * @param mContext
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void save(Context mContext, String fileName, String key,
			Object value) {
		initial(mContext, fileName);
		if (value instanceof String) {
			sp.edit().putString(key, (String) value).commit();
		} else if (value instanceof Boolean) {
			sp.edit().putBoolean(key, (Boolean) value).commit();
		} else if (value instanceof Integer) {
			sp.edit().putInt(key, (Integer) value).commit();
		} else if (value instanceof Float) {
			sp.edit().putFloat(key, (Float) value).commit();
		} else if (value instanceof Long) {
			sp.edit().putLong(key, (Long) value).commit();
		}
	}

	/**
	 * 获取默认的文件下的值 类型为boolean的
	 * 
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(Context mContext, String key) {
		initial(mContext, SPNAME);
		try {
			return sp.getBoolean(key, false);
		} catch (Exception e) {
			return false;
		}
	}

	public static Boolean getBoolean(Context mContext, String fileName,
			String key) {
		initial(mContext, fileName);
		try {
			return sp.getBoolean(key, false);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取string类型的值
	 * 
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static String getString(Context mContext, String key) {
		initial(mContext, SPNAME);
		return sp.getString(key, "");
	}

	public static String getString(Context mContext, String fileName, String key) {
		initial(mContext, fileName);
		return sp.getString(key, "");
	}

	/**
	 * 获取默认的int类型的存储的值
	 * 
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static int getInteger(Context mContext, String key) {
		initial(mContext, SPNAME);
		return sp.getInt(key, 0);
	}

	public static int getInteger(Context mContext, String fileName, String key) {
		initial(mContext, SPNAME);
		return sp.getInt(key, 0);
	}

	/**
	 * 获取默认的float类型的值
	 * 
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static Float getFloat(Context mContext, String key) {
		initial(mContext, SPNAME);
		return sp.getFloat(key, 0);
	}

	public static Float getFloat(Context mContext, String fileName, String key) {
		initial(mContext, SPNAME);
		return sp.getFloat(key, 0);
	}

	/**
	 * 获取默认的Long类型的值
	 * 
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static Long getLong(Context mContext, String key) {
		initial(mContext, SPNAME);
		return sp.getLong(key, 0);
	}

	public static Long getLong(Context mContext, String fileName, String key) {
		initial(mContext, SPNAME);
		return sp.getLong(key, 0);
	}

	/**
	 * 清空默认的那个存储文件
	 * 
	 * @param mContext
	 */
	public static void removeFile(Context mContext) {
		initial(mContext, SPNAME);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 清空指定的文件目录
	 * 
	 * @param mContext
	 * @param fileName
	 */
	public static void removeFile(Context mContext, String fileName) {
		initial(mContext, fileName);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

}