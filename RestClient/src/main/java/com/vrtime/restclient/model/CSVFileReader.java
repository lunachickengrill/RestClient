package com.vrtime.restclient.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import com.vrtime.restclient.InputReader;
import com.vrtime.restclient.SSOSubAccount;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;

public class CSVFileReader implements InputReader {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	private String directory;
	private String done;
	private Pattern pattern = Pattern.compile(",");

	public CSVFileReader(final String directory, final String done) {
		this.directory = directory;
		this.done = done;
	}

	public String getDirectory() {
		return directory;
	}

	public String getDone() {
		return done;
	}

	private List<Path> getPathToFiles() {

		logger.debug("Reading directory " + directory + " for files");
		List<Path> paths = new ArrayList<Path>();

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
			for (Path path : directoryStream) {
				paths.add(path);
				logger.info("File found in directory " + Paths.get(directory).toAbsolutePath().toString() + ": "
						+ path.toString());
			}
		} catch (IOException ex) {
			logger.error("Could not read files in directory " + directory);
		}
		return paths;

	}

	private List<SSOSubAccount> getSubAccounts(final Path path) {
		List<SSOSubAccount> accounts = new ArrayList<SSOSubAccount>();

		try (BufferedReader in = new BufferedReader(new FileReader(path.toFile()));) {
			accounts = in.lines().skip(1).map(line -> {
				String[] x = pattern.split(line);
				return new SSOSubAccount(x[0], x[1], x[2], x[3], x[4]);
			}).collect(Collectors.toList());

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}

		return accounts;

	}

	private void moveProcessedFiles(final Path path) {
		Path done = Paths.get(getDone());
		try {
			logger.debug("Moving file: " + path.getFileName().toString());
			Files.move(path, done, StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException ex) {
			logger.error("Could not move file: " +  ex.getMessage());
		}
	}

	@Override
	public List<SSOSubAccount> produceSubAccounts() {
		List<Path> paths = getPathToFiles();
		List<SSOSubAccount> accounts = new ArrayList<SSOSubAccount>();

		for (Path p : paths) {
			logger.debug("Processing file: " + p.toFile().toString());
			List<SSOSubAccount> subs = getSubAccounts(p);

			for (SSOSubAccount s : subs) {

				accounts.add(s);
			}

			moveProcessedFiles(p);
		}

		return accounts;

	}

}
