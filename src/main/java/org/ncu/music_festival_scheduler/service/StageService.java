package org.ncu.music_festival_scheduler.service;
import org.ncu.music_festival_scheduler.entity.Stage;
import org.ncu.music_festival_scheduler.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    // Get all stages
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    // Get stage by ID
    public Optional<Stage> getStageById(Long id) {
        return stageRepository.findById(id);
    }

    // Get stage by name
    public Stage getStageByName(String stageName) {
        return stageRepository.findByStageName(stageName);
    }

    // Create a new stage
    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    // Update an existing stage
    public Stage updateStage(Long id, Stage updatedStage) {
        if (stageRepository.existsById(id)) {
            updatedStage.setStageId(id);
            return stageRepository.save(updatedStage);
        }
        return null;  // Or throw custom exception for stage not found
    }

    // Delete stage
    public void deleteStage(Long id) {
        stageRepository.deleteById(id);
    }
}
