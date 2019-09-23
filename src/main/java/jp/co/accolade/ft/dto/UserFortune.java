package jp.co.accolade.ft.dto;

import lombok.Data;

/**
 * ユーザー占い結果.
 * @author ksato
 */
@Data
public class UserFortune {
    /** ユーザーID. */
    private Long userId;
    /** 占った年月日. */
    private String fortuneYmd;
    /** 対応する占い. */
    private Long fortuneId;
}
