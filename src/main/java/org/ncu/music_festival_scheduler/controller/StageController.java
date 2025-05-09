package org.ncu.music_festival_scheduler.controller;
import org.ncu.music_festival_scheduler.entity.Stage;
import org.ncu.music_festival_scheduler.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping("/get_stages")
    public List<Stage> getAllStages() {
        return stageService.getAllStages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        return stageService.getStageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Stage> getStageByName(@PathVariable String name) {
        Stage stage = stageService.getStageByName(name);
        return stage != null ? ResponseEntity.ok(stage) : ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public Stage createStage(@RequestBody Stage stage) {
        return stageService.createStage(stage);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody Stage updatedStage) {
        Stage stage = stageService.updateStage(id, updatedStage);
        return stage != null ? ResponseEntity.ok(stage) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }
}
