package jp.co.accolade.ft.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;
/**
*
* @author takaa
*
*/
@Controller
public class SummaryController {
   /**
    *
    */
      @Autowired
      private JdbcTemplate jdbcTemplate;
      //取得用SQL
      private static final String GET_SQL = "select count(*) as count, us.login_id, sum(frs.score) as score from  fortunes frs inner join users_fortunes ufs on frs.fortune_id = ufs.fortune_id inner join users us on ufs.user_id = us.user_id where SUBSTR(ufs.fortune_ymd, 0, 6) = '%s' group by us.login_id,SUBSTR(ufs.fortune_ymd, 0, 6)" ;
      //プルダウン用
      private static final String GET_YMD = "select substr(FORTUNE_YMD,0,6) as fortuneYmd from USERS_FORTUNES  group by substr(FORTUNE_YMD,0,6) ";
      @GetMapping("/summary")
      public String sample(Model model) {
    	  //プルダウン用の日付取得
          List<Sample> ymd = this.jdbcTemplate.query(GET_YMD,
                 new BeanPropertyRowMapper<Sample>(Sample.class));
          ymd.forEach(System.out::println);
          //現在日付を取得
          LocalDateTime date1 = LocalDateTime.now();
          //取得した日付を整形
          String date = DateTimeFormatter.ofPattern("uuuu/MM/dd").format(date1).replaceAll("/", "").substring(0, 6);
          List<Sample> samples = this.jdbcTemplate.query(String.format(GET_SQL, date),
                  new BeanPropertyRowMapper<Sample>(Sample.class));
          //日付プルダウンをセット
          model.addAttribute("ymd", ymd);
          //現在日付をセット
          model.addAttribute("nowDate",date);
          //現在日付の結果をセット
          model.addAttribute("result",samples);

          return "summary";
      }
      @GetMapping("/test")
      public String test(@RequestParam("select") String linkselect,Model model) {
          List<Sample> samples = this.jdbcTemplate.query(String.format(GET_SQL, linkselect),
                       new BeanPropertyRowMapper<Sample>(Sample.class));
          samples.forEach(System.out::println);
          List<Sample> ymd = this.jdbcTemplate.query(GET_YMD,
                 new BeanPropertyRowMapper<Sample>(Sample.class));
          ymd.forEach(System.out::println);
          model.addAttribute("ymd", ymd);
          model.addAttribute("result", samples);
          model.addAttribute("selected",linkselect);
          samples.forEach(System.out::println);
          return "summary";
      }
      @Data
      static public class Sample {
          private Long userId;
//            public Long getUserId() { return this.userId; }
//            public void setUserId(Long UserId) { this.UserId = UserId; }
          private int count;
          private String loginId;
//            public String getLoginId() { return this.loginId; }
//            public void setLoginId(String loginId) { this.loginId = loginId; }
          private  String password;
//            public String getPassword() { return this.password; }
//            public void setPassword(String password) { this.password = password; }
          private  String userType;
//          public String getUserType() { return this.userType; }
//          public void setPassword(String userType) { this.userType = userType; }
          private  Date createdAt;
//          public Date getCreatedAt() { return this.createdAt; }
//          public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
          private int score;
//            public int getScore() {return this.score;}
//            public void setScore(int score) {this.score = score;}
          private String fortuneYmd;
//            public int getScore() {return this.score;}
//            public void setScore(int score) {this.score = score;}
      }
}