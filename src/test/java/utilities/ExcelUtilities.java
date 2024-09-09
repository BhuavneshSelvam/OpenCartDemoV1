package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtilities {
		
		//Creating all static methods, so that we can use them directly inside the main method without creating any objects.

		public FileInputStream fi;
		public FileOutputStream fo;
		public XSSFWorkbook wb;
		public XSSFSheet ws;
		public XSSFRow row;
		public XSSFCell cell;
		public CellStyle style;
		String path;
	
	public ExcelUtilities(String path)
	{
		this.path = path;
	}
		
	 public int getRowCount(String xlsheet) throws IOException
	 {
		 fi = new FileInputStream(path);
		 wb = new XSSFWorkbook(fi);
		 ws = wb.getSheet(xlsheet);
		 int rowCount = ws.getLastRowNum();
		 wb.close();
		 fi.close();
		 return rowCount;
	 }
	 
	 public int getCellCount(String xlsheet, int rownum) throws IOException
	 {
		 fi = new FileInputStream(path);
		 wb = new XSSFWorkbook(fi);
		 ws = wb.getSheet(xlsheet);
		 row = ws.getRow(rownum);
		 int cellCount = row.getLastCellNum();
		 wb.close();
		 fi.close();
		 return cellCount;
	 }
	 
	 
	 public String getCellData(String xlsheet, int rownum, int cellnum) throws IOException
	 {
		 fi = new FileInputStream(path);
		 wb = new XSSFWorkbook(fi);
		 ws = wb.getSheet(xlsheet);
		 
		 row = ws.getRow(rownum);
		 cell = row.getCell(cellnum);
		 wb.close();
		 fi.close();
		 return cell.toString();
		 
		 /*
		  * String data;
		  * 
		  * try 
		  * {
		  *		DataFormatter formatter = new DataFormatter();
		  *		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as as String regardless of the cell's datatype.
		  * }
		  * 
		  * catch (Exception e)
		  * {
		  * 	data = "";
		  * }
		  * 
		  * 
		  * wb.close();
		  * fi.close();
		  * 
		  * return data;
		  * 
		  */
	 } 
		 
	public void setCellData(String xlsheet, int rownum, int cellnum, String data) throws IOException
		{
		
			File xlfile = new File(path);
			if(!xlfile.exists())  // if file not exists then create new file
			{
				wb = new XSSFWorkbook();
				fo = new FileOutputStream(path);
				wb.write(fo);	
			}
			
			fi = new FileInputStream(path);
			wb = new XSSFWorkbook(fi);
			
			if(wb.getSheetIndex(xlsheet)==-1) //if sheet not exists create new sheet
			{
				wb.createSheet(xlsheet);
			}
			
			ws = wb.getSheet(xlsheet);
			
			if(ws.getRow(rownum)==null) //if row not exists then create new Row
			{
				ws.createRow(rownum);
			}
			
			row = ws.getRow(rownum);
			cell = row.getCell(cellnum);
			cell.setCellValue(data);
			fo = new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
			
		}


	public void fillGreenColor(String xlsheet, int rownum, int cellnum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	public void fillRedColor(String xlsheet, int rownum, int cellnum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close(); 
	}	 
	 
		 
}