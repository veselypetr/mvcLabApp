/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
   Interface ItemRepository allows us to use methods of JpaRepository
   with the entity Item.
 */

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}