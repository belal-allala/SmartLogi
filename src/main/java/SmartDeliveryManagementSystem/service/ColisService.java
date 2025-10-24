package SmartDeliveryManagementSystem.service;

import SmartDeliveryManagementSystem.entity.Colis;
import SmartDeliveryManagementSystem.entity.Livreur;
import SmartDeliveryManagementSystem.entity.Statut;
import SmartDeliveryManagementSystem.repository.ColisRepository;
import SmartDeliveryManagementSystem.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ColisService {

    private final ColisRepository colisRepository;
    private final LivreurRepository livreurRepository;

    @Autowired
    public ColisService(ColisRepository colisRepository, LivreurRepository livreurRepository) {
        this.colisRepository = colisRepository;
        this.livreurRepository = livreurRepository;
    }

    public Colis saveColis(Colis colis) {
        return colisRepository.save(colis);
    }

    public List<Colis> getAllColis() {
        return colisRepository.findAll();
    }

    public Optional<Colis> getColisById(Long id) {
        return colisRepository.findById(id);
    }

    public void deleteColis(Long id) {
        colisRepository.deleteById(id);
    }

    public Colis updateColis(Colis colis) {
        return colisRepository.save(colis);
    }

    public List<Colis> findColisByLivreurId(Long livreurId) {
        return colisRepository.findByLivreurId(livreurId);
    }

    @Transactional
    public Colis assignColisToLivreur(Long colisId, Long livreurId) {
        Optional<Colis> colisOptional = colisRepository.findById(colisId);
        Optional<Livreur> livreurOptional = livreurRepository.findById(livreurId);

        if (colisOptional.isPresent() && livreurOptional.isPresent()) {
            Colis colis = colisOptional.get();
            Livreur livreur = livreurOptional.get();
            colis.setLivreur(livreur);
            return colisRepository.save(colis);
        } else {
            throw new RuntimeException("Colis ou Livreur non trouvé pour l'assignation.");
        }
    }

    @Transactional
    public Colis updateColisStatus(Long colisId, Statut newStatut) {
        Optional<Colis> colisOptional = colisRepository.findById(colisId);

        if (colisOptional.isPresent()) {
            Colis colis = colisOptional.get();
            colis.setStatut(newStatut);
            return colisRepository.save(colis);
        } else {
            throw new RuntimeException("Colis non trouvé pour la mise à jour du statut.");
        }
    }
}
