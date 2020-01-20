package nl.jovmit.katas.legacy;

import java.util.Objects;
import java.util.UUID;

class WeeklyReportedDefaultCard {

    private final UUID userId;
    private final String name;
    private final int initialCount;
    private int timesNotShown;

    public WeeklyReportedDefaultCard(UUID userId, String name, int initialCount) {
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

    public Card getCardType() {
        return new Card(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeeklyReportedDefaultCard that = (WeeklyReportedDefaultCard) o;
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
