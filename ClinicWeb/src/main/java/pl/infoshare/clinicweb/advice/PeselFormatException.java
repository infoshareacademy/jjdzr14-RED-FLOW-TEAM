package pl.infoshare.clinicweb.advice;

import static java.lang.String.format;

public class PeselFormatException extends RuntimeException {

    private static final String MSG = "Incorrect pesel format %s.";

    public PeselFormatException(String pesel) {
        super(format(MSG, pesel));
    }
}
