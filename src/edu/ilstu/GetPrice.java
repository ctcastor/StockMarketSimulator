/**
 *
 * @author Christopher Castor
 *
 */
package edu.ilstu;

import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.YahooFinance;

public class GetPrice {
	
	// Method used to access real-time stock prices
	public static double getPrice(String symbol) {
		BigDecimal price = new BigDecimal(0);
		yahoofinance.Stock stock = null;
		
		try {
			stock = YahooFinance.get(symbol);
		
			price = stock.getQuote().getPrice();
		}
		catch (IOException e1) {
			System.out.println("Couldn't connect to server.");
			System.exit(0);
		}
		return price.doubleValue();
	}

}