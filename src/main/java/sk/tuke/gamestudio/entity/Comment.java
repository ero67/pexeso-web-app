package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.util.Date;
@Table(
        name = "comment"
)
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int ident;
    @Column
    private String player;
    @Column
    private String game;
    @Column
    private String comment;
    /*@Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date commented_at;*/

    @Column
    private Date commentedAt;


    public Comment(){

    }
    public Comment(String player, String game, String comment,Date commented_at) {
        this.player = player;
        this.game = game;
        this.comment = comment;
//        this.commented_at=commented_at;
        this.commentedAt=commented_at;
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
        /*return commented_at;*/
        return commentedAt;
    }

    public void setCommentedAt(Date commented_at) {
        /*this.commented_at = commented_at;*/
        this.commentedAt=commented_at;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "ident=" + ident +
                ", player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", comment='" + comment + '\'' +
                ", commented_at=" + commentedAt +
                '}';
    }
}
