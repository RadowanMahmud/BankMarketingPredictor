package com.example.banksubcriptiondetector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.banksubcriptiondetector.knn.*;
import com.example.banksubcriptiondetector.Decision.*;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));

        knnStarter k= new knnStarter();
       // k.startMethod();

        Decisiontree d= new Decisiontree();
        // d.StartDecisionTree("data.csv");

        GridPane mainlayout = new GridPane();
        mainlayout.setAlignment(Pos.CENTER);
        mainlayout.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        mainlayout.setHgap(5.5);
        mainlayout.setVgap(5.5);

        GridPane classificationlayout = new GridPane();
        classificationlayout.setAlignment(Pos.CENTER);
        classificationlayout.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        classificationlayout.setHgap(5.5);
        classificationlayout.setVgap(5.5);
        classificationlayout.setAlignment(Pos.CENTER);

        GridPane predictionLayout = new GridPane();
        predictionLayout.setAlignment(Pos.CENTER);
        predictionLayout.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        predictionLayout.setHgap(5.5);
        predictionLayout.setVgap(5.5);
        predictionLayout.setAlignment(Pos.CENTER);

        Scene mainScene = new Scene(mainlayout, 600, 500);
        Scene classificationScene = new Scene(classificationlayout, 600, 500);
        Scene predictionScene = new Scene(predictionLayout, 600, 500);

        predictionLayout.add(new Label("Prediction"), 0, 0);
        Button backButton2 = new Button("<=");
        backButton2.setOnAction(e -> primaryStage.setScene(mainScene));
        predictionLayout.add(backButton2,5,0);

        classificationlayout.add(new Label("Classification"), 0, 0);
        Button backButton = new Button("<=");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
        classificationlayout.add(backButton,5,0);


        mainlayout.add(new Label("Welcome To Bank Marketing Prediction Software"), 0, 0);
        Button predictionButton = new Button("Classification");
        predictionButton.setOnAction(e -> primaryStage.setScene(classificationScene));
        mainlayout.add(predictionButton,0,1);
        Button decisionButton = new Button("Prediction");
        decisionButton.setOnAction(e -> primaryStage.setScene(predictionScene));
        mainlayout.add(decisionButton,0,3);
        //mainlayout.getChildren().addAll(label1, button);

        primaryStage.setTitle("Bank Marketing Predictions");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}