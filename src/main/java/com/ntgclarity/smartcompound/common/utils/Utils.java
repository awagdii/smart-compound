package com.ntgclarity.smartcompound.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import com.ntgclarity.smartcompound.common.entity.Lookup;

public class Utils {

	public static boolean isEmpty(Object obj) {
		return obj == null;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str) && str.trim().length() > 0;
	}

	public static boolean isNumericValue(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean isWrapperClass(Class<?> type) {
		return (type.isPrimitive() && type != void.class)
				|| type == Double.class || type == Float.class
				|| type == Long.class || type == Integer.class
				|| type == Short.class || type == Character.class
				|| type == Byte.class || type == Boolean.class;
	}

	public static Date getStartOfDay(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date startOfDay = calendar.getTime();
		return startOfDay;
	}

	public static Date getEndOfDay(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		Date EndOfDay = calendar.getTime();
		return EndOfDay;
	}

	public static boolean isLong(Class field) {
		if (field == Long.class) {
			return true;
		}
		return false;
	}

	public static Collection<String> getResourcesFromDirectory(
			final File directory, final Pattern pattern) {
		final ArrayList<String> retval = new ArrayList<String>();
		final File[] fileList = directory.listFiles();
		for (final File file : fileList) {
			if (file.isDirectory()) {
				retval.addAll(getResourcesFromDirectory(file, pattern));
			} else {
				try {
					final String fileName = file.getCanonicalPath();
					final boolean accept = pattern.matcher(fileName).matches();
					if (accept) {
						retval.add(fileName);
					}
				} catch (final IOException e) {
					throw new Error(e);
				}
			}
		}

//		for (final String name : retval) {
//			System.out.println(name);
//		}
		return retval;
	}

	public static List<String> getLookupAsString(List<Lookup> lookupList) {
		ArrayList<String> result = new ArrayList<>();
		Iterator<Lookup> iterator = lookupList.iterator();
		while (iterator.hasNext()) {
			result.add(iterator.next().getLookupName());

		}
		return result;

	}
	
	public static boolean isEmpty(List list) {
		return list == null || list.size()==0;
	}

}
