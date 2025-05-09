package org.ncu.music_festival_scheduler.service;
import org.ncu.music_festival_scheduler.entity.Performance;
import org.ncu.music_festival_scheduler.exception.SchedulingConflictException;
import org.ncu.music_festival_scheduler.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;



    // Core logic for scheduling with conflict detection
    public Performance schedulePerformance(Performance performance) {
        LocalDate date = performance.getPerformanceDate();
        LocalTime start = performance.getStartTime();
        LocalTime end = performance.getEndTime();

        // Stage conflict check
        List<Performance> stagePerformances = performanceRepository
                .findByStage_StageIdAndPerformanceDate(performance.getStage().getStageId(), date);

        for (Performance existing : stagePerformances) {
            if (timeOverlap(start, end, existing.getStartTime(), existing.getEndTime())) {
                throw new SchedulingConflictException("Stage conflict: This stage is already booked during this time.");
            }
        }

        // Artist conflict check
        List<Performance> artistPerformances = performanceRepository
                .findByArtist_ArtistIdAndPerformanceDate(performance.getArtist().getArtistId(), date);

        for (Performance existing : artistPerformances) {
            if (timeOverlap(start, end, existing.getStartTime(), existing.getEndTime())) {
                throw new SchedulingConflictException("Artist conflict: This artist is already performing at this time.");
            }
        }

        return performanceRepository.save(performance);
    }

    // Utility: Time overlap logic
    private boolean timeOverlap(LocalTime newStart, LocalTime newEnd,
                                LocalTime existingStart, LocalTime existingEnd) {
        return !newEnd.isBefore(existingStart) && !newStart.isAfter(existingEnd);
    }



    // Group performances by stage
    public Map<String, List<Performance>> getScheduleGroupedByStage() {
        List<Performance> allPerformances = performanceRepository.findAll();
        return allPerformances.stream()
                .collect(Collectors.groupingBy(performance ->
                        performance.getStage().getStageName()));
    }

    // Group performances by stage for a specific date
    public Map<String, List<Performance>> getScheduleByDateGroupedByStage(LocalDate date) {
        List<Performance> performances = performanceRepository.findByPerformanceDate(date);
        return performances.stream()
                .collect(Collectors.groupingBy(performance ->
                        performance.getStage().getStageName()));
    }




    // Get all performances
    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    // Get performance by ID
    public Optional<Performance> getPerformanceById(Long id) {
        return performanceRepository.findById(id);
    }

    // Get performances by artist name
    public List<Performance> getPerformancesByArtist(String artistName) {
        return performanceRepository.findByArtist_ArtistName(artistName);
    }

    // Get performances by stage name
    public List<Performance> getPerformancesByStage(String stageName) {
        return performanceRepository.findByStage_StageName(stageName);
    }

    // Get performances on a specific date
    public List<Performance> getPerformancesByDate(LocalDate performanceDate) {
        return performanceRepository.findByPerformanceDate(performanceDate);
    }

    // Create a new performance
    public Performance createPerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    // Update an existing performance
    public Performance updatePerformance(Long id, Performance updatedPerformance) {
        if (performanceRepository.existsById(id)) {
            updatedPerformance.setPerformanceId(id);
            return performanceRepository.save(updatedPerformance);
        }
        return null;  // Or throw custom exception for performance not found
    }

    // Delete performance
    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }


    public void deleteAllPerformances() {
       performanceRepository.deleteAll();
    }



}
