package daos;

import models.Car;

public class App {
    public static void main(String[] args) {
        OperationsCRUD<Integer> db = new OperationsCRUD<>();
        System.out.println(db.findAll());
    }
}
