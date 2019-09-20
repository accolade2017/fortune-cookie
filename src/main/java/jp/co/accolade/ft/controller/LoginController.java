package jp.co.accolade.ft.controller;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.accolade.ft.controller.SampleController.Sample;

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
	@GetMapping("/sample")
    public String sample(Model model) {
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



        return "sample";
	}
}