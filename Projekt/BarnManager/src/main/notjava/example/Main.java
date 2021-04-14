package example;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import example.model.Animal;
import example.model.Barn;
import example.model.GenderType;
import example.model.User;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/example/view/login.fxml"));
        primaryStage.setTitle("Barn Manager");
        primaryStage.setScene(new Scene(root, 700, 400));

        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        launch(args);


        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        LocalDate now = LocalDate.now();


        User pista = new User("pista", "password");

        Barn first = new Barn();
        first.setName("Első");
        Barn second = new Barn();
        second.setName("Második");
        Set<Barn> barnSet = new HashSet<>();
        barnSet.add(first);
        barnSet.add(second);

        Animal elephant = new Animal();
        elephant.setType("Elefánt");
        elephant.setGender(GenderType.FEMALE);
        Animal giraffe = new Animal();
        giraffe.setType("Zsiráf");
        giraffe.setAddedOn(now);
        giraffe.setGender(GenderType.MALE);
        Set<Animal> animalSet = new HashSet<>();
        animalSet.add(elephant);
        animalSet.add(giraffe);

        Animal dog = new Animal();
        dog.setType("Kutya");
        dog.setGender(GenderType.FEMALE);
        Animal cat = new Animal();
        cat.setType("Macska");
        cat.setGender(GenderType.MALE);
        Set<Animal> animalSet2 = new HashSet<>();
        animalSet2.add(dog);
        animalSet2.add(cat);

        first.setAnimals(animalSet);
        second.setAnimals(animalSet2);

        pista.setBarns(barnSet);

        entityManager.getTransaction().begin();
        entityManager.persist(pista);
        entityManager.getTransaction().commit();

        //stopDatabase();
    }

    private static Server s = new Server();

    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
