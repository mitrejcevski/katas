package nl.jovmit.katas.legacy;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

class Legacy {

    private static final List<String> ACTIONS_WEEKLY_REPORT_DEFAULT_CARDS = Collections.emptyList();
    private static final int TOP_PRIO_INDEX = 0;
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

    public void validateAndUpdateDefaultCard(UserContext userContext, List<CardType> configuredActionCardsInOrder) {
        if (userContext.isFeatureEnabled() && !configuredActionCardsInOrder.isEmpty()) {
            updateDefaultCard(userContext, configuredActionCardsInOrder);
        }
    }

    private void updateDefaultCard(UserContext userContext, List<CardType> configuredActionCardsInOrder) {
        final UUID userId = userContext.getUserId();
        //check if top prio card is one of the 2 default cards, if no then delete any entry in the table because it has to be 2 times in a row (reset)
        if (!weeklyDefaultCards.contains(configuredActionCardsInOrder.get(TOP_PRIO_INDEX).name())) {
            actionsDefaultCardRepository.deleteIfExists(userId);
        } else {
            CardType defaultCardConfigured = configuredActionCardsInOrder.get(TOP_PRIO_INDEX);

            //check the entry of the default card in the table, if there is no entry create one
            ActionsWeeklyReportDefaultCard actionsWeeklyReportDefaultCard = actionsDefaultCardRepository.find(userId, defaultCardConfigured.name());
            if (actionsWeeklyReportDefaultCard == null) {
                actionsDefaultCardRepository.deleteIfExists(userId); //takes care of any other entry if present, so that we only have one entry per user at a time
                actionsWeeklyReportDefaultCard = new ActionsWeeklyReportDefaultCard(userId, defaultCardConfigured.name(), INITIAL_COUNT);
                actionsDefaultCardRepository.save(actionsWeeklyReportDefaultCard);
            } else if (actionsWeeklyReportDefaultCard.getNoTimesShown() == MAX_NO_TIMES_TO_SHOW) {
                //check the count, if it is 2 delete the current one from db and also remove from the list
                //and repeat the process, the scenario will not be repeated if the card in question is the MSM general card
                //because that is the lowest in prio and there is no card after that
                //But, in case the current card is SS and count is 2 already, it will be removed from the list, deleted from the table and the recursive
                //call will make a new entry of the MSM card in the db and show it in the list
                actionsDefaultCardRepository.delete(userId, actionsWeeklyReportDefaultCard.getCardType());
                configuredActionCardsInOrder.remove(TOP_PRIO_INDEX);
                validateAndUpdateDefaultCard(userContext, configuredActionCardsInOrder);
            }
        }
    }
}
