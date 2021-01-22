package Lab09;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


public class APIData {
    public String date;
    public float closePrice;
    public int year;
    public int month;
    public int day;
    public Date mydate;

    public APIData(String d, float c){
        this.date = d;
        this.closePrice = c;
    }

    public String getDate() {
        return date;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(float closePrice) {
        this.closePrice = closePrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMydate(String date) throws ParseException {
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
        Date date4 = formatter.parse(date);
        this.mydate = date4;

    }

    public Date getMydate(){return mydate;}
}
