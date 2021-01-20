package com.wx.base.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(RandomUtils.class);

	private static MultipartFileUtils instance;

	public static MultipartFileUtils getInstance() {
		if (instance == null) {
			instance = new MultipartFileUtils();
		}
		return instance;
	}

	public boolean save(MultipartFile multipartFile, String dest) {
		try {
			File file = imba.game.base.common.utils.FileUtils.getInstance().getFileByPath(dest);
			return save(multipartFile, file);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public boolean save(MultipartFile multipartFile, File dest) {
		InputStream is = null;
		try {
			is = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(is, dest);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}
