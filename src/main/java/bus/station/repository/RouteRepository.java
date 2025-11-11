package bus.station.repository;

import bus.station.model.Route;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RouteRepository extends JsonFileRepository<Route> {

    public RouteRepository(ObjectMapper objectMapper) {
        super("data/routes.json", objectMapper, new TypeReference<>() {});
    }}
