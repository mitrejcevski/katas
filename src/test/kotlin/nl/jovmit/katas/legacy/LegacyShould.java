package nl.jovmit.katas.legacy;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
    private List<CardType> configuredCardsInOrder = new ArrayList<CardType>() {{
        add(card1);
        add(card2);
    }};

    private ActionsDefaultCardRepository repository = new InMemoryDefaultCardRepository();
    private Legacy legacy;

    @Before
    public void setUp() {
        List<String> weeklyDefaultCards = Arrays.asList(FIRST_CARD_NAME, SECOND_CARD_NAME);
        legacy = new Legacy(repository, weeklyDefaultCards);
    }

    @Test
    public void delete_by_user_id_when_weekly_cards_collection_does_not_contain_top_priority_card() {
        Legacy legacy = new Legacy(repository);

        legacy.validateThenUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertNull(repository.find(USER_ID, CARD_NAME));
    }

    @Test
    public void save_new_weekly_default_card() {
        ActionsWeeklyReportDefaultCard weeklyCard = aWeeklyCard()
                .withCardName(FIRST_CARD_NAME)
                .withUserId(USER_ID)
                .build();

        legacy.validateThenUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertEquals(weeklyCard, repository.find(USER_ID, FIRST_CARD_NAME));
    }

    @Test
    public void remove_old_weekly_reported_default_card() {
        ActionsWeeklyReportDefaultCard oldWeeklyCard = aWeeklyCard()
                .withUserId(USER_ID)
                .withCardName(CARD_NAME)
                .build();
        repository.save(oldWeeklyCard);

        legacy.validateThenUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertNull(repository.find(USER_ID, CARD_NAME));
    }

    @Test
    public void do_nothing_when_weekly_default_card_not_shown_times_differs_from_default() {
        int differentThanDefault = 5;
        ActionsWeeklyReportDefaultCard weeklyCard = aWeeklyCard()
                .withUserId(USER_ID)
                .withCardName(FIRST_CARD_NAME)
                .withTimesNotShown(differentThanDefault)
                .build();
        repository.save(weeklyCard);

        legacy.validateThenUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertEquals(weeklyCard, repository.find(USER_ID, FIRST_CARD_NAME));
    }

    @Test
    public void delete_weekly_default_card_when_times_not_shown_is_same_as_default() {
        int sameAsDefault = 0;
        ActionsWeeklyReportDefaultCard weeklyCard = aWeeklyCard()
                .withUserId(USER_ID)
                .withCardName(FIRST_CARD_NAME)
                .withTimesNotShown(sameAsDefault)
                .build();
        repository.save(weeklyCard);

        legacy.validateThenUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertNull(repository.find(USER_ID, FIRST_CARD_NAME));
    }

    @Test
    public void remove_top_priority_record_from_configured_cards_in_order() {
        int sameAsDefault = 0;
        ActionsWeeklyReportDefaultCard weeklyCard = aWeeklyCard()
                .withUserId(USER_ID)
                .withCardName(FIRST_CARD_NAME)
                .withTimesNotShown(sameAsDefault)
                .build();
        repository.save(weeklyCard);

        legacy.validateThenUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertEquals(1, configuredCardsInOrder.size());
        assertEquals(SECOND_CARD_NAME, configuredCardsInOrder.get(0).name());
    }

    @Test
    public void promote_new_card() {
        ActionsWeeklyReportDefaultCard currentWeeklyCard = aWeeklyCard()
                .withUserId(USER_ID)
                .withCardName(FIRST_CARD_NAME)
                .build();
        repository.save(currentWeeklyCard);

        ActionsWeeklyReportDefaultCard newWeeklyCard = aWeeklyCard()
                .withUserId(USER_ID)
                .withCardName(SECOND_CARD_NAME)
                .build();

        legacy.validateThenUpdateDefaultCard(userContext, configuredCardsInOrder);

        assertEquals(newWeeklyCard, repository.find(USER_ID, SECOND_CARD_NAME));
    }
}