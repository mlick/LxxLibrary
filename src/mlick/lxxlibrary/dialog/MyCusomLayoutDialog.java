package mlick.lxxlibrary.dialog;

import android.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * 带界面的自定布局的dialog
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午3:53:39
 */
public class MyCusomLayoutDialog {
	/**
	 * 自定义的Dialog框架
	 * 
	 * 需要在外部生命一个 AlertDialog 例如这样 final AlertDialog dlg =
	 * new AlertDialog.Builder(this).create();
	 * 布局文件可以参考layout目录下的dialog_call_telephone.xml这个文件
	 * 
	 * @param context
	 *            创造dialog的context
	 * 
	 * @param RLayoutId
	 *            自定义的布局
	 * 
	 * @param ROkId
	 *            确定按钮的id
	 * 
	 * @param RCancelId
	 *            取消按钮的id
	 * 
	 * @param okListener
	 *            确定按钮的监听事件
	 * 
	 * @param cancleListener
	 *            取消按钮的监听事件
	 */
	public static void showExitGameAlert(AlertDialog dlg, int RLayoutId,
			int ROkId, int RCancelId, View.OnClickListener okListener,
			View.OnClickListener cancleListener, String content, int RTextId) {
		dlg.show(); // 先进行调用，再进行设置

		Window window = dlg.getWindow();// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(RLayoutId);

		// 为确认按钮添加事件
		View ok = window.findViewById(ROkId);
		ok.setOnClickListener(okListener);

		// 为取消按钮添加事件
		View cancel = window.findViewById(RCancelId);
		cancel.setOnClickListener(cancleListener);

		TextView text = (TextView) window.findViewById(RTextId);
		text.setText(content);
	}

	
	
}
