package nl.jovmit.katas.legacy;

import java.util.Objects;
import java.util.UUID;

class ActionsWeeklyReportDefaultCard {

    private final UUID userId;
    private final String name;
    private final int initialCount;
    private int timesNotShown;

    public ActionsWeeklyReportDefaultCard(UUID userId, String name, int initialCount) {
        this.userId = userId;
        this.name = name;
        this.initialCount = initialCount;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getNoTimesShown() {
        return timesNotShown;
    }

    public void setTimesNotShown(int timesNotShown) {
        this.timesNotShown = timesNotShown;
    }

    public CardType getCardType() {
        return new CardType(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionsWeeklyReportDefaultCard that = (ActionsWeeklyReportDefaultCard) o;
        return initialCount == that.initialCount &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(timesNotShown, that.timesNotShown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, initialCount, timesNotShown);
    }
}
