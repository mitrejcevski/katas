package nl.jovmit.katas.legacy;

import java.util.UUID;

class ActionsWeeklyReportDefaultCardBuilder {

    private UUID userId;
    private String cardName;

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

    public ActionsWeeklyReportDefaultCard build() {
        return new ActionsWeeklyReportDefaultCard(userId, cardName, 0);
    }
}
