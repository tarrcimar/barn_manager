package example.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String userName;

    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;

    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    private GenderType gender;

    @Basic
    @Enumerated(EnumType.ORDINAL)
    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public User(){}

    public User(String userName, String password, GenderType gender){
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    private Set<Barn> barns;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public Set<Barn> getBarns() {
        return barns;
    }

    public void setBarns(Set<Barn> barns) {
        this.barns = barns;
    }
}
