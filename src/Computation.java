public class Computation {
	
	private int numberRow=0,numberColumn=0;
	private int powermatrix;
	private String machaine = "";
	private String[] mystring;
	private String finale;
	Matrice matrice= new Matrice(1,1);
	
	/*
	 * ParseMatrix method computes the power of a matrice
	 * @pre: a String concatenation of a string representation of a matrix and of 
	 * an exponent n
	 * @post:  returns the matrix powered by n
	 * 
	 */
	
	public void  ParseMatrix(String mystringinput){
	
		mystring = mystringinput.split(" ");
	 
		/* Get the number of Rows and the number of column */
		for( int w = 0; w < mystring.length; w++)
		{
			if( mystring[w].contains(",")) 
			{
				numberRow    = w + 1;
				numberColumn = w + 1;
				break;
			}
		}
	 
		powermatrix = Integer.parseInt( ( mystring[mystring.length-1] ).substring(1));
		matrice.setLength( numberRow, numberColumn);
	 
		int l=0;  String[] temp;
	 
		for(int i = 0; i < numberRow; i++)
		{
			for( int j = 0; j < numberColumn; j++)
			{
				if ( j == numberColumn - 1 )
				{ 
					temp = mystring[l].split(",");
					matrice.setValue(i, j, Integer.parseInt( temp[0] ) );
					machaine +=matrice.getValue(i, j) + " ";
				  
				}
				else
				{	
					matrice.setValue( i, j, Integer.parseInt( mystring[l] ) );
					machaine +=matrice.getValue(i, j)+" ";
				}
				l++;
			}
		 
		}
		
		matrice = matrice.matricePow( powermatrix );
		
	 }
	
	/*
	 * This method returns a String representation of a matrix
	 */
	public String ToString() {
		
		return matrice.toString2();
			
	}
	 	 
}
	

