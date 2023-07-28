package polimi.it.smsSofwareEng.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id){

        super("The student with id="+id+"does not exist");
    }
}
