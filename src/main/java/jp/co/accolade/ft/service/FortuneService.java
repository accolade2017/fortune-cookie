package jp.co.accolade.ft.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import jp.co.accolade.ft.dto.Fortune;
import jp.co.accolade.ft.dto.UserFortune;

/**
 * 占いサービス.
 * @author ksato
 *
 */
@Component
public class FortuneService {

    /** JDBCテンプレート. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 占い処理を行う.
     * @param userId ユーザーID.
     * @param ymd 日付.
     * @return
     */
    public Fortune fortuneTelling(Long userId, String ymd) {
        List<Long> fortuneIds = this.jdbcTemplate.queryForList("SELECT fortune_id FROM users_fortunes WHERE user_id = ? AND fortune_ymd = ?"
                ,new Object[]{userId, ymd}, Long.class);
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
            userFortune.setUserId(userId);
            userFortune.setFortuneYmd(ymd);
            userFortune.setFortuneId(fortuneId.get());

            SqlParameterSource param = new BeanPropertySqlParameterSource(userFortune);
            SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("users_fortunes");
            insert.execute(param);
        }
        List<Fortune> fortunes = this.jdbcTemplate.query("SELECT * FROM fortunes WHERE fortune_id = ?", new Object[]{fortuneId.get()}, new BeanPropertyRowMapper<Fortune>(Fortune.class));
        Fortune fortune = fortunes.stream().findFirst().orElse(new Fortune());
        return fortune;
    }

}
