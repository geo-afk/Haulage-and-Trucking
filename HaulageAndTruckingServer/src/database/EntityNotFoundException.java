package src.database;


public class EntityNotFoundException extends RuntimeException {
        
    public EntityNotFoundException(String message) {
            super(message);
    }
}