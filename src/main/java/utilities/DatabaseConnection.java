package utilities;

import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DatabaseConnection
{
	public ResultSet databaseConnection() throws SQLException
	{
		java.sql.Connection conn = null;
		ResultSet result = null;
		Utility ul = new Utility();
		String firmname, TaxSoft, clientNumber ;

		try
		{
			String url = "jdbc:sqlserver://w2-stg-db01.ab3169001287.database.windows.net:1433;"
					+ "databaseName=SPSTAGE2007;";
			String username = "stg-app-db";
			String password = "9vgviUdre4ptS8P!";
			List<JSONObject> jcred = ul.GetJsonData(
					System.getProperty("user.dir") + "/src/main/java/TestData/clientDetails.json", "ClientDetails");
			for (JSONObject jo : jcred) {
				firmname = (String) jo.get("FirmName");
				int taxYear = Integer.parseInt((String) jo.get("TaxYear"));
				clientNumber = (String) jo.get("TaxClientID");
				TaxSoft = (String) jo.get("TaxSoftware");

				// Creating connection in DB
				
				conn = DriverManager.getConnection(url, username, password);
				
				// Checking for connection
				//System.out.println(conn.isClosed());

				// SQL query to display values
				// String query = "Select Top 1 DRLRequestID from DRLRequest With(Nolock) where
				// FirmName='Taxcaddy-06' and TaxYear=2020 and ClientNumber='Amol_Aug3' and
				// TaxSoftware ='CCH Axcess Tax' order by 1 desc";
				String query = "Select Top 1 DRLRequestID from DRLRequest With(Nolock) where FirmName='" + firmname
						+ "' and TaxYear=" + taxYear + " and ClientNumber='" + clientNumber + "' and TaxSoftware ='"
						+ TaxSoft + "' order by 1 desc";
				
				// Providing query under prepareStatement parameter
				PreparedStatement pst = conn.prepareStatement(query);

				// Command to execute query & capturing result
				result = pst.executeQuery();
				while (result.next()) {
					System.out.println(result.getInt("DRLRequestID"));
				}
				return result;
			}
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		
		finally
		{
			conn.close();
		}
		return result;
	}
	
}
