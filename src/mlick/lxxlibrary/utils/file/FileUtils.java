package mlick.lxxlibrary.utils.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import mlick.lxxlibrary.utils.ListUtils;
import mlick.lxxlibrary.utils.StringUtils;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

public class FileUtils {

    private final static String TAG = FileUtils.class.getSimpleName();

    /**
     * 在SD卡上创建文件
     * 
     * @param filepath
     * @return
     * @throws IOException
     */
    public static File createSDFile(String filepath) throws IOException {
        File file = new File(filepath);
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     * 
     * @param filepath
     * @return
     */
    public static File createSDDir(String filepath) {
        File dir = new File(filepath);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断文件夹是否存在不存在创建文件夹
     * 
     * @param strFolder
     * @return
     */
    public static boolean isFolderExists(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            if (file.mkdir()) {
                // 资源目录创建成功
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取图片的存储目录，在有sd卡的情况下为 “/sdcard/apps_images/本应用包名/cach/images/”
     * 没有sd的情况下为“/data/data/本应用包名/cach/images/”
     * 
     * @param context
     *            上下文
     * @return 本地图片存储目录
     */
    public static String getPath(Context context) {
        String path = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);// 判断是否有sd卡
        String packageName = context.getPackageName() + "/cach/images/";
        if (hasSDCard) {
            path =  FileUtils.getESDir() + "/apps_images/" + packageName;
        } else {
            path = "/data/" + packageName;
        }
        File file = new File(path);
        boolean isExist = file.exists();
        if (!isExist) {
            file.mkdirs();
        }
        return file.getPath();
    }

    /**
     * 得到sd卡的存储路径
     * 
     * @return
     */
    public static String getESDir() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static String getPath2(Context context, String p) {
        String path = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);// 判断是否有sd卡
        String packageName = context.getPackageName();
        if (hasSDCard) {
            path = FileUtils.getESDir() + "/" + packageName + "/" + p;
        } else {
            path = "/data/" + packageName + "/" + p;
        }
        File file = new File(path);
        boolean isExist = file.exists();
        if (!isExist) {
            file.mkdirs();
        }
        return file.getPath();
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

    public final static String FILE_EXTENSION_SEPARATOR = ".";

    private FileUtils() {
        throw new AssertionError();
    }

    /**
     * read file
     * 
     * @param filePath
     * @param charsetName
     *            The name of a supported {@link java.nio.charset.Charset
     *            </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException
     *             if an error occurs while operator BufferedReader
     */
    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file
     * 
     * @param filePath
     * @param content
     * @param append
     *            is append, if true, write to the end of file, else clear
     *            content of file and write into it
     * @return return false if content is empty, true otherwise
     * @throws RuntimeException
     *             if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, String content,
            boolean append) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file
     * 
     * @param filePath
     * @param contentList
     * @param append
     *            is append, if true, write to the end of file, else clear
     *            content of file and write into it
     * @return return false if contentList is empty, true otherwise
     * @throws RuntimeException
     *             if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, List<String> contentList,
            boolean append) {
        if (ListUtils.isEmpty(contentList)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            int i = 0;
            for (String line : contentList) {
                if (i++ > 0) {
                    fileWriter.write("\r\n");
                }
                fileWriter.write(line);
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file, the string will be written to the begin of the file
     * 
     * @param filePath
     * @param content
     * @return
     */
    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false);
    }

    /**
     * write file, the string list will be written to the begin of the file
     * 
     * @param filePath
     * @param contentList
     * @return
     */
    public static boolean writeFile(String filePath, List<String> contentList) {
        return writeFile(filePath, contentList, false);
    }

    /**
     * write file, the bytes will be written to the begin of the file
     * 
     * @param filePath
     * @param stream
     * @return
     * @see {@link #writeFile(String, InputStream, boolean)}
     */
    public static boolean writeFile(String filePath, InputStream stream) {
        return writeFile(filePath, stream, false);
    }

    /**
     * write file
     * 
     * @param file
     *            the file to be opened for writing.
     * @param stream
     *            the input stream
     * @param append
     *            if <code>true</code>, then bytes will be written to the end of
     *            the file rather than the beginning
     * @return return true
     * @throws RuntimeException
     *             if an error occurs while operator FileOutputStream
     */
    public static boolean writeFile(String filePath, InputStream stream,
            boolean append) {
        return writeFile(filePath != null ? new File(filePath) : null, stream,
                append);
    }

    /**
     * write file, the bytes will be written to the begin of the file
     * 
     * @param file
     * @param stream
     * @return
     * @see {@link #writeFile(File, InputStream, boolean)}
     */
    public static boolean writeFile(File file, InputStream stream) {
        return writeFile(file, stream, false);
    }

    /**
     * write file
     * 
     * @param file
     *            the file to be opened for writing.
     * @param stream
     *            the input stream
     * @param append
     *            if <code>true</code>, then bytes will be written to the end of
     *            the file rather than the beginning
     * @return return true
     * @throws RuntimeException
     *             if an error occurs while operator FileOutputStream
     */
    public static boolean writeFile(File file, InputStream stream,
            boolean append) {
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * move file
     * 
     * @param sourceFilePath
     * @param destFilePath
     */
    public static void moveFile(String sourceFilePath, String destFilePath) {
        if (TextUtils.isEmpty(sourceFilePath)
                || TextUtils.isEmpty(destFilePath)) {
            throw new RuntimeException(
                    "Both sourceFilePath and destFilePath cannot be null.");
        }
        moveFile(new File(sourceFilePath), new File(destFilePath));
    }

    /**
     * move file
     * 
     * @param srcFile
     * @param destFile
     */
    public static void moveFile(File srcFile, File destFile) {
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            deleteFile(srcFile.getAbsolutePath());
        }
    }

    /**
     * copy file
     * 
     * @param sourceFilePath
     * @param destFilePath
     * @return
     * @throws RuntimeException
     *             if an error occurs while operator FileOutputStream
     */
    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
        return writeFile(destFilePath, inputStream);
    }

    /**
     * read file to string list, a element of list is a line
     * 
     * @param filePath
     * @param charsetName
     *            The name of a supported {@link java.nio.charset.Charset
     *            </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException
     *             if an error occurs while operator BufferedReader
     */
    public static List<String> readFileToList(String filePath,
            String charsetName) {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<String>();
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * get file name from path, not include suffix
     * 
     * <pre>
     *      getFileNameWithoutExtension(null)               =   null
     *      getFileNameWithoutExtension("")                 =   ""
     *      getFileNameWithoutExtension("   ")              =   "   "
     *      getFileNameWithoutExtension("abc")              =   "abc"
     *      getFileNameWithoutExtension("a.mp3")            =   "a"
     *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
     *      getFileNameWithoutExtension("c:\\")              =   ""
     *      getFileNameWithoutExtension("c:\\a")             =   "a"
     *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
     *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
     *      getFileNameWithoutExtension("/home/admin")      =   "admin"
     *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
     * </pre>
     * 
     * @param filePath
     * @return file name from path, not include suffix
     * @see
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0,
                    extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
                extenPosi) : filePath.substring(filePosi + 1));
    }

    /**
     * get file name from path, include suffix
     * 
     * <pre>
     *      getFileName(null)               =   null
     *      getFileName("")                 =   ""
     *      getFileName("   ")              =   "   "
     *      getFileName("a.mp3")            =   "a.mp3"
     *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
     *      getFileName("abc")              =   "abc"
     *      getFileName("c:\\")              =   ""
     *      getFileName("c:\\a")             =   "a"
     *      getFileName("c:\\a.b")           =   "a.b"
     *      getFileName("c:a.txt\\a")        =   "a"
     *      getFileName("/home/admin")      =   "admin"
     *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
     * </pre>
     * 
     * @param filePath
     * @return file name from path, include suffix
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * get folder name from path
     * 
     * <pre>
     *      getFolderName(null)               =   null
     *      getFolderName("")                 =   ""
     *      getFolderName("   ")              =   ""
     *      getFolderName("a.mp3")            =   ""
     *      getFolderName("a.b.rmvb")         =   ""
     *      getFolderName("abc")              =   ""
     *      getFolderName("c:\\")              =   "c:"
     *      getFolderName("c:\\a")             =   "c:"
     *      getFolderName("c:\\a.b")           =   "c:"
     *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
     *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     *      getFolderName("/home/admin")      =   "/home"
     *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
     * </pre>
     * 
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * get suffix of file from path
     * 
     * <pre>
     *      getFileExtension(null)               =   ""
     *      getFileExtension("")                 =   ""
     *      getFileExtension("   ")              =   "   "
     *      getFileExtension("a.mp3")            =   "mp3"
     *      getFileExtension("a.b.rmvb")         =   "rmvb"
     *      getFileExtension("abc")              =   ""
     *      getFileExtension("c:\\")              =   ""
     *      getFileExtension("c:\\a")             =   ""
     *      getFileExtension("c:\\a.b")           =   "b"
     *      getFileExtension("c:a.txt\\a")        =   ""
     *      getFileExtension("/home/admin")      =   ""
     *      getFileExtension("/home/admin/a.txt/b")  =   ""
     *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
     * </pre>
     * 
     * @param filePath
     * @return
     */
    public static String getFileExtension(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        }
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }

    /**
     * Creates the directory named by the trailing filename of this file,
     * including the complete directory path required to create this directory. <br/>
     * <br/>
     * <ul>
     * <strong>Attentions:</strong>
     * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
     * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
     * </ul>
     * 
     * @param filePath
     * @return true if the necessary directories have been created or the target
     *         directory already exists, false one of the directories can not be
     *         created.
     *         <ul>
     *         <li>if {@link FileUtils#getFolderName(String)} return null,
     *         return false</li>
     *         <li>if target directory already exists, return true</li>
     *         <li>return {@link java.io.File#makeFolder}</li>
     *         </ul>
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder
                .mkdirs();
    }

    /**
     * @param filePath
     * @return
     * @see #makeDirs(String)
     */
    public static boolean makeFolders(String filePath) {
        return makeDirs(filePath);
    }

    /**
     * Indicates if this file represents a file on the underlying file system.
     * 
     * @param filePath
     * @return
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * Indicates if this file represents a directory on the underlying file
     * system.
     * 
     * @param directoryPath
     * @return
     */
    public static boolean isFolderExist(String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * delete file or directory
     * <ul>
     * <li>if path is null or empty, return true</li>
     * <li>if path not exist, return true</li>
     * <li>if path exist, delete recursion. return true</li>
     * <ul>
     * 
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isBlank(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    /**
     * get file size
     * <ul>
     * <li>if path is null or empty, return -1</li>
     * <li>if path exist and it is a file, return file size, else return -1</li>
     * <ul>
     * 
     * @param path
     * @return returns the length of this file in bytes. returns -1 if the file
     *         does not exist.
     */
    public static long getFileSize(String path) {
        if (StringUtils.isBlank(path)) {
            return -1;
        }

        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }

    /**
     * 向指定的文件中写入指定的数据
     * 
     * @param filename
     * @param message
     * @param context
     */
    public static void writeFileData(String filename, String message,
            Context context) {
        try {
            FileOutputStream fout = context.openFileOutput(filename,
                    Context.MODE_PRIVATE);// 获得FileOutputStream
            // 将要写入的字符串转换为byte数组
            byte[] bytes = message.getBytes();
            fout.write(bytes);// 将byte数组写入文件
            fout.close();// 关闭文件输出流
        } catch (Exception e) {
        }
    }

    /**
     * 打开指定文件，读取其数据，返回字符串对象
     * 
     * @param fileName
     * @param context
     * @return
     */
    public static String readFileData(String fileName, Context context) {
        String result = "";
        String ENCODING = "UTF-8";
        try {
            FileInputStream fin = context.openFileInput(fileName);
            // 获取文件长度
            int lenght = fin.available();
            byte[] buffer = new byte[lenght];
            fin.read(buffer);
            // 将byte数组转换成指定格式的字符串
            result = EncodingUtils.getString(buffer, ENCODING);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 
     * @param ins
     * @param file
     */
    public static boolean inputstreamToFile(InputStream ins, File file) {
        OutputStream os;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 
     * @param ins
     * @param file
     */
    public static boolean strToFile(String data, File file) {
        ByteArrayInputStream ins = new ByteArrayInputStream(data.getBytes());
        return inputstreamToFile(ins, file);
    }

    /**
     * 保存照片
     * 
     * @param context
     * @param imgName
     * @param pic
     * @return
     */
    public static boolean saveImage(Context context, String imgName, Bitmap pic) {
        try {
            FileOutputStream fout = context.openFileOutput(imgName,
                    Context.MODE_PRIVATE);// 获得FileOutputStream
            pic.compress(CompressFormat.PNG, 100, fout);
            fout.flush();
            fout.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据文件加载图片
     * 
     * @param context
     * @param imgName
     * @return
     */
    public static Bitmap loadImage(Context context, String imgName) {
        try {
            return BitmapFactory.decodeStream(context.openFileInput(imgName));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * 
     * @param ins
     * @param file
     */
    public static boolean bitmapToFile(Bitmap bitmap, File file) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100 /* ignored for PNG */, bos);
        byte[] bitmapdata = bos.toByteArray();

        // write the bytes in file
        FileOutputStream fos;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除文件
     * 
     * @param context
     * @param fileName
     */
    public static void delFile(Context context, String fileName) {
        File f = new File(context.getFilesDir(), fileName);
        if (f.exists()) {
            f.delete();
        }
    }
}
