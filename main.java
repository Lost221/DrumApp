import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws SQLException, FileNotFoundException, URISyntaxException {
		dbConnector b = new dbConnector();
		b.makeConnection();
		User us = b.getUser("Tara","Tocilarilor");
		System.out.println(new File("test.txt").getAbsolutePath());
		File f = new File("test.txt");
		f.getAbsolutePath();
		System.out.println(f.exists());
		
		//b.uploadSheet("Test1", 1, 1, "test", "");
	}

}
