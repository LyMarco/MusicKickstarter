package team11.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

// * Need more information for storing music data or others.
public class SerializationBase extends Activity {
	// path to store all the data
	private String path = "/storage/emulated/0/Android/data/team11.pack/cache/";
	
	// Basic serialization for any object
	public <T> void saveObject(T o, String filename) {
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
            Log.v("Serialization Save Error : ", e.getMessage());
            e.printStackTrace();
        }
    }

	// For note? lyrics?
	public void saveStringList(ArrayList<String> s, String filename){
		saveObject(s, filename);
    }

	// Basic deserialization for any object
	public Object loadObject(File filename)
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
        Log.v("Serialization Read Error : ",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
	
	// For note or lyrics again
	@SuppressWarnings("unchecked")
	public Object loadStringList(File filename) {
		ArrayList<String> s = new ArrayList<String>();
		return (ArrayList<String>) loadObject(filename);
	}
	
	// Load for spectic type of data
	@SuppressWarnings("unchecked")
	public <T> T genericLoad(File filename,T t) {
		t = (T) loadObject(filename);
		return t;
	}
	
	// Helper function to create path
	public String pathGenerator(String username,
			String songname,
			String type,
			String... more) {
		String s = "";
		if (more != null) {
			for (String m : more) {
				s += "/" + m;
			}
		}
		return username + '/' + songname + '/' + type + '/' + s;
	}
	
}
