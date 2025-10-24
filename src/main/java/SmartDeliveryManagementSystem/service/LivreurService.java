package SmartDeliveryManagementSystem.service;

import SmartDeliveryManagementSystem.entity.Livreur;
import SmartDeliveryManagementSystem.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

// L'annotation @Service est supprimée car le bean est déclaré dans applicationContext.xml
public class LivreurService {

    private final LivreurRepository livreurRepository;

    // @Autowired est conservé pour l'injection par constructeur
    @Autowired
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
