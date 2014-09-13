package mlick.lxxlibrary.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * 显示toast的内容
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午5:31:34
 */
public class ToastUtils {
	/**
	 * 
	 * 描述 长显示
	 * 
	 * @param context
	 *            上下文对象
	 * @param content
	 *            提示信息
	 */
	public static void showLongToast(Context context, String content) {
		if (context != null) {
			Toast.makeText(context, content, Toast.LENGTH_LONG).show();
		}
	}

	public static void showLongToast(Context context, int content) {
		if (context != null) {
			Toast.makeText(context, context.getResources().getString(content),
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 描述 短显示
	 * 
	 * @param context
	 *            上下文对象
	 * @param content
	 *            提示信息
	 */
	public static void showShortToast(Context context, String content) {
		if (context != null) {
			Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
		}
	}

	public static void showShortToast(Context context, int content) {
		if (context != null) {
			Toast.makeText(context, context.getResources().getString(content),
					Toast.LENGTH_SHORT).show();
		}
	}
}
