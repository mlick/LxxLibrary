package mlick.lxxlibrary.activity;

import android.view.View;

/**
 * 增加点击事件
 * 
 * @author lxx
 * @date 2014-9-15
 * @time 下午1:47:25
 */
public abstract class BaseOnClickFragmentActivity extends BaseFragmentActivity
        implements android.view.View.OnClickListener {
    @Override
    public void onClick(View v) {
        setMyOnClickListen(v);
    }

    // 设置布局文件的监听
    public abstract void setMyOnClickListen(View v);

}
