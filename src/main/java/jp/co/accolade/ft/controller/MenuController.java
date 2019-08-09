package jp.co.accolade.ft.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@Autowired
	HttpSession session;

	@GetMapping("/menu")
	public String index(Model model) {

		System.out.println("123");


		session.setAttribute("data", "保存したいデータ");

		System.out.println(session.getAttribute("data") );

		model.addAttribute("authority","admin");



		return "menu";

	}

}
