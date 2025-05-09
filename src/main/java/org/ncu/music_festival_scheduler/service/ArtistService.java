package org.ncu.music_festival_scheduler.service;
import org.ncu.music_festival_scheduler.entity.Artist;
import org.ncu.music_festival_scheduler.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // Get all artists
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    // Get artist by ID
    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    // Get artist by name
    public Artist getArtistByName(String artistName) {
        return artistRepository.findByArtistName(artistName);
    }

    // Create a new artist
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    // Update an existing artist
    public Artist updateArtist(Long id, Artist updatedArtist) {
        if (artistRepository.existsById(id)) {
            updatedArtist.setArtistId(id);
            return artistRepository.save(updatedArtist);
        }
        return null;  // Or throw custom exception for artist not found
    }

    // Delete artist
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}
