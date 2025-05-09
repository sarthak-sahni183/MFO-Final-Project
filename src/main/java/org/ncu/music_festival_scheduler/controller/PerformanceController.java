package org.ncu.music_festival_scheduler.controller;
import org.ncu.music_festival_scheduler.entity.Performance;
import org.ncu.music_festival_scheduler.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;




    @GetMapping
    public List<Performance> getAllPerformances() {
        return performanceService.getAllPerformances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable Long id) {
        return performanceService.getPerformanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-artist/{artistName}")
    public List<Performance> getPerformancesByArtist(@PathVariable String artistName) {
        return performanceService.getPerformancesByArtist(artistName);
    }

    @GetMapping("/by-stage/{stageName}")
    public List<Performance> getPerformancesByStage(@PathVariable String stageName) {
        return performanceService.getPerformancesByStage(stageName);
    }

    @GetMapping("/by-date/{date}")
    public List<Performance> getPerformancesByDate(@PathVariable String date) {
        return performanceService.getPerformancesByDate(LocalDate.parse(date));
    }

    @PostMapping("/save")
    public Performance createPerformance(@RequestBody Performance performance) {
        return performanceService.createPerformance(performance);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Performance> updatePerformance(@PathVariable Long id, @RequestBody Performance updatedPerformance) {
        Performance performance = performanceService.updatePerformance(id, updatedPerformance);
        return performance != null ? ResponseEntity.ok(performance) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long id) {
        performanceService.deletePerformance(id);
        return ResponseEntity.noContent().build();
    }

    // BONUS: grouped schedule by date
    @GetMapping("/schedule/date/{date}")
    public List<Performance> getScheduleByDate(@PathVariable String date) {
        return performanceService.getPerformancesByDate(LocalDate.parse(date));
    }

    // BONUS: grouped schedule by stage (you may refactor this later to return a Map<String, List<Performance>>)
    @GetMapping("/schedule/stage/{stageName}")
    public List<Performance> getScheduleByStage(@PathVariable String stageName) {
        return performanceService.getPerformancesByStage(stageName);
    }


    @GetMapping("/schedule/stages")
    public ResponseEntity<Map<String, List<Performance>>> getScheduleGroupedByStage() {
        return ResponseEntity.ok(performanceService.getScheduleGroupedByStage());
    }

    @GetMapping("/schedule/grouped/date/{date}")
    public ResponseEntity<Map<String, List<Performance>>> getScheduleByDateGroupedByStage(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(performanceService.getScheduleByDateGroupedByStage(localDate));
    }



}
