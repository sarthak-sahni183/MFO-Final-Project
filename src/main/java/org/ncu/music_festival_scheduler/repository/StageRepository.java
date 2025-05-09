package org.ncu.music_festival_scheduler.repository;
import org.ncu.music_festival_scheduler.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    // Custom query methods (if any) can be added here, such as finding a stage by its name.
    Stage findByStageName(String stageName);
}
