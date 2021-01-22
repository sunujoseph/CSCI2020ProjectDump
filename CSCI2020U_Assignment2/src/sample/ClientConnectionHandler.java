package sample;

import java.io.*;
import java.net.Socket;


// Handles Client's Files to Server

public class ClientConnectionHandler implements Runnable {
        public static String ROOT = "ServerStorage";
        private Socket socket;
        private PrintWriter out2;

        public ClientConnectionHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // opens streams
                InputStream is = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                OutputStream os = socket.getOutputStream();
                out2 = new PrintWriter(os);
                String request = null;
                while (request == null) {
                    request = in.readLine();
                }
                //Sorts command into array
                String[] requestParts = request.split(" ");// CMD Uri

                // Deals with command
                String command = requestParts[0];
                if (command.equalsIgnoreCase("DIR")) {
                    cmdDIR();
                }else if (command.equalsIgnoreCase("UPLOAD")){
                    cmdUPLOAD(requestParts[1]);
                }else if (command.equalsIgnoreCase("DOWNLOAD")){
                    cmdDOWNLOAD(requestParts[1]);
                } else {
                    System.out.println ("CMD not found.");
                }
                socket.close();
            }catch (FileNotFoundException e){
               System.out.println("File Is Not Found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    // DIR
    // -Returns a listing of the contents of the shared folder, on the server’s machine
    // -The server will disconnect immediately after sending the list of files to the client
    // List of Files to a string
        private void cmdDIR(){
            String toSend = "";
            File baseDir = new File(ROOT);
            File fileList[] = baseDir.listFiles();
            for (int i = 0; i< fileList.length; i++){
                toSend += fileList[i].getName();
                if (i != (fileList.length - 1)){
                    toSend += " ";
                }
            }
            out2.print(toSend);
            out2.flush();
        }

    // UPLOAD filename
    // -Immediately after the newline after this command will be the contents of a text file
    // -The server will connect the text from this text file, and save it as a new file filename
    // -The server will disconnect immediately after saving the text file’s contents
    // open streams
    // read saves the response
        private void cmdUPLOAD(String fileName) throws IOException{
            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                String response;
                File newFile = new File("ServerStorage", fileName);
                if (!newFile.exists()) { // Overwrites files
                    newFile.createNewFile();
                } else {
                    newFile.delete();
                    newFile.createNewFile();
                }
                PrintWriter fout = new PrintWriter(newFile);
                while ((response = in.readLine()) != null) {
                    fout.println(response);
                }
                fout.close();
                // close the connection
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
    }

        //DOWNLOAD filename
        // -The server will load the text from the text file filename, and will immediately send that text to the client
        // and disconnect
        private void cmdDOWNLOAD(String fileName) throws IOException{ // Handles DOWNLOAD command, sends file as string
            String toSend = "", line = "";
            File file = new File(ROOT, fileName);
            BufferedReader in = new BufferedReader(new FileReader(file));
            while ((line = in.readLine()) != null){
                toSend += line;
                toSend += "\n";
            }
            out2.print(toSend);
            out2.flush();
        }
}
