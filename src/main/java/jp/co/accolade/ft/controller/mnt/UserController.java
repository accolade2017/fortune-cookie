package jp.co.accolade.ft.controller.mnt;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.accolade.ft.annotations.NoAuthentication;
import lombok.Data;

/**
 * ユーザー管理コントローラー.
 */
@Controller
@RequestMapping("/mnt/user")
public class UserController {

    /** JDBCテンプレート. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @NoAuthentication
    @RequestMapping("")
    public String init(Model model) {
        List<User> users = this.searchUsers();
        model.addAttribute("users", users);
        return "/mnt/user";
    }

    @GetMapping("/add")
    public String add() {
        return "/mnt/addUser";
    }

    @PostMapping("/add")
    public String doAdd(Model model, @ModelAttribute("user") User user) {
        user.setCreatedAt(new Date());
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("users").usingGeneratedKeyColumns("user_id");
        insert.execute(param);
        return init(model);
    }

    private List<User> searchUsers() {
        List<User> users = this.jdbcTemplate.query("SELECT * FROM Users",
                new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    public String edit() {
        return null;
    }

    public String delete() {
        return null;
    }

    @Data
    public static class User {
        /** ユーザーID. */
        private long userId;
        /** ログインID. */
        private String loginId;
        /** ユーザー名. */
        private String name;
        private String password;
        private String userType;
        private Date createdAt;
    }

}
