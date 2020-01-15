package nl.jovmit.katas.legacy;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNull;

public class LegacyShould {

    private static final UUID USER_ID = UUID.randomUUID();
    private static final String CARD_NAME = "::irrelevant card name::";

    private ActionsDefaultCardRepository repository = new ActionsDefaultCardRepository();

    @Test
    public void delete_by_user_id_when_weekly_cards_collection_does_not_contain_top_priority_card() {
        UserContext userContext = new UserContext();
        List<CardType> configuredCardsInOrder = Collections.emptyList();

        new Legacy().validateAndUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertNull(repository.find(USER_ID, CARD_NAME));
    }
}