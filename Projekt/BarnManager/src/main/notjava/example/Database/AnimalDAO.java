package example.Database;

import example.model.Animal;

import java.util.List;

public interface AnimalDAO extends AutoCloseable{
    public void saveAnimal(Animal a);
    public void deleteAnimal(Animal a);
    public void updateAnimal(Animal a);
    public List<Animal> getAnimals();
}
