/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.service;

import cz.fim.uhk.lab.model.*;
import cz.fim.uhk.lab.repository.AccessTerminalHistoryRepository;
import cz.fim.uhk.lab.repository.AccessTerminalRepository;
import cz.fim.uhk.lab.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
    TerminalService is a service that is primarily used by the Terminal controller.
    It encapsulates the methods that the controller needs to function.
 */
@Service
public class TerminalService {
    @Autowired
    private AccessTerminalHistoryRepository accessTerminalHistoryRepository;

    @Autowired
    private AccessTerminalRepository accessTerminalRepository;

    @Autowired
    private PersonRepository personRepository;

    public AccessTerminal getTerminal(Long terminalId) {
        AccessTerminal terminal = accessTerminalRepository.findById(terminalId).get();
        return terminal;
    }

    public void addHistory(AccessTerminalHistory ath, PersonRequest pr, Long terminalID) {
        Optional personOptional = personRepository.findById(pr.getRequestedId());
        if (personOptional.isPresent()) {
            Person p = (Person) personOptional.get();
            ath.setPerson(p);
            ath.setTerminal(getTerminal(terminalID));
            List<PrivilegeLevelCodebook> privileges = new ArrayList<>();
            for (PersonPrivilege priv : p.getPersonPrivilegeList()) {
                privileges.add(priv.getPrivilegeLevel());
            }
            ath.setApproved(privileges.contains(ath.getTerminal().getPrivilegeLevel()));
            ath.setEvent(pr.getEvent());
            ath.setTimestamp(new Timestamp(new Date().getTime()));
            accessTerminalHistoryRepository.save(ath);
        }
    }

}
