package com.cucumber.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ConversionServiceImpl implements ConversionService {
	//string array to represent ones
	String ones[] = { "", "ONE", "TWO","THREE", "FOUR", 
	                 "FIVE", "SIX", "SEVEN", "EIGHT", 
	                 "NINE", "TEN", "ELEVEN", "TWELVE", 
	                 "THIRTEEN", "FOURTEEN", "FIFTEEN", 
	                 "SIXTEEN", "SEVENTEEN", "EIGHTEEN", 
	                 "NINETEEN"
	               };
	
	//string array to represent tens
	String tens[] = { "", "", "TWENTY", "THIRTY", "FORTY", 
	                 "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", 
	                 "NINETY"
	               };

	/**
	 * Method converts numbers to words and appends prefix provided accordingly
	 * @param number
	 * @param value
	 * @param separator
	 * @return
	 */
	private String numToWords(int number, String value, String separator) 
	{ 
	    StringBuilder strBuilder = new StringBuilder();
	    // if number is more than 19, divide it
	    if (number > 19) 
	        strBuilder.append(tens[number / 10]).append(separator).append(ones[number % 10]);
	    else
	        strBuilder.append(ones[number]);
	  
	    // if number is non-zero 
	    if (number>0) 
	        strBuilder.append(value);
	  
	    return strBuilder.toString();
	} 
	
	@Override
	public String convertInStringRepresentation(double number) {
		String[] numArr = String.format("%.2f", number).split("\\.");
		int dollars = Integer.parseInt(numArr[0]);
		int cent=0;
		if(numArr.length > 1)
			cent = Integer.parseInt(numArr[1]);
		
	    StringBuilder out = new StringBuilder();

	    out.append(numToWords((dollars / 10000000), " CRORE "," "));
	    out.append(numToWords(((dollars / 100000) % 100), " LAKH "," "));
	    out.append(numToWords(((dollars / 1000) % 100), " THOUSAND "," "));
	    out.append(numToWords(((dollars / 100) % 10), " HUNDRED "," "));

	    if (dollars > 100 && dollars % 100 > 0) 
	        out.append("AND ");
	  
	    out.append(numToWords((dollars % 100), " DOLLARS ","-"));
	    
	    if(cent>0) {
	    	out.append(dollars>0?"AND ":"");
	    	out.append(numToWords(cent, cent > 9 ?" CENTS":" CENT","-"));
	    }
	    
		return out.toString();
	}
}