/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathan;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.lang.reflect.Proxy;

/**
 *
 * @author E089044M
 */
public class TOA {

    static String setterName(String m) {
        return "set" + m.substring(0, 1).toUpperCase() + m.substring(1);
    }
    
    static Object converte(Class<?> uneClass, Object source) throws Exception{
        Object result = uneClass.newInstance();
        for(Method m: uneClass.getMethods() ){
            if(m.getName().startsWith("set")){
                Method unGetter= findGetter(source.getClass(),m);
                if(unGetter!=null){
                    Object invoke = m.invoke(result, unGetter.invoke(source, null));
                }
            }
        }
        return result;
    }

    static Method findGetter(Class<?> uneClass, Method setter) throws NoSuchMethodException{
        Method result = uneClass.getMethod("g"+setter.getName().substring(1), null);
        return result;
    } 
   static  Object chargerClass(String path) throws Exception{
        Properties p = new Properties();
        p.load(new FileReader(path));
        Class<?> c = Class.forName((String) p.get("Class"));
        Object o = c.newInstance();
        for (Object k : p.keySet()) {
            if (!k.equals("Class")) {
                Method m = c.getMethod(setterName((String) k), String.class);
                Object invoke = m.invoke(o, (String) p.get(k));
            }
        }
        return o;
    }
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

//       Object o = chargerClass("/comptes/E089044M/properties.txt");
      //Ipersonne pers = (Personne) o;
        Animal a = new Animal();
        System.out.println(a.getNom());
       Ipersonne pers= (Ipersonne) new Personne("Bizet","Jonathan",20);
       for (Class<?> i : pers.getClass().getInterfaces())
            System.out.println(i.toString());
       pers= (Ipersonne) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),pers.getClass().getInterfaces(),new NotSelfArgumentHandler(pers)) ;

       pers.uneMethode(pers, a);
    }
}
