package nl.jovmit.katas.legacy;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static nl.jovmit.katas.legacy.ActionsWeeklyReportDefaultCardBuilder.aWeeklyCard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LegacyShould {

    private static final UUID USER_ID = UUID.randomUUID();
    private static final String CARD_NAME = "::irrelevant card name::";
    private static final String FIRST_CARD_NAME = "::card 1::";
    private static final String SECOND_CARD_NAME = "::card 2::";

    private UserContext userContext = UserContextBuilder.aUserContext()
            .withUserId(USER_ID)
            .withFeatureEnabled()
            .build();
    private CardType card1 = new CardType(FIRST_CARD_NAME);
    private CardType card2 = new CardType(SECOND_CARD_NAME);
    private List<CardType> configuredCardsInOrder = Arrays.asList(card1, card2);

    private ActionsDefaultCardRepository repository = new InMemoryDefaultCardRepository();

    @Test
    public void delete_by_user_id_when_weekly_cards_collection_does_not_contain_top_priority_card() {
        Legacy legacy = new Legacy(repository);
        legacy.validateAndUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertNull(repository.find(USER_ID, CARD_NAME));
    }

    @Test
    public void save_new_weekly_default_card() {
        ActionsWeeklyReportDefaultCard weeklyCard = aWeeklyCard()
                .withCardName(FIRST_CARD_NAME)
                .withUserId(USER_ID)
                .build();

        Legacy legacy = new TestableLegacy(repository);
        legacy.validateAndUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertEquals(weeklyCard, repository.find(USER_ID, FIRST_CARD_NAME));
    }

    private static class TestableLegacy extends Legacy {

        public TestableLegacy(ActionsDefaultCardRepository repository) {
            super(repository);
        }

        @Override
        protected List<String> getActionsWeeklyReportDefaultCards() {
            return Arrays.asList(FIRST_CARD_NAME, SECOND_CARD_NAME);
        }
    }
}