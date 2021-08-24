/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.repository;

import cz.fim.uhk.lab.model.SecuredPersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/*
   Interface SecuredPersonInfoRepository allows us to use methods of JpaRepository
   with the entity SecuredPersonInfo.
 */

@Repository
public interface SecuredPersonInfoRepository extends JpaRepository<SecuredPersonInfo, Long> {

    @Query(value = "select fetch_user(?)", nativeQuery = true)
    Long fetch_user(String username);
}
