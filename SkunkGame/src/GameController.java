import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameController
{
	protected ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	protected Player activePlayer;
	protected Map<Player, Round> playerRound = new HashMap<Player, Round>();
	protected int activePlayerindex;

	protected boolean isLastTurn;

	public Player createPlayer(String newPlayerName)
	{

		Player newPlayer = new Player(newPlayerName);
		this.listOfPlayers.add(newPlayer);
		return newPlayer;
	}

	// setting the user entered player as an active player
	public void setActivePlayer(String activePlayerToBe)
	{
		for (int i = 0; i < listOfPlayers.size(); i++)
		{
			if (listOfPlayers.get(i).getPlayerName().equalsIgnoreCase(activePlayerToBe))
			{
				this.activePlayer = listOfPlayers.get(i);
				this.activePlayerindex = i;

				break;
			}
		}

	}

	// setting the first player as an active player
	public void setFirstActivePlayer()
	{
		this.activePlayer = listOfPlayers.get(0);
		this.activePlayerindex = 0;
		StdOut.println("Active player " + this.activePlayer.getPlayerName());
	}

	public boolean checkPlayerNameExist(String readLine)
	{
		for (int i = 0; i < listOfPlayers.size(); i++)
		{
			if (this.listOfPlayers.get(i).getPlayerName().equalsIgnoreCase(readLine))
			{
				return true;

			}
		}
		return false;
	}

	protected ArrayList<Player> getListOfPlayers()
	{
		return this.listOfPlayers;
	}

	protected Player getActivePlayer()
	{

		return activePlayer;
	}

	public Roll startRound(Map<Player, Round> playerRound, GameController newGame, Turn newTurn)
	{
		return playerRound.get(newGame.getActivePlayer()).playRound(playerRound, newGame, newTurn);

	}

	// Advancing to the next available player
	public void moveToNextPlayer(Player activePlayer, GameController newGame)
	{

		// Check if end of list reached and start over from the beginning,
		// otherwise set the next player in the list
		if (newGame.activePlayerindex == newGame.listOfPlayers.size() - 1)
		{
			newGame.setActivePlayer(newGame.listOfPlayers.get(0).getPlayerName());
		}
		else
		{
			newGame.setActivePlayer(newGame.listOfPlayers.get(activePlayerindex + 1).getPlayerName());

		}

	}

	protected Player calculateFinalScore(GameController newGame)
	{
		Map<Player, Round> tempRounds = newGame.playerRound;

		StdOut.println("00000" + tempRounds.size());

		int maxScore = tempRounds.get(getActivePlayer()).getRoundScore();
		Player playerWithMaxScore = activePlayer;

		// find out the player with the max round score
		for (Player player : tempRounds.keySet())
		{
			if (tempRounds.get(player).getRoundScore() > maxScore)
			{
				maxScore = tempRounds.get(player).getRoundScore();
				playerWithMaxScore = player;
			}
		}

		// update the final score for the winner
		int FinalScore = tempRounds.get(playerWithMaxScore).getRoundScore() + Kitty.getKittyTotal();

		// Add 5 chips from each losing player
		for (Player player : tempRounds.keySet())
		{
			if (!tempRounds.containsKey(playerWithMaxScore))
			{
				FinalScore += 5;
				player.setChip(5);
			}
			else if (tempRounds.get(player).getRoundScore() == 0)
			{
				FinalScore += 10;
			}
			tempRounds.get(playerWithMaxScore).setRoundScore(FinalScore);
		}

		return playerWithMaxScore;

	}
	// update the total chips for the winner
}
