package bus.station.repository;

import bus.station.model.BusStation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;


@Repository
public class BusStationRepo extends JsonFileRepository<BusStation> {

    public BusStationRepo(ObjectMapper objectMapper) {
        super("data/bus-station", objectMapper, new TypeReference<>() {});
    }

}
