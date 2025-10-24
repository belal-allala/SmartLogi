# SmartLogi

Ce projet, nommé "SmartLogi", est un système de gestion de livraison intelligent (Smart Delivery Management System).

## Objectif du projet

L'application est conçue pour gérer et suivre des livraisons de colis. Elle permet de :
- Gérer des **colis** (avec un destinataire, une adresse, un poids, et un statut).
- Gérer des **livreurs** (avec nom, prénom, véhicule, téléphone).
- Assigner des colis à des livreurs.
- Suivre le **statut** d'un colis, qui peut être en `PREPARATION`, `EN_TRANSIT`, ou `LIVRE`.

## Technologies utilisées

- **Langage :** Java (version 17)
- **Framework principal :** Spring (Spring MVC pour le web, Spring Data JPA pour l'accès aux données).
- **Base de données :** PostgreSQL.
- **Gestion de projet :** Maven.
- **Type d'application :** Application web (packagée en tant que fichier WAR), déployée sur un serveur d'applications.

## Diagramme

Voici un diagramme illustrant l'architecture ou le flux de travail du projet :

![Diagramme du projet](docs/Diagramme%20sans%20nom.drawio.png)