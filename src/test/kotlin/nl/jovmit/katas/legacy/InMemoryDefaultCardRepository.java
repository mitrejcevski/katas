package nl.jovmit.katas.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class InMemoryDefaultCardRepository extends CardsRepository {

    private List<WeeklyReportedDefaultCard> cards = new ArrayList<>();

    @Override
    public void deleteIfExists(UUID userId) {
        List<WeeklyReportedDefaultCard> matchingItems = cards.stream()
                .filter(it -> it.getUserId().equals(userId))
                .collect(Collectors.toList());
        cards.removeAll(matchingItems);
    }

    @Override
    public WeeklyReportedDefaultCard find(UUID userId, String name) {
        return cards.stream()
                .filter(it -> it.getUserId().equals(userId) &&
                        it.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(WeeklyReportedDefaultCard weeklyReportedDefaultCard) {
        cards.add(weeklyReportedDefaultCard);
    }

    @Override
    public void delete(UUID userId, Card card) {
        List<WeeklyReportedDefaultCard> matchingItems = cards.stream()
                .filter(it -> it.getUserId().equals(userId) &&
                        it.getCardType().equals(card))
                .collect(Collectors.toList());
        cards.removeAll(matchingItems);
    }
}
