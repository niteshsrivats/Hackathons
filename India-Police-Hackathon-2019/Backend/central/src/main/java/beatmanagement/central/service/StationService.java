package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.entities.Station;
import beatmanagement.central.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class StationService {
    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Transactional
    public Station addStation(Station station) {
        return stationRepository.save(station);
    }

    @Transactional
    public Station getStation(Long id) {
        return stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Station.class, id));
    }

    @Transactional
    public ArrayList<Double> getStationLocations(Long id) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Station.class, id));
        ArrayList<Double> location = new ArrayList<>();
        location.add(station.getLatitude());
        location.add(station.getLongitude());
        return location;
    }
}
