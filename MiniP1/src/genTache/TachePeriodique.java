package genTache;

public class TachePeriodique extends AbstractTache {
	private int Pi;

	public TachePeriodique(int ci, int di,int id,int pi) {
		super(ci, di,id);
		Pi=pi;
	}

	public int getPi() {
		return Pi;
	}

	public void setPi(int pi) {
		Pi = pi;
	}

	

}
