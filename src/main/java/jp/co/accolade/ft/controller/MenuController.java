package jp.co.accolade.ft.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニュー処理のコントローラー.
 * @author ksato
 *
 */
@Controller
public class MenuController {

    /** セッション情報. */
	@Autowired
	private HttpSession session;

	@GetMapping("/menu")
	public String index(Model model) {
		model.addAttribute("isAdmin", "1".equals(this.session.getAttribute("userType")));
		return "menu";
	}

}
