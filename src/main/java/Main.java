import classloader.CClassLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Andrey_Bindyuk on 2/27/2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        while (true) {

            ClassLoader loader= new CClassLoader(new String[] {"D:\\MENTORINGAPPLICATIONS"});
            Class clazz= Class.forName("SemaphoreTest",true,loader);
            Object object= clazz.newInstance();
            System.out.println(object);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }

    }
}
