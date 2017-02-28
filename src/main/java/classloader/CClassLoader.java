package classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Andrey_Bindyuk on 2/28/2017.
 */
public class CClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("semaphore.SemaphoreTest")) {
            try {
                InputStream is = CClassLoader.class.getClassLoader().getResourceAsStream("semaphore/SemaphoreTest.class");
                byte[] buf = new byte[10000];
                int len = is.read(buf);
                return defineClass(name, buf, 0, len);
            } catch (IOException e) {
                throw new ClassNotFoundException("", e);
            }
        }
        return getParent().loadClass(name);
    }
}
