import java.util.Map;

public class Round
{
	protected int roundScore;

	public Round()
	{
		this.roundScore = 0;

	}

	protected int getRoundScore()
	{
		return roundScore;
	}

	protected void setRoundScore(int turnScore)
	{
		this.roundScore += turnScore;

	}

	public Roll playRound(Map<Player, Round> playerRound, GameController newGame, Turn activeTurn)
	{
		return activeTurn.playTurn(playerRound, newGame, activeTurn);

	}
}
