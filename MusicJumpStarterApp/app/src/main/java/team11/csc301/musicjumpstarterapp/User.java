package team11.csc301.musicjumpstarterapp;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Terrence_Z on 2018/3/5.
 */

public class User implements Serializable{
    private String username;
    private HashSet<Song> songs = new HashSet<Song>();
    public User(String name) {
        this.username = name;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public Song getSong(String songName) {
        for (Song s : songs) {
            if (s.getSongname().equals(songName)) {
                return s;
            }
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashSet<Song> getSongs() {
        return songs;
    }

    public void setSongs(HashSet<Song> songs) {
        this.songs = songs;
    }
}
