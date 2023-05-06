package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.util.Date;
@Table(
        name="score"
)
@Entity
public class Score {

    @Id
    @GeneratedValue
    private int ident;
    @Column
    private String player;
    @Column
    private String game;
    @Column
    private int points;
    @Column
    private Date playedAt;



    public Score(){

    }

    public Score(String player,String game, int points, Date playedAt) {
        this.game = game;
        this.points = points;
        this.playedAt = playedAt;
        this.player = player;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }
    @Override
    public String toString() {
        return "Score{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", points=" + points +
                ", playedAt=" + playedAt +
                '}';
    }


}


