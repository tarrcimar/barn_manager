package example.Database;

import example.model.Animal;
import example.model.Barn;
import example.model.User;

import java.util.List;

public interface DAO extends AutoCloseable{
    void saveAnimal(Animal a);
    void deleteAnimal(Animal a);
    void updateAnimal(Animal a);
    List<Animal> getAnimals();

    void saveBarn(Barn b);
    void deleteBarn(Barn b);
    void updateBarn(Barn b);
    List<Barn> getBarns();
    Barn getBarnByUserId(long userId);

    void saveUser(User u);
    void deleteUser(User u);
    void updateUser(User u);
    List<User> getUsers();
    List<Animal> getAnimalsByBarnId(long barnId);

}
