package mlick.lxxlibrary.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * 判断手机属性
 */
public class PhoneProperty {
	public Context context;
	private static PhoneProperty mPhoneProperty = null;

	public static PhoneProperty getInstance(Context context) {

		if (mPhoneProperty == null) {
			mPhoneProperty = new PhoneProperty(context);

		}
		return mPhoneProperty;
	}

	public PhoneProperty(Context context2) {
		this.context = context2;
	}

	private DisplayMetrics getPhoneMetrics() {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}

	public float getPhoneDensity() {
		return getPhoneMetrics().density;
	}

	public float getPhoneScaledDensity() {
		return getPhoneMetrics().scaledDensity;
	}

	/**
	 * 
	 * @描述: 获取手机分辨率高度
	 * @作者: 陈强
	 * @日期: 2013-3-14 上午11:31:43
	 * @return int 手机分辨率高度
	 */
	public int getPhoneHeight() {
		return getPhoneMetrics().heightPixels;
	}

	/**
	 * 
	 * @描述: 获取手机分辨率宽度
	 * @作者: 陈强
	 * @日期: 2013-3-14 上午11:31:43
	 * @return int 手机分辨率宽度
	 */
	public int getPhoneWeigh() {
		return getPhoneMetrics().widthPixels;
	}

	/**
	 * 打电话 描述
	 * 
	 * @param context
	 * @param number
	 */
	public static void callUser(final Context context, final String number) {
		if (number == null || number.length() < 8) {
			Toast.makeText(context, "电话号码为空，或者格式不正确", Toast.LENGTH_SHORT).show();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("确定要拨打电话？");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					try {
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
						context.startActivity(intent);
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(context, "已取消拨号", Toast.LENGTH_SHORT).show();
					}

				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.show();

		}
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public String getVersion() {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	/**
	 * 验证手机号
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[^4,\\D]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 验证手机格式 >>>>> 添加人 mlick 2014年8月8日15:03:19
	 */
	public static boolean isMobileNO2(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (StringUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	/**
	 * 验证手机号 2015.02.12 by SHDB 11位，1开始
	 * 
	 * @param 需要验证的电话号码
	 * 
	 * @return (true:正确 /false:不正确)
	 */
	public static boolean isMobileNum(String mobiles) {
		// TODO
		// 后台正则 regexp = "^((1[3458]\\d)|(170))\\d{8}$"
		String telRegex = "[1]\\d{10}"; // "[1]"代表第1位为数字1，"\\d{9}"代表后面是可以是0～9的数字，有10位。
		if (StringUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	/**
	 * 验证邮箱格式 >>>>> 添加人 mlick 2014年8月8日
	 */
	public static boolean isEmail(String strEmail) {
		if (StringUtils.isEmpty(strEmail)) {
			return false;
		}

		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取系统名称 2015.02.10 by SHDB
	 * 
	 * @return 系统名称
	 */
	public String getSysName() {
		// TODO　20150210
		// android.os.Build.MODEL + ","+ android.os.Build.VERSION.SDK + ","+
		// android.os.Build.VERSION.RELEASE);
		//return "1";
		// 获取手机型号
				return android.os.Build.MODEL;
	}

	/**
	 * 获取系统类型 2015.02.10 by SHDB
	 * 
	 * @return 系统类型
	 */
	public String getSysType() {
		// TODO 20150210
		// 获取手机型号
		return android.os.Build.MODEL;

	}

	/**
	 * 获取系统版本号 2015.02.10 by SHDB
	 * 
	 * @return 系统版本号
	 */
	public String getSysVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取系统IMEI 2015.02.10 by SHDB
	 * 
	 * @return 系统IMEI
	 */
	public String getSysImei() {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		return imei;
	}

	/**
	 * 获取系统WiFi Mac地址 2015.02.10 by SHDB
	 * 
	 * @return 系统WiFi Mac地址
	 */
	public String getSysWifiMac() {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		String wifiMac = info.getMacAddress();
		return wifiMac;
	}

}
