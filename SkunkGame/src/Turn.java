import java.util.Map;

public class Turn
{
	private int turnScore;

	public Turn()
	{
		this.turnScore = 0;

	}

	protected int getTurnScore()
	{
		return turnScore;
	}

	protected void setTurnScore(int turnScore)
	{
		this.turnScore = turnScore;
	}

	// One Turn per Player at a time
	public Roll playTurn(Map<Player, Round> playerRound, GameController newGame, Turn activeTurn)
	{
		Roll newRoll = new Roll();
		Kitty newKitty = new Kitty();
		// Check order matters here
		if (newRoll.isTwoSkunksRolled())
		{
			activeTurn.turnScore = 0;
			playerRound.get(newGame.getActivePlayer()).roundScore = 0;

			newGame.getActivePlayer().setChip(4);

			newKitty.addToKitty(4);

			return newRoll;
		}

		else if (newRoll.isSkunkAndDeuceRolled())
		{

			activeTurn.turnScore = 0;

			newGame.getActivePlayer().setChip(2);

			newKitty.addToKitty(2);

			return newRoll;
		}

		else if (newRoll.isSkunkRolled())
		{

			activeTurn.turnScore = 0;
			newGame.getActivePlayer().setChip(1);

			newKitty.addToKitty(1);

			return newRoll;
		}

		activeTurn.turnScore += newRoll.getLastTotal();
		playerRound.get(newGame.getActivePlayer()).setRoundScore(activeTurn.turnScore);
		return newRoll;
	}

}
