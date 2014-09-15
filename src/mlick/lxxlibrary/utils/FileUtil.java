package mlick.lxxlibrary.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.os.Environment;

/**
 * 文件操作的工具类
 * 
 * @author lxx
 * @date 2014-9-15
 * @time 下午3:41:43
 */
public class FileUtil {

	/**
	 * 在SD卡上创建文件
	 * 
	 * @param filepath
	 * @return 为null说明文件已经存在
	 * @throws IOException
	 */
	public static File createSDFile(String filepath) throws IOException {

		if (isFileExist(filepath)) {
			return null;
		}

		File file = new File(filepath);
		file.createNewFile();
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param filepath
	 * @return 为null说明已经存在该目录
	 */
	public static File createSDDir(String filepath) {

		if (isFolderExists(filepath)) {
			return null;
		}

		File dir = new File(filepath);

		dir.mkdir();
		return dir;
	}

	/*
	 * 判断SD卡上的文件夹是否存在
	 */
	public static boolean isFileExist(String filepath) {
		File file = new File(filepath);
		return file.exists();
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param strFolder
	 * @return
	 */
	public static boolean isFolderExists(String filepath) {
		File file = new File(filepath);
		if (!file.exists()) {
			return true;
		}
		return false;
	}

	public static String getPath(Context context, String packageName,
			String content) {
		String path = null;
		boolean hasSDCard = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);// 判断是否有sd卡

		if (hasSDCard) {
			path = Environment.getExternalStorageDirectory().getPath()
					+ packageName + "/" + content;
		} else {
			path = context.getFilesDir().getPath() + packageName + "/"
					+ content;
		}

		// File file = new File(path);
		//
		// if (!file.exists()) {
		// file.mkdirs();
		// }
		// return file.getPath();
		return path;

	}

	/*
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public static File write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			// 创建目录
			createSDDir(path);
			// 创建文件
			file = createSDFile(path + fileName);
			// 创建输出流
			output = new FileOutputStream(file);
			// 创建缓冲区
			byte buffer[] = new byte[4 * 1024];
			// 写入数据
			int bufferLength = 0;
			while ((bufferLength = input.read(buffer)) != -1) {
				output.write(buffer, 0, bufferLength);
			}
			// 清空缓存
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭输出流
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}
	}
}
