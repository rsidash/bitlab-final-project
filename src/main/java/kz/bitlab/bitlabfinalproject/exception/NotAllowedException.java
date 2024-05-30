package kz.bitlab.bitlabfinalproject.exception;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException() {
        super("Operation not allowed");
    }
}
