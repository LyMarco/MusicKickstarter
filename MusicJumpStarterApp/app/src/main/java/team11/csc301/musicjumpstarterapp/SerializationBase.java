package team11.csc301.musicjumpstarterapp;

/**
 * Created by Terrence_Z on 2018/3/4.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

// * Need more information for storing music data or others.
public class SerializationBase extends Activity {
    // path to store all the data
    private static String path = "/sdcard/Android/data/team11.pack/cache/";

    // This method should be in main
    public void mainStart()
            throws IOException, ClassNotFoundException {
        HashSet<User> users = null;
        String userfile = "users.ser";
        users = genericLoad(userfile, new HashSet<User>());
    }

    // This method should be in main
    public void mainStop(HashSet<User> users)
            throws IOException, ClassNotFoundException {
        String userfile = "users.ser";
        saveObject(users, userfile);
    }

    //
    public static boolean isFile(String filename) {
        File f = new File(path + filename);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    // Basic serialization for any object
    public static <T> void saveObject(T o, String filename) {
        String fullFilename = path + filename;
        try
        {
            FileOutputStream fos= new FileOutputStream(fullFilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch(Exception e)
        {
            Log.v("Serial_Save Error : ", e.getMessage());
            e.printStackTrace();
        }
    }

    // For note? lyrics?
    public static void saveStringList(ArrayList<String> s, String filename){
        saveObject(s, filename);
    }

    // Basic deserialization for any object
    public static Object loadObject(String filename)
    {
        String fullFilename = path + filename;
        try
        {
            FileInputStream fis = new FileInputStream(fullFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            ois.close();
            fis.close();
            return o;
        }
        catch(Exception e)
        {
            Log.v("Serial_Read Error : ",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // For note or lyrics again
    @SuppressWarnings("unchecked")
    public static Object loadStringList(String filename) {
        ArrayList<String> s = new ArrayList<String>();
        return (ArrayList<String>) loadObject(filename);
    }

    // Load for spectic type of data
    @SuppressWarnings("unchecked")
    public static <T> T genericLoad(String filename,T t) {
        t = (T) loadObject(filename);
        return t;
    }

    // Helper function to create path
    public static String pathGenerator(Song song,
                                String type,
                                String... more) {
        String s = "";
        if (more != null) {
            for (String m : more) {
                s += "/" + m;
            }
        }
        return song.getUser().getUsername() + '/' + song.getSongname() + '/' + type + s;
    }

}
