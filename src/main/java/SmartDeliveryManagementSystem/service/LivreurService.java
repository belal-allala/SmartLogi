package SmartDeliveryManagementSystem.service;

import SmartDeliveryManagementSystem.entity.Livreur;
import SmartDeliveryManagementSystem.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class LivreurService {

    private final LivreurRepository livreurRepository;


    public LivreurService(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    public Livreur saveLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    public Optional<Livreur> getLivreurById(Long id) {
        return livreurRepository.findById(id);
    }

    public void deleteLivreur(Long id) {
        livreurRepository.deleteById(id);
    }

    public Livreur updateLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }
}
