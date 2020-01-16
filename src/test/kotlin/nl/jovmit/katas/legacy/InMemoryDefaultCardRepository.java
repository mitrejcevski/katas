package nl.jovmit.katas.legacy;

import java.util.UUID;

class InMemoryDefaultCardRepository extends ActionsDefaultCardRepository {

    @Override
    public void deleteIfExists(UUID userId) {
        super.deleteIfExists(userId);
    }

    @Override
    public ActionsWeeklyReportDefaultCard find(UUID userId, String name) {
        return super.find(userId, name);
    }

    @Override
    public void save(ActionsWeeklyReportDefaultCard actionsWeeklyReportDefaultCard) {
        super.save(actionsWeeklyReportDefaultCard);
    }

    @Override
    public void delete(UUID userId, CardType cardType) {
        super.delete(userId, cardType);
    }
}
