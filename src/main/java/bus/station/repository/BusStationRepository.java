package bus.station.repository;

import bus.station.model.BusStation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;


@Repository
public class BusStationRepository extends JsonFileRepository<BusStation> {

    public BusStationRepository(ObjectMapper objectMapper) {
        super("data/bus-station.json", objectMapper, new TypeReference<>() {});
    }

}
