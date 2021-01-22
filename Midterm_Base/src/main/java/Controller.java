import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

import org.json.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.*;
import java.net.*;
import java.io.*;

public class Controller {
  // Your FXML references go here
  public URL obj;
  public static String summaryFilename = "summary.csv";
  public static String url = "http://csundergrad.science.uoit.ca/courses/csci2020u/data_mtle/lodging_houses.csv";
  public LodgingHouse lh;
  public Scanner sc ;
  public List<String[]> content = new ArrayList<>();//array list for testing
  //public LodgingHouse[] LG = new LodgingHouse[9];
  public List<LodgingHouse> LG = new ArrayList<LodgingHouse>();



  public void initialize() throws IOException {
    // Your code to do the following goes here:
    // 1. Load the data from 'url' into a list of LodgingHouse objects
    obj = new URL(this.url);

    ReadableByteChannel rbc = Channels.newChannel(obj.openStream());
    FileOutputStream fos = new FileOutputStream(summaryFilename);


    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    //load url csv data to csv file summary.csv



    // 2. Show the data from the first lodging house in the user interface
    //input the correct data in the right lodgeinghouse value



    try(BufferedReader br = new BufferedReader(new FileReader(summaryFilename))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        content.add(line.split(","));

        String[] split = line.split(",");
        lh.setObjectId(Integer.parseInt(split[0]));
        lh.setStreetName(split[1]);
        lh.setPropertyClass(split[2]);
        lh.setShapeArea(Integer.parseInt(split[3]));
        lh.setShapeLength(Integer.parseInt(split[4]));
        LG.add(lh);
      }
    } catch (FileNotFoundException e) {
      //Some error logging
    }




    // 3. Save the summary of all of the lodging house records to 'summaryFilename'
  }

  // Your code to handle the 'next' and 'previous' events goes here

  public void prev()throws Exception{
    Main on;

    String prev = null;
    String curr = null;
    String next = null;

    if(this.lh.getObjectId() > 1 ){
      this.lh.getObjectId();
    }



  }

  public void next(){

    if(this.lh.getObjectId() < 9 ){
      this.lh.getObjectId();
    }

  }

}
