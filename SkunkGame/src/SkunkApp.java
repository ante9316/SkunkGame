import java.util.Scanner;

public class SkunkApp
{

	public static void main(String[] args)
	{
		Scanner newInput = new Scanner(System.in);
		// int numOfRounds = 0;
		int numOfPlayers = 0;
		boolean notValidinput = false;
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

		GameController newGame = new GameController();
		String newPlayerName;

		for (int i = 0; i < numOfPlayers; i++)
		{
			StdOut.println("Please enter the name of player : ");
			newPlayerName = newInput.nextLine();
			newGame.createPlayer(newPlayerName);

		}

		pickActivePlayer(newInput, newGame);

		String userInput;

		// variable to keep track of number of players have to take turn when at
		// least one player score >=100 and stop the game
		int numOfRemainingPlayers = newGame.listOfPlayers.size();

		Roll activeRoll;
		// link players with round to keep track of round score per player
		for (int i = 0; i < newGame.listOfPlayers.size(); i++)
		{
			Round newRound = new Round();
			newGame.playerRound.put(newGame.getListOfPlayers().get(i), newRound);
		}

		// Play until a player with score >=100 stops the game
		while (newGame.getActivePlayer().getPlayerRoundScore() < 100)
		{

			Turn newTurn = new Turn();

			do
			{
				StdOut.println(newGame.getActivePlayer().getPlayerName()
						+ ", it is your Turn. Press ENTER to roll or 'S' to give up your turn ");
				userInput = newInput.nextLine();

				if (userInput.equalsIgnoreCase("s"))
				{
					break;
				}
				activeRoll = newGame.startRound(newGame.playerRound, newGame, newTurn);

				StdOut.println(
						"Your First Die: " + activeRoll.getDie1() + "\nYour Second Die: " + activeRoll.getDie2());
				StdOut.println("Your Roll Score: " + activeRoll.getLastTotal() + "\nYour turn Score: "
						+ newTurn.getTurnScore() + "\nYour Round Score: "
						+ newGame.playerRound.get(newGame.getActivePlayer()).getRoundScore());
			}
			while (!userInput.equalsIgnoreCase("s") && (!activeRoll.isSkunkAndDeuceRolled()
					&& !activeRoll.isSkunkRolled() && !activeRoll.isTwoSkunksRolled()));

			if (userInput.equalsIgnoreCase("s") && newGame.getActivePlayer().getPlayerRoundScore() >= 100)
			{
				numOfRemainingPlayers--;
			}
			if (!newGame.moveToNextPlayer(newGame.getActivePlayer(), newGame, numOfRemainingPlayers))
			{
				// 1 Round of Game is Over. Report back the final Score
				StdOut.println("This round is Over. The winnder is :" + newGame.whoIsWinner());
			}
			else
			{
				StdOut.println("******** Sorry! You SKUNK :( ********");
			}
		}

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
				// Allow re-entry?
			}

		}
		else
		{
			newGame.setFirstActivePlayer();
		}
	}

}
