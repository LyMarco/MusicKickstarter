package team11.csc301.musicjumpstarterapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terrence_Z on 2018/3/4.
 */

// The class represent a song
public class Song implements Serializable{
    private String songname;
    private List<String> verses;
    private List<String> titles;
    private List<String> notes;
    // Variable of Audio

    // Init Constructor
    public Song(String songname) {
        this.songname = songname;
        this.verses = new ArrayList<String>();
        this.titles = new ArrayList<String>();
        this.notes = new ArrayList<String>();
        //this.metronome = null;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public List<String> getVerses() {
        return verses;
    }

    public void setVerses(ArrayList<String> verses) {
        this.verses = verses;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

}
