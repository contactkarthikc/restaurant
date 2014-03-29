package application;

import java.util.Random;



class CodeGenerator{

	protected static String characters;
	protected int length;
	static Random rng;
	
	protected CodeGenerator(){
		this.characters= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		this.length = 10;
		this.rng = new Random();
		
	}

	public static String generateString(int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
}