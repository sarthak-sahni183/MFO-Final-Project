package org.ncu.music_festival_scheduler.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stageId;

    private String stageName;
    private String stageDescription;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Performance> stagePerformances;

    public Stage() {}

    public Stage(String stageName, String stageDescription) {
        this.stageName = stageName;
        this.stageDescription = stageDescription;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageDescription() {
        return stageDescription;
    }

    public void setStageDescription(String stageDescription) {
        this.stageDescription = stageDescription;
    }

    public List<Performance> getStagePerformances() {
        return stagePerformances;
    }

    public void setStagePerformances(List<Performance> stagePerformances) {
        this.stagePerformances = stagePerformances;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "stageId=" + stageId +
                ", stageName='" + stageName + '\'' +
                ", stageDescription='" + stageDescription + '\'' +
                ", stagePerformances=" + stagePerformances +
                '}';
    }
}
