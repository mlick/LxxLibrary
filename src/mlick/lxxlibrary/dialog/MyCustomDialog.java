package mlick.lxxlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

public class MyCustomDialog {
	/**
	 * 
	 * @param context
	 * @param title
	 *            标题
	 * @param content
	 *            提示内容
	 * @param confirmClick
	 *            确认
	 * @param cancelClick
	 *            取消
	 */
	public static void showDialog(Context context, String title,
			String content, String confirm,
			DialogInterface.OnClickListener confirmClick, String cancel,
			DialogInterface.OnClickListener cancelClick) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		adb.setTitle(title);
		adb.setMessage(content);
		// adb.setPositiveButton("确定", confirmClick);
		// adb.setNegativeButton("取消", cancelClick);
		adb.setPositiveButton(confirm, confirmClick);
		adb.setNegativeButton(cancel, cancelClick);
		adb.show();
	}

	/**
	 * 只有确定键的布局
	 * 
	 * @param context
	 * @param title
	 *            标题
	 * @param content
	 *            提示内容
	 * @param confirmClick
	 *            确认
	 */
	public static void showDialog2(Context context, String title,
			String content, String confirm,
			DialogInterface.OnClickListener confirmClick) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		adb.setTitle(title);
		adb.setMessage(content);
		// adb.setPositiveButton("确定", confirmClick);
		adb.setPositiveButton(confirm, confirmClick);
		adb.show();
		// .setIcon(android.R.drawable.ic_dialog_info)
	}

	public static void showDialogList(Context context, String titleString,
			final String items[], DialogInterface.OnClickListener listener) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		// adb.setTitle("列表框");
		adb.setTitle(titleString);
		adb.setIcon(android.R.drawable.ic_dialog_info);
		adb.setItems(items, listener);
		adb.show();
	}

	/**
	 * 提示信息 列表对话框
	 * 
	 * @param context
	 * @param items
	 * @param editText
	 *            设置某个布局内容为点击的内容
	 */
	public static void showDialogList2(Context context, String titleString,
			final String items[], final EditText editText) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		// adb.setTitle("列表框");
		adb.setTitle(titleString);
		adb.setIcon(android.R.drawable.ic_dialog_info);
		adb.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				editText.setText(items[which]);
			}
		});
		adb.show();
	}
	
	
	
}
