package world.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

public class PersistDemo {

    public static void main(String[] args) {

        Country javae = new Country("JavaLandMissy", "SouthAmerica", 100000);
        javae.setRegion("Estern");
        javae.setCode("342");

        City eM = new City("ExpressoMissy");
        eM.setDistrict("Beans");

        eM.setPopulation(20000);
        eM.setCountry(javae);

        javae.setCapital(eM);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
        EntityManager em = emf.createEntityManager();

        Country c2 = em.find(Country.class, "342");

        if (c2 != null) {
            em.getTransaction().begin();
            em.merge(javae);
            em.getTransaction().commit();
        } else {
            try {

                em.getTransaction().begin();
                em.persist(javae);
                em.getTransaction().commit();

            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        }

    }
}
