package com.networknt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author vlad
 *
 */
public final class FileUtils {
	
	private FileUtils() {
		
	}

	/**
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static String readInputStream(final InputStream input) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
			return buffer.lines().collect(Collectors.joining("\n"));
		}
	}
}
