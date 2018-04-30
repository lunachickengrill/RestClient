package com.vrtime.restclient;

import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vrtime.restclient.input.CSVFileReader;
import com.vrtime.restclient.input.InputReader;
import com.vrtime.restclient.shared.SSOSubAccount;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InputReader inputReader;

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {


		List<SSOSubAccount> sso = inputReader.produceSubAccounts();


		for (SSOSubAccount s : sso) {
			logger.info(s.toString());

		}

	}

}
