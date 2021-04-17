package example.Database;

import example.model.Barn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaBarnDAO implements BarnDAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveBarn(Barn b) {
        entityManager.getTransaction().begin();
        entityManager.persist(b);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteBarn(Barn b) {
        entityManager.getTransaction().begin();
        entityManager.remove(b);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateBarn(Barn b) {
        saveBarn(b);
    }

    @Override
    public List<Barn> getBarns() {
        TypedQuery<Barn> query = entityManager.createQuery("SELECT b FROM Barn b", Barn.class);
        List<Barn> barns = query.getResultList();
        return barns;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
