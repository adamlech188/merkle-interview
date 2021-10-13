package com.merkle.challenge;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationMain {

	public static void main(String[] args) throws FileNotFoundException {
		DictionaryBuilder.buildScrabbleWordsSet();
		SpringApplication.run(ApplicationMain.class, args);
	}
}
