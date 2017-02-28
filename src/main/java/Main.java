import classloader.CClassLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Andrey_Bindyuk on 2/27/2017.
 */
public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        while (true) {
            Class cls = new CClassLoader().loadClass("semaphore.SemaphoreTest");
            Object obj = cls.newInstance();
            cls.getMethod("lever").invoke(obj);
            Thread.sleep(10000);

//            try {
//                System.out.println("type Class path for loading");
//                String input = br.readLine();
//                Class<?> c = customClassLoader.findClass(input);
//                Object ob = c.newInstance();
//                Method md = c.getMethod("lever");
//                md.invoke(ob);
//                System.out.println("success");
//            } catch (Exception e) {
//                System.out.println("error");
//            }
        }

    }
}
