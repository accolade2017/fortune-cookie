package jp.co.accolade.ft.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 認証不要.
 * @author ksato
 *
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface NoAuthentication {

}
