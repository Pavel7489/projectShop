package Shop.exceptions.NumberException;

public class NumberExceptionPlans extends RuntimeException{
    private int number;
    int getNumber() {
        return number;
    }
    public NumberExceptionPlans(String message, int plans){
        super(message);
        number=plans;
    }
}
