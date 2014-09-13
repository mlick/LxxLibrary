package mlick.lxxlibrary.fragment;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * 继承自的最基类的BaseFragment的基类
 * @category 添加点击监听的Onclick事件
 * @author lxx
 * @date 2014-9-4
 * @time 下午3:03:30
 */
public abstract class BaseFragment1 extends BaseFragment {
	// 布局文件中的监听事件
	public OnClickListener baseListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			setMyOnClickListen(v);
		}
	};

	// 设置布局文件的监听
	public abstract void setMyOnClickListen(View v);

}
