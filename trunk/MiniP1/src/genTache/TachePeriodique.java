package genTache;

public class TachePeriodique extends AbstractTache {
	private int Pi;

	public TachePeriodique(int id,int ci, int di,int pi) {
		super(id,ci, di);
		Pi=pi;
	}

	public int getPi() {
		return Pi;
	}

	public void setPi(int pi) {
		Pi = pi;
	}

	

}
