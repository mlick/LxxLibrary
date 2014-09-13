package mlick.lxxlibrary.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResID());
		ViewInJect(this);
		initValue();
		initView();
	}

	public abstract int getLayoutResID();

	// 进行绑定到框架中，这个可以根据用户的需要，进行选择
	// 在Afinal中是这样进行绑定的 FinalActivity.initInjectedView(baseActivity);
	// 在xUtils中是这样进行绑定的 ViewUtils.inject(baseActivity);
	public abstract void ViewInJect(BaseFragmentActivity baseFragmentActivity);

	// 初始化参数值
	public abstract void initValue();

	// 初始化布局文件
	public abstract void initView();

}
