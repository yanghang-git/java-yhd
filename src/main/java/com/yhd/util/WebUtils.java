package com.yhd.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 杨航
 * @Date 2020/6/29 18:29
 * @Since 1.8
 */
public class WebUtils {
	/**
	 * towards web send String data
	 * @param resp response instance
	 * @param value send of data
	 */
	public static void sendValue(HttpServletResponse resp, String value) {
		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			writer.print(value);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * get day of millisecond
	 * @param day day
	 * @return millisecond
	 */
	public static long getDayMillisecond(int day) {
		return day * 1000*60*60*24;
	}

	/**
	 * calculate two time among gap
	 * @param start start date
	 * @param end end date
	 * @return String Data
	 */
	public static String calculateTwoTimeGap(long start, long end) {
		long residue = (end - start) / (60 * 1000);
		StringBuilder sb = new StringBuilder("");
		if(residue / (60 * 24) > 0) {
			sb.append(residue / (60 * 24)).append("天");
		}
		if(residue / 60 > 0) {
			sb.append((residue / 60) % 24).append("小时");
		}
		return sb.append(residue % 60).append("分钟").toString();
	}
}
