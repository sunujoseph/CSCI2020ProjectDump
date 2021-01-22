package assignment;


import javafx.stage.DirectoryChooser;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Socket socket;
    private InputStream io;
    private ObjectOutputStream os;
    private PrintWriter pw;


    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public long getId() {
        return Thread.currentThread().getId();
    }

    @Override
    public void run() {
        //Start Javafx server side info
        String curpath = System.getProperty("user.dir");
        String newfolder = "/Storage";
        String newpath = curpath + newfolder;
        File theDir = new File(newpath);
        //EchoClient.StoragePath = newpath;
        //EchoClient.DIR = theDir;
        System.out.println("HEHEHEHEHEH");
        //System.out.println(System.getProperty("user.dir"));
        // if directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
                se.printStackTrace();
            }

            if (result) {
                System.out.println("DIR created");
            } else {
                System.out.println(result);
            }
        } else {
            System.out.println("Directory Exists already");
        }

        //EchoClient.StoragePath = EchoClient.StoragePath = newpath;

        File[] filesList = theDir.listFiles();
        for(int i=0;i<filesList.length;i++){
            System.out.println(filesList[i].getName());
        }
        //Got it to read the files in the server's storage folder.
        //Know how to make the client get that fileList at all?
        try{

            io = socket.getInputStream();
            os = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Send file List
        try {
            os.writeObject(filesList);
            System.out.println("Sent Files List");
        }catch(IOException e){
            System.out.println("IOEXCPETION");
        }
        //

    }
}//
