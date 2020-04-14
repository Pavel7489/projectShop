package Shop;

import Shop.dto.EmployeeForm;

import Shop.exceptions.NumberException.NumberExceptionLateness;
import Shop.exceptions.NumberException.NumberExceptionPlans;
import Shop.exceptions.NumberException.NumberExceptionSalary;
import Shop.exceptions.PositionException;
import Shop.exceptions.NoSuchEmployeeException;
import Shop.services.IOService;
import Shop.services.StaffService;

import java.io.IOException;
import java.sql.SQLException;

public class MainMenu {


    private final IOService ioService;
    private final StaffService staffService;

    public MainMenu() {
        this.ioService = new IOService();
        this.staffService = new StaffService();
    }

    public void start() {

        String menuInput = "";

        do {
            ioService.printLegend();
            try {
                menuInput = ioService.getString();
            } catch (IOException e) {
                System.err.println("ERROR: Bad input");
            }

            switch (menuInput) {
                case "1":
                    try {
                        staffService.getAll().forEach(System.out::println);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        EmployeeForm form = getEmployeeFormFromConsole();
                        staffService.saveNewEmployee(form);
                    } catch (IOException | NumberFormatException e) {
                        System.err.println("Bad input");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    deleteEmployee();
                    break;


                case "4":
                    changeEmployeeSalary();
                    break;

                case "5":
                    changeEmployeePosition();
                    break;
                case "6":
                    changeEmployeePlans();
                    break;
                case "7":
                    changeEmployeeLateness();

                    break;

                case "e":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.err.println("Bad input");
                    break;
            }

        } while (!menuInput.equals(IOService.EXIT_CHAR));
    }

    private void changeEmployeeLateness() {
        System.out.println("Enter lateness and plans:");

        try {

            int lateness=ioService.getInt();
            String name = ioService.getString();
            staffService.changeEmployeeLatenessByName(lateness, name);
        } catch (IOException e) {
            System.err.println("Bad input");
        } catch (NoSuchEmployeeException e) {
            System.err.println("No such pupil: " + e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (PositionException e){
            System.err.println("No such position");
        }catch (NumberExceptionLateness e){
            System.out.println("Number of lateness can`t be less 0");
        }
    }


    private void changeEmployeePlans() {
        System.out.println("Enter plans and names:");

        try {

            int plans=ioService.getInt();
            String name = ioService.getString();
            staffService.changeEmployeePlansByName( plans, name);
        } catch (IOException e) {
            System.err.println("Bad input");
        } catch (NoSuchEmployeeException e) {
            System.err.println("No such pupil: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NumberExceptionPlans e){
            System.out.println("number<0");
        }
    }

    private void changeEmployeePosition() {
        System.out.println("Enter position and name:");

        try {
            String position=ioService.getString();
            String name = ioService.getString();
            staffService.changeEmployeePositionByName( position, name);
        } catch (IOException e) {
            System.err.println("Bad input");
        } catch (PositionException e) {
            System.err.println("No such pupil: " + e.getMessage());
        }catch (NoSuchEmployeeException e) {
            System.err.println("No such pupil: " + e.getMessage());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteEmployee() {
        System.out.println("Enter name:");
        try {
            String name = ioService.getString();
            staffService.deleteEmployeeByName(name);
        } catch (IOException e) {
            System.err.println("Bad input");
        } catch (NoSuchEmployeeException e) {
            System.err.println("No such pupil: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EmployeeForm getEmployeeFormFromConsole() throws IOException, NumberFormatException{

        System.out.println("Enter name:");
        String name = ioService.getString();
        System.out.println("Enter position:");
        String position = ioService.getString();
        System.out.println("Enter salary:");

    int salary = ioService.getInt();
    if (salary<0) throw new NumberExceptionSalary("number of salary cant be less then 0", salary);
    System.out.println("\n" + "For each completed plan you will receive a bonus of 100 to your salary. Enter the number of completed plans:");
    int plans = ioService.getInt();
    if (plans<0) throw new NumberExceptionPlans("number of plans cant be less then 0",plans);
    System.out.println("For each lateness at the workplace you will deduction of 50 .Enter lateness:");
    int lateness = ioService.getInt();
    if (lateness<0) throw new NumberExceptionLateness("number of lateness cant be less then 0",lateness);
    return new EmployeeForm(name, position, salary, plans, lateness);
    }


    private void changeEmployeeSalary() {

        System.out.println("Enter salary and position:");
        try {
            int salary = ioService.getInt();
            String position = ioService.getString();
            staffService.changeEmployeeSalaryByPosition(salary, position);
        } catch (IOException e) {
            System.err.println("Bad input");
        } catch (PositionException e) {
            System.err.println("No such position: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NumberExceptionSalary e){
            System.out.println();
        }

    }
}