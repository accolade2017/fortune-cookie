package jp.co.accolade.ft.dto;

import java.util.Date;

import lombok.Data;

/**
 * ユーザー.
 * @author ksato
 *
 */
@Data
public class User {

    /** ログインID. */
    private String loginId;

    /** パスワード. */
    private String password;

    /** ユーザーID. */
    private long userId;

    /** ユーザー種別. */
    private String userType;

    /** 名称. */
    private String name;

    /** Slackユーザー名. */
    private String slackUserName;

    private Date createdAt;

}
