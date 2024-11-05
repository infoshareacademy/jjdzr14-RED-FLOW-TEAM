package pl.infoshare.clinicweb.exception.validation;

import java.time.LocalDateTime;

public class CalendarValidationException extends RuntimeException {

    private static final String MSG = "The selected time slot is already booked. %Ts";

    public CalendarValidationException(LocalDateTime dateTime) {
        super(String.format(MSG, dateTime));
    }
}
