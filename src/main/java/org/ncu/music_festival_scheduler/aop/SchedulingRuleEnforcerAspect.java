package org.ncu.music_festival_scheduler.aop;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.ncu.music_festival_scheduler.entity.Performance;
import org.ncu.music_festival_scheduler.exception.FestivalRuleViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class SchedulingRuleEnforcerAspect {

    private final LocalDate festivalStart = LocalDate.of(2025, 5, 15);
    private final LocalDate festivalEnd = LocalDate.of(2025, 5, 18);

    @Before("execution(* org.ncu.music_festival_scheduler.service.PerformanceService.addPerformance(..)) && args(performance)")
    public void enforcePerformanceRules(Performance performance) {
        LocalDate date = performance.getPerformanceDate();
        LocalTime start = performance.getStartTime();
        LocalTime end = performance.getEndTime();

        if (date.isBefore(festivalStart) || date.isAfter(festivalEnd)) {
            throw new FestivalRuleViolationException("Performance date must be within the festival dates: May 15–18, 2025.");
        }

        if (start.isAfter(end) || start.equals(end)) {
            throw new FestivalRuleViolationException("Start time must be before end time.");
        }
    }
}
