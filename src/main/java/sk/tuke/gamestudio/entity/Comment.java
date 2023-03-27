package sk.tuke.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int ident;
    private String player;
    private String game;
    private String comment;
    private Date commented_at;

    public Comment(){

    }
    public Comment(String player, String game, String comment,Date commented_at) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commented_at=commented_at;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getCommentedAt() {
        return commented_at;
    }
}
