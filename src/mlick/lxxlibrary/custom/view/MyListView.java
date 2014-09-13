package mlick.lxxlibrary.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ÖØÐ´ListView·ÀÖ¹ÓëscrollView³åÍ»
 * 
 * @author lxx
 * @date 2014-9-4
 * @time ÏÂÎç5:47:47
 */
public class MyListView extends ListView {

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
