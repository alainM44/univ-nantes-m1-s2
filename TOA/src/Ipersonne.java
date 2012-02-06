

import proxy.NotSelfArgument;

public interface Ipersonne
{
//	public String nom ="ee";
	@NotSelfArgument
	void setConjoint(Ipersonne p);

}
