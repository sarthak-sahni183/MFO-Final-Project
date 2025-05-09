package org.ncu.music_festival_scheduler.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceId;

    private LocalDate performanceDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    public Performance() {}

    public Performance(LocalDate performanceDate, LocalTime startTime, LocalTime endTime, Artist artist, Stage stage) {
        this.performanceDate = performanceDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.stage = stage;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public LocalDate getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(LocalDate performanceDate) {
        this.performanceDate = performanceDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public String toString() {
        return "Performance{" +
                "performanceId=" + performanceId +
                ", performanceDate=" + performanceDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", artist=" + artist +
                ", stage=" + stage +
                '}';
    }

    public void setDate(LocalDate now) {
    }
}
