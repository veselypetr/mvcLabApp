/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/*
   Interface PhotoRepository allows us to use methods of JpaRepository
   with the entity Photo.
 */

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
