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

import lombok.Data;

/**
 * サンプルコントローラー.
 *
 * @author bp.k.sato
 *
 */
@Controller
public class SampleController {

    /** JDBCテンプレート. */
    @Autowired
    private JdbcTemplate jdbcTemplate;


//    @GetMapping("/sample")
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


//    @GetMapping("/")
    public String index(Model model) {
        return "sample";

    }


    @Data
    static public class Sample {
        private Long id;
//        public Long getId() { return this.id; }
//        public void setId(Long id) { this.id = id; }
        private String name;
//        public String getName() { return this.name; }
//        public void setName(String name) { this.name = name; }
        private int age;
//        public Integer getAge() { return this.age; }
//        public void setAge(Integer age) { this.age = age; }
    }


}
