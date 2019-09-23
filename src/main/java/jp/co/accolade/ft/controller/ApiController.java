package jp.co.accolade.ft.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.accolade.ft.annotations.NoAuthentication;
import jp.co.accolade.ft.dto.Fortune;
import jp.co.accolade.ft.dto.FortuneResponse;
import jp.co.accolade.ft.service.FortuneService;

/**
 * WebAPIコントローラー.
 * @author ksato
 *
 */
@RestController
public class ApiController {

    /** サービスクラス. */
    @Autowired
    private FortuneService service;

    /** JDBCテンプレート. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 占い.
     * @return
     */
    @PostMapping(value = "/api/fortune"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @NoAuthentication
    public FortuneResponse fortuneTellingApi(@RequestBody MultiValueMap<String, Object> params) {
        for (Entry<String, List<Object>> entry : params.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().stream().forEach(System.out::println);
        }
        String userName = (String)params.getFirst("user_name");
        List<Long> userIds = this.jdbcTemplate.queryForList("SELECT user_id FROM users WHERE slack_user_name = ?", new Object[] {userName}, Long.class);
        if (userIds == null || !userIds.stream().findFirst().isPresent()) {
            return this.createFortune("占い失敗", "ユーザーがいません");
        }
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("uuuuMMdd"));
        Fortune fortune = this.service.fortuneTelling(userIds.stream().findFirst().get(), today);
        return this.createFortune("占い結果", fortune.getMessage());
    }

    /**
     * メッセージの作成.
     * @param title
     * @param value
     * @return
     */
    private FortuneResponse createFortune(String title, String value) {
        FortuneResponse resp = new FortuneResponse();
        resp.setText(title);
        FortuneResponse contents = new FortuneResponse();
        contents.setText(value);
        resp.addAttachment(contents);
        return resp;
    }

}
