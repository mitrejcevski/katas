package nl.jovmit.katas.legacy;

import java.util.Objects;

class CardType {

    private final String cardName;

    public CardType(String cardName) {
        this.cardName = cardName;
    }

    public String name() {
        return cardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardType cardType = (CardType) o;
        return Objects.equals(cardName, cardType.cardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardName);
    }
}
