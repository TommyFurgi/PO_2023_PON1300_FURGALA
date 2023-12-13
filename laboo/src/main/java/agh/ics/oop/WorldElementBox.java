package agh.ics.oop;

import agh.ics.oop.model.WorldElement;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class WorldElementBox extends StackPane {

    public WorldElementBox(WorldElement worldElement) throws IllegalArgumentException {

        String filepath = worldElement.getResourceName();
        Image image = new Image(filepath);

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Text label;
        switch (worldElement.getClass().getSimpleName()) {
            case "Animal" -> label = new Text("Z " + worldElement.getPosition());
            case "Grass" -> label = new Text("Grass");
            default -> throw new IllegalArgumentException("Unknown worldElement type");
        }

        VBox vbox = new VBox(imageView, label);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);

        this.setAlignment(Pos.CENTER);
    }
}
