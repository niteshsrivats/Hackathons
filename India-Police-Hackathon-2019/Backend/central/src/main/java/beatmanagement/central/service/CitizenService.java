package beatmanagement.central.service;

import beatmanagement.central.exceptions.EntityNotFoundException;
import beatmanagement.central.models.constants.CitizenType;
import beatmanagement.central.models.entities.Citizen;
import beatmanagement.central.repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @Transactional
    public Citizen addCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Citizen.class, id));
    }

    @Transactional
    public List<Citizen> getCitizensByBeatAndCitizenType(Long beatId, CitizenType citizenType) {
        List<Citizen> citizens = citizenRepository.findCitizensByBeat_IdAndCitizenType(beatId, citizenType);
        if (citizens.isEmpty()) {
            throw new EntityNotFoundException(Citizen.class, beatId);
        }
        return citizens;
    }
}
