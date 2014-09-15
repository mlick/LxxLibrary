package mlick.lxxlibrary.system;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断网络是否连通的工具类
 * 
 * @author lxx
 * @date 2014-9-15
 * @time 下午3:04:20
 */
public class NetworkUtils {

	public enum STATE {
		/**
		 * 无连接
		 */
		CONNECT_NONE,

		/**
		 * WIFI连接
		 */
		CONNECT_WIFI,

		/**
		 * WIFI连接
		 */
		CONNECT_MOBILE
	};

	// /**
	// * 无连接
	// */
	// public static final int STATE_CONNECT_NONE = 0;
	//
	// /**
	// * WIFI连接
	// */
	// public static final int STATE_CONNECT_WIFI = 1;
	//
	// /**
	// * 移动网络 2G/3G
	// */
	// public static final int STATE_CONNECT_MOBILE = 2;

	/**
	 * 获取当前网络连接状态
	 * 
	 * @param context
	 * @return 常量 STATE_CONNECT_NONE：无连接， STATE_CONNECT_WIFI：WIFI连接,
	 *         STATE_CONNECT_MOBILE：移动网络 2G/3G
	 */
	public static Enum<?> getNetConnectState(Context context) {
		final ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (networkInfo != null && networkInfo.isConnected()) {
			// return STATE_CONNECT_WIFI;
			return STATE.CONNECT_NONE;
		}
		networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null && networkInfo.isConnected()) {
			// return STATE_CONNECT_MOBILE;
			return STATE.CONNECT_MOBILE;
		}
		// return STATE_CONNECT_NONE;
		return STATE.CONNECT_NONE;
	}

	public static boolean isNetworkAvailable(Context context) {
		boolean bConnected = false;
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			NetworkInfo[] infos = connManager.getAllNetworkInfo();
			for (NetworkInfo info : infos) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					bConnected = true;
					break;
				}
			}
		}
		return bConnected;
	}
}
