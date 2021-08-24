/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
   Interface BorrowedRepository allows us to use methods of JpaRepository
   with the entity Borrowed.
 */

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {

}