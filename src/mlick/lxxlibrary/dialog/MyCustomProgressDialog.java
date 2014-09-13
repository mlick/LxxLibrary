package mlick.lxxlibrary.dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 自定义的进度条
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午5:13:30
 */
public class MyCustomProgressDialog {

	/**
	 * 自定义的ProgressDialog对话框进行的处理
	 */
	public static ProgressDialog getCustomProgressDialog(Context context,
			String content, boolean canceledOnTouchOutside) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(content);
		progressDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		return progressDialog;
	}
}
