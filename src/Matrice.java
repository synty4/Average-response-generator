

public class Matrice
{
	private long[][] coeff = null;

	//Matrix constructors
	public Matrice(int i, int j)
	{
		this.setLength(i,j);
	}
	
	public Matrice()
	{
		this(0,0);
	}

	public Matrice(long[][] mat)
	{
		this.coeff = mat;
	}

	//Matrix setters
	public void setMatrice(long[][] mat)
	{
		this.coeff = mat;
	}
	

	public void setValue(int i, int j, long value)
	{
		this.coeff[i][j] = value;
	}
	
	
	// Set Matrix's length
	public void setLength(int i, int j)
	{
		this.coeff = new long[i][j];
	}
	

	public long[][] getMatrice()
	{
		return this.coeff;
	}
	
	// returns the number of lignes
	public int getRows()
	{
		return this.coeff.length;
	}
	
	// returns the number of columns
	public int getColumns()
	{
		return this.coeff[0].length;
	}
	
	// returns the value at position i and j
	public long getValue(int i, int j)
	{
		return this.coeff[i][j];
	}

		
	public Matrice multiply(final Matrice matrice)
	{
		Matrice a = new Matrice(this.getRows(), this.getColumns());
		int k,i,j,m;
		long value = 0;
				
		for (k=0; k<this.getColumns(); k++)
		{
						
			for (i=0; i<this.getRows(); i++)
			{
							for (j=0; j<this.getColumns(); j++)
					value += this.getValue(i,j)*matrice.getValue(j,k);
				a.setValue(i,k,value);
				value = 0;
			}
		}
		
		return a;
	}

	public Matrice matricePow( int n )
	{
		Matrice a = this;
		Matrice b = a;
		
		for ( int i=0; i < n-1; i++ )
			b = a.multiply(b);
			
		return b;
	}
	
	
	
	public String toString()
	{

		String out = "";
		
		for (int i=0; i<this.getRows(); i++)
		{
			for (int j=0; j<this.getColumns(); j++)
				out +=this.coeff[i][j]+"\t";
				
				out+="\n";
		}
				
		return out;
	}
	public String toString2()
	{

		String out = "";
		
		for (int i=0; i<this.getRows(); i++)
		{
			for (int j=0; j<this.getColumns(); j++)
				out +=this.coeff[i][j]+"\t";
				
				out+="_";
		}
				
		return out;
	}
}