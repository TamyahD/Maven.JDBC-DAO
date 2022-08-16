package daos;

import models.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperationsCRUD<T> implements InterfaceDAO<T>{
    public T findById(Integer id) {
        Car newCar=null;
        try {
            Connection connector = DataBaseConnector.getConnection();
            PreparedStatement query = connector.prepareStatement(
                    "SELECT * FROM CarInventory WHERE id=?");
            query.setString(1, String.valueOf(id));
            ResultSet resSet = query.executeQuery();

            while (resSet.next()) {
                newCar=new Car(resSet.getInt(1), resSet.getString(2),
                        resSet.getString(3), resSet.getInt(4),
                        resSet.getString(5), resSet.getInt(6));

                System.out.println(newCar.getId()+": "+newCar.getMake()+" "+newCar.getModel()+
                        " "+newCar.getYear()+" "+newCar.getColor()+" "+newCar.getVin());
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return (T)newCar;
    }

    public List findAll() {
        return null;
    }

    public T update(T dto) {
        return null;
    }

    public T create(T dto) {
        return null;
    }
}
