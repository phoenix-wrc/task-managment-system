package site.ph0en1x.taskmanagementsystem.security.exception;

public class AccesDeniedException extends RuntimeException {
    public AccesDeniedException (String message) {
        super(message);
    }
}
