package anime;


import javafx.scene.image.Image;


import java.net.URL;

public class Waifu {

    private Image waifuImage;
    private URL waifuImageURL;
    private String waifuName;
    private double waifuRating;

    public Waifu(){

    }

    public void setWaifuImage(Image wi){
        this.waifuImage = wi;
    }
    public Image getWaifuImage(){
        return this.waifuImage;
    }

    public void setWaifuImageURL(URL waifuURL){
        this.waifuImageURL = waifuURL;
    }

    public URL getWaifuImageURL() {
        return waifuImageURL;
    }

    public void setWaifuName(String name){
        this.waifuName = name;
    }

    public String getWaifuName() {
        return waifuName;
    }

    public void setWaifuRating(double rate){
        this.waifuRating = rate;
    }

    public double getWaifuRating() {
        return waifuRating;
    }
}
