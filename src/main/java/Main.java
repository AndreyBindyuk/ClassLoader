import classloader.CClassLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Andrey_Bindyuk on 2/27/2017.
 */
public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        String input;
        while (true) {
            System.out.println("Type directory path: ");
            input = br.readLine();
            ClassLoader loader = new CClassLoader(new String[]{input});
            System.out.println("enter Class name without extension: ");
            input = br.readLine();
            Class clazz = Class.forName(input, true, loader);
            Object object = clazz.newInstance();
            System.out.println(object);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }

    }
}
