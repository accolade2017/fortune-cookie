package jp.co.accolade.ft.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.accolade.ft.annotations.NoAuthentication;
import jp.co.accolade.ft.dto.User;

/**
 * logincontroller
 * @author m-nis
 *
 */
@Controller
public class LoginController {

    /** JDBCテンプレート. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** セッション. */
    @Autowired
    private HttpSession session;

    /**
     * 初期表示処理.
     */
    @GetMapping("")
    @NoAuthentication
    public String index() {
        if (this.session.getAttribute("userId") != null) {
            return "redirect:menu";
        }
        return "login";
    }

    /**
     * ログイン処理.
     */
    @PostMapping("/login")
    @NoAuthentication
    public String login(Model model, @RequestParam("loginId") Optional<String> loginId,
            @RequestParam("password") Optional<String> password) {
        if (!loginId.isPresent() || loginId.get().isEmpty()) {
            this.addError(model, "ログインIDが未入力です");
        }
        if (!password.isPresent() || password.get().isEmpty()) {
            this.addError(model, "パスワードが未入力です");
        }
        if (model.containsAttribute("errMsg")) {
            return "login";
        }

        List<User> users = this.jdbcTemplate.query("SELECT * FROM users WHERE login_id = ?"
                , new Object[] {loginId.get()}
                , new BeanPropertyRowMapper<User>(User.class));
        User user = users.stream().findFirst().orElse(null);
        if (user == null || !user.getPassword().equals(password.get())) {
            model.addAttribute("errMsg", "ログインID または パスワードが不正です");
            return "login";
        }

        this.session.setAttribute("userType", user.getUserType());
        this.session.setAttribute("userId", user.getUserId());
        return "redirect:menu";
    }

    /**
     * ログアウト処理.
     * @return
     */
    @GetMapping("/logout")
    @NoAuthentication
    public String logout() {
        Stream.of("userType", "userId").forEach(this.session::removeAttribute);
        return "login";
    }

    /**
     * エラーを追加する.
     * @param model
     * @param message
     */
    private void addError(Model model, String message) {
        model.addAttribute("errMsg", message);
    }
}