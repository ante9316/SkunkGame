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

	public GameController()
	{

	}

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

	public boolean isTurnOver()
	{
		// TODO Auto-generated method stub
		return false;
	}

	// Advancing to the next available player
	public boolean moveToNextPlayer(Player activePlayer, GameController newGame, int numOfRemainingPlayers)
	{

		// Check if end of list reached and start over from the beginning,
		// otherwise set the next player in the list
		if (numOfRemainingPlayers != 0)
		{
			if (newGame.activePlayerindex == newGame.listOfPlayers.size() - 1)
			{
				newGame.setActivePlayer(newGame.listOfPlayers.get(0).getPlayerName());
			}
			else
			{
				newGame.setActivePlayer(newGame.listOfPlayers.get(activePlayerindex + 1).getPlayerName());

			}
			return true;
		}
		else
		{
			return false;
		}

	}

	public String whoIsWinner()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
