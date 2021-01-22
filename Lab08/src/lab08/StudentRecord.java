package lab08;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentRecord {


    private final SimpleStringProperty StudentID;
    private final SimpleDoubleProperty Midterm;
    private final SimpleDoubleProperty Assignments;
    private final SimpleDoubleProperty Finalexam;
    private final SimpleDoubleProperty FinalMark;
    private final SimpleStringProperty LetterGrade;

    public StudentRecord(String studentID, double assignments, double midterm, double finalexam) {
        this.StudentID = new SimpleStringProperty(studentID);
        this.Midterm = new SimpleDoubleProperty(midterm);
        this.Assignments = new SimpleDoubleProperty(assignments);
        this.Finalexam = new SimpleDoubleProperty(finalexam);

        double finalMark = (midterm*0.30)+(assignments*0.20)+(finalexam*0.50);
        this.FinalMark = new SimpleDoubleProperty(finalMark);

        String letterGrade = null;
        if(finalMark >= 80){
            //A mark
            letterGrade = "A";
        }
        else if(finalMark >= 70){
            //B mark
            letterGrade = "B";
        }
        else if(finalMark >= 60){
            //C mark
            letterGrade = "C";
        }
        else if(finalMark >= 50){
            //D mark
            letterGrade = "D";
        }
        else{
            // F mark
            letterGrade = "F";
        }
        this.LetterGrade = new SimpleStringProperty(letterGrade);
    }

    public StudentRecord(String studentID, double assignments, double midterm, double finalexam, double finalMark, String letterGrade) {
        this.StudentID = new SimpleStringProperty(studentID);
        this.Midterm = new SimpleDoubleProperty(midterm);
        this.Assignments = new SimpleDoubleProperty(assignments);
        this.Finalexam = new SimpleDoubleProperty(finalexam);
        this.FinalMark = new SimpleDoubleProperty(finalMark);
        this.LetterGrade = new SimpleStringProperty(letterGrade);
    }

    public StudentRecord() {
        this.StudentID = new SimpleStringProperty(null);
        this.Midterm = new SimpleDoubleProperty(0);
        this.Assignments = new SimpleDoubleProperty(0);
        this.Finalexam = new SimpleDoubleProperty(0);
        this.FinalMark = new SimpleDoubleProperty(0);
        this.LetterGrade = new SimpleStringProperty("F");
    }

    public String getStudentID() {
        return this.StudentID.get();
    }

    public void setStudentID(String studentID) {
        this.StudentID.set(studentID);
    }

    public double getMidterm() {
        return this.Midterm.get();
    }

    public void setMidterm(double midterm) {
        this.Midterm.set(midterm);
    }

    public double getAssignments() {
        return this.Assignments.get();
    }

    public void setAssignments(double assignments) {
        this.Assignments.set(assignments);
    }

    public double getFinalexam() {
        return this.Finalexam.get();
    }

    public void setFinalexam(double finalexam) {
        this.Finalexam.set(finalexam);
    }

    public double getFinalMark() {
        return this.FinalMark.get();
    }

    public void setFinalMark(double finalMark) {
        this.FinalMark.set(finalMark);
    }

    public String getLetterGrade() {
        return this.LetterGrade.get();
    }

    public void setLetterGrade(String letterGrade) {
        this.LetterGrade.set(letterGrade);
    }

    public double determineFinalMarks(double assignments, double midterm, double finalexam){
        this.setFinalMark((midterm*0.30)+(assignments*0.20)+(finalexam*0.50));
        return this.getFinalMark();
    }


    public String determineLetterGrade(double finalMark) {
        String letterGrade = null;
            if(finalMark >= 80){
            //A mark
                letterGrade = "A";
            }
            else if(finalMark >= 70){
            //B mark
                letterGrade = "B";
            }
            else if(finalMark >= 60){
            //C mark
                letterGrade = "C";
            }
            else if(finalMark >= 50){
            //D mark
                letterGrade = "D";
            }
            else{
            // F mark
                letterGrade = "F";
            }
        this.setLetterGrade(letterGrade);
        return letterGrade;
    }


}


