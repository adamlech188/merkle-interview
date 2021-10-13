package com.merkle.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DictionaryBuilder {

	private static Set<String> dictionarySet;

	public static Set<String> buildScrabbleWordsSet() throws FileNotFoundException {

		if (dictionarySet == null) {
			dictionarySet = new TreeSet<>();
			File file = new File("src/main/resources/scrabblewords.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				dictionarySet.add(scanner.nextLine());
			}
			scanner.close();
			return dictionarySet;
		}
		return dictionarySet;
	}

}
