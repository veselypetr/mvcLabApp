/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.service;

import cz.fim.uhk.lab.exeption.ResourceNotFoundException;
import cz.fim.uhk.lab.model.Borrowed;
import cz.fim.uhk.lab.model.Item;
import cz.fim.uhk.lab.model.ItemRequest;
import cz.fim.uhk.lab.repository.BorrowedRepository;
import cz.fim.uhk.lab.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
    MyInventoryService is a service that is primarily used by the My Inventory controller.
    It encapsulates the methods that the controller needs to function.
 */

@Service
public class MyInventoryService {
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<Borrowed> getAllPastBorrowedForPerson(Long id) {
        List<Borrowed> result = borrowedRepository.findAll();
        result.removeIf(b -> !b.getPerson().getPersonid().equals(id));
        result.removeIf(Borrowed::getBorrowed);
        result.sort(Comparator.comparing(Borrowed::getBorrowedTimestamp));
        return result;
    }

    public List<Borrowed> getAllCurrentBorrowedForPerson(Long id) {
        List<Borrowed> result = borrowedRepository.findAll();
        result.removeIf(b -> !b.getPerson().getPersonid().equals(id));
        result.removeIf(borrowed -> !borrowed.getBorrowed());
        result.sort(Comparator.comparing(Borrowed::getBorrowedTimestamp));
        return result;
    }

    public void returnItem(ItemRequest returnRequest) throws ResourceNotFoundException {
        Optional bOptional = borrowedRepository.findById(returnRequest.getRequestedId());
        if (bOptional.isPresent()) {
            Borrowed b = (Borrowed) bOptional.get();
            b.setReturnedTimestamp(new Timestamp(new Date().getTime()));
            b.setBorrowed(false);
            borrowedRepository.save(b);
            Item i = b.getItem();
            i.setCurrentlyAvailable(i.getCurrentlyAvailable() + b.getQuantity());
            itemRepository.save(i);
        } else {
            throw new ResourceNotFoundException("Person not found");
        }


    }
}