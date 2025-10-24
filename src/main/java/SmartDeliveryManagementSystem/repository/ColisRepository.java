package SmartDeliveryManagementSystem.repository;

import SmartDeliveryManagementSystem.entity.Colis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {
    List<Colis> findByLivreurId(Long livreurId);
}
