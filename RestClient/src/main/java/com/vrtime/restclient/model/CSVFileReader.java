package com.vrtime.restclient.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;

public class CSVFileReader {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	private String directory;
	private Pattern pattern = Pattern.compile(",");

	public CSVFileReader(final String directory) {
		this.directory = directory;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public List<Path> getPathToFiles() {
		List<Path> paths = new ArrayList<Path>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
			for (Path path : directoryStream) {
				paths.add(path);
			}
		} catch (IOException ex) {
			logger.error("Could not read files in directory " + directory);
		}
		return paths;

	}

	public List<Map<String, String>> getCsvMap(final Path path) {
		List<Map<String, String>> lines = new ArrayList();

		try (BufferedReader in = new BufferedReader(new FileReader(path.toFile()));) {
			lines = in.lines().skip(1).map(line ->{
				String[] x = pattern.split(line);
				return new HashMap<String, String>().put(key, value)
			})

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}

	}

}
