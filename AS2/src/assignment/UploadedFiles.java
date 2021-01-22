package assignment;

import java.io.File;

public class UploadedFiles {

    private String fileName;
    private File file;


    public UploadedFiles(File f, String fName){
        this.file = f;
        this.fileName = fName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }
}
