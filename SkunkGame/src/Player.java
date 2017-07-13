public class Player
{

	private String playerName;
	private int chip;
	private int gameScore;

	public Player(String newPlayerName)
	{

		this.playerName = newPlayerName;
		this.chip = 50;
	}

	public Player()
	{

	}

	protected String getPlayerName()
	{
		return playerName;
	}

	protected void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	protected int getChip()
	{
		return chip;
	}

	protected void setChip(int chip)
	{
		this.chip = chip;
	}

	protected int getPlayerRoundScore()
	{
		return gameScore;
	}

	protected void setPlayerRoundScore(int playerRoundScore)
	{
		this.gameScore = playerRoundScore;
	}

}
