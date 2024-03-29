package genTache;

public abstract class AbstractTache implements ITache
{

	protected int ri;
	protected int id;
	protected int Ci;
	protected int Di;

	public AbstractTache(int id, int ci, int di)
	{
		super();
		this.id = id;
		Ci = ci;
		Di = di;
	}

	public int getRi()
	{
		return ri;
	}

	public void setRi(int ri)
	{
		this.ri = ri;
	}

	public int getCi()
	{
		return Ci;
	}

	public void setCi(int ci)
	{
		Ci = ci;
	}

	public int getDi()
	{
		return Di;
	}

	public void setDi(int di)
	{
		Di = di;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "AbstractTache [id=" + id + ", Ci=" + Ci + ", Di=" + Di
				+ ", ri=" + ri + "]";
	}

}
