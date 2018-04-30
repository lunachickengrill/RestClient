package com.vrtime.restclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.vrtime.restclient.model.CSVFileReader;
import com.vrtime.restclient.model.InputReader;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Autowired
	Environment env;

	@Bean
	InputReader inputReader() {
		return new CSVFileReader(env.getProperty("source.directory"), env.getProperty("source.done"));
	}

}
