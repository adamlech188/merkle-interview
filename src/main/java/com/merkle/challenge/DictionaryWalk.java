package com.merkle.challenge;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DictionaryWalk {

	private Set<String> dict;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public DictionaryWalk() {
		try {
			dict = DictionaryBuilder.buildScrabbleWordsSet();
		} catch (FileNotFoundException e) {
			logger.error("File was not found");
		}
	}

	public List<List<String>> walkThroughDictionary(String start, String end) {
		List<List<String>> res = new ArrayList<List<String>>();
		HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for
																									// every node
		HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
		ArrayList<String> solution = new ArrayList<String>();

		dict.add(end);
		bfs(start, end, dict, nodeNeighbors, distance);
		dfs(start, end, dict, nodeNeighbors, distance, solution, res);
		return res;
	}

	// BFS: Trace every node's distance from the start node (level by level).
	private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,
			HashMap<String, Integer> distance) {
		for (String str : dict)
			nodeNeighbors.put(str, new ArrayList<String>());

		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		distance.put(start, 0);

		while (!queue.isEmpty()) {
			int count = queue.size();
			boolean foundEnd = false;
			for (int i = 0; i < count; i++) {
				String cur = queue.poll();
				int curDistance = distance.get(cur);
				ArrayList<String> neighbors = getNeighbors(cur, dict);

				for (String neighbor : neighbors) {
					nodeNeighbors.get(cur).add(neighbor);
					if (!distance.containsKey(neighbor)) {// Check if visited
						distance.put(neighbor, curDistance + 1);
						if (end.equals(neighbor))// Found the shortest path
							foundEnd = true;
						else
							queue.offer(neighbor);
					}
				}
			}

			if (foundEnd)
				break;
		}
	}

	// Find all next level nodes.
	private ArrayList<String> getNeighbors(String node, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char chs[] = node.toCharArray();

		for (char ch = 'a'; ch <= 'z'; ch++) {
			for (int i = 0; i < chs.length; i++) {
				if (chs[i] == ch)
					continue;
				char old_ch = chs[i];
				chs[i] = ch;
				if (dict.contains(String.valueOf(chs))) {
					res.add(String.valueOf(chs));
				}
				chs[i] = old_ch;
			}

		}
		for (char ch = 'a'; ch <= 'z'; ch++) {
			for (int i = 0; i <= chs.length; i++) {
				List<Character> tempList = charArrayAsList(chs);
				tempList.add(i, ch);
				char[] tempChar = characterListToArray(tempList);
				if (dict.contains(String.valueOf(tempChar))) {
					res.add(String.valueOf(tempChar));
				}
			}

		}
		for (int i = 0; i < chs.length; i++) {
			List<Character> tempList = charArrayAsList(chs);
			tempList.remove(i);
			char[] tempChar = characterListToArray(tempList);
			if (dict.contains(String.valueOf(tempChar))) {
				res.add(String.valueOf(tempChar));
			}
		}
		return res;
	}

	// DFS: output all paths with the shortest distance.
	private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,
			HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
		solution.add(cur);
		if (end.equals(cur)) {
			res.add(new ArrayList<String>(solution));
		} else {
			for (String next : nodeNeighbors.get(cur)) {
				if (distance.get(next) == distance.get(cur) + 1) {
					dfs(next, end, dict, nodeNeighbors, distance, solution, res);
				}
			}
		}
		solution.remove(solution.size() - 1);
	}

	private List<Character> charArrayAsList(char[] chars) {
		List<Character> list = new LinkedList<>();
		for (int i = 0; i < chars.length; i++) {
			list.add(chars[i]);
		}
		return list;
	}

	private char[] characterListToArray(List<Character> list) {
		int arrayLength = list.size();
		char[] charArray = new char[arrayLength];

		for (int i = 0; i < arrayLength; i++) {
			charArray[i] = list.get(i);
		}
		return charArray;
	}
}
