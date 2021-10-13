package com.merkle.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.merkle.challenge.DictionaryWalk;
import com.merkle.challenge.input.Input;

@Controller
public class DictionaryWalkController {

	private DictionaryWalk dictionaryWalk;
	
	@Autowired
	public DictionaryWalkController(DictionaryWalk dictionaryWalk) {
		super();
		this.dictionaryWalk = dictionaryWalk;
	}

	@GetMapping("/index")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("input", new Input());
		model.addAttribute("name", name);
		return "index";
	}

	@PostMapping("/greeting")
	public String getWordPath(@ModelAttribute Input input,
			Model model) {
		
		List<List<String>> words = dictionaryWalk.walkThroughDictionary(input.getStartWord(), input.getEndWord());
		model.addAttribute("words", words);
		return "result";
	}

}
