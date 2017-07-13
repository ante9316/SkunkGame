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

		// Check order matters here
		if (newRoll.isTwoSkunksRolled())
		{
			activeTurn.turnScore = 0;

			playerRound.get(newGame.getActivePlayer()).roundScore = 0;
			return newRoll;
		}

		if (newRoll.isSkunkAndDeuceRolled())
		{
			// StdOut.println("ONE SKUNK & ONE DEUCE");
			activeTurn.turnScore = 0;
			return newRoll;
		}

		if (newRoll.isSkunkRolled())
		{
			// StdOut.println("ONE SKUNK");
			activeTurn.turnScore = 0;
			return newRoll;
		}

		activeTurn.turnScore += newRoll.getLastTotal();
		playerRound.get(newGame.getActivePlayer()).setRoundScore(activeTurn.turnScore);
		return newRoll;
	}

}
