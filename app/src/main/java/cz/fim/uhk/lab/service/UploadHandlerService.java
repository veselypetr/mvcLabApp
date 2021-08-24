/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import cz.fim.uhk.lab.exeption.RecordNotFoundException;
import cz.fim.uhk.lab.model.Item;
import cz.fim.uhk.lab.model.Person;
import cz.fim.uhk.lab.repository.ItemRepository;
import cz.fim.uhk.lab.repository.PersonRepository;
import cz.fim.uhk.lab.repository.PhotoRepository;
import cz.fim.uhk.lab.security.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/*
    UploadHandlerService is responsible for upload and base64 conversion of image files.
 */

@Service
public class UploadHandlerService {

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    UserManagementService userManagementService;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CheckoutService checkoutService;
    @Autowired
    ItemRepository itemRepository;

    public void handlePersonPhoto(MultipartFile file, Long id) {
        if (!file.isEmpty()) {
            byte[] fileContent = new byte[0];
            Person person = null;
            try {
                person = userManagementService.getPersonById(id);
                fileContent = file.getBytes();
                person.getPhoto().setDataType(file.getContentType());
                photoRepository.save(person.getPhoto());
                person.getPhoto().setImage(Base64.encode(fileContent));
                person.getPhoto().setDateModified(new Timestamp(new Date().getTime()));
                personRepository.save(person);

            } catch (IOException | RecordNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void handleItemPhoto(MultipartFile file, Long id) {
        if (!file.isEmpty()) {
            byte[] fileContent = new byte[0];
            Optional i = itemRepository.findById(id);
            if (i.isPresent()) {
                try {
                    Item item = (Item) i.get();
                    fileContent = file.getBytes();
                    item.getPhoto().setDataType(file.getContentType());
                    photoRepository.save(item.getPhoto());
                    item.getPhoto().setImage(Base64.encode(fileContent));
                    item.getPhoto().setDateModified(new Timestamp(new Date().getTime()));
                    itemRepository.save(item);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

