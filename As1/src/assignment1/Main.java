package assignment1;
//Import Required Files
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.*;

//Declare Main Class
public class Main extends Application{
    //total number of messages that we tested.
    private int number_of_messages_tested;

    //the total number of messages that we correctly classified as ling.
    private int TrueNegatives;

    //the total number of messages that we correctly classified as spam.
    private int TruePositives;

    //the total number of messages that we wrongly classified as ling.
    private int FalseNegatives;

    //the total number of messages that we wrongly classified as spam.
    private int FalsePositives;

//Main Function
    public static void main(String[] args) {
        Application.launch(args);
    }
//Create observable list that imports TestFile For later use This will be used to store data of the files
    public ObservableList<TestFile> test = FXCollections.observableArrayList();
    //Declare Text Fields for later use
    public TextField fname;
    public TextField acase;
    public TextField prob;


    @Override
    public void start(Stage stage) {


        //ADDED a alert box to before every directory chooser thus allowing the user to select each fold one by one.
        //direct

        //Declare Directory Choosers
        DirectoryChooser directoryChooser = new DirectoryChooser();
        //Declare our NaiveBayesClassifier
        NaiveBayesClassifier nb = new NaiveBayesClassifier();
    //inform user about First Set of files to import
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Assignment 1");
        alert.setHeaderText("Please select the following folder: ");
        String s ="Spam";
        alert.setContentText(s);
        alert.showAndWait();


        //Grab Files
        File selectedDirectory = directoryChooser.showDialog(stage);
        String directory = selectedDirectory.getAbsolutePath();

    //Inform user about second set of files to import
        alert.setTitle("Assignment 1");
        alert.setHeaderText("Please select the following folder: ");
        s ="Ham";
        alert.setContentText(s);
        alert.showAndWait();
        //Grab Files
        File selectedDirectory2 = directoryChooser.showDialog(stage);
        String directory2 = selectedDirectory2.getAbsolutePath();

        //Inform user about third set of files to import
        alert.setTitle("Assignment 1");
        alert.setHeaderText("Please select the following folder: ");
        s ="Ham2";
        alert.setContentText(s);
        alert.showAndWait();
        //Grab Files
        File selectedDirectory3 = directoryChooser.showDialog(stage);
        String directory3 = selectedDirectory3.getAbsolutePath();
//Inform user that this will take some time(roughly 1-2 minutes depending cpu speed)
        alert.setTitle("Assignment 1");
        alert.setHeaderText("Loading files to trainer. This will take a while.");
        s ="Please Wait.";
        alert.setContentText(s);
        alert.showAndWait();


//Train files using NaiveBayesClassifier
        nb.train(directory2, directory, directory3);


//Grab TestFolder Path for either spam or ham
        alert.setTitle("Assignment 1");
        alert.setHeaderText("Choose your Test Folder Path: ");
        s ="ham or spam folder";
        alert.setContentText(s);
        alert.showAndWait();
//Grab files
        File selectedDirectory4 = directoryChooser.showDialog(stage);
        String directory4 = selectedDirectory4.getAbsolutePath();




//Format Output table
        stage.setTitle("Assignment 1");
//Declare BorderPane
        BorderPane layout = new BorderPane();
        TableView table = new TableView();
        table.setEditable(true);

//First column "FILE"
        TableColumn<String, String> file = new TableColumn("File");
        file.setMinWidth(100);

//Second Column "File
        TableColumn<String, String> actualClass = new TableColumn("Actual Class");
        actualClass.setMinWidth(100);
//Third Column "Spam Probability"
        TableColumn<Double, Double> tspamProbablity = new TableColumn("Spam Probablity");
        tspamProbablity.setMinWidth(100);








//======================================================================================================================
            //compare test files to trainer results of spam

            //Set up variables
            File f = new File(directory4);
            File[] files = f.listFiles();
            double fmsg = 0;
            double tmsg = 0;
            int numfiles = 0;


            this.number_of_messages_tested=files.length;
            numfiles = files.length;
//Filter Through each word in each file
            try
            {

                for(File fl : files)
                {
                    BufferedReader br = new BufferedReader(new FileReader(fl));

                    String msg="";

                    String line = br.readLine();

                    while(line!=null)
                    {
                        msg+=line;
                        line=br.readLine();
                    }

                    int inSpams = 0;
                    for(Word k : nb.words){

                        if(msg.contains(k.toString())){
                            tmsg++;


                            inSpams++;
                        }
                        else{
                            fmsg++;
                        }


                    }


                    //calculate spam probability
                    double spamProbability= ( (double)(1+inSpams)/(double)(nb.words.size()+inSpams) );

                    test.addAll(FXCollections.observableArrayList(new TestFile(fl.getName(), spamProbability, selectedDirectory4.getName())));


                    boolean result = spamProbability>=0.2;
//Add to True Positives or False Positives
                    if(selectedDirectory4.getName().contains("spam") && result)
                    {TruePositives++;
                    }
                    else if (selectedDirectory4.getName().contains("spam") && !result)
                    {FalseNegatives++;
                    }
                    else if(!selectedDirectory4.getName().contains("spam") && result)
                    {FalsePositives++;
                    }
                    else
                    {TrueNegatives++;
                    }

//Print to terminal so we know our pc didnt crash and how far it is
                    System.out.println("Name " + fl.getName() + "  " + spamProbability);

//Close buffered Reader no longer neccesary
                    br.close();
                }
            }
//Catch any excpetions
            catch(Exception e)
            {
                System.err.println("ERROR : "+e.getMessage());
            }




//==================================================================================================================


//Grab each Columns Values
        file.setCellValueFactory(new PropertyValueFactory<>("filename"));
        actualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));
        tspamProbablity.setCellValueFactory(new PropertyValueFactory<>("spamProbability"));
        table.getColumns().addAll(file,actualClass,tspamProbablity);
//Set Items to the test variable
        table.setItems(test);

//Open Stackpane and add table
        StackPane root = new StackPane();
        root.getChildren().add(table);


        //Accuracy and Precision

        Double Acc = getAccuracy();
        Double Per = getPrecision();
        //Open gridpane to make the output area for acc & percision
        GridPane gp = new GridPane();
        gp.setPrefSize(200,100);

        gp.setPadding(new Insets(10,10,10,10));

        Label textLabel= new Label("Accuracy:");
        TextField accField=new TextField();//AccuracyVariableGoesHere.toString());
        accField.setDisable(true);
        accField.setText(Acc.toString());
        Label textLabel2=new Label("Precision:");

        TextField perField=new TextField();//PercisionGoesHere.toString());
        perField.setDisable(true);
        perField.setText(Per.toString());
        gp.add(textLabel,0,0);
        gp.add(accField,1,0);
        gp.add(textLabel2,0,1);
        gp.add(perField,1,1);

        layout.setBottom(gp);

        //Show Stage

        layout.setCenter(table);
        Scene scene = new Scene(layout, 600, 700);
        stage.setScene(scene);
        stage.show();








    }

//Accruacy and Precision is calcuated here.


    public double getAccuracy()
    {
        if(number_of_messages_tested==0) return 0;
        return ( (double)(TruePositives + TrueNegatives)/(double)number_of_messages_tested);
    }




    public double getPrecision()
    {
        if((TruePositives + FalsePositives)==0) return 0;
        return  ( (double)TruePositives / (double)(TruePositives + FalsePositives));
    }




}
