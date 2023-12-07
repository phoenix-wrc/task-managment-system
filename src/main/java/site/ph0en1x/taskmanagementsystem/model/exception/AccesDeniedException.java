package site.ph0en1x.taskmanagementsystem.model.exception;

public class AccesDeniedException extends RuntimeException {
    public AccesDeniedException (String message) {
        super(message);
    }
}
