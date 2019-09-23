package jp.co.accolade.ft.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.accolade.ft.dto.Fortune;
import jp.co.accolade.ft.dto.UserFortune;

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

    /** JDBCテンプレート. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 占い処理.
     * @return
     */
    @PostMapping("/fortune")
    public String fortuneTelling(Model model) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("uuuuMMdd"));
        List<Long> fortuneIds = this.jdbcTemplate.queryForList("SELECT fortune_id FROM users_fortunes WHERE user_id = ? AND fortune_ymd = ?"
                ,new Object[]{this.session.getAttribute("userId"), today}, Long.class);
        Optional<Long> fortuneId = fortuneIds.stream().findFirst();
        if (!fortuneId.isPresent()) {
            // ない場合：登録する
            List<Long> allFortuneIds = this.jdbcTemplate.queryForList("SELECT fortune_id FROM fortunes WHERE enabled = 1", Long.class);
            if (allFortuneIds == null || allFortuneIds.isEmpty()) {
                throw new RuntimeException("占いマスタに設定がありません.");
            }
            Collections.shuffle(allFortuneIds);
            fortuneId = Optional.of(allFortuneIds.get(0));

            UserFortune userFortune = new UserFortune();
            userFortune.setUserId((Long)this.session.getAttribute("userId"));
            userFortune.setFortuneYmd(today);
            userFortune.setFortuneId(fortuneId.get());

            SqlParameterSource param = new BeanPropertySqlParameterSource(userFortune);
            SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("users_fortunes");
            insert.execute(param);
        }
        List<Fortune> fortunes = this.jdbcTemplate.query("SELECT * FROM fortunes WHERE fortune_id = ?", new Object[]{fortuneId.get()}, new BeanPropertyRowMapper<Fortune>(Fortune.class));
        Fortune fortune = fortunes.stream().findFirst().orElse(new Fortune());
        model.addAttribute("fortune", fortune);
        return "fortune";
    }


}
