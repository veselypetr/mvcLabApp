/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;


import cz.fim.uhk.lab.model.PersonPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
   Interface PersonPrivilegeRepository allows us to use methods of JpaRepository
   with the entity PersonPrivilege.
 */

@Repository
public interface PersonPrivilegeRepository extends JpaRepository<PersonPrivilege, Long> {
}
