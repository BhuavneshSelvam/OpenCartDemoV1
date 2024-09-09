package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\loginData.xlsx"; //getting excel file location and storing it in a variable called path
		
		ExcelUtilities xlutil = new ExcelUtilities(path); //creating object of ExcelUtilities class and passing the parameter path
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1 );
		
		String logindata[][] = new String[totalrows][totalcols]; //created a 2-D array of size same as the 'no of rows' and 'no of cols' in the excel sheet
		
		for(int r=1; r<=totalrows; r++) //1 (row starts from 1 in excel)// read data from XL starting from row number 1 ignoring row 0 - i.e heading
		{
			for(int c=0; c<totalcols; c++) //0 (col starts from 0 in excel)
			{
				logindata[r-1][c] = xlutil.getCellData("Sheet1", r, c);
			}
		}
		
		return logindata; // returning 2-D array
	}
	
	
	//DataProvider 2
	

}
