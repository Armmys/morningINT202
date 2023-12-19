package int202.prefinalint202;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import int202.prefinalint202.entities.Customer;
import int202.prefinalint202.repositories.CustomerRepository;

import java.util.List;

public class InitPassword {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepository();
        List<Customer> customerList = customerRepository.findAll();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
        customerRepository.getEntityManager().getTransaction().begin();
        for (Customer customer : customerList) {
            char[] passwordArray = customer.getCustomerNumber().toString().toCharArray();
            String hashPassword = argon2.hash(2, 16, 1, passwordArray);
            customer.setPassword(hashPassword);
        }
        customerRepository.getEntityManager().getTransaction().commit();
    }
}
