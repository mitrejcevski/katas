package nl.jovmit.katas.legacy;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

class Legacy {

    private static final List<String> ACTIONS_WEEKLY_REPORT_DEFAULT_CARDS = Collections.emptyList();
    private static final int TOP_PRIORITY_INDEX = 0;
    private static final int INITIAL_COUNT = 0;
    private static final int MAX_NO_TIMES_TO_SHOW = 0;

    private final ActionsDefaultCardRepository actionsDefaultCardRepository;
    private final List<String> weeklyDefaultCards;

    public Legacy(ActionsDefaultCardRepository repository) {
        this(repository, ACTIONS_WEEKLY_REPORT_DEFAULT_CARDS);
    }

    public Legacy(ActionsDefaultCardRepository repository,
                  List<String> weeklyDefaultCards) {
        this.actionsDefaultCardRepository = repository;
        this.weeklyDefaultCards = weeklyDefaultCards;
    }

    public void validateThenUpdateDefaultCard(UserContext userContext, List<CardType> configuredActionCardsInOrder) {
        if (validate(userContext, configuredActionCardsInOrder)) {
            updateDefaultCard(userContext, configuredActionCardsInOrder);
        }
    }

    private boolean validate(UserContext userContext, List<CardType> configuredActionCardsInOrder) {
        return userContext.isFeatureEnabled() && !configuredActionCardsInOrder.isEmpty();
    }

    private void updateDefaultCard(UserContext userContext, List<CardType> configuredActionCardsInOrder) {
        final UUID userId = userContext.getUserId();
        if (!weeklyDefaultCards.contains(configuredActionCardsInOrder.get(TOP_PRIORITY_INDEX).name())) {
            actionsDefaultCardRepository.deleteIfExists(userId);
        } else {
            promoteNewWeeklyCard(userContext, configuredActionCardsInOrder, userId);
        }
    }

    private void promoteNewWeeklyCard(UserContext userContext, List<CardType> configuredActionCardsInOrder, UUID userId) {
        CardType defaultCardConfigured = configuredActionCardsInOrder.get(TOP_PRIORITY_INDEX);
        ActionsWeeklyReportDefaultCard actionsWeeklyReportDefaultCard =
                actionsDefaultCardRepository.find(userId, defaultCardConfigured.name());
        if (actionsWeeklyReportDefaultCard == null) {
            promoteDefaultCardFor(userId, defaultCardConfigured);
        } else if (actionsWeeklyReportDefaultCard.getNoTimesShown() == MAX_NO_TIMES_TO_SHOW) {
            actionsDefaultCardRepository.delete(userId, actionsWeeklyReportDefaultCard.getCardType());
            configuredActionCardsInOrder.remove(TOP_PRIORITY_INDEX);
            validateThenUpdateDefaultCard(userContext, configuredActionCardsInOrder);
        }
    }

    private void promoteDefaultCardFor(UUID userId, CardType defaultCardConfigured) {
        actionsDefaultCardRepository.deleteIfExists(userId);
        ActionsWeeklyReportDefaultCard actionsWeeklyReportDefaultCard =
                new ActionsWeeklyReportDefaultCard(userId, defaultCardConfigured.name(), INITIAL_COUNT);
        actionsDefaultCardRepository.save(actionsWeeklyReportDefaultCard);
    }
}
