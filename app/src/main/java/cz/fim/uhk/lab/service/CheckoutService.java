/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import cz.fim.uhk.lab.exeption.RecordNotFoundException;
import cz.fim.uhk.lab.exeption.ResourceNotFoundException;
import cz.fim.uhk.lab.model.*;
import cz.fim.uhk.lab.repository.*;
import cz.fim.uhk.lab.security.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Predicate;

/*
    CheckoutService is a service that is primarily used by the Checkout controller.
    It encapsulates the methods that the User Management controller needs to function,
    such as filtering the database output to include only items with correct privileges,
    and item creation an editing.
 */

@Service
public class CheckoutService {
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserManagementService service;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ItemCodebookRepository itemCodebookRepository;
    @Autowired
    private PrivilegeLevelCodebookRepository privilegeLevelCodebookRepository;
    @Autowired
    private SpecimenRepository specimenRepository;

    public List<Item> getAvailableItems(Long id) {
        List<Item> results = new ArrayList<>();
        try {
            List<Item> items = itemRepository.findAll();
            List<PersonPrivilege> privs = service.getPersonById(id).getPersonPrivilegeList();
            List<PrivilegeLevelCodebook> privileges = new ArrayList<>();
            for (PersonPrivilege p : privs) {
                privileges.add(p.getPrivilegeLevel());
            }
            for (Item it : items) {
                if (it.getCurrentlyAvailable() <= 0) {
                    continue;
                }
                if (it.getSpecimenDetails() == null) {
                    results.add(it);
                    continue;
                }
                if (privileges.contains(it.getSpecimenDetails().getPrivilegeCategory())) {
                    results.add(it);
                }
            }
            results.sort(Comparator.comparing(Item::getName));
            return results;


        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Item getItemById(Long id) {
        Optional<Item> it = itemRepository.findById(id);
        return it.orElse(null);
    }

    public void checkout(ItemRequest itemRequest, Person person) throws ResourceNotFoundException {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Borrowed newCheckout = new Borrowed();
        newCheckout.setBorrowedTimestamp(timestamp);
        newCheckout.setBorrowed(true);
        Item i = itemRepository.findById(itemRequest.getRequestedId()).get();
        newCheckout.setItem(i);

        if (itemRequest.getRequestedQuantity() <= 0 || itemRequest.getRequestedQuantity() > i.getCurrentlyAvailable()) {
            throw new ResourceNotFoundException("Invalid checkout request.");
        }
        i.setCurrentlyAvailable(i.getCurrentlyAvailable() - itemRequest.getRequestedQuantity());
        newCheckout.setQuantity(itemRequest.getRequestedQuantity());
        newCheckout.setPerson(person);
        borrowedRepository.save(newCheckout);
    }

    public void saveNewItem(Item newItem) {
        Photo newPhoto = new Photo();
        newPhoto.setDateModified(new Timestamp(new Date().getTime()));
        try {
            newPhoto.setImage(Base64.encode(Files.readAllBytes(Paths.get("src/main/resources/static/img/blank-item.png"))));
            newPhoto.setDataType("image/png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        photoRepository.save(newPhoto);

        if (newItem.getSpecimenDetails() != null) {
            Specimen newSpec = new Specimen();
            newSpec.setAdditionalInfo(newItem.getSpecimenDetails().getAdditionalInfo());
            newSpec.setPrivilegeCategory(newItem.getSpecimenDetails().getPrivilegeCategory());
            specimenRepository.save(newSpec);
            newItem.setSpecimenDetails(newSpec);
            try {
                newPhoto.setImage(Base64.encode(Files.readAllBytes(Paths.get("src/main/resources/static/img/specimen.jpg"))));
                newPhoto.setDataType("image/jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        newItem.setBorrowedList(new ArrayList<Borrowed>());
        newItem.setName("");
        newItem.setQuantity(0);
        newItem.setCurrentlyAvailable(newItem.getQuantity());
        newItem.setPhoto(newPhoto);
        itemRepository.save(newItem);

        newPhoto.setItem(newItem);
        photoRepository.save(newPhoto);
    }

    public void updateItem(Item updateItem, Long id) {
        Item oldItem = getItemById(id);
        if (oldItem != null) {
            int quantityDelta;
            if (oldItem.getQuantity() >= updateItem.getQuantity()) {
                quantityDelta = oldItem.getQuantity() - updateItem.getQuantity();
            } else {
                quantityDelta = oldItem.getQuantity() + updateItem.getQuantity();
            }
            if (quantityDelta <= oldItem.getCurrentlyAvailable() + quantityDelta) {
                if (oldItem.getSpecimenDetails() != null) {
                    oldItem.getSpecimenDetails().setPrivilegeCategory(updateItem.getSpecimenDetails().getPrivilegeCategory());
                    oldItem.getSpecimenDetails().setAdditionalInfo(updateItem.getSpecimenDetails().getAdditionalInfo());
                }
                oldItem.setName(updateItem.getName());
                oldItem.setItemCodebook(updateItem.getItemCodebook());
                oldItem.setQuantity(updateItem.getQuantity());
                oldItem.setDescription(updateItem.getDescription());
                oldItem.setCurrentlyAvailable(oldItem.getCurrentlyAvailable() + quantityDelta);
                itemRepository.save(oldItem);
                return;
            }
        }
        try {
            throw new Exception(new IOException("Invalid quantity changes."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(Long id) {
        itemRepository.deleteById(id);
    }

    public List<ItemCodebook> getAllGeneralCategories() {
        List<ItemCodebook> list = itemCodebookRepository.findAll();
        Predicate<ItemCodebook> condition = ic -> ic.getCategoryName().contains("specimen");
        list.removeIf(condition);
        return list;
    }

    public List<ItemCodebook> getAllSpecimenCategories() {
        List<ItemCodebook> list = itemCodebookRepository.findAll();
        Predicate<ItemCodebook> condition = ic -> !ic.getCategoryName().contains("specimen");
        list.removeIf(condition);
        return list;
    }

    public List<PrivilegeLevelCodebook> getAllPrivs() {
        List<PrivilegeLevelCodebook> list = privilegeLevelCodebookRepository.findAll();
        Predicate<PrivilegeLevelCodebook> condition = ic -> !ic.getPrivilegeLevelTitle().contains("specimen");
        list.removeIf(condition);
        return list;
    }

}
