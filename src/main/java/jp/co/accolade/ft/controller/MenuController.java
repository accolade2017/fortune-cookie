package jp.co.accolade.ft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
	@GetMapping("/menu")
	public String	index(Model model) {
		
		return "menu";

	}
}

