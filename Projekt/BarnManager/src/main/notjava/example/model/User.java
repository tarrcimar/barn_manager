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


    public User(){}

    public User(String userName, String password){
        this.username = userName;
        this.password = password;
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
