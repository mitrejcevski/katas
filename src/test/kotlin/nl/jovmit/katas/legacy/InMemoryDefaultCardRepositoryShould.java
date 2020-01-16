package nl.jovmit.katas.legacy;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class InMemoryDefaultCardRepositoryShould {


    private static final UUID USER_ID = UUID.randomUUID();
    private static final String CARD_NAME = "::irrelevant card name::";

    private ActionsDefaultCardRepository repository;

    @Before
    public void initialize() {
        repository = new InMemoryDefaultCardRepository();
    }

    @Test
    public void save_new_card() {
        ActionsWeeklyReportDefaultCard cardToSave =
                new ActionsWeeklyReportDefaultCard(USER_ID, CARD_NAME, 0);

        repository.save(cardToSave);

        assertEquals(cardToSave, repository.find(USER_ID, CARD_NAME));
    }
}