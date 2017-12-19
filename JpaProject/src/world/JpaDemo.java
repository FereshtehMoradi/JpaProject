package world;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import world.domain.City;
import world.domain.Country;

public class JpaDemo {

    static boolean loop;
    static Scanner sc = new Scanner(System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
//    static EntityManager em = emf.createEntityManager();

    public static void findCity(String name) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c from City c where c.name= :name");
        q.setParameter("name", name);

        List<City> myCity = (List<City>) q.getResultList();

        System.out.printf("Found %d matches for %s\n", myCity.size(), name);

        for (City c : myCity) {
            System.out.println(c.getName() + " pop " + c.getPopulation() + " and is located in " + c.getCountry().getName());
        }
        em.close();
    }


    public static void findCountry(String name) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c from Country c where c.name= :name");
        q.setParameter("name", name);

        try {

            Country c = (Country) q.getSingleResult();
            System.out.println(c.getName() + " capital city is " + c.getCapital().getName());
            System.out.println("Citeies in " + c.getName() + " are ");

            System.out.println(c.getName() + " is in " + c.getRegion());
            System.out.println("The population in the " + c.getName() + " is " + c.getPopulation());
            System.out.println("Cities in " + c.getName() + " are:");

            for (City temp : c.getMyCities()) {
                System.out.println(temp.getName());
            }
            System.out.println("Cities type is " + c.getMyCities().getClass().getName());

        } catch (Exception e) {
            System.out.println("No such a counrty!");

        }

        em.close();

    }

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();


        findCountry("Sweden");
        findCountry("Iran");
        findCity("ExpressoMissy");

        while (loop) {
            System.out.println("Enter a country for more info:");
            String country = sc.nextLine();
            findCountry(country);
            System.out.println("Make a new search:");
            System.out.println("Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 2) {
                loop = false;
                System.out.println("Thanks and bye!");
            } else {
                System.out.println("*********************************");
            }
        }
        em.close();
        emf.close();
    }

    public static void update() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Country sweden = em.find(Country.class, "SWE");
        sweden.setPopulation(12000000);
        tx.commit();
    }
}
