package example;

import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class Main {


  private static final Logger logger = Logger.getLogger(Main.class);

  private static final String resourcesPath = "/tmp/src/main/resources/";
  private static final String connectionStr = "jdbc:postgresql://db/animal?user=maria&password=pass";
  private static final String driver = "org.postgresql.Driver";

  private static String[] readTableViewFile(){
  List<String> lines = new ArrayList<String>();
    try{
      // Open the file that is the first
      // command line parameter
      FileInputStream fstream = new FileInputStream(resourcesPath + "tbl-names");
      // Get the object of DataInputStream
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String strLine;
      //Read File Line By Line
      while ((strLine = br.readLine()) != null)   {
        // Print the content on the console
        logger.info("Found database resource name: " + strLine);
        lines.add(strLine);
      }
      //Close the input stream
      in.close();
    }catch (Exception e){//Catch exception if any
        logger.fatal("Failed to load file");
    }
    return lines.toArray(new String[lines.size()]);
  }
  public static void main(String[] args) {
    PropertyConfigurator.configure(resourcesPath + "log4j.xml");

    example.chain.ILink obj = new example.chain.Driver();

    if (obj.hasResource(driver, connectionStr)){
      example.db.print.output.IOutput output = new example.db.print.output.ToFile();
      for ( String tbl : readTableViewFile())
        example.db.DBQuery.query(connectionStr, tbl, output);
    }
  }
}
