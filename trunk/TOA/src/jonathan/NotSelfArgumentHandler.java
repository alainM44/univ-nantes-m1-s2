/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathan;


import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * @author E089044M
 */
public class NotSelfArgumentHandler implements InvocationHandler {

    Object target;

    public NotSelfArgumentHandler(Object o) {
        target = o;
    }

    @Override
    public Object invoke(Object o, Method m, Object[] os) throws Exception {
        boolean existe = false;
        System.out.println(m.getName());
        if (m.getAnnotation(NotSelfArgument.class) != null) {
            for (Object arg : os) {
                if (arg.equals(o)|| arg.equals(target)) {
                    throw new RuntimeException();
                }
            }
        }

        return m.invoke(target, os);
    }
}
