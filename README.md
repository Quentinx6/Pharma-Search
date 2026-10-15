# Pharma Search

Mini moteur de recherche de médicaments construit pour apprendre Java 17 / Spring Boot et Angular,
à partir de la Base de données publique des médicaments (data.gouv.fr).

## Lancer

    docker compose up -d          # PostgreSQL
    cd backend
    mvn spring-boot:run           # importe data/CIS_bdpm.txt au premier démarrage

API : http://localhost:8080/api/medicaments?q=doliprane
Swagger : http://localhost:8080/swagger-ui.html

## Avancement

- [x] Import BDPM
- [x] Recherche paginée, fiche par code CIS
- [ ] Présentations (relation 1-N)
- [ ] Favoris
- [ ] Front Angular
- [ ] Tests d'intégration Testcontainers, CI
