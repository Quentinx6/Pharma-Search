package fr.quentinlecompte.pharmasearch.medicament;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament, String> {
    Page<Medicament> findByDenominationContainingIgnoreCase(String query, Pageable pageable);
}
