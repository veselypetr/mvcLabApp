/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.AccessTerminalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/*
   Interface AccessTerminalHistoryRepository allows us to use methods of JpaRepository
   with the entity AccessTerminalHistory.
 */

public interface AccessTerminalHistoryRepository extends JpaRepository<AccessTerminalHistory, Long> {
}
