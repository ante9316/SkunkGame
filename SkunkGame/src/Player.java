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
		return this.playerName;
	}

	protected void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	protected int getChip()
	{
		return this.chip;
	}

	protected void setChip(int chip)
	{
		this.chip = this.chip - chip;
	}

	protected int getPlayerRoundScore()
	{
		return this.gameScore;
	}

	protected void setPlayerRoundScore(int playerRoundScore)
	{
		this.gameScore = playerRoundScore;
	}

}
