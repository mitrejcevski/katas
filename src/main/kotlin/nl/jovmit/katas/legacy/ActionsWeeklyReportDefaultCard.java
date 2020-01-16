package nl.jovmit.katas.legacy;

import java.util.UUID;

class ActionsWeeklyReportDefaultCard {

    private final UUID userId;
    private final String name;
    private final int initialCount;

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
        throw new UnsupportedOperationException("Not Implemented");
    }

    public CardType getCardType() {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
