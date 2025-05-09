package org.ncu.music_festival_scheduler.repository;
import org.ncu.music_festival_scheduler.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    // Custom query methods (if any) can be added here, such as finding artists by name or genre.
    Artist findByArtistName(String artistName);
}

