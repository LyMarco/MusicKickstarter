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
    private ArrayList<String> lyrics;
    private ArrayList<String> notes;
    // private Metronome metronome;
    // Variable of Audio

    // Init Constructor
    public Song(User user, String songname) {
        this.user = user;
        this.songname = songname;
        this.lyrics = null;
        this.notes = null;
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

    public ArrayList<String> getLyrics() {
        return lyrics;
    }

    public void setLyrics(ArrayList<String> lyrics) {
        this.lyrics = lyrics;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    //public Metronome getMetronome() {
    //    return metronome;
    //}

    //public void setMetronome(Metronome metronome) {
    //    this.metronome = metronome;
    //}
}
