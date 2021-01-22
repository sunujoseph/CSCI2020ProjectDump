package lab05_inclass_monday;

public class StudentRecord {
    private String sid;
    private String fname;
    private String lname;
    private float value;

    public StudentRecord() { }

    public StudentRecord(String sid, String fname, String lname, float value) {
        this.sid = sid;
        this.fname = fname;
        this.lname = lname;
        this.value = value;
    }

    public String getSid() { return this.sid; }

    public float getValue() { return this.value; }

    public String getFirstname() { return this.fname; }

    public String getLastname() { return this.lname; }
}
