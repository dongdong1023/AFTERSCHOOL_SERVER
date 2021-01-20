package com.wx.base.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Class comments.
 *
 * @author 东东
 */
public class FileUtils {

	private static final Log logger = LogFactory.getLog(FileUtils.class);

	public static final String PATH_ERGODIC = "..";/* 路径遍历 */


	private static FileUtils instance;

	public static FileUtils getInstance() {
		if (instance == null) {
			instance = new FileUtils();
		}
		return instance;
	}


	/**
	 * 获得文件夹下的文件夹.
	 *
	 * @param folder
	 * @return
	 */
	public List<File> getFolders(File folder) {
		List<File> folders = new ArrayList<File>();
		if (!folder.exists()) {
			return folders;
		}
		for (File fileOrFolder : folder.listFiles()) {
			if (fileOrFolder.isDirectory()) {
				folders.add(fileOrFolder);
			}
		}
		return folders;
	}

	public static void flow(InputStream input, OutputStream out, byte[] buf) throws IOException {
		int numRead;
		while ((numRead = input.read(buf)) >= 0) {
			out.write(buf, 0, numRead);
		}
		out.flush();
	}

	public static void flow(Reader input, Writer out, char[] buf) throws IOException {
		int numRead;
		while ((numRead = input.read(buf)) >= 0) {
			out.write(buf, 0, numRead);
		}
		out.flush();
	}


	public static String loadFile(File file) {
		FileReader reader = null;
		if (file.exists()) {
			try {
				reader = new FileReader(file);
				StringWriter writer = new StringWriter();
				flow(reader, writer, new char[1024]);
				return writer.toString();
			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "";
	}

	public static List<String> loadLine(File file) throws IOException {
		List<String> items = new ArrayList<String>();
		FileReader areade = new FileReader(file);
		BufferedReader reader = new BufferedReader(areade);
		while (true) {
			String aLine = reader.readLine();
			if (aLine == null)
				break;
			items.add(aLine.trim());
		}
		reader.close();
		return items;
	}

	public static void writeFile(File file, String string) throws IOException {
		FileWriter writer = new FileWriter(file);
		try {
			writer.write(string);
		} finally {
			if (writer != null)
				writer.close();
		}

	}

	public static InputStream getURLInputStream(String urlStr) throws IOException {
		InputStream in = null;
		URL url = new URL(urlStr);
		in = url.openStream();
		return in;
	}

	public static List<String> loadLine(InputStream in) throws IOException {
		List<String> items = new ArrayList<String>();

		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader bufReader = new BufferedReader(reader);
		while (true) {
			String aLine = bufReader.readLine();
			if (aLine == null)
				break;
			items.add(aLine.trim());
		}
		return items;
	}

	public static Properties loadProperites(File file) {
		Properties result = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			result.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	@SuppressWarnings({"rawtypes"})
	public static void writeFile(List result, String file) throws IOException {
		FileWriter writer = new FileWriter(file);
		try {
			for (Iterator iter = result.iterator(); iter.hasNext(); ) {
				writer.write(iter.next().toString());
				writer.write("\r\n");
			}

		} finally {
			if (writer != null)
				writer.close();
		}

	}


	/**
	 * 读取文件流.
	 *
	 * @param file
	 * @return
	 */
	public static InputStream getInputStream(File file) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			logger.error(e);
		}

		return inputStream;
	}

	/**
	 * @param nasPath
	 * @return
	 */
	public static String getFTPRelativePath(String nasPath) {
		String ip = nasPath.split(CharUtils.BACKSLASH)[2];
		String[] result = nasPath.split(ip);
		if (result.length == 1) {
			return CharUtils.BACKSLASH;
		}
		return result[1];
	}

	// 获取文件预防路径遍历
	public File getFileByPath(String path) throws Exception {
		if (path.contains(PATH_ERGODIC)) {
			throw new Exception("path ergodic : " + path);
		}
		return new File(path);
	}

	// 获取文件预防路径遍历
	public File getFileByPath(String parent, String child) throws Exception {
		if (parent.contains(PATH_ERGODIC) || child.contains(PATH_ERGODIC)) {
			throw new Exception("path ergodic : " + parent + CharUtils.COMMA + child);
		}
		return new File(parent, child);
	}

	// 获取文件预防路径遍历
	public File getFileByPath(File parent, String child) throws Exception {
		if (parent.getAbsolutePath().contains(PATH_ERGODIC) || child.contains(PATH_ERGODIC)) {
			throw new Exception("path ergodic : " + parent.getAbsolutePath() + CharUtils.COMMA + child);
		}
		return new File(parent, child);
	}

	/**
	 * 递归删除文件
	 *
	 * @param file
	 */
	public static void deleteFile(File file) {
		// 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
		if (file.isDirectory()) {
			// 获取子文件/目录
			File[] subFiles = file.listFiles();
			// 遍历该目录
			for (File subFile : subFiles) {
				// 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除.
				// 如果这是一个非空目录, 多次递归清空其内容后再删除
				deleteFile(subFile);
			}
		}
		// 删除空目录或文件
		file.delete();
	}

	public void deleteFiles(File... files) {
		for (File file : files) {
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 下载文件
	 *
	 * @param response
	 * @param file
	 * @param newFileName
	 */
	public static void downloadFile(HttpServletResponse response, File file, String newFileName) {
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(newFileName.getBytes("ISO-8859-1"), "UTF-8"));
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			InputStream is = new FileInputStream(file.getAbsolutePath());
			BufferedInputStream bis = new BufferedInputStream(is);
			int length = 0;
			byte[] temp = new byte[1 * 1024 * 10];
			while ((length = bis.read(temp)) != -1) {
				bos.write(temp, 0, length);
			}
			bos.flush();
			bis.close();
			bos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件重命名
	 *
	 * @param path    文件目录
	 * @param oldname 原来的文件名
	 * @param newname 新文件名
	 */
	public static boolean renameFile(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {
			//新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(path + oldname);
			File newfile = new File(path + newname);
			//重命名文件不存在
			if (!oldfile.exists()) {
				logger.error("重命名文件不存在");
				return false;
			}
			//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
			if (newfile.exists()) {
				logger.error(newname + "已经存在！");
				return false;
			} else {
				oldfile.renameTo(newfile);
				return true;
			}
		} else {
			logger.error("新文件名和旧文件名相同");
			return false;
		}
	}

}
