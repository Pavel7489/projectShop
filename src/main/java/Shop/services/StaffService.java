package Shop.services;
import Shop.EmploeeModel.Employees;
import Shop.db.DBManager;
import Shop.dto.EmployeeForm;
import Shop.exceptions.NumberException.NumberExceptionLateness;
import Shop.exceptions.PositionException;
import Shop.exceptions.NoSuchEmployeeException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffService {
    private static final String GET_ALL_QUERY = "select * from staff;";
    private static final String save_NEW_EMPLOYEE =
            "insert into staff value (null, ?, ?, ?, ?, ?)";
    private static final String IS_NAME_PERSIST =
            "select count(name) from staff where name = ?;";
    private static final String IS_POSITION_PERSIST =
            "select count(position) from staff where position = ?;";
    private static final String DELETE_BY_NAME =
            "delete from staff where name = ?;";
    private static final String UPDATE_BY_POSITION = "update staff set salary=? where position =?";
    private static final String UPDATE_BY_NAME = "update staff set position=? where name =?";
    private static final String UPDATE_PLANS = "update staff set plans=? where name =?";
    private static final String UPDATE_LATENESS = "update staff set lateness=? where name =?";

    public List<Employees> getAll() throws SQLException {

        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);

        List<Employees> result = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String position = resultSet.getString(3);
            int salary = resultSet.getInt(4);
            int plans = resultSet.getInt(5);
            int lateness = resultSet.getInt(6);


            if (plans >= 1) {
                salary = salary + 100 * plans;
            }
            if (lateness >= 1) {
                salary = salary - 50 * plans;
            }

            result.add(new Employees(id, name, position, salary, plans, lateness));
        }

        return result;
    }

    public void saveNewEmployee(EmployeeForm form) throws SQLException {

        if (isNamePersists(form.getName())) {
            System.err.println("Duplicate name");
            return;
        }

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(save_NEW_EMPLOYEE);

        preparedStatement.setString(1, form.getName());
        preparedStatement.setString(2, form.getPosition());
        preparedStatement.setInt(3, form.getSalary());
        preparedStatement.setInt(4, form.getPlans());
        preparedStatement.setInt(5, form.getLateness());
        preparedStatement.executeUpdate();
    }

    public void changeEmployeeSalaryByPosition(int salary, String position) throws SQLException, NumberExceptionLateness {

          if (!isPositionPersist(position)) {
            throw new PositionException(position);
        }
        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(UPDATE_BY_POSITION);

        preparedStatement.setInt(1, salary);
        preparedStatement.setString(2, position);
        preparedStatement.executeUpdate();
    }

    public void changeEmployeePositionByName(String position, String name) throws SQLException {

        if (!isNamePersists(name)) {
            throw new NoSuchEmployeeException(name);
        }

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(UPDATE_BY_NAME);

        preparedStatement.setString(1, position);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
    }

    public void changeEmployeePlansByName(int plans, String name) throws SQLException {

        if (!isNamePersists(name)) {
            throw new NoSuchEmployeeException(name);
        }

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(UPDATE_PLANS);

        preparedStatement.setInt(1, plans);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();


    }


    public void changeEmployeeLatenessByName(int lateness, String name) throws SQLException {

        if (!isNamePersists(name)) {
            throw new NoSuchEmployeeException(name);
        }

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(UPDATE_LATENESS);

        preparedStatement.setInt(1, lateness);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();

    }


    public void deleteEmployeeByName(String name) throws SQLException {

        if (!isNamePersists(name)) {
            throw new NoSuchEmployeeException(name);
        }

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(DELETE_BY_NAME);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    private boolean isNamePersists(String name) throws SQLException {

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(IS_NAME_PERSIST);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count >= 1;
    }


    private boolean isPositionPersist(String position) throws SQLException {

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(IS_POSITION_PERSIST);
        preparedStatement.setString(1, position);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count >= 1;
    }
}
