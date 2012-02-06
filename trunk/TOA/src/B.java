public class B
{
	private int j, k, l;

	public B()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public B(int l, int j, int k)
	{
		super();
		this.l = l;
		this.j = j;
		this.k = k;
	}

	public int getL()
	{
		return l;
	}

	public void setI(int l)
	{
		this.l = l;
	}

	public int getJ()
	{
		return j;
	}

	public void setJ(int j)
	{
		this.j = j;
	}

	public int getK()
	{
		return k;
	}

	public void setK(int k)
	{
		this.k = k;
	}

	@Override
	public String toString()
	{
		return this.j + this.k  + "";
	}
}