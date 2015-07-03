package mlick.lxxlibrary.image.utils;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author lxx
 * @date 2014-9-4
 * @time 下午5:02:07
 */
@SuppressLint("ParcelCreator")
public class FileTraversal implements Parcelable {
	public String filename;// 需要进接口传送的文件名
	public List<String> filecontent = new ArrayList<String>();

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(filename);
		dest.writeList(filecontent);
	}

	public static final Parcelable.Creator<FileTraversal> CREATOR = new Creator<FileTraversal>() {

		@Override
		public FileTraversal[] newArray(int size) {
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public FileTraversal createFromParcel(Parcel source) {
			FileTraversal ft = new FileTraversal();
			ft.filename = source.readString();
			ft.filecontent = source.readArrayList(FileTraversal.class
					.getClassLoader());
			return ft;
		}
	};
}
