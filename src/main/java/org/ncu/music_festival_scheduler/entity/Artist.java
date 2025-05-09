package org.ncu.music_festival_scheduler.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    private String artistName;
    private String musicGenre;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Performance> artistPerformances;

    public Artist() {}

    public Artist(String artistName, String musicGenre) {
        this.artistName = artistName;
        this.musicGenre = musicGenre;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public List<Performance> getArtistPerformances() {
        return artistPerformances;
    }

    public void setArtistPerformances(List<Performance> artistPerformances) {
        this.artistPerformances = artistPerformances;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", musicGenre='" + musicGenre + '\'' +
                ", artistPerformances=" + artistPerformances +
                '}';
    }
}
