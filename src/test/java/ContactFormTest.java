import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.views.component.ContactForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ContactFormTest {
    public static List<Company> companies;
    public static List<Status> statuses;
    public static Contact marcUsher;
    public static Company company1;
    public static Company company2;
    public static Status status1;
    public static Status status2;

    @BeforeAll
    static void setupData() {
        company1 = new Company("Vaadin Ltd", null);
        company2 = new Company("IT Mill", null);
        companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        status1 = new Status("Status 1");
        status2 = new Status("Status 2");
        statuses = new ArrayList<>();
        statuses.add(status1);
        statuses.add(status2);
    }

    @Test
    void formFieldsPopulated(){
        ContactForm form = new ContactForm(companies, statuses);
        form.setContact(new Contact("Marc","Usher","marc@usher.com",company2,status1));

        Assertions.assertEquals("Marc",form.firstName.getValue());
        Assertions.assertEquals("Usher",form.lastName.getValue());
        Assertions.assertEquals("marc@usher.com",form.email.getValue());
        Assertions.assertEquals(company2,form.company.getValue());
        Assertions.assertEquals(status1,form.status.getValue());
    }

    @Test
    void saveEvent(){
        ContactForm form = new ContactForm(companies, statuses);
        Contact contact = new Contact("John","Doe","john@doe.com",company1,status2);
        form.setContact(contact);

        AtomicReference<Contact> saveContactRef = new AtomicReference<>(null);
        form.addListener(ContactForm.SaveEvent.class, e -> saveContactRef.set(e.getContact()));
        form.save.click();
        Contact savedContact = saveContactRef.get();

        Assertions.assertEquals("John",savedContact.getFirstName());
        Assertions.assertEquals("Doe",savedContact.getLastName());
        Assertions.assertEquals("john@doe.com",savedContact.getEmail());
        Assertions.assertEquals(company1,savedContact.getCompany());
        Assertions.assertEquals(status2,savedContact.getStatus());
    }
}
