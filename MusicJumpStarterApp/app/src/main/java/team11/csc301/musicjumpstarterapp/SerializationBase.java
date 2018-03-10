package team11.csc301.musicjumpstarterapp;

/**
 * Created by Terrence_Z on 2018/2/28.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.content.Context;

// * Need more information for storing music data or others.
public class SerializationBase extends Activity {

    // Helper method to save data in onStop
    public static void saveStop(HashSet<Song> songs, String songname)
            throws IOException, ClassNotFoundException {
        String songsfile = "/songs.ser";
        String songfile = "/song.ser";
        saveObject(songs, songsfile);
        saveObject(songname, songfile);
    }

    //
    public static boolean isFile(String filename) {
        File f = new File( Lyrics.sPath + filename);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    // Basic serialization for any object
    public static <T> void saveObject(T o, String filename) {
        String fullFilename =  Lyrics.sPath+ filename;
        Log.d("Save:", fullFilename);
        try
        {
            FileOutputStream fos= new FileOutputStream(new File(fullFilename));
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
        String fullFilename = Lyrics.sPath + filename;
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
        return '/' + song.getSongname() + '/' + type + s;
    }

}