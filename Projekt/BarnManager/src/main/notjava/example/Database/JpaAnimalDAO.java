package example.Database;

import example.model.Animal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaAnimalDAO implements AnimalDAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void saveAnimal(Animal a) {
        entityManager.getTransaction().begin();
        entityManager.merge(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAnimal(Animal a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }
    public void deleteAnimalByIds(long barnId,long animalId){
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Animal WHERE id = "+animalId+" AND barn_id = "+barnId+"").executeUpdate();
        entityManager.getTransaction().commit();
    }
    public Animal editAnimalById(long id){
        Animal actual = new Animal();
        actual = (Animal) entityManager.createQuery("SELECT a FROM Animal a WHERE id = "+id+"", Animal.class);
        return actual;
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
    public List<Animal> getAnimalsByBarnId(long barnId) {
        List<Animal> all = new ArrayList<>();
        List<Animal> animals = getAnimals();
        for (Animal animal : animals) {
            if(animal.getBarn().getId() ==  barnId){
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
