import java.util.Scanner;

public class SkunkApp
{

	public static void main(String[] args)
	{
		Scanner newInput = new Scanner(System.in);

		int numOfPlayers = 0;

		numOfPlayers = enterPlayerInfo(numOfPlayers);

		GameController newGame = new GameController();

		String newPlayerName;

		for (int i = 0; i < numOfPlayers; i++)
		{
			StdOut.println("Please enter the name of player : ");
			newPlayerName = newInput.nextLine();
			newGame.createPlayer(newPlayerName);

		}

		pickActivePlayer(newInput, newGame);

		// link players with round to keep track of round score per player
		for (int i = 0; i < newGame.listOfPlayers.size(); i++)
		{
			Round newRound = new Round();
			newGame.playerRound.put(newGame.getListOfPlayers().get(i), newRound);
		}

		Turn newTurn = new Turn();
		Roll activeRoll = null;

		// Game is on until at least one player score >=100 and all remaining
		// players took their turns
		boolean isRoundActive = true;

		while (isRoundActive)
		{

			activeRoll = playGame(newGame, newTurn);

			// check if active player lost his/her turn due to JUST SKIPPING
			if (activeRoll == null && newGame.playerRound.get(newGame.getActivePlayer()).getRoundScore() < 100)
			{
				newGame.moveToNextPlayer(newGame.getActivePlayer(), newGame);
			}

			// check if active player want to stop after scoring >=100
			else if (activeRoll == null && newGame.playerRound.get(newGame.getActivePlayer()).getRoundScore() >= 100)
			{
				StdOut.println("\nGOAL! " + newGame.getActivePlayer().getPlayerName()
						+ " You are a Potenial Winner... but NOT YET");

				for (int i = 1; i < (newGame.getListOfPlayers().size()); i++)
				{
					newGame.moveToNextPlayer(newGame.getActivePlayer(), newGame);
					activeRoll = playGame(newGame, newTurn);

					// check if active player lost the turn due to SKUNK
					if (activeRoll.isSkunkAndDeuceRolled() || activeRoll.isSkunkRolled()
							|| activeRoll.isTwoSkunksRolled())
					{
						StdOut.println("\nSorry, You *SKUNKED!* ");
						continue;

					}
				}

				isRoundActive = false;
			}

			// check if active player lost his/her turn due to SKUNK
			else if (activeRoll.isSkunkAndDeuceRolled() || activeRoll.isSkunkRolled() || activeRoll.isTwoSkunksRolled())
			{
				StdOut.println("\nSorry, You *SKUNKED!* ");
				newGame.moveToNextPlayer(newGame.getActivePlayer(), newGame);
			}

		}

		StdOut.print("\n******* GAME OVER *******");

		Player winnerPlayer = newGame.calculateFinalScore(newGame);

		displayFinalScore(winnerPlayer, newGame);

	}

	private static void displayFinalScore(Player winnerPlayer, GameController newGame)
	{
		for (Player player : newGame.playerRound.keySet())
		{

			if (winnerPlayer.getPlayerName().equalsIgnoreCase(player.getPlayerName()))
			{
				StdOut.println("\nWinner: " + winnerPlayer.getPlayerName() + ", You are the winner with score of "
						+ newGame.playerRound.get(player).getRoundScore());
			}
			else
			{
				StdOut.println("\n@" + player.getPlayerName() + ": you have a final score of "
						+ newGame.playerRound.get(player).getRoundScore());
			}
		}
	}

	protected static Roll playGame(GameController newGame, Turn newTurn)
	{
		Roll activeRoll;

		Scanner nextInput = new Scanner(System.in);
		String nextUserInput;
		StdOut.println("\n" + newGame.getActivePlayer().getPlayerName()
				+ ", it is your Turn. Press ENTER to roll or 'S' to give up your turn ");

		nextUserInput = nextInput.nextLine();

		if (nextUserInput.equalsIgnoreCase("s"))
		{
			return null;
		}
		activeRoll = newGame.startRound(newGame.playerRound, newGame, newTurn);

		StdOut.println("Your First Die: " + activeRoll.getDie1() + "\nYour Second Die: " + activeRoll.getDie2());

		StdOut.println("Your Roll Score: " + activeRoll.getLastTotal() + "\nYour turn Score: " + newTurn.getTurnScore()
				+ "\nYour Round Score: " + newGame.playerRound.get(newGame.getActivePlayer()).getRoundScore());
		return activeRoll;
	}

	protected static int enterPlayerInfo(int numOfPlayers)
	{
		boolean notValidinput;
		do
		{

			try
			{
				StdOut.println("What is the number of players in the game?");
				numOfPlayers = Integer.parseInt(StdIn.readLine());
				notValidinput = false;

				if (numOfPlayers < 2)
				{
					StdOut.println("Please enter at least 2 players");
					notValidinput = true;
				}
			}
			catch (NumberFormatException ex)
			{
				StdOut.println("You entered an invalid number, Please enter a valid integer");
				notValidinput = true;
			}
		}
		while (notValidinput);

		return numOfPlayers;
	}

	protected static void pickActivePlayer(Scanner newInput, GameController newGame)
	{
		StdOut.println(
				"Do you want to pick who is going to play first (Y/N)? If No(N), the system would pick the fist player you entered. ");

		if (newInput.nextLine().equalsIgnoreCase("Y"))
		{
			StdOut.println(
					"Who do you want to be the first player? THEN goes to the next player in clockwise direction");
			String activePlayerToBe = newInput.nextLine();

			if (newGame.checkPlayerNameExist(activePlayerToBe))
			{
				newGame.setActivePlayer(activePlayerToBe);
			}
			else
			{
				StdOut.println("Sorry, the player doesn't exist in the system. Exiting the application...");
				System.exit(0);

			}

		}
		else
		{
			newGame.setFirstActivePlayer();
		}
	}

}
