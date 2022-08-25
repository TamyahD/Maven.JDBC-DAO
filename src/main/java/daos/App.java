package daos;

import models.Car;

public class App {
    public static void main(String[] args) {
        OperationsCRUD<Car> db = new OperationsCRUD<>();
//        System.out.println(db.findById(333));
//        db.create(new Car("Nissan", "Altima", 2012,
//                "gray", 1122334455));
        System.out.println(db.findAll());

    }
}
