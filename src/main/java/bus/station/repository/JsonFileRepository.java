package bus.station.repository;

import bus.station.interfaces.Identifiable;
import bus.station.interfaces.RepoInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public abstract class JsonFileRepository<T extends Identifiable> implements RepoInterface<T, String> {
    protected final Map<String, T> storage = new HashMap<>();
    private final String jsonFilePath;
    private final ObjectMapper objectMapper;
    private final TypeReference<List<T>> typeReference;

    public JsonFileRepository(String jsonFilePath, ObjectMapper objectMapper, TypeReference<List<T>> typeReference) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = objectMapper;
        this.typeReference = typeReference;
    }

    private void loadDataFromFile() {
        File file = Paths.get(jsonFilePath).toFile();
        if (!file.exists() || file.length() == 0) {
            System.out.println("File not found : " + jsonFilePath);
            return;
        }

        try {
            List<T> entities = objectMapper.readValue(file, typeReference);
            storage.clear();
            for (T entity : entities) {
                storage.put(entity.getId(), entity);
            }
            System.out.println("Successfuly loaded JSON entities into storage!");
        } catch (IOException e) {
            throw new RuntimeException("Error while reading from JSON file: " + jsonFilePath, e);
        }
    }

    private synchronized void persistDataToFile() {
        File file = Paths.get(jsonFilePath).toFile();
        file.getParentFile().mkdirs();
        try {
            objectMapper.writeValue(file, storage.values());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing into file: " + jsonFilePath, e);
        }

    }

    @Override
    public T save(T entity) {
        String id =  entity.getId();
        if (id == null || id.isEmpty() || storage.get(id) == null) {
            id = UUID.randomUUID().toString();
            entity.setId(id);
        }

        storage.put(id, entity);
        persistDataToFile();
        return entity;
    }

    @Override
    public boolean deleteById(String id) {
        storage.remove(id);
        persistDataToFile();
        return true;
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
