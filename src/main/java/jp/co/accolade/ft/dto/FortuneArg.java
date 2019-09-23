package jp.co.accolade.ft.dto;

import lombok.Data;

/**
 * 占い要求.
 * @author ksato
 *
 */
@Data
public class FortuneArg {

    /** トークン. */
    private String token;

    /** ユーザー名. */
    private String userName;

}
