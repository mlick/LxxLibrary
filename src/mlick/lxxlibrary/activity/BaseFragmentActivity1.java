package mlick.lxxlibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseFragmentActivity1 extends BaseFragmentActivity {

	// 放置布局文件的监听
	public abstract void setViewOnClickListen();

	// 添加到setViewOnclickListen
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setViewOnClickListen();
	}

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
