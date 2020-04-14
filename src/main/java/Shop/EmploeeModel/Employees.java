package Shop.EmploeeModel;

import java.util.Objects;

public class Employees {
    private int id;
    private String name;
    private String position;
    private int salary;
    private int plans;
    private int lateness;
    public Employees(int id, String name, String position, int salary,
                     int plans, int lateness){
        this.id=id;
        this.name=name;
        this.position=position;
        this.salary=salary;
        this.plans=plans;
        this.lateness=lateness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getPlans() {
        return plans;
    }

    public void setPlans(int plans) {
        this.plans = plans;
    }

    public int getLateness() {
        return lateness;
    }

    public void setLateness(int lateness) {
        this.lateness = lateness;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", salary=" + salary+
                ", plans=" + plans+
                ", lateness=" + lateness+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return id == employees.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

