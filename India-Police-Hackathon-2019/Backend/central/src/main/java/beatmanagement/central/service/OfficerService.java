package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.constants.OfficerType;
import beatmanagement.central.models.entities.BeatLocation;
import beatmanagement.central.models.entities.Officer;
import beatmanagement.central.repositories.BeatReportRepository;
import beatmanagement.central.repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class OfficerService {

    private final OfficerRepository officerRepository;
    private final BeatService beatService;

    @Autowired
    public OfficerService(OfficerRepository officerRepository, BeatService beatService) {
        this.officerRepository = officerRepository;
        this.beatService = beatService;
    }

    @Transactional
    public Officer addOfficer(Officer officer) {
        getOfficerById(officer.getId());
        return officerRepository.save(officer);
    }

    @Transactional
    public Officer getOfficerById(Long id) {
        return officerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Officer.class, id));
    }

    @Transactional
    public Officer updateOfficer(Long officerId, Long beatId) {
        Officer officer = getOfficerById(officerId);
        if (beatId == null) {
            officer.setBeat(null);
        } else {
            officer.setBeat(beatService.getBeatById(beatId));
        }
        return officerRepository.save(officer);
    }

    @Transactional
    public List<Officer> getOfficersByStationAndOfficerType(Long stationId, OfficerType officerType) {
        List<Officer> officers = officerRepository.findOfficersByStation_IdAndOfficerType(stationId, officerType);
        if (officers.isEmpty()) {
            throw new EntityNotFoundException(Officer.class, stationId);
        }
        return officers;
    }
}
