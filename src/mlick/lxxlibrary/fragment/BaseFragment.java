package mlick.lxxlibrary.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义的Fragment基类
 * 
 * @category 对于需要添加其它新的功能，可以进行继承该类去实现
 * @author lxx
 * @date 2014-9-4
 * @time 下午2:23:13
 */
public abstract class BaseFragment extends Fragment {

	private LayoutInflater inflater;
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		// View view = getLayoutView(inflater, container, savedInstanceState);
		mView = inflater.inflate(getLayoutResID(), container, false); //
		// 是要利用框架绑定fragment布局
		// ViewUtils.inject(this, view);
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

	/**
	 * 获取子布局中inflater,便于进行实例化其他布局
	 * 
	 * @return
	 */
	public LayoutInflater getBaseInflater() {
		return inflater;
	}

	// 获取所需要的子布局文件
	public View getBaseView() {
		return mView;
	}

	// 得到需要加载的布局文件的id，并将其返回
	public abstract int getLayoutResID();

	// 进行绑定到框架中，这个可以根据用户的需要，进行选择
	// 在Afinal中是这样进行绑定的 FinalActivity.initInjectedView(baseFragment,view);
	// 在xUtils中是这样进行绑定的 ViewUtils.inject(baseFragment,view);
	public abstract void ViewInJect(Fragment baseFragment, View view);

	// 初始化参数值
	public abstract void initValue();

	// 初始化布局文件
	public abstract void initView();
}
