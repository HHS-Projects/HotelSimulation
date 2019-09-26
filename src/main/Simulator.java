package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.hotel.area.Area;
import model.hotel.area.AreaType;
import controller.AreaCreator;
import view.AlertBox;

public class Simulator extends Application{

	private Stage window;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		File file = new File("C:\\Apps\\Projects\\IdeaProjects\\HotelSimulation\\resources\\layout");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Layout File");
		fileChooser.setInitialDirectory(file);
		file = fileChooser.showOpenDialog(stage);
		String inputFile = file.getName();

		AreaCreator ac = new AreaCreator(inputFile);
		ArrayList<Area> area = ac.getAreaList();

		int scale = 50;
		int width = (ac.getMaxX()+1-ac.getMinX())*scale;
		int height = (ac.getMaxY()+1-ac.getMinY())*scale;

		window = stage;

		Group root = new Group();
		Scene scene = new Scene(root, width, height, Color.WHITE);

		final Canvas canvas =  new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		for(Area a : area) {

			String URL = "file:resources/images/"+a.getAreaType().toString().toLowerCase()+".png";
			Image image = new Image(URL);

			System.out.println(a.getAreaType().toString()+","+a.getPositionToString()+","+a.getDimensionToString());

			double baseline = ac.getMaxY()-a.getHeight()+1;
			double x = a.getX()*scale;
			double y = (baseline-a.getY())*scale;
			double w = a.getWidth()*scale;
			double h = a.getHeight()*scale;

			if(a.getAreaType().equals(AreaType.ROOM)
				|| a.getAreaType().equals(AreaType.FITNESS)
				|| a.getAreaType().equals(AreaType.RESTAURANT)
				|| a.getAreaType().equals(AreaType.CINEMA)
				|| a.getAreaType().equals(AreaType.STAIRS)
				|| a.getAreaType().equals(AreaType.LIFT)
				|| a.getAreaType().equals(AreaType.LOBBY))
			{
				gc.drawImage(image,x,y,w,h);
			}
			else {
				gc.setFill(Color.DARKMAGENTA);
				gc.fillRect(x,y,w,h);
			}
		}

		/*
		GridPane grid = new GridPane();

		Button b1 = new Button("b1");
		b1.setPrefWidth(100);
		b1.setPrefHeight(200);
		GridPane.setConstraints(b1,0,0);

		Button b2 = new Button("b2");
		GridPane.setConstraints(b2,1,0);

		grid.getChildren().addAll(b1,b2);

		Scene scene = new Scene(grid,300,300);
		*/

		root.getChildren().add(canvas);
		window.setScene(scene);
		window.setTitle("Hotel Simulation");
		window.setResizable(false);
		window.show();

	}

}
