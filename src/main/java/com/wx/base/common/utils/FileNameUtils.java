package com.wx.base.common.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class FileNameUtils {
	public static String recense(String fileName) {
		return StringUtils.replace(fileName, CharUtils.SLASH, CharUtils.BACKSLASH);
	}

	/**
	 * 根据文件的路径 , 获取文件的目录.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String[] getPaths(String filePath) {
		String temp = FileNameUtils.recense(filePath);
		int idx = temp.lastIndexOf(CharUtils.BACKSLASH);
		if (idx > 0) {
			return temp.substring(0, idx).split(CharUtils.BACKSLASH);
		} else {
			// return temp.split(split);
			return new String[0];
		}
	}

	/**
	 * 获取文件的相对路径.
	 * 
	 * @param relativePath
	 *            配置的存放相对路径.
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static String getRelativePath(String relativePath, String fileName) {
		relativePath = FileNameUtils.recense(relativePath);

		String data = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
		StringBuffer path = new StringBuffer();
		if (!relativePath.startsWith(CharUtils.BACKSLASH)) {
			path.append(CharUtils.BACKSLASH);
		}

		path.append(relativePath);

		if (!relativePath.endsWith(CharUtils.BACKSLASH)) {
			path.append(CharUtils.BACKSLASH);
		}
		path.append(data).append(CharUtils.BACKSLASH).append(fileName);

		return path.toString();
	}

	/**
	 * 获取文件的绝对路径.
	 * 
	 * @param root
	 *            配置的根路径.
	 * @param relativePath
	 *            文件的相对路径.
	 * @return
	 */
	public static String getAbsolutePath(String root, String relativePath) {
		String path = recense(root);
		if (path.endsWith(CharUtils.BACKSLASH)) {
			path = path.substring(0, path.length() - 1);
		}

		if (!relativePath.startsWith(CharUtils.BACKSLASH)) {
			path = path + CharUtils.BACKSLASH;
		}

		path = path + relativePath;

		return path;
	}

	public static String getFileNamePath(String fullFileName) {
		String path = recense(fullFileName);
		int idx = path.lastIndexOf(CharUtils.SPOT);
		if (idx > 0) {
			path = path.substring(0, idx);
		}
		return path;
	}

	/**
	 * 获取文件名称.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getName(String fileName) {
		fileName = FileNameUtils.recense(fileName);
		int idx = fileName.lastIndexOf(CharUtils.BACKSLASH);
		if (idx > 0) {
			return fileName.substring(idx + 1);
		} else {
			return fileName;
		}
	}

	/**
	 * 取得文件扩展名.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtName(String fileName) {
		fileName = FileNameUtils.recense(fileName);
		int idx = fileName.lastIndexOf(CharUtils.BACKSLASH);
		if (idx > 0) {
			fileName = fileName.substring(idx + 1);
		}

		idx = fileName.lastIndexOf(CharUtils.SPOT);

		if (idx > 0) {
			return fileName.substring(idx + 1);
		} else {
			return "";
		}
	}

	/**
	 * 取得文件名.
	 * 
	 * @param shortName
	 * @param extName
	 * @return
	 */
	public static String getFileName(String shortName, String extName) {
		StringBuffer ret = new StringBuffer();
		ret.append(shortName).append(CharUtils.SPOT).append(extName);
		return ret.toString();
	}

	/**
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	public static String join(String path, String name) {
		String aName = name.startsWith(CharUtils.BACKSLASH) ? name.substring(1) : name;
		if (path.endsWith(CharUtils.BACKSLASH)) {
			return path + aName;
		} else {
			return path + CharUtils.BACKSLASH + aName;
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getPath(String fileName) {
		fileName = FileNameUtils.recense(fileName);
		int idx = fileName.lastIndexOf(CharUtils.BACKSLASH);
		if (idx > 0) {
			return fileName.substring(0, idx);
		} else {
			return fileName;
		}
	}

	/**
	 * 判断文件是否为图片文件(GIF,PNG,JPG)
	 * 
	 * @param srcFileName
	 * @return
	 */
	public static boolean isImage(String srcFileName) {
		FileInputStream imgFile = null;
		byte[] b = new byte[10];
		int l = -1;
		try {
			imgFile = new FileInputStream(srcFileName);
			l = imgFile.read(b);
			imgFile.close();
		} catch (Exception e) {
			return false;
		}
		if (l == 10) {
			byte b0 = b[0];
			byte b1 = b[1];
			byte b2 = b[2];
			byte b3 = b[3];
			byte b6 = b[6];
			byte b7 = b[7];
			byte b8 = b[8];
			byte b9 = b[9];
			if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F') {
				return true;
			} else if (b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G') {
				return true;
			} else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I' && b9 == (byte) 'F') {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String getFileContent(String path) throws Exception {
		File file = imba.game.base.common.utils.FileUtils.getInstance().getFileByPath(path);
		if (!file.exists() || file.isDirectory()) {
			return null;
		}
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			StringBuffer sb = new StringBuffer();
			while (in.read(bytes) != -1) {
				sb.append(new String(bytes));
				bytes = new byte[1024];
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getWebContent(String str) {
		if (str == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '<') {
				sb.append("&lt;");
			} else if (ch == '>') {
				sb.append("&gt;");
			} else if (ch == '&') {
				sb.append("&amp;");
			} else if (ch == '"') {
				sb.append("&quot;");
			} else if (ch == '\r') {
				sb.append("<br>");
			} else if (ch == '\n') {
				if (i > 0 && str.charAt(i - 1) == '\r') {

				} else {
					sb.append("<br>");
				}
			} else if (ch == '\t') {
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			} else if (ch == ' ') {
				sb.append("&nbsp;");
			} else if (ch == '<') {
				sb.append("&lt;");
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

}
