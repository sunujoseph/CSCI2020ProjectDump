package anime;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Main extends Application {
    public ObservableList<Waifu> waifuList = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception{
        Image image = new Image("http://goo.gl/kYEQl");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("java-buddy.blogspot.com");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
