package com.lakala.ipos.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {
	private static final String encoding = "iso-8859-1";
	
	public GzipUtils() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * GZIP 压缩
	 */
	public static String compressMessage(String targetStr) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(bos);
			gzip.write(targetStr.getBytes("UTF-8"));
			gzip.finish();
			gzip.close();
			return bos.toString(encoding);
		} catch (IOException e) {
			return targetStr;
		} finally {
			bos = null;
		}
	}

	/**
	 * GZIP 解压缩
	 */
	public static String uncompressMessage(String compressedStr) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPInputStream gzip;
		try {
			gzip = new GZIPInputStream(new ByteArrayInputStream(compressedStr.getBytes(encoding)));
			byte[] buff = new byte[1024];
			int len;
			while ((len = gzip.read(buff)) != -1) {
				bos.write(buff, 0, len);
			}
			gzip.close();
			byte[] content = bos.toByteArray();
			//String uncompressedData = new String(content, 0, content.length, encoding);
			String uncompressedData = new String(content);
			return uncompressedData;
			//return bos.toString();
		} catch (IOException e) {
			return compressedStr;
		} finally {
			bos = null;
			gzip = null;
		}
	}
	
}
