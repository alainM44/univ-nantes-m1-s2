package genTache;
/**
 *  Définition d'une tâche périodique 
 * @author MARGUERITE alain  
 * @author RINCE Romain
 *
 */
public class TacheAperiodique extends AbstractTache
{

	public TacheAperiodique(int id, int ci, int di, int ri)
	{
		super(id, ci, di);
		this.ri = ri;
	}

	public TacheAperiodique(TacheAperiodique source)
	{
		super(source.getId(), source.getCi(), source.getDi());
		this.ri = source.getRi();
	}

}
