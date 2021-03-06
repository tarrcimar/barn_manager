package example;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import example.model.*;
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

        /*
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        LocalDate now = LocalDate.now();
        */
        /*
        User pista = new User("pista", "password");

        Barn first = new Barn();
        first.setName("Első");
        Barn second = new Barn();
        second.setName("Második");
        Set<Barn> barnSet = new HashSet<>();
        barnSet.add(first);
        barnSet.add(second);

        Animal horse = new Animal();
        horse.setType("Horse");
        horse.setGender(GenderType.FEMALE);
        Animal cow = new Animal();
        cow.setType("Cow");
        cow.setAddedOn(now);
        cow.setGender(GenderType.MALE);
        Animal cow2 = new Animal();
        cow2.setType("Cow");
        cow2.setAddedOn(now);
        cow2.setGender(GenderType.FEMALE);
        Set<Animal> animalSet = new HashSet<>();
        animalSet.add(horse);
        animalSet.add(cow);
        animalSet.add(cow2);

        Animal pig = new Animal();
        pig.setType("Pig");
        pig.setGender(GenderType.FEMALE);
        Animal goat = new Animal();
        goat.setType("Goat");
        goat.setGender(GenderType.MALE);
        Animal goat2 = new Animal();
        goat2.setType("Goat");
        goat2.setGender(GenderType.FEMALE);
        Set<Animal> animalSet2 = new HashSet<>();
        animalSet2.add(pig);
        animalSet2.add(goat);
        animalSet2.add(goat2);

        first.setAnimals(animalSet);
        second.setAnimals(animalSet2);

        pista.setBarns(barnSet);

        Forage buza = new Forage();
        buza.setName(ForageType.HAY);
        buza.setAmount((long)10);
        buza.setUnit(MeasurementUnit.TON);
        Forage kukac = new Forage();
        kukac.setName(ForageType.WORM);
        kukac.setAmount((long)22);
        kukac.setUnit(MeasurementUnit.BOX);
        Set<Forage> takarmanyok = new HashSet<>();
        takarmanyok.add(buza);
        takarmanyok.add(kukac);

        pista.setForages(takarmanyok);

        entityManager.getTransaction().begin();
        entityManager.persist(pista);
        entityManager.getTransaction().commit();
        */
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
