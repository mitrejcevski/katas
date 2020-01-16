package nl.jovmit.katas.legacy;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static nl.jovmit.katas.legacy.ActionsWeeklyReportDefaultCardBuilder.aWeeklyCard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InMemoryDefaultCardRepositoryShould {


    private static final UUID USER_ID = UUID.randomUUID();
    private static final String CARD_NAME = "::irrelevant card name::";
    private static final String ANOTHER_CARD_NAME = "::another irrelevant card name::";

    private ActionsDefaultCardRepository repository;
    private ActionsWeeklyReportDefaultCard cardToSave;

    @Before
    public void initialize() {
        repository = new InMemoryDefaultCardRepository();
        cardToSave = aWeeklyCard().withUserId(USER_ID).withCardName(CARD_NAME).build();
        repository.save(cardToSave);
    }

    @Test
    public void save_new_card() {
        assertEquals(cardToSave, repository.find(USER_ID, CARD_NAME));
    }

    @Test
    public void delete_cards_by_user_id() {
        ActionsWeeklyReportDefaultCard anotherCardToSave = aWeeklyCard()
                .withUserId(USER_ID)
                .withCardName(ANOTHER_CARD_NAME)
                .build();
        repository.save(anotherCardToSave);

        repository.deleteIfExists(USER_ID);

        assertNull(repository.find(USER_ID, CARD_NAME));
    }

    @Test
    public void delete_cards_by_user_id_and_card_type() {
        CardType cardType = new CardType(CARD_NAME);

        repository.delete(USER_ID, cardType);

        assertNull(repository.find(USER_ID, CARD_NAME));
    }
}