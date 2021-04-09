package example.model;

import javax.persistence.*;

@Entity
public class User {
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    private String userPassword;

    @Basic
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public User(String userName, String userPassword, GenderType gender){
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
    }
}
