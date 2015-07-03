package mlick.lxxlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

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

    /**
     * 自定义的Dialog框架
     * 
     * 需要在外部生命一个 AlertDialog 例如这样 final AlertDialog dlg = new
     * AlertDialog.Builder(this).create();
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
