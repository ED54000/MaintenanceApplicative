
package trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {

	private Game game;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUp() {
		game = new Game();
		System.setOut(new PrintStream(outContent));
	}


	@Test
	@Disabled("Creation des tests unitaires")
	public void caracterizationTest() {
		// runs 10.000 "random" games to see the output of old and new code matches
		for (int seed = 1; seed < 10_000; seed++) {
			testSeed(seed, false);
		}
	}

	private void testSeed(int seed, boolean printExpected) {
		String expectedOutput = extractOutput(new Random(seed), new GameOld());
		if (printExpected) {
			System.out.println(expectedOutput);
		}
		String actualOutput = extractOutput(new Random(seed), new Game());
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	@Disabled("enable back and set a particular seed to see the output")
	public void oneSeed() {
		testSeed(1, true);
	}

	private String extractOutput(Random rand, IGame aGame) {
		PrintStream old = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream inmemory = new PrintStream(baos)) {
			// WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
			System.setOut(inmemory);

			aGame.addPlayer("Chet");
			aGame.addPlayer("Pat");
			aGame.addPlayer("Sue");

			boolean notAWinner = false;
			do {
				aGame.roll(rand.nextInt(5) + 1);

				if (rand.nextInt(9) == 7) {
					notAWinner = aGame.handleWrongAnswer();
				} else {
					notAWinner = aGame.handleCorrectAnswer();
				}

			} while (notAWinner);
		} finally {
			System.setOut(old);
		}

		return new String(baos.toByteArray());
	}


	@Test
	public void testGameInitialization() {
		assertEquals(0, game.howManyPlayers());
		assertFalse(game.isPlayable());
	}

	@Test
	public void testAddPlayer() {
		assertTrue(game.addPlayer("Player1"));
		assertEquals(1, game.howManyPlayers());
		assertTrue(game.addPlayer("Player2"));
		assertEquals(2, game.howManyPlayers());

		assertTrue(outContent.toString().contains("Player1 was added"));
		assertTrue(outContent.toString().contains("Player2 was added"));
	}

	@Test
	public void testIsPlayable() {
		assertFalse(game.isPlayable());

		game.addPlayer("Player1");
		assertFalse(game.isPlayable());

		game.addPlayer("Player2");
		assertTrue(game.isPlayable());
	}

	@Test
	public void testRollNotInPenaltyBox() {
		game.addPlayer("Player1");
		outContent.reset();

		game.roll(3);

		String output = outContent.toString();
		assertTrue(output.contains("Player1 is the current player"));
		assertTrue(output.contains("They have rolled a 3"));
		assertTrue(output.contains("Player1's new location is 4"));
		assertTrue(output.contains("The category is"));
	}

	@Test
	public void testRollInPenaltyBoxWithOddRoll() {
		game.addPlayer("Player1");
		game.handleWrongAnswer(); // Met le joueur dans la penalty box
		outContent.reset();

		game.roll(3); // Roll impair - sort de la penalty box

		String output = outContent.toString();
		assertTrue(output.contains("Player1 is the current player"));
		assertTrue(output.contains("They have rolled a 3"));
		assertTrue(output.contains("Player1 is getting out of the penalty box"));
		assertTrue(output.contains("Player1's new location is 4"));
	}

	@Test
	public void testRollInPenaltyBoxWithEvenRoll() {
		game.addPlayer("Player1");
		game.handleWrongAnswer(); // Met le joueur dans la penalty box
		outContent.reset();

		game.roll(2); // Roll pair - reste dans la penalty box

		String output = outContent.toString();
		assertTrue(output.contains("Player1 is the current player"));
		assertTrue(output.contains("They have rolled a 2"));
		assertTrue(output.contains("Player1 is not getting out of the penalty box"));
		assertFalse(output.contains("Player1's new location is"));
	}

	@Test
	public void testPositionWrapping() {
		game.addPlayer("Player1");
		outContent.reset();

		game.roll(12); // Position initiale 1 + 12 = 13, doit revenir à 1

		String output = outContent.toString();
		assertTrue(output.contains("Player1's new location is 1"));
	}

	@Test
	public void testHandleCorrectAnswerNotInPenaltyBox() {
		game.addPlayer("Player1");
		game.addPlayer("Player2");
		outContent.reset();

		boolean result = game.handleCorrectAnswer();

		assertTrue(result); // Le jeu continue
		String output = outContent.toString();
		assertTrue(output.contains("Answer was correct!!!!"));
		assertTrue(output.contains("Player1 now has 1 Gold Coins"));
	}

	@Test
	public void testHandleCorrectAnswerInPenaltyBoxNotGettingOut() {
		game.addPlayer("Player1");
		game.addPlayer("Player2");

		game.handleWrongAnswer(); // Met Player1 dans la penalty box
		game.roll(2); // Roll pair, ne sort pas de la penalty box
		outContent.reset();

		boolean result = game.handleCorrectAnswer();

		assertTrue(result); // Le jeu continue
		String output = outContent.toString();
		assertTrue(output.contains("Answer was correct!!!!"));
	}

	@Test
	public void testHandleWrongAnswer() {
		game.addPlayer("Player1");
		game.addPlayer("Player2");
		outContent.reset();

		boolean result = game.handleWrongAnswer();

		assertTrue(result); // Le jeu continue
		String output = outContent.toString();
		assertTrue(output.contains("Question was incorrectly answered"));
		assertTrue(output.contains("Player1 was sent to the penalty box"));
	}

	@Test
	public void testPlayerWinCondition() {
		game.addPlayer("Player1");
		game.addPlayer("Player2");

		// Faire gagner 5 points au joueur
		for (int i = 0; i < 5; i++) {
			game.handleCorrectAnswer();
		}
		outContent.reset();

		// Au prochain point, le joueur atteindra 6 points
		boolean result = game.handleCorrectAnswer();

		assertTrue(result); 
	}

	@Test
	public void testPlayerRotation() {
		game.addPlayer("Player1");
		game.addPlayer("Player2");
		game.addPlayer("Player3");

		game.roll(1); // Player1 joue
		game.handleCorrectAnswer(); // Passe à Player2

		outContent.reset();
		game.roll(1); // Player2 joue
		assertTrue(outContent.toString().contains("Player2 is the current player"));

		game.handleCorrectAnswer(); // Passe à Player3

		outContent.reset();
		game.roll(1); // Player3 joue
		assertTrue(outContent.toString().contains("Player3 is the current player"));

		game.handleCorrectAnswer(); // Devrait revenir à Player1

		outContent.reset();
		game.roll(1); // Player1 joue à nouveau
		assertTrue(outContent.toString().contains("Player1 is the current player"));
	}

	@Test
	public void testCurrentCategory() {
		game.addPlayer("Player1");

		// Tester différentes positions et les catégories correspondantes
		game.roll(0); // Position 1
		assertTrue(outContent.toString().contains("The category is Pop"));

		outContent.reset();
		game.roll(1); // Position 2
		assertTrue(outContent.toString().contains("The category is Science"));

		outContent.reset();
		game.roll(1); // Position 3
		assertTrue(outContent.toString().contains("The category is Sports"));

		outContent.reset();
		game.roll(1); // Position 4
		assertTrue(outContent.toString().contains("The category is Rock"));
	}
}
