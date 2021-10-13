package com.mergle.challenge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.merkle.challenge.DictionaryBuilder;
import com.merkle.challenge.DictionaryWalk;

public class DictionaryWalkTest {

	@Test
	// Testing walk through hate -> love
	public void test_DictionaryWalk_1() throws FileNotFoundException {

		DictionaryWalk solution = new DictionaryWalk();
		String start = "hate";
		String end = "love";

		List<List<String>> result = solution.walkThroughDictionary(start, end);
		List<String> expectedResult = List.of("hate", "have", "hove", "love");
		assertTrue(result.contains(expectedResult));
	}

	@Test
	// Testing walk through dogs -> wolves
	public void test_DictionaryWalk_2() throws FileNotFoundException {

		DictionaryWalk solution = new DictionaryWalk();
		String start = "dogs";
		String end = "wolves";

		List<List<String>> result = solution.walkThroughDictionary(start, end);
		List<String> expectedResult = List.of("dogs", "does", "doles", "soles", "solves", "wolves");
		assertTrue(result.contains(expectedResult));
	}

	@Test
	// Testing walk through man -> woman
	public void test_DictionaryWalk_3() throws FileNotFoundException {

		DictionaryWalk solution = new DictionaryWalk();
		String start = "man";
		String end = "woman";

		List<List<String>> result = solution.walkThroughDictionary(start, end);
		List<String> expectedResult = List.of("man", "ran", "roan", "roman", "woman");
		assertTrue(result.contains(expectedResult));
	}

	@Test
	// Testing walk through flour -> flower
	public void test_DictionaryWalk_4() throws FileNotFoundException {

		DictionaryWalk solution = new DictionaryWalk();
		String start = "flour";
		String end = "flower";

		List<List<String>> result = solution.walkThroughDictionary(start, end);
		List<String> expectedResult = List.of("flour", "lour", "dour", "doer", "dower", "lower", "flower");
		assertTrue(result.contains(expectedResult));
	}

}
