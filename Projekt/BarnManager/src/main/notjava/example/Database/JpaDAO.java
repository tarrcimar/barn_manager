package example.Database;

import example.model.Animal;
import example.model.Barn;
import example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaDAO implements DAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void saveAnimal(Animal a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAnimal(Animal a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAnimal(Animal a) {
        saveAnimal(a);
    }

    @Override
    public List<Animal> getAnimals() {
        TypedQuery<Animal> query = entityManager.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animals = query.getResultList();
        return animals;
    }

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
    public Barn getBarnByUserId(long userId) {
        List<Barn> barns = getBarns();
        for(Barn barn : barns){
            if(barn.getUser().getId() == userId){
                return barn;
            }
        }

        return null;
    }

    @Override
    public void saveUser(User u) {
        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUser(User u) {
        entityManager.getTransaction().begin();
        entityManager.remove(u);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(User u) {
        saveUser(u);
    }

    @Override
    public List<User> getUsers() { //get a list of users
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public List<Animal> getAnimalsByBarnId(long barnId) {
        List<Animal> all = new ArrayList<>();
        List<Animal> animals = getAnimals();
        for (Animal animal : animals) {
            if(animal.getBarn().getId() == barnId){
                all.add(animal);
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
