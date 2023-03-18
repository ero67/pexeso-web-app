package sk.tuke.gamestudio.entity;

public class Rating {
    private String player;
    private String game;
    private int rating;

    public Rating(String player, String game, int rating) {
        this.player = player;
        this.game = game;
        this.rating = rating;
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
}
