package me.songJe2;

import me.songJe.AssignMentNumber;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    @AssignMentNumber(number = {1,2})    
    public void testAnnotation()
    {
    	
    }
}
