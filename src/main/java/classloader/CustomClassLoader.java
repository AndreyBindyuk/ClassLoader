package classloader;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by Andrey_Bindyuk on 2/27/2017.
 */
public class CustomClassLoader extends ClassLoader {

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    public CustomClassLoader() {
    }


    @Override
    public Class<?> findClass(String name) {
        byte[] bt = loadClassData(name);
        Class aClass = null;
        try {
            aClass = changeLeverOutput(defineClass(name, bt, 0, bt.length));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return aClass;
    }

    public Class changeLeverOutput(Class c) throws IllegalAccessException, InstantiationException {
        Field privateField;
        try {
            privateField = c.getDeclaredField("str");
            privateField.setAccessible(true);
            privateField.set(c, "hi");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return c;
    }


    private byte[] loadClassData(String className) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/") + ".class");
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        int len;
        try {
            while ((len = is.read()) != -1) {
                byteSt.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteSt.toByteArray();
    }
}
