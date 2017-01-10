/**
 *
 * @author Christopher Castor
 *
 */
package edu.ilstu;

public class Stock {
	
	private String symbol;
	private int initialShares, currentShares;
	private double initialPrice, currentPrice;
	
	public Stock(String symbol, int currentShares, double currentPrice) {
		this.symbol = symbol;
		this.currentShares = currentShares;
		this.currentPrice = currentPrice;
	}
	
	public Stock(String symbol, int initialShares, int currentShares, double initialPrice, double currentPrice) {
		this(symbol, currentShares, currentPrice);
		this.initialShares = initialShares;
		this.initialPrice = initialPrice;
		
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setInitialShares(int initialShares) {
		this.initialShares = initialShares;
	}
	public int getInitialShares() {
		return initialShares;
	}
	public void setCurrentShares(int currentShares) {
		this.currentShares = currentShares;
	}
	public int getCurrentShares() {
		return currentShares;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	// Method used to calculate current market value of a stock 
	public double calcCurrentMarketValue() { 
		double currentMarketValue = currentPrice * currentShares;
		return currentMarketValue;
	}
	
	// Method used to calculate initial market value of a stock
	public double calcInitialMarketValue() { 
		double initialMarketValue = initialPrice * initialShares;
		return initialMarketValue;
	}
	
	// Method used to change current shares after a stock is edited
	public void changeCurrentShares(int numShares) { 
		this.currentShares += numShares; 
	}
	
	public String toCsv () {
		return symbol + "," + currentShares + "," + currentPrice + "\n";
	}

}
