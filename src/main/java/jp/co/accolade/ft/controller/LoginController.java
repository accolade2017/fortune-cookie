package jp.co.accolade.ft.controller;

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
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.accolade.ft.controller.SampleController.Sample;

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


    /**
     * 初期表示処理.
     */
    @GetMapping("")
    public String index() {
        return "login";
    }

    /**
     * ログイン処理.
     */
	@PostMapping("/login")
    public String login(Model model) {
//        System.out.println("Hello");
//        this.jdbcTemplate.

        List<Sample> samples = this.jdbcTemplate.query("SELECT * FROM sample",
                new BeanPropertyRowMapper<Sample>(Sample.class));
        model.addAttribute("result", samples);


        samples.forEach(System.out::println);

        Sample aSample = new Sample();
        aSample.setId(99L);
        aSample.setName("Tanaka");
        aSample.setAge(44);
        SqlParameterSource param = new BeanPropertySqlParameterSource(aSample);

        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("sample");
        insert.execute(param);

        return "login";
	}
}