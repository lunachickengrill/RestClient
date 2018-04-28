package com.vrtime.restclient.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;

public class FileReader {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	private String directory;

	public FileReader(final String directory) {
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
}
