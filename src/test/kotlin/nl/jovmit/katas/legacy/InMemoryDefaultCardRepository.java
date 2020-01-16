package nl.jovmit.katas.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class InMemoryDefaultCardRepository extends ActionsDefaultCardRepository {

    private List<ActionsWeeklyReportDefaultCard> cards = new ArrayList<>();

    @Override
    public void deleteIfExists(UUID userId) {
        List<ActionsWeeklyReportDefaultCard> matchingItems = cards.stream()
                .filter(it -> it.getUserId().equals(userId))
                .collect(Collectors.toList());
        cards.removeAll(matchingItems);
    }

    @Override
    public ActionsWeeklyReportDefaultCard find(UUID userId, String name) {
        return cards.stream()
                .filter(it -> it.getUserId().equals(userId) &&
                        it.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(ActionsWeeklyReportDefaultCard actionsWeeklyReportDefaultCard) {
        cards.add(actionsWeeklyReportDefaultCard);
    }

    @Override
    public void delete(UUID userId, CardType cardType) {
        super.delete(userId, cardType);
    }
}
