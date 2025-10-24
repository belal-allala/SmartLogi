package SmartDeliveryManagementSystem.repository;

import SmartDeliveryManagementSystem.entity.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Long> {
}
