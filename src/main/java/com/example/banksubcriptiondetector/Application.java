package com.example.banksubcriptiondetector;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.banksubcriptiondetector.knn.*;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));

        knnStarter k= new knnStarter();
        k.startMethod();

        GridPane mainlayout = new GridPane();
        mainlayout.setAlignment(Pos.CENTER);
        mainlayout.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        mainlayout.setHgap(5.5);
        mainlayout.setVgap(5.5);

        GridPane predictorlayout = new GridPane();
        predictorlayout.setAlignment(Pos.CENTER);
        predictorlayout.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        predictorlayout.setHgap(5.5);
        predictorlayout.setVgap(5.5);
        predictorlayout.setAlignment(Pos.CENTER);

        GridPane decisionlayout = new GridPane();
        decisionlayout.setAlignment(Pos.CENTER);
        decisionlayout.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        decisionlayout.setHgap(5.5);
        decisionlayout.setVgap(5.5);
        decisionlayout.setAlignment(Pos.CENTER);

        Scene mainScene = new Scene(mainlayout, 600, 500);
        Scene predictionScene = new Scene(predictorlayout, 600, 500);
        Scene decisionScene = new Scene(decisionlayout, 600, 500);

        mainlayout.add(new Label("Welcome To Banking Prediction Software"), 0, 0);
        Button predictionButton = new Button("Prediction");
        predictionButton.setOnAction(e -> primaryStage.setScene(predictionScene));
        mainlayout.add(predictionButton,0,1);
        Button decisionButton = new Button("Decision");
        decisionButton.setOnAction(e -> primaryStage.setScene(decisionScene));
        mainlayout.add(decisionButton,0,3);
        //mainlayout.getChildren().addAll(label1, button);


        Label label2 = new Label("This is the Second Scene");


        Button button2 = new Button("Backwards");
        button2.setOnAction(e -> primaryStage.setScene(mainScene));

        decisionlayout.getChildren().addAll(label2, button2);

        primaryStage.setTitle("Bank Marketing Predictions");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}