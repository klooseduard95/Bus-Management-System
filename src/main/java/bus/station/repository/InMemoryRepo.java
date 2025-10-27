package bus.station.repository;

import bus.station.interfaces.Identifiable;
import bus.station.interfaces.RepoInterface;

import java.util.*;

public abstract class InMemoryRepo<T extends Identifiable> implements RepoInterface<T, String> {

    private Map<String, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        String id =  entity.getId();
        if (id == null || id.isEmpty() || storage.get(id) == null) {
            id = UUID.randomUUID().toString();
            entity.setId(id);
        }

        storage.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}
