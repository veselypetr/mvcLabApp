/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.PrivilegeLevelCodebook;
import org.springframework.data.jpa.repository.JpaRepository;

/*
   Interface PrivilegeLevelCodebookRepository allows us to use methods of JpaRepository
   with the entity PrivilegeLevelCodebook.
 */

public interface PrivilegeLevelCodebookRepository extends JpaRepository<PrivilegeLevelCodebook, Long> {
}
