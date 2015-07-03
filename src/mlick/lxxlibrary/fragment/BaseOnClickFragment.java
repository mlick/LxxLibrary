package mlick.lxxlibrary.fragment;

import android.view.View;

/**
 * 继承自的最基类的BaseFragment的基类
 * 
 * @category 添加抽象监听Onclick事件的函数
 * @author lxx
 * @date 2014-9-4
 * @time 下午3:03:30
 */
public abstract class BaseOnClickFragment extends BaseFragment implements
        android.view.View.OnClickListener {

    @Override
    public void onClick(View v) {
        setMyOnClickListen(v);
    }

    // 设置布局文件的监听
    public abstract void setMyOnClickListen(View v);
}
