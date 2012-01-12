package ArithmIntervalles;

public class ExtendedInteger {
	//ATTRIBUTS
	short entierR;
	String entierNR;
	boolean representable;


	//Constructeur
	public ExtendedInteger(short x){
		entierR=x;
		entierNR="";
		representable=true;

	}

	public ExtendedInteger(String s){
		entierR=0;
		entierNR=s;
		representable=false;
	}
	//Opération arithmetiques

	public ExtendedInteger add(ExtendedInteger x){

		if(x.representable && this.representable && (x.entierR+this.entierR<=32767) && (x.entierR+this.entierR>=-32768))

			return new ExtendedInteger((short) (this.entierR+x.entierR));

		else if (x.representable && !this.representable )

			return new ExtendedInteger(this.entierNR);


		else if(!x.representable && this.representable )

			return new ExtendedInteger(x.entierNR);

		else //(!x.representable && !this.representable )
		{
			if (x.entierNR.equals("+Inf")||this.entierNR.equals("+Inf"))
				return new ExtendedInteger("+Inf");
			else if(x.entierNR.equals("-Inf")||this.entierNR.equals("-Inf"))
				return new ExtendedInteger("-Inf");
			else
				return new ExtendedInteger("NaN");
		}

	}

	public ExtendedInteger sub(ExtendedInteger x){

		if(x.representable && this.representable && (this.entierR-x.entierR<=32767) && (this.entierR-x.entierR>=-32768))
			return new ExtendedInteger((short) (this.entierR-x.entierR));
		else if (x.representable && !this.representable )
			return new ExtendedInteger(this.entierNR);

		else if(!x.representable && this.representable )

			return new ExtendedInteger(x.entierNR);

		else //(!x.representable && !this.representable )
		{
			if (x.entierNR.equals("+Inf")||this.entierNR.equals("-Inf"))
				return new ExtendedInteger("+Inf");
			else if(x.entierNR.equals("-Inf")||this.entierNR.equals("+Inf"))
				return new ExtendedInteger("-Inf");
			else
				return new ExtendedInteger("NaN");
		}
	}

	public ExtendedInteger mult(ExtendedInteger x){
		if(x.representable && this.representable && (x.entierR*this.entierR<=32767) && (x.entierR*this.entierR>=-32768))
			return new ExtendedInteger((short) (this.entierR*x.entierR));
		else if (x.representable && !this.representable && x.entierR!=0)
			return new ExtendedInteger(this.entierNR);
		else if(!x.representable && this.representable && this.entierR!=0 )
			return new ExtendedInteger(x.entierNR);
		else //(!x.representable && !this.representable )
		{
			if (x.entierNR.equals(this.entierNR) && !this.entierNR.equals("NaN"))
				return new ExtendedInteger("+Inf");
			else if(!x.entierNR.equals(this.entierNR) && !this.entierNR.equals("NaN"))
				return new ExtendedInteger("-Inf");
			else
				return new ExtendedInteger("NaN");
		}

	}
	public ExtendedInteger div(ExtendedInteger x){
		if(x.representable && this.representable && x.entierR!=0 )
			return new ExtendedInteger((short) (this.entierR/x.entierR));
		else if (x.representable && !this.representable )
			return new ExtendedInteger(this.entierNR);
		else if(!x.representable && this.representable )
			return new ExtendedInteger(x.entierNR);
		else //(!x.representable && !this.representable )
			return new ExtendedInteger("NaN");
	}


	// retourne le plus petit ou le plus grand des entier étendu
	public ExtendedInteger min(ExtendedInteger x){
		if(!x.representable && x.entierNR.equals("NaN") ||!this.representable && this.entierNR.equals("NaN"))
			return new ExtendedInteger("NaN");
		else if	(!x.representable && x.entierNR.equals("-Inf") ||!this.representable && this.entierNR.equals("-Inf"))
			return new ExtendedInteger("-Inf");
		else if (x.representable ||this.representable)
		{
			if (this.entierR<x.entierR)
				return new ExtendedInteger(this.entierR);
			else
				return new ExtendedInteger(x.entierR);
		}
		else
		{
			if(!x.representable)
				return new ExtendedInteger(this.entierR);
			else
				return new ExtendedInteger(x.entierR);

		}
	}


//	public ExtendedInteger max(ExtendedInteger x){
//		ExtendedInteger result;
//
//
//		return result;
//	}


	//accesseurs pratiques
	public boolean isNaN(){
		return this.entierNR.equals("NaN");

	}
	public boolean isPositiveInfinity(){
		return this.entierNR.equals("+Inf");
		
	}





}
