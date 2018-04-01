package team11.csc301.musicjumpstarterapp;

/**
 * Created by Terrence_Z on 2018/2/28.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.content.Context;

// * Need more information for storing music data or others.
public class SerializationBase extends Activity {

    // Helper method to save data in onStop
    public static void saveStop(Set<Song> songs)
            throws IOException, ClassNotFoundException {
        File directory = new File(Lyrics.sPath);
        directory.mkdirs();
        String songsfile = "songs.ser";
        saveObject(songs, Lyrics.sPath + songsfile);
        for (Song s : songs) {
            String path = pathGenerator(s);
            File d = new File(path + '/');
            d.mkdirs();
            if (s.getVerses() != null) {
                saveLyricsToText(s.getVerses(), s.getTitles(), path + "Lyrics.txt");
            }
        }
    }

    // Basic serialization for any object
    public static <T> void saveObject(T o, String filename) {
        Log.d("Save:", filename);
        try
        {
            FileOutputStream fos= new FileOutputStream(new File(filename));
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

    // For save lyrics
    public static void saveLyricsToText(List<String> l, List<String> t , String filename){
        Log.d("Save:", filename);
        try
        {
            File f = new File(filename);
            FileWriter writer = new FileWriter(f);
            for (int i = 0; i < l.size(); i++) {
                writer.write(t.get(i) + "\n");
                writer.write(l.get(i) + "\n");
            }
            writer.close();
        }
        catch(Exception e)
        {
            Log.v("Serial_Save Error : ", e.getMessage());
            e.printStackTrace();
        }
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

    // Load for spectic type of data
    @SuppressWarnings("unchecked")
    public static <T> T genericLoad(String filename,T t) {
        t = (T) loadObject(filename);
        return t;
    }

    // Helper function to create path
    public static String pathGenerator(Song song) {
        if (song.getSongname().equals("")) {
            song.setSongname("Default");
        }
        return Lyrics.sPath + song.getSongname() + '/';
    }

}