package Shop.dto;

public class EmployeeForm {
    private final String name;
    private final String position;
    private final int salary;
    private final int plans;
    private final int lateness;
    public EmployeeForm(String name, String position,
                        int salary, int plans, int lateness){
        this.name=name;
        this.position=position;
        this.salary=salary;
        this.plans=plans;
        this.lateness=lateness;
    }

    public String getName() {

        return name;
    }

    public String getPosition() {

        return position;
    }

    public int getSalary() {

        return salary;
    }

    public int getPlans() {

        return plans;
    }

    public int getLateness() {

        return lateness;
    }
}
