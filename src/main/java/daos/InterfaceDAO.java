package daos;

import java.util.List;

public interface InterfaceDAO<T> {

    T findById(Integer id);

    List findAll();

    T update(T dto);

    T create(T dto);
}
