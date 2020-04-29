package person;

import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.ZoneId;

public class Main {
    private static Faker faker = new Faker();

    public static Person randomPerson() {
        Person person = Person.builder()
              .name(faker.name().name())
              .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
              .gender(faker.options().option(Person.Gender.class))
              .address(Address.builder()
                      .country(faker.address().country())
                      .state(faker.address().state())
                      .city(faker.address().city())
                      .zip(faker.address().zipCode())
                      .streetAddress(faker.address().streetAddress())
                      .build())
              .email(faker.internet().emailAddress())
              .profession(faker.company().profession())
              .build();
        return person;
   }

   public static void personMaker(int db){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       for(int i=0; i < db; i++){
           em.persist(randomPerson());
       }
       em.getTransaction().commit();

       System.out.println(em.find(Person.class, (long)123));

       em.close();
       emf.close();
   }

   public static void main(String[] args){
        personMaker(1000);
   }
}
