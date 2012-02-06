/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author E089044M
 */
@Target (ElementType.METHOD)
@Retention (RetentionPolicy.RUNTIME)
public @interface NotSelfArgument {
    
}
