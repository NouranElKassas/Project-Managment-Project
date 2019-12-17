import java.sql.*;
import javax.swing.*;
public class main_connection {
	Connection conn =null;
	public static Connection DB_Connector()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\admin\\Desktop\\workspace\\PM(project_GUI)\\Project_Managment_DB.sqlite");
			//JOptionPane.showMessageDialog(null,"connection successful");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}

}
