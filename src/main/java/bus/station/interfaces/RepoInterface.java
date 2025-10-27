package bus.station.interfaces;

import java.util.List;
import java.util.Optional;

public interface RepoInterface<T, ID>{
    T save(T entity);
    boolean deleteById(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}
