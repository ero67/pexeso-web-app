package org.tuke.entity;

import java.util.Date;

public class Score {
    private String game;
    private int points;
    private Date playedAt;
    private String player;

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


}
