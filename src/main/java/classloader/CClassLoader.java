package classloader;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey_Bindyuk on 2/28/2017.
 */
public class CClassLoader extends ClassLoader {
    private Map classesHash = new HashMap();
    public final String[] classPath;

    public CClassLoader(String[] classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result= findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class result = (Class)classesHash.get(name);
        if (result!=null) {
            return result;
        }
        File f= findFile(name.replace('.','/'),".class");
        if (f==null) {
            return findSystemClass(name);
        }

        try {
            byte[] classBytes= loadFileAsBytes(f);
            result= defineClass(name,classBytes,0,classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Cannot load class "+name+": "+e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class "+name+": "+e);
        }
        classesHash.put(name,result);
        return result;
    }

    private File findFile(String name, String extension)
    {
        for (int k=0; k <classPath.length; k++) {
            File f= new File((new File(classPath[k])).getPath() +
                    File.separatorChar + name.replace('/',File.separatorChar) + extension);
            if (f.exists())
                return f;
        }
        return null;
    }

    public static byte[] loadFileAsBytes(File file) throws IOException{
        byte[] result = new byte[(int)file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result,0,result.length);
        }finally {
            try {
                f.close();
            }
            catch (Exception e) {
            }
        }
        return result;
    }
}
