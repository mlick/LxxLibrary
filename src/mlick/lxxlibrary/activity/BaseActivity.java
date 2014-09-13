package mlick.lxxlibrary.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * 封装自定义的Activity
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午3:25:16
 */
public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResID());
		ViewInJect(this);
		initValue();
		initView();
	}

	// 得到布局文件的id
	public abstract int getLayoutResID();

	// 对该Activity进行绑定，可以根据用户的需要而定
	// 在Afinal中是这样进行绑定的 FinalActivity.initInjectedView(baseActivity);
	// 在xUtils中是这样进行绑定的 ViewUtils.inject(baseActivity);
	public abstract void ViewInJect(BaseActivity baseActivity);

	// 初始化变量
	public abstract void initValue();

	// 出事话试图
	public abstract void initView();

	// // 初始监听事件
	// public abstract void setViewOnClickListen();

}
