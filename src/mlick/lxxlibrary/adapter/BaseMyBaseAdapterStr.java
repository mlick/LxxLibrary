package mlick.lxxlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Adapter 二度封装 数据为String数组
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午2:19:47
 */
public abstract class BaseMyBaseAdapterStr extends BaseAdapter {

	public String[] baseData; // 封装的数据
	public Context baseContext; // 获取到的context
	public LayoutInflater baseInf; // 获取到的layoutinflater

	public BaseMyBaseAdapterStr(Context context) {
		this.baseContext = context;
		baseInf = LayoutInflater.from(context);
	}

	public void setmData(String[] baseData) {
		this.baseData = baseData;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (baseData != null) {
			return baseData.length;
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (baseData != null) {
			return baseData[position];
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (baseData != null) {
			return baseData[position].hashCode();
		}
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return this.getMyView(position, convertView, parent);
	}

	public abstract View getMyView(int position, View convertView,
			ViewGroup parent);

}
