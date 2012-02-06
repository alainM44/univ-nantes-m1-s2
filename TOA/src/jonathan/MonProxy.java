/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jonathan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *
 * @author E089044M
 */
public class MonProxy {
    Object target;
    Object leProxy;
    InvocationHandler h;
   public MonProxy(Object unObject) {
//        super(ClassLoader.getSystemClassLoader(),target.getClass().getInterfaces(),h);
//        h= new NotSelfArgumentHandler(unObject);
  //      leProxy= Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),target.getClass().getInterfaces(),h);
    }



}
