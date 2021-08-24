/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.service;

import cz.fim.uhk.lab.model.SecuredPersonInfo;
import cz.fim.uhk.lab.repository.PersonRepository;
import cz.fim.uhk.lab.repository.SecuredPersonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;

/*
    UsernameResolverService is fetching the personid, or SecuredPersonInfo instance of a user based on their login.
 */

@Service
public class UsernameResolverService {

    @Autowired
    SecuredPersonInfoRepository securedPersonInfoRepository;
    @Autowired
    PersonRepository personRepository;

    public Long findIdByUsername(String username) {
        //System.out.println(securedPersonInfoRepository.fetch_user(username));
        return securedPersonInfoRepository.fetch_user(username);
    }
}