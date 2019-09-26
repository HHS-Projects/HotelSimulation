package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    public static void display(String title, String message){

        Stage window = new Stage();
        window.setMinHeight(250);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label(message);
        Button button = new Button("I understand");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,button);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
