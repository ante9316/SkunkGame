
public class Roll
{

	protected int die1;
	protected int die2;
	protected int lastTotal;

	protected void setLastTotal(int lastTotal)
	{
		this.lastTotal = lastTotal;
	}

	public Roll()
	{
		this.die1 = rollDie();
		this.die2 = rollDie();

		lastTotal = this.die1 + this.die2;

	}

	protected int getLastTotal()
	{
		return lastTotal;
	}

	protected int getDie1()
	{
		return die1;
	}

	protected int getDie2()
	{
		return die2;
	}

	public int rollDie()
	{
		return ((int) (Math.random() * 6 + 1));
	}

	public boolean isSkunkRolled()
	{
		return (this.die1 == 1 || this.die2 == 1);

	}

	public boolean isTwoSkunksRolled()
	{
		return (this.die1 == 1 && this.die2 == 1);
	}

	public boolean isSkunkAndDeuceRolled()
	{
		return ((this.die1 == 2 && this.die2 == 1) || (this.die1 == 1 && this.die2 == 2));
	}
}
