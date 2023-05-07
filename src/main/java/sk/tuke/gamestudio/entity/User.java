package sk.tuke.gamestudio.entity;


 import javax.persistence.*;
 import java.util.Date;

@Entity
@Table(name = "gamestudio_user")
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private Date registeredAt;

    public User() {
    }

    public User(String username, String password, Date registeredAt) {
        this.username = username;
        this.password = password;
        this.registeredAt = registeredAt;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
}


/*package sk.tuke.gamestudio.entity;

public class User {
    private String login;

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}*/
