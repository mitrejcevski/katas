package nl.jovmit.katas.legacy;

import java.util.Objects;

class Card {

    private final String cardName;

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public String name() {
        return cardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardName, card.cardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardName);
    }
}
