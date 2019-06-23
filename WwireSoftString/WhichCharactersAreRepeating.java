package com.exercise;
/**
 * 
 * find the repeating characters into given String and how many times 
 *
 */
public class WhichCharactersAreRepeating  
{ 
    static final int maxLength = 256; 
    
    static int elements = 0;
      
    static void fillCharCounts(String string, int[] count) 
    { 
       for (int loopCounter = 0; loopCounter < string.length();  loopCounter++) 
          count[string.charAt(loopCounter)]++; 
    } 
       
    static int printDups(String str) 
    { 
      int count[] = new int[maxLength]; 
      fillCharCounts(str, count); 
      
      for (int i = 0; i < maxLength; i++) 
        if(count[i] > 1) {
            System.out.printf("%c,  count = %d \n", i,  count[i]); 
            elements++;
        }   
      
      return elements;
    } 
   
    public static int duplicateCharacters(String input) {
    	int len = printDups(input); 
    	return len;
    }
    
    public static void main(String[] args) 
    { 
        String str = "Aasdefsgh!!!"; 
        System.out.println(" Total number of repeating characters are : " +duplicateCharacters(str));
    } 
    
   
} 
