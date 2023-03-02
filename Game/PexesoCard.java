package Game.Game;

public class PexesoCard {
    private CardState state= CardState.HIDDEN;
    private final String value;

    public PexesoCard(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public CardState getState() {
        return state;
    }

    public void setState(CardState state) {
        this.state = state;
    }
}
