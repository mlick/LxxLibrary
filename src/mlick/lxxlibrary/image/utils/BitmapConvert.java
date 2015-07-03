package mlick.lxxlibrary.image.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class BitmapConvert {

	/**
	 * 压缩本地图片然后存到一个新的文件中 最后返回新的图片的路径
	 * 
	 * @param dst
	 * @param width
	 * @param height
	 * @param newFile
	 * @param MaxKB
	 *            设置不要超过的图片的大小
	 * @return 返回新的图片的路径,如果产生异常返回null
	 */
	public static String compressImageToFile(File newFile, String dst,
			int width, int height, int MaxKB) {

		Bitmap image = getBitmapFromFile(dst, width, height);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;

		while (baos.toByteArray().length / 1024 > MaxKB) { // 循环判断如果压缩后图片是否大于1024kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}

		byte[] bitmapdata = baos.toByteArray();

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(newFile);
			fos.write(bitmapdata);
			fos.close();
			baos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return newFile.getPath();
	}

	/**
	 * byte字节转成bitmap
	 * 
	 * @param b
	 * @return
	 */
	public static Bitmap Bytes2Bimap(byte[] b) {
		if (b == null) {
			return null;
		}

		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 * 牺牲照片的质量进行的压缩返回bitmap
	 * 
	 * @param image
	 * @return bitmap
	 */
	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 1024) { // 循环判断如果压缩后图片是否大于1024kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中

		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/**
	 * 从本地资源的文件中读取图片到bitmap中
	 * 
	 * @param dst
	 *            文件路径
	 * @param width
	 *            图片的宽度
	 * @param height
	 *            图片的高度
	 * @return Bitmap 返回Bitmap图片
	 */
	public static Bitmap getBitmapFromFile(String dst, int width, int height) {
		if (null != dst && !dst.equals("")) {
			BitmapFactory.Options opts = null;
			if (width > 0 && height > 0) {
				opts = new BitmapFactory.Options(); // 设置inJustDecodeBounds为true后，decodeFile并不分配空间，此时计算原始图片的长度和宽度
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(dst, opts);
				// 计算图片缩放比例
				final int minSideLength = Math.min(width, height);
				opts.inSampleSize = computeSampleSize(opts, minSideLength,
						width * height); // 这里一定要将其设置回false，因为之前我们将其设置成了true
				opts.inJustDecodeBounds = false;
				opts.inInputShareable = true;
				opts.inPurgeable = true;
			}
			try {
				return BitmapFactory.decodeFile(dst, opts);
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 工具类，计算图片的大小，防止内存的溢出
	 * 
	 * @param options
	 * @param minSideLength
	 * @param maxNumOfPixels
	 * @return
	 */
	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	/**
	 * 工具类，计算图片的大小，防止内存的溢出
	 * 
	 * @param options
	 * @param minSideLength
	 * @param maxNumOfPixels
	 * @return
	 */
	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	/**
	 * 变换图片的形状之去圆角
	 * 
	 * @param image
	 * @param outerRadiusRat
	 *            半角的园角
	 * @return Bitmap
	 */
	public static Bitmap createFramedPhoto(Bitmap image, float outerRadiusRat) {

		// 根据源文件新建一个darwable对象
		Drawable imageDrawable = new BitmapDrawable(image);

		// 新建一个新的输出图片
		Bitmap output = Bitmap.createBitmap(image.getWidth(),
				image.getHeight(), Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(output);

		// 新建一个矩形
		RectF outerRect = new RectF(0, 0, image.getWidth(), image.getHeight());

		// 产生一个红色的圆角矩形

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

		paint.setColor(Color.RED);

		canvas.drawRoundRect(outerRect, outerRadiusRat, outerRadiusRat, paint);

		// 将源图片绘制到这个圆角矩形上
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		imageDrawable.setBounds(0, 0, image.getWidth(), image.getHeight());

		canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);

		imageDrawable.draw(canvas);

		canvas.restore();

		return output;

	}
}
