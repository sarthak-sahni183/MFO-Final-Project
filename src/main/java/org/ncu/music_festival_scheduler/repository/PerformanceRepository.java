package org.ncu.music_festival_scheduler.repository;
import org.ncu.music_festival_scheduler.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    // Find all performances for a given artist
    List<Performance> findByArtist_ArtistName(String artistName);

    // Find all performances on a specific date
    List<Performance> findByPerformanceDate(LocalDate performanceDate);

    // Find all performances for a given stage
    List<Performance> findByStage_StageName(String stageName);

    // Find all performances on a given stage and date
    List<Performance> findByStage_StageIdAndPerformanceDate(Long stageId, LocalDate performanceDate);

    // Find all performances for an artist on a given date
    List<Performance> findByArtist_ArtistIdAndPerformanceDate(Long artistId, LocalDate performanceDate);


    

}
