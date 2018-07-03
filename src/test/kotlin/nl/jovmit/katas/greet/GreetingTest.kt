package nl.jovmit.katas.greet

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GreetingTest {

    private lateinit var greeter: Greeter

    @Before
    fun setup() {
        greeter = Greeter()
    }

    @Test
    fun shouldReturnGreetingForGivenName() {
        assertEquals("Hello, Bob.", greeter.greet("Bob"))
    }

    @Test
    fun shouldReturnGeneralGreetForGivenEmptyName() {
        assertEquals("Hello, my friend.", greeter.greet(""))
    }

    @Test
    fun shouldReturnShoutingForGivenShoutingName() {
        assertEquals("HELLO JERRY!", greeter.greet("JERRY"))
    }

    @Test
    fun shouldReturnGreetingForBothWhenTwoNamesProvided() {
        assertEquals("Hello, Jill and Jane.", greeter.greet("Jill", "Jane"))
    }

    @Test
    fun shouldReturnGreetingForAllCommaSeparatedWhenMoreThenTwoNamesGiven() {
        assertEquals("Hello, Amy, Brain, and Charlotte.", greeter.greet("Amy", "Brain", "Charlotte"))
    }

    @Test
    fun shouldReturnMixedGreetingForShoutedAndNonShoutedGivenNames() {
        assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN!", greeter.greet("Amy", "BRIAN", "Charlotte"))
    }

    @Test
    fun shouldAutomaticallySplitCommaSeparatedNames() {
        assertEquals("Hello, Bob, Charlie, and Dianne.", greeter.greet("Bob", "Charlie, Dianne"))
    }
}
