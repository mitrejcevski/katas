package nl.jovmit.katas.legacy;

import java.util.UUID;

class ActionsWeeklyReportDefaultCardBuilder {

    private UUID userId;
    private String cardName;
    private int timesNotShown;

    public static ActionsWeeklyReportDefaultCardBuilder aWeeklyCard() {
        return new ActionsWeeklyReportDefaultCardBuilder();
    }

    public ActionsWeeklyReportDefaultCardBuilder withUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public ActionsWeeklyReportDefaultCardBuilder withCardName(String cardName) {
        this.cardName = cardName;
        return this;
    }

    public ActionsWeeklyReportDefaultCardBuilder withTimesNotShown(
            int timesNotShown) {
        this.timesNotShown = timesNotShown;
        return this;
    }

    public WeeklyReportedDefaultCard build() {
        WeeklyReportedDefaultCard card =
                new WeeklyReportedDefaultCard(userId, cardName, 0);
        card.setTimesNotShown(timesNotShown);
        return card;
    }
}
