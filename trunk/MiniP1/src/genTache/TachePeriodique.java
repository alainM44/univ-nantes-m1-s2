package genTache;

public class TachePeriodique extends AbstractTache
{
	private int Pi;

	public TachePeriodique()
	{
		super(0, 0, 0);
		Pi = 0;
		ri = 0;
	}

	public TachePeriodique(int id, int ci, int di, int pi)
	{
		super(id, ci, di);
		Pi = pi;
		ri = 0;
	}

	public int getPi()
	{
		return Pi;
	}

	public void setPi(int pi)
	{
		Pi = pi;
	}

}
