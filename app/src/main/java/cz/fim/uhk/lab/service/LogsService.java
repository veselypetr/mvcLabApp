/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.service;

import cz.fim.uhk.lab.model.AccessTerminalHistory;
import cz.fim.uhk.lab.model.Borrowed;
import cz.fim.uhk.lab.repository.AccessTerminalHistoryRepository;
import cz.fim.uhk.lab.repository.BorrowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    LogsService is a service that is primarily used by the Logs controller.
    It encapsulates the methods that the controller needs to function.
 */

@Service
public class LogsService {
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private AccessTerminalHistoryRepository accessTerminalHistoryRepository;

    public List<Borrowed> getAllBorrowed() {
        return borrowedRepository.findAll();
    }

    public List<AccessTerminalHistory> getAllTerminalHistory() {
        return accessTerminalHistoryRepository.findAll();
    }
}
