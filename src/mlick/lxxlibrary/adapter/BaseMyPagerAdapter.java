package mlick.lxxlibrary.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewPager中普通的PagerAdapter封装
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午2:18:35
 */
public class BaseMyPagerAdapter extends PagerAdapter {

	private ArrayList<View> mViews;

	public BaseMyPagerAdapter() {

	}

	public BaseMyPagerAdapter(ArrayList<View> mViews) {
		this.mViews = mViews;
		// userinfoJson.getImages();
	}

	@Override
	public int getCount() {
		return mViews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViews.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mViews.get(position), 0);
		return mViews.get(position);
	}

}
