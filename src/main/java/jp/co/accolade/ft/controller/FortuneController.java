package jp.co.accolade.ft.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.accolade.ft.dto.Fortune;
import jp.co.accolade.ft.service.FortuneService;

/**
 * 占いコントローラー.
 * @author ksato
 *
 */
@Controller
public class FortuneController {

    /** セッション. */
    @Autowired
    private HttpSession session;

    /** サービスクラス. */
    @Autowired
    private FortuneService service;

    /**
     * 占い処理.
     * @return
     */
    @PostMapping("/fortune")
    public String fortuneTelling(Model model) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("uuuuMMdd"));
        Long userId = (Long)this.session.getAttribute("userId");
        Fortune fortune = this.service.fortuneTelling(userId, today);
        model.addAttribute("fortune", fortune);
        return "fortune";
    }

}
