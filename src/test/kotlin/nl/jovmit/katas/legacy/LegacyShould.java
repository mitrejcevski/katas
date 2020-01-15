package nl.jovmit.katas.legacy;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNull;

public class LegacyShould {

    private static final UUID USER_ID = UUID.randomUUID();
    private static final String CARD_NAME = "::irrelevant card name::";
    private static final String FIRST_CARD_NAME = "::card 1::";
    private static final String SECOND_CARD_NAME = "::card 2::";

    private ActionsDefaultCardRepository repository = new ActionsDefaultCardRepository();

    @Test
    public void delete_by_user_id_when_weekly_cards_collection_does_not_contain_top_priority_card() {
        UserContext userContext = UserContextBuilder.aUserContext()
                .withUserId(USER_ID)
                .withFeatureEnabled()
                .build();
        CardType card1 = new CardType(FIRST_CARD_NAME);
        CardType card2 = new CardType(SECOND_CARD_NAME);
        List<CardType> configuredCardsInOrder = Arrays.asList(card1, card2);

        Legacy legacy = new Legacy(repository);
        legacy.validateAndUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertNull(repository.find(USER_ID, CARD_NAME));
    }
}