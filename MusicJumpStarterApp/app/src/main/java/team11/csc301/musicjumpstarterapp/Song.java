package team11.csc301.musicjumpstarterapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Terrence_Z on 2018/3/4.
 */

// The class represent a song
public class Song implements Serializable{
    private User user;
    private String songname;
    private ArrayList<String> verses;
    private ArrayList<String> titles;
    private ArrayList<String> notes;
    // Variable of Audio

    // Init Constructor
    public Song(User user, String songname) {
        this.user = user;
        this.songname = songname;
        this.verses = new ArrayList<String>();
        this.titles = new ArrayList<String>();
        this.notes = new ArrayList<String>();
        //this.metronome = null;
    }

    public User getUser() {
        return user;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public ArrayList<String> getVerses() {
        return verses;
    }

    public void setVerses(ArrayList<String> verses) {
        this.verses = verses;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

}
