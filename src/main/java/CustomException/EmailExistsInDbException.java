package CustomException;

public class EmailExistsInDbException extends RuntimeException{

    public EmailExistsInDbException(String message){
        super(message);
    }
}
