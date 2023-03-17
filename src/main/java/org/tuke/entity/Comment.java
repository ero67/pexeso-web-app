package org.tuke.entity;

public class Comment {
    private String player;
    private String game;
    private String comment;

    public Comment(String player, String game, String comment) {
        this.player = player;
        this.game = game;
        this.comment = comment;
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
}
