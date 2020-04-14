package Shop.exceptions.NumberException;

public class NumberExceptionLateness extends RuntimeException {

private int number;
int getNumber(){
    return number;
}
    public NumberExceptionLateness(String message, int lateness) {
        super(message);
        number=lateness;
    }

}
