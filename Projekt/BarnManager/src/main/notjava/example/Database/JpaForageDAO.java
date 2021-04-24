package example.Database;

import example.model.Forage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaForageDAO implements ForageDAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void saveForage(Forage f) {
        entityManager.getTransaction().begin();
        entityManager.persist(f);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteForage(Forage f) {
        entityManager.getTransaction().begin();
        entityManager.remove(f);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateForage(Forage f) {
        saveForage(f);
    }

    @Override
    public List<Forage> getForage() {
        TypedQuery<Forage> query = entityManager.createQuery("SELECT f FROM Forage f", Forage.class);
        List<Forage> forages = query.getResultList();
        return forages;
    }

    public List<Forage> getForagesByUserId(long userId) {
        List<Forage> all = new ArrayList<>();
        List<Forage> forages = getForage();
        for (Forage forage : forages) {
            if(forage.getOwner_id().getId() == userId){
                all.add(forage);
            }
        }
        return all;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
