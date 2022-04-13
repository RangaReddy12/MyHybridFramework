package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	String inputpath ="D:\\Automation_Selenium\\HybridFrameWork\\TestInput\\HybridTest.xlsx";
	String outputpath ="D:\\Automation_Selenium\\HybridFrameWork\\TestOutput\\HybridResults.xlsx";
	String TCSheet ="MasterTCS";
	String TSSheet ="TestSteps";
	ExtentReports report;
	ExtentTest test;
		@Test
	public void startTest()throws Throwable
	{
			report = new ExtentReports("./ExtentReports/Hybrid.html");
		boolean res = false;
		String tcres="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int TCCount =xl.rowCount(TCSheet);
		int TSCount =xl.rowCount(TSSheet);
		Reporter.log(TCCount+"    "+TSCount,true);
		for(int i=1;i<=TCCount;i++)
		{
			String moduleName =xl.getCellData(TCSheet, i, 1);
			test=report.startTest(moduleName);
			String executionstatus= xl.getCellData(TCSheet, i, 2);
			if(executionstatus.equalsIgnoreCase("Y"))
			{
				String tcid =xl.getCellData(TCSheet, i, 0);
				for(int j=1;j<=TSCount;j++)
				{
					String description = xl.getCellData(TSSheet, j, 2);
					String tsid =xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						String keyword = xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							res =FunctionLibrary.verifyLogin(para1, para2);
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("NewBranch"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para3 =xl.getCellData(TSSheet, j, 7);
							String para4 =xl.getCellData(TSSheet, j, 8);
							String para5 =xl.getCellData(TSSheet, j, 9);
							String para6 =xl.getCellData(TSSheet, j, 10);
							String para7 =xl.getCellData(TSSheet, j, 11);
							String para8 =xl.getCellData(TSSheet, j, 12);
							String para9 =xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.clickBraches();
							res =FunctionLibrary.verifynewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("UpdateBranch"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para6 =xl.getCellData(TSSheet, j, 10);
							FunctionLibrary.clickBraches();
							res = FunctionLibrary.verifyBranchUpdation(para1, para2, para6);
							test.log(LogStatus.INFO, description);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res = FunctionLibrary.verifyLogout();
							test.log(LogStatus.INFO, description);
						}
						
						String tsres="";
						if(res)
						{
							//write as pass into TSSheet
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.PASS, description);
						}
						else
						{
							//write as pass into TSSheet
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.FAIL, description);
						}
						tcres=tsres;
					}
					report.endTest(test);
					report.flush();
				}
				//write as pass or fail into TCSheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked in status cell which flaged to N
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
		
	}

}









