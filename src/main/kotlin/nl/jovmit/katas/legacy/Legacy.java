package nl.jovmit.katas.legacy;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

class Legacy {

    private static final List<String> WEEKLY_REPORTED_DEFAULT_CARDS = Collections.emptyList();
    private static final int TOP_PRIORITY_INDEX = 0;
    private static final int INITIAL_COUNT = 0;
    private static final int MAX_TIMES_NOT_SHOWN = 0;

    private final CardsRepository cardsRepository;
    private final List<String> weeklyDefaultCards;

    public Legacy(CardsRepository repository) {
        this(repository, WEEKLY_REPORTED_DEFAULT_CARDS);
    }

    public Legacy(CardsRepository repository,
                  List<String> weeklyDefaultCards) {
        this.cardsRepository = repository;
        this.weeklyDefaultCards = weeklyDefaultCards;
    }

    public void validateThenUpdateDefaultCard(
            UserContext userContext,
            List<Card> configuredActionCardsInOrder) {
        if (validate(userContext, configuredActionCardsInOrder)) {
            updateDefaultCard(userContext, configuredActionCardsInOrder);
        }
    }

    private boolean validate(UserContext userContext, List<Card> configuredActionCardsInOrder) {
        return userContext.isFeatureEnabled() && !configuredActionCardsInOrder.isEmpty();
    }

    private void updateDefaultCard(UserContext userContext, List<Card> configuredActionCardsInOrder) {
        final UUID userId = userContext.getUserId();
        if (!weeklyDefaultCards.contains(configuredActionCardsInOrder.get(TOP_PRIORITY_INDEX).name())) {
            cardsRepository.deleteIfExists(userId);
        } else {
            promoteNewWeeklyCard(userContext, configuredActionCardsInOrder, userId);
        }
    }

    private void promoteNewWeeklyCard(UserContext userContext, List<Card> configuredActionCardsInOrder, UUID userId) {
        Card defaultCardConfigured = configuredActionCardsInOrder.get(TOP_PRIORITY_INDEX);
        WeeklyReportedDefaultCard weeklyReportedDefaultCard =
                cardsRepository.find(userId, defaultCardConfigured.name());
        if (weeklyReportedDefaultCard == null) {
            promoteDefaultCardFor(userId, defaultCardConfigured);
        } else if (weeklyReportedDefaultCard.getNoTimesShown() == MAX_TIMES_NOT_SHOWN) {
            cardsRepository.delete(userId, weeklyReportedDefaultCard.getCardType());
            configuredActionCardsInOrder.remove(TOP_PRIORITY_INDEX);
            validateThenUpdateDefaultCard(userContext, configuredActionCardsInOrder);
        }
    }

    private void promoteDefaultCardFor(UUID userId, Card defaultCardConfigured) {
        cardsRepository.deleteIfExists(userId);
        WeeklyReportedDefaultCard weeklyReportedDefaultCard =
                new WeeklyReportedDefaultCard(userId, defaultCardConfigured.name(), INITIAL_COUNT);
        cardsRepository.save(weeklyReportedDefaultCard);
    }
}
