/**
 *
 * @author Christopher Castor
 *
 */
package edu.ilstu;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
public class Portfolio {
	
	private Stock stock1, stock2, stock3, stock4;
	private double cashBalance;
	
	public Portfolio() {
	}
	
	public void setStock1(Stock stock1) {
		this.stock1 = stock1;
	}
	public Stock getStock1() {
		return stock1;
	}
	public void setStock2(Stock stock2) {
		this.stock2 = stock2;
	}
	public Stock getStock2() {
		return stock2;
	}
	public void setStock3(Stock stock3) {
		this.stock3 = stock3;
	}
	public Stock getStock3() {
		return stock3;
	}
	public void setStock4(Stock stock4) {
		this.stock4 = stock4;
	}
	public Stock getStock4() {
		return stock4;
	}
	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}
	public double getCashBalance() {
		return cashBalance;
	}
	
	DecimalFormat formatter = new DecimalFormat("#0.00");
	
	// Method will display information about stocks 
	public void displayPositions() { 		
		System.out.printf("%-11s%-11s %-15s%-15s %-14s%s\n", "Symbol", "Shares", "Last Price", "Market Value", "Change", "% Portfolio");
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock1.getSymbol(), stock1.getCurrentShares(), formatter.format(stock1.getCurrentPrice()), formatter.format(stock1.calcCurrentMarketValue()), formatter.format(((stock1.calcCurrentMarketValue()) - (stock1.calcInitialMarketValue()))), formatter.format((stock1.calcCurrentMarketValue() / calcTotalAccountValue())*100));
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock2.getSymbol(), stock2.getCurrentShares(), formatter.format(stock2.getCurrentPrice()), formatter.format(stock2.calcCurrentMarketValue()), formatter.format(((stock2.calcCurrentMarketValue()) - (stock2.calcInitialMarketValue()))), formatter.format((stock2.calcCurrentMarketValue() / calcTotalAccountValue())*100));
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock3.getSymbol(), stock3.getCurrentShares(), formatter.format(stock3.getCurrentPrice()), formatter.format(stock3.calcCurrentMarketValue()), formatter.format(((stock3.calcCurrentMarketValue()) - (stock3.calcInitialMarketValue()))), formatter.format((stock3.calcCurrentMarketValue() / calcTotalAccountValue())*100));
		System.out.printf("%-14s%-10s %-15s%-13s %-16s%s\n", stock4.getSymbol(), stock4.getCurrentShares(), formatter.format(stock4.getCurrentPrice()), formatter.format(stock4.calcCurrentMarketValue()), formatter.format(((stock4.calcCurrentMarketValue()) - (stock4.calcInitialMarketValue()))), formatter.format((stock4.calcCurrentMarketValue() / calcTotalAccountValue())*100));
	}
	
	// Method will calculate total account value useful for finding portfolio percentages
	public double calcTotalAccountValue() { 
		double totalAccountValue = stock1.calcCurrentMarketValue() + stock2.calcCurrentMarketValue() + stock3.calcCurrentMarketValue() + stock4.calcCurrentMarketValue() + cashBalance;
		return totalAccountValue;
	}
	
	// Method used for updating current stock prices
	public void updateCurrentPrices() { 
		stock1.setCurrentPrice(GetPrice.getPrice(stock1.getSymbol()));
		stock2.setCurrentPrice(GetPrice.getPrice(stock2.getSymbol()));
		stock3.setCurrentPrice(GetPrice.getPrice(stock3.getSymbol()));
		stock4.setCurrentPrice(GetPrice.getPrice(stock4.getSymbol()));
	}
	
	// Method used to change number of shares of a stock
	public void editPosition(int choice, int numberOfShares) { 
		if (choice == 1) {
			this.stock1.changeCurrentShares(numberOfShares);
			cashBalance += -numberOfShares * stock1.getCurrentPrice();
		}
		else if (choice == 2) {
			this.stock2.changeCurrentShares(numberOfShares);
			cashBalance += -numberOfShares * stock2.getCurrentPrice();
		}	
		else if (choice == 3) {
			this.stock3.changeCurrentShares(numberOfShares);
			cashBalance += -numberOfShares * stock3.getCurrentPrice();
		}
		else if (choice == 4) {
			this.stock4.changeCurrentShares(numberOfShares);
			cashBalance += -numberOfShares * stock4.getCurrentPrice();
		}
	}
	
	// Method used to save portfolio to a csv file for the next simulation
	public void savePortfolio(String fileName) {
		Stock firstStock = new Stock(stock1.getSymbol(), stock1.getCurrentShares(), stock1.getCurrentPrice());
		Stock secondStock = new Stock(stock2.getSymbol(), stock2.getCurrentShares(), stock2.getCurrentPrice());
		Stock thirdStock = new Stock(stock3.getSymbol(), stock3.getCurrentShares(), stock3.getCurrentPrice());
		Stock fourthStock = new Stock(stock4.getSymbol(), stock4.getCurrentShares(), stock4.getCurrentPrice());
		
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(firstStock);
		stocks.add(secondStock);
		stocks.add(thirdStock);
		stocks.add(fourthStock);
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);
			
			for (Stock stock : stocks) {
				fileWriter.append(stock.toCsv());
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing fileWriter");
				e.printStackTrace();
			}
		}
	}
	
	// Method used to save the cash balance to a csv file for the next simulation
	public void saveCashBalance(String fileName) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append(String.valueOf(cashBalance));
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing fileWriter");
				e.printStackTrace();
			}
		}
	}
}
