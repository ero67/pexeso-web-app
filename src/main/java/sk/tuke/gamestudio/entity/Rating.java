package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.util.Date;
@Table(
        name = "rating",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"game","player"})
        }
)
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private int ident;
    @Column
    private String player;
    @Column
    private String game;
    @Column
    private int rating;
    @Column
    private Date ratedAt;
    //@Table
    //@UniqueConstraint
    //TO DO
    public Rating(){

    }

    public Rating(String player, String game, int rating,Date ratedAt) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedAt=ratedAt;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int points) {
        this.rating = points;
    }
    public Date getRatedAtdAt() {
        return ratedAt;
    }
    public void setRatedAt(Date ratedAt) {
        this.ratedAt = ratedAt;
    }
}
