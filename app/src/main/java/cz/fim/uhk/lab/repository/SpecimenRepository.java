/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
   Interface SpecimenRepository allows us to use methods of JpaRepository
   with the entity JpaRepository.
 */

@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Long> {
}
