package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private int ident;

    private String player;
    private String game;
    private int rating;
    private Date ratedAt;
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
