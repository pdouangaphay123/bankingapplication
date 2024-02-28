package CustomException;

public class InvalidEmailPasswordException extends RuntimeException{

    public InvalidEmailPasswordException(String message){
        super(message);
    }
}
