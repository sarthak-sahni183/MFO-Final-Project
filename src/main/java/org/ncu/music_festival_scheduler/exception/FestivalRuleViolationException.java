package org.ncu.music_festival_scheduler.exception;

public class FestivalRuleViolationException extends RuntimeException {
    public FestivalRuleViolationException(String message) {
        super(message);
    }
}
