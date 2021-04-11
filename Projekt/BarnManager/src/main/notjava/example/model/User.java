package example.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @Basic
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    private GenderType gender;


    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public User(){}

    public User(String userName, String password, GenderType gender){
        this.username = userName;
        this.password = password;
        this.gender = gender;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Barn> barns;


    public Set<Barn> getBarns() {
        return barns;
    }

    public void setBarns(Set<Barn> barns) {
        this.barns = barns;
    }
}
