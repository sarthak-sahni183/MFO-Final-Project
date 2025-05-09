
package org.ncu.music_festival_scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ncu.music_festival_scheduler.entity.Artist;
import org.ncu.music_festival_scheduler.entity.Performance;
import org.ncu.music_festival_scheduler.entity.Stage;
import org.ncu.music_festival_scheduler.repository.ArtistRepository;
import org.ncu.music_festival_scheduler.repository.StageRepository;
import org.ncu.music_festival_scheduler.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class MusicFestivalSchedulerApplicationTests {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private StageRepository stageRepository;

	@Autowired
	private PerformanceService performanceService;

	private Artist testArtist;
	private Stage testStage;

	@BeforeEach
	void setUp() {
		performanceService.deleteAllPerformances(); // clean slate
		artistRepository.deleteAll();
		stageRepository.deleteAll();

		testArtist = artistRepository.save(new Artist("Test Artist", "Rock"));
		testStage = stageRepository.save(new Stage("Main Stage", "Open Air"));
	}

	@Test
	void testAddPerformance() {
		Performance performance = new Performance();
		performance.setArtist(testArtist);
		performance.setStage(testStage);
		performance.setPerformanceDate(LocalDate.now());
		performance.setStartTime(LocalTime.of(14, 0));
		performance.setEndTime(LocalTime.of(15, 0));

		Performance saved = performanceService.createPerformance(performance);

		assertNotNull(saved.getPerformanceId());
		assertEquals(testArtist.getArtistId(), saved.getArtist().getArtistId());
		assertEquals(testStage.getStageName(), saved.getStage().getStageName());
		assertEquals(LocalTime.of(14, 0), saved.getStartTime());
	}

	@Test
	void testPreventStageConflict() {
		Performance first = new Performance();
		first.setArtist(testArtist);
		first.setStage(testStage);
		first.setDate(LocalDate.now());
		first.setStartTime(LocalTime.of(14, 0));
		first.setEndTime(LocalTime.of(15, 0));
		performanceService.createPerformance(first);

		Artist secondArtist = artistRepository.save(new Artist("Another Artist", "Jazz"));
		Performance conflicting = new Performance();
		conflicting.setArtist(secondArtist);
		conflicting.setStage(testStage);
		conflicting.setDate(LocalDate.now());
		conflicting.setStartTime(LocalTime.of(14, 30));
		conflicting.setEndTime(LocalTime.of(15, 30));

		Exception ex = assertThrows(RuntimeException.class, () -> {
			performanceService.createPerformance(conflicting);
		});
		assertTrue(ex.getMessage().contains("conflict"));
	}

	@Test
	void testPreventArtistDoubleBooking() {
		Performance first = new Performance();
		first.setArtist(testArtist);
		first.setStage(testStage);
		first.setDate(LocalDate.now());
		first.setStartTime(LocalTime.of(14, 0));
		first.setEndTime(LocalTime.of(15, 0));
		performanceService.createPerformance(first);

		Stage secondStage = stageRepository.save(new Stage("Second Stage", "Indoors"));
		Performance doubleBooked = new Performance();
		doubleBooked.setArtist(testArtist);
		doubleBooked.setStage(secondStage);
		doubleBooked.setDate(LocalDate.now());
		doubleBooked.setStartTime(LocalTime.of(14, 30));
		doubleBooked.setEndTime(LocalTime.of(15, 30));

		Exception ex = assertThrows(RuntimeException.class, () -> {
			performanceService.createPerformance(doubleBooked);
		});
		assertTrue(ex.getMessage().contains("already booked"));
	}
}
