package jp.co.accolade.ft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * logincontroller
 * @author m-nis
 *
 */
@Controller
public class LoginController {
/**
 * 初期表示
 *
 */
	@GetMapping("/login")
	public String index(Model model) {
		return "login";
	}
}