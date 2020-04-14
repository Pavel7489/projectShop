package Shop.exceptions.NumberException;

public class NumberExceptionSalary extends RuntimeException{
    private int number;
    int getNumber() {
        return number;
    }
    public NumberExceptionSalary(String message, int salary){
        super(message);
        number=salary;
    }
}