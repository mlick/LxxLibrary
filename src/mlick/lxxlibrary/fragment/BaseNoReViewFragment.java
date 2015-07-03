package mlick.lxxlibrary.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义的Fragment基类 重复的onCreateView
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午3:11:03
 */
public abstract class BaseNoReViewFragment extends Fragment {

	private LayoutInflater inflater;

	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (mView != null) {
			ViewGroup parent = (ViewGroup) mView.getParent();
			if (parent != null) {
				parent.removeView(mView);
			}
			return mView;
		}

		mView = inflater.inflate(getLayoutResID(), container, false); // 加载fragment布局

		this.inflater = inflater;
		ViewInJect(this, mView);
		initValue();

		initView();
		return mView;

	}

	// 当Activity开始启动时，加载视图监听
	// public void onActivityCreated(Bundle savedInstanceState) {
	// super.onActivityCreated(savedInstanceState);
	// // setViewOnClickListen();
	// }

	public LayoutInflater getBaseInflater() {
		return inflater;
	}

	// 获取所需要的子布局文件
	public View getBaseView() {
		return mView;
	}

	// 进行绑定到框架中，这个可以根据用户的需要，进行选择
	public abstract void ViewInJect(Fragment baseFragment, View view);

	/**
	 * 得到需要加载的布局文件的id，并将其返回
	 * 
	 * @return Rid
	 */
	public abstract int getLayoutResID();

	// 初始化参数值
	public abstract void initValue();

	// 初始化布局文件
	public abstract void initView();

	// 放置布局文件的监听
	// public abstract void setViewOnClickListen();
}
