/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.AccessTerminal;
import org.springframework.data.jpa.repository.JpaRepository;

/*
   Interface AccessTerminalRepository allows us to use methods of JpaRepository
   with the entity AccessTerminal.
 */

public interface AccessTerminalRepository extends JpaRepository<AccessTerminal, Long> {
}
