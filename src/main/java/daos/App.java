package daos;

import models.Car;

public class App {
    public static void main(String[] args) {
        OperationsCRUD<Integer> db = new OperationsCRUD<>();
        db.findById(333);
    }
}
