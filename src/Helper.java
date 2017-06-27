import java.util.Random;

public class Helper {
	private int numberRow=0,numberColumn=0;
	private int powermatrix;
	private String machaine = "";
	private String[] mystring;
	private String finale;
	Matrice matrice= new Matrice(1,1);

	
	
    /*
     * Returns a value uniformaly distributed 
     * in the interval [0, 1]
     */
	public static double Uniform(){
		
		Random r = new Random();
		double i = r.nextDouble();
		return i;
		 
		
	}
	/*
	 * Computes the inverse law
	 * @pre: rate is positive
	 */
	public static double InverseTransformSampling(double rate){
 
		return -(Math.log(Uniform()))/rate	;	 
		
	}
    
	/*
     * This method generates  exponential and random time intervals 
     * using the inverse law
     * 
     * @pre: time is a positive long 
     * 		 rate is a positive double
     * @post: randomly and exponentially generated time interval 
     */
	
	
	public static void generate_exponential_number(final long time, double rate){

		long interval = (long)(InverseTransformSampling(rate) * time);
		
	    try {
	    	
			Thread.sleep(interval);
			
		} catch (InterruptedException e) {
			System.err.println("Interrupted Thread");
 	        System.exit(1);
		}
	
 }


	/*public long generate_exponential_number(final long time, double rate){
			
		Random rng = new MersenneTwisterRNG();
		ExponentialGenerator gen = new ExponentialGenerator(rate, rng);

		long interval = Math.round(gen.nextValue() * time);
		try
		{
			Thread.sleep(interval);
		}
		catch (InterruptedException e) 
		{
		    System.err.println("Interrupted Thread");
 	        System.exit(1);
		}
		 
		return interval;
	}
	
	
*/
	public Matrice  parse_string_into_matrix(String mystringinput){
		
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
		return matrice;
		
	}

	public void PrintMatrix(String string)
	{		
		String[] response=string.split("\t");
		String out = "";
			
		for (int i=0; i < response.length; i++)
		{
			if(response[i].contains("_")) 
			{
				String a= response[i].substring(1);
				out+="\n"+a+"\t";	
			}
			else out +=response[i]+"\t"; 
		}
			
		System.out.println(out);
	}

	/*
	 * 	This method clears a two dimensional array content
	 * 	it set all its cells to a null value
	 *
	 *	@pre: a valid and  two dimensional String array "array"
	 *	@post; the "array"'s  contents are set to a null value
	 *
	 */		
	
	public  void clear(String[][] array)
	{
		int i, j;
		for( i = 0; i < array.length; i++  )
		{
			for( j = 0; j < array[i].length; j++ )
			{
				array[i][j] = null;
			}
		}		
	}
	
	
	/*
	 * This method checks whether a string is a number or not
	 * @pre: a String my_string
	 * @post: yes if my_string is a number no otherwise
	 */
	public boolean isNumber(String my_string) {
		
	    return isNumber(my_string,10);
	}

	public boolean isNumber(String my_string, int radix) {
		
	    if(my_string.isEmpty()) return false;
	    
	    for(int i = 0; i < my_string.length(); i++) 
	    {
	        if(i == 0 && my_string.charAt(i) == '-') 
	        {
	            if(my_string.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(my_string.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}



}
