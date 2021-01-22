package assignment;

import java.io.File;

public class DownloadedFiles {
    private String fileName;
    private File file;


    public DownloadedFiles(File f, String fName){
        this.file = f;
        this.fileName = fName;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }
}
