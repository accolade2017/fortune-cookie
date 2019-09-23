package jp.co.accolade.ft.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 占い応答.
 * @author ksato
 *
 */
@Data
public class FortuneResponse {
    /** テキスト. */
    private String text;
    /** 内容. */
    private List<FortuneResponse> attachments = new ArrayList<>();
    /** 内容の追加. */
    public void addAttachment(FortuneResponse attachment) {
        this.attachments.add(attachment);
    }
}
