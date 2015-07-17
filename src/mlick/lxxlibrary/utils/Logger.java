package mlick.lxxlibrary.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class Logger {

    public static final int LOG_LEVEL_NONE = 0;
    public static final int LOG_LEVEL_ERROR = 1;
    public static final int LOG_LEVEL_WARN = 2;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_VERBOSE = 4;
    public static final int LOG_LEVEL_DEBUG = 5;

    private static int LogLevel = LOG_LEVEL_DEBUG;

    private static boolean LOG_ERROR = LogLevel >= LOG_LEVEL_ERROR;
    private static boolean LOG_WARN = LogLevel >= LOG_LEVEL_WARN;
    private static boolean LOG_INFO = LogLevel > LOG_LEVEL_INFO;
    private static boolean LOG_VERBOSE = LogLevel >= LOG_LEVEL_VERBOSE;
    private static boolean LOG_DEBUG = LogLevel >= LOG_LEVEL_DEBUG;

    private static Boolean MYLOG_WRITE_TO_FILE = true;// 日志写入文件开关
    private static String MYLOG_PATH_SDCARD_DIR = "/sdcard/";// 日志文件在sdcard中的路径
    private static int SDCARD_LOG_FILE_SAVE_DAYS = 2;// sd卡中日志文件的最多保存天数
    private static String MYLOGFILEName = "qiyuLog";// 本类输出的日志文件名称
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");// 日志的输出格式
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyyMMdd");// 日志文件格

    // http://xiangqianppp-163-com.iteye.com/blog/1417743 写日志文件地址

    public static int getLogLevel() {
        return Logger.LOG_LEVEL_DEBUG;
    }

    public static void setLogLevel(int logLevel) {
        LogLevel = logLevel;
        LOG_ERROR = LogLevel >= LOG_LEVEL_ERROR;
        LOG_WARN = LogLevel >= LOG_LEVEL_WARN;
        LOG_INFO = LogLevel > LOG_LEVEL_INFO;
        LOG_VERBOSE = LogLevel >= LOG_LEVEL_VERBOSE;
        LOG_DEBUG = LogLevel >= LOG_LEVEL_DEBUG;
    }

    public static void e(String tag, String msg) {
        if (Logger.LOG_ERROR) {
            Log.e(tag, msg);
        }
        if (MYLOG_WRITE_TO_FILE){
            writeLogtoFile(String.valueOf("e"), tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (Logger.LOG_ERROR) {
            Log.e(tag, msg, tr);
        }
        if (MYLOG_WRITE_TO_FILE){
            writeLogtoFile(String.valueOf("e"), tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (Logger.LOG_WARN) {
            Log.w(tag, msg);
        }
        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile(String.valueOf("w"), tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (Logger.LOG_WARN) {
            Log.w(tag, msg, tr);
        }
        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile(String.valueOf("w"), tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (Logger.LOG_INFO) {
            Log.i(tag, msg);
        }
        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile(String.valueOf("i"), tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (Logger.LOG_VERBOSE) {
            Log.v(tag, msg);
        }
        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile(String.valueOf("v"), tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Logger.LOG_DEBUG) {
            Log.d(tag, msg);
        }
        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile(String.valueOf("d"), tag, msg);
        }
    }

    private static String substringTag(String tag) {
        if (StringUtils.isNotEmpty(tag) && tag.length() > 22) {
            return tag.substring(0, 22);
        } else {
            return tag;
        }
    }

    /**
     * 打开日志文件并写入日志
     * 
     * @return
     * **/
    private static void writeLogtoFile(String mylogtype, String tag, String text) {// 新建或打开日志文件
        Date nowtime = new Date();
        String needWriteFiel = logfile.format(nowtime);
        String needWriteMessage = myLogSdf.format(nowtime) + "    " + mylogtype
                + "    " + tag + "    " + text;
        try {
            String path = "";
            boolean hasSDCard = Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
            if (hasSDCard) {
                path = Environment.getExternalStorageDirectory().getPath();
            } else {
                path = "/data/";
            }
            File file = new File(path, MYLOGFILEName + needWriteFiel + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {

        }
    }

    /**
     * 删除制定的日志文件
     * */
    public static void delFile() {// 删除日志文件
        String needDelFiel = logfile.format(getDateBefore());
        File file = new File(MYLOG_PATH_SDCARD_DIR, MYLOGFILEName + needDelFiel
                + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     * */
    private static Date getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }
}
