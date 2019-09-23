package jp.co.accolade.ft.dto;

import lombok.Data;

/**
 * 占い.
 * @author ksato
 *
 */
@Data
public class Fortune {

    /** ID. */
    private Long fortuneId;
    /** メッセージ. */
    private String message;
    /** スコア. */
    private int score;
    /** パス. */
    private String path;

}
