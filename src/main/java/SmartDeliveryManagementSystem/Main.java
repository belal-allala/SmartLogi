package SmartDeliveryManagementSystem;

import SmartDeliveryManagementSystem.entity.Colis;
import SmartDeliveryManagementSystem.entity.Livreur;
import SmartDeliveryManagementSystem.entity.Statut;
import SmartDeliveryManagementSystem.service.ColisService;
import SmartDeliveryManagementSystem.service.LivreurService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Optional;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        LivreurService livreurService = context.getBean(LivreurService.class);
        ColisService colisService = context.getBean(ColisService.class);

        System.out.println(ANSI_BLUE + "\n--- DÉBUT DES TESTS DE LA LOGIQUE MÉTIER ---" + ANSI_RESET);

        try {
            Long livreurId = testLivreurCrud(livreurService);
            Long colisId = testColisCreationAndAssignment(colisService, livreurId);
            testColisStatusUpdate(colisService, colisId);
            testLivreurColisRelation(colisService, livreurId);
            cleanupTestData(livreurService, colisService, livreurId, colisId);
        } catch (Exception e) {
            System.err.println(ANSI_RED + "\n!!! Un test a échoué: " + e.getMessage() + ANSI_RESET);
            e.printStackTrace();
        }

        context.close();
        System.out.println(ANSI_BLUE + "\n--- FIN DES TESTS ---" + ANSI_RESET);
    }

    private static Long testLivreurCrud(LivreurService livreurService) {
        System.out.println(ANSI_YELLOW + "\n--- 1. Test du CRUD pour les Livreurs ---" + ANSI_RESET);

        System.out.println(ANSI_CYAN + "  a) Création d'un nouveau livreur..." + ANSI_RESET);
        Livreur nouveauLivreur = new Livreur("Martin", "Paul", "Scooter", "0611223344");
        Livreur livreurEnregistre = livreurService.saveLivreur(nouveauLivreur);
        Long livreurId = livreurEnregistre.getId();
        System.out.println(ANSI_GREEN + "  Livreur créé avec succès: " + livreurEnregistre + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\n  b) Lecture du livreur par ID..." + ANSI_RESET);
        livreurService.getLivreurById(livreurId)
                .ifPresent(livreur -> System.out.println(ANSI_GREEN + "  Livreur trouvé: " + livreur + ANSI_RESET));

        System.out.println(ANSI_CYAN + "\n  c) Mise à jour du véhicule du livreur..." + ANSI_RESET);
        livreurEnregistre.setVehicule("Camionnette");
        Livreur livreurMisAJour = livreurService.updateLivreur(livreurEnregistre);
        System.out.println(ANSI_GREEN + "  Livreur mis à jour: " + livreurMisAJour + ANSI_RESET);

        return livreurId;
    }

    private static Long testColisCreationAndAssignment(ColisService colisService, Long livreurId) {
        System.out.println(ANSI_YELLOW + "\n--- 2. Test de l'enregistrement et de l'assignation des colis ---" + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\n  a) Enregistrement d'un nouveau colis..." + ANSI_RESET);
        Colis nouveauColis = new Colis("Alice Dupont", "123 Rue de la Paix", 5.5, Statut.PREPARATION);
        Colis colisEnregistre = colisService.saveColis(nouveauColis);
        Long colisId = colisEnregistre.getId();
        System.out.println(ANSI_GREEN + "  Colis enregistré avec succès: " + colisEnregistre + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\n  b) Assignation du colis au livreur ID: " + livreurId + ANSI_RESET);
        Colis colisAssigne = colisService.assignColisToLivreur(colisId, livreurId);
        System.out.println(ANSI_GREEN + "  Colis assigné: " + colisAssigne + ANSI_RESET);
        System.out.println(ANSI_GREEN + "  Livreur du colis: " + colisAssigne.getLivreur() + ANSI_RESET);

        return colisId;
    }

    private static void testColisStatusUpdate(ColisService colisService, Long colisId) {
        System.out.println(ANSI_YELLOW + "\n--- 3. Test de la mise à jour du statut du colis ---" + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\n  a) Passage du statut à 'EN_TRANSIT'..." + ANSI_RESET);
        Colis colisEnTransit = colisService.updateColisStatus(colisId, Statut.EN_TRANSIT);
        System.out.println(ANSI_GREEN + "  Nouveau statut: " + colisEnTransit.getStatut() + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\n  b) Passage du statut à 'LIVRE'..." + ANSI_RESET);
        Colis colisLivre = colisService.updateColisStatus(colisId, Statut.LIVRE);
        System.out.println(ANSI_GREEN + "  Nouveau statut: " + colisLivre.getStatut() + ANSI_RESET);
    }

    private static void testLivreurColisRelation(ColisService colisService, Long livreurId) {
        System.out.println(ANSI_YELLOW + "\n--- 4. Test de la relation: lister les colis d'un livreur ---" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "\n  Recherche des colis pour le livreur ID: " + livreurId + ANSI_RESET);
        List<Colis> colisDuLivreur = colisService.findColisByLivreurId(livreurId);
        System.out.println(ANSI_GREEN + "  Nombre de colis trouvés: " + colisDuLivreur.size() + ANSI_RESET);
        colisDuLivreur.forEach(c -> System.out.println(ANSI_GREEN + "    - " + c + ANSI_RESET));
    }

    private static void cleanupTestData(LivreurService livreurService, ColisService colisService, Long livreurId, Long colisId) {
        System.out.println(ANSI_YELLOW + "\n--- Nettoyage des données de test ---" + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\n  Suppression du livreur (et de ses colis en cascade)..." + ANSI_RESET);
        livreurService.deleteLivreur(livreurId);
        System.out.println(ANSI_GREEN + "  Livreur avec ID " + livreurId + " supprimé." + ANSI_RESET);

        Optional<Livreur> livreurSupprime = livreurService.getLivreurById(livreurId);
        Optional<Colis> colisSupprime = colisService.getColisById(colisId);
        System.out.println(ANSI_CYAN + "  Vérification suppression livreur: " + (livreurSupprime.isEmpty() ? ANSI_GREEN + "OK" : ANSI_RED + "Échoué") + ANSI_RESET);
        System.out.println(ANSI_CYAN + "  Vérification suppression colis: " + (colisSupprime.isEmpty() ? ANSI_GREEN + "OK" : ANSI_RED + "Échoué") + ANSI_RESET);
    }
}
