package daos;

import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsCRUD<T> implements InterfaceDAO<T> {
    public T findById(Integer id) {
        Connection connection = DataBaseConnector.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CarInventory WHERE id=" + id);

            if (rs.next()) {
                return extractDataFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
        //    public T findById(Integer id) {
//        Car newCar=null;
//        try {
//            Connection connector = DataBaseConnector.getConnection();
//            PreparedStatement query = connector.prepareStatement(
//                    "SELECT * FROM CarInventory WHERE id=?");
//            query.setString(1, String.valueOf(id));
//            ResultSet resSet = query.executeQuery();
//
//            while (resSet.next()) {
//                newCar=new Car(resSet.getInt(1), resSet.getString(2),
//                        resSet.getString(3), resSet.getInt(4),
//                        resSet.getString(5), resSet.getInt(6));
//
//                System.out.println(newCar.getId()+": "+newCar.getMake()+" "+newCar.getModel()+
//                        " "+newCar.getYear()+" "+newCar.getColor()+" "+newCar.getVin());
//            }
//        }
//        catch (SQLException sqlEx) {
//            sqlEx.printStackTrace();
//        }
//        return (T)newCar;
    }

    private T extractDataFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setMake(resultSet.getString("make"));
        car.setModel(resultSet.getString("model"));
        car.setYear(resultSet.getInt("year"));
        car.setColor(resultSet.getString("color"));
        car.setVin(resultSet.getInt("vin"));

        return (T) car;
    }

    public List findAll() {
        DataBaseConnector connector = new DataBaseConnector();
        Connection connection = DataBaseConnector.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CarInventory");
            List cars = new ArrayList();

            while (rs.next()) {
                Car car = (Car) extractDataFromResultSet(rs);
//                System.out.println(car);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T update(T dto) {
        DataBaseConnector connector = new DataBaseConnector();
        Connection connection = DataBaseConnector.getConnection();
        if (dto instanceof Car) {
            Car carDto = (Car) dto;
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "UPDATE CarInventory SET make=?," +
                                "model=?, year=?, color=?, vin=?");

                ps.setString(1, ((Car) dto).getMake());
                ps.setString(2, ((Car) dto).getModel());
                ps.setInt(3, ((Car) dto).getYear());
                ps.setString(4, ((Car) dto).getColor());
                ps.setInt(5, ((Car) dto).getVin());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return (T)carDto;
        }
//        DataBaseConnector connector = new DataBaseConnector();
//        Connection connection = DataBaseConnector.getConnection();
//        try {
//            PreparedStatement ps = connection.prepareStatement(
//                    "UPDATE CarInventory SET make=?, model=?, year=?, color=?, vin=?");
//            ps.setString(1, (Car)dto.getMake());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public T create(T dto) {
        DataBaseConnector connector = new DataBaseConnector();
        Connection connection = DataBaseConnector.getConnection();
        if (dto instanceof Car) {
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO CarInventory " +
                                "(make, model, year, color, vin) " +
                                "VALUES (?, ?, ?, ?, ?)");

                ps.setString(1, ((Car) dto).getMake());
                ps.setString(2, ((Car) dto).getModel());
                ps.setInt(3, ((Car) dto).getYear());
                ps.setString(4, ((Car) dto).getColor());
                ps.setInt(5, ((Car) dto).getVin());
                int i = ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return dto;
        }
        return null;
    }
}
