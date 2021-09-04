package com.example.banksubcriptiondetector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
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
         d.StartDecisionTree("data.csv");

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

        ComboBox jobComBox = new ComboBox();
        jobComBox.getItems().add("management");
        jobComBox.getItems().add("self-employed");
        jobComBox.getItems().add("student");
        jobComBox.getItems().add("housemaid");
        jobComBox.getItems().add("admin");
        jobComBox.getItems().add("technician");
        jobComBox.getItems().add("retired");
        jobComBox.getItems().add("services");
        jobComBox.getItems().add("blue-collar");
        jobComBox.getItems().add("unemployed");
        jobComBox.getItems().add("entrepreneur");
        jobComBox.getItems().add("unknown");

        ComboBox marriageCombox = new ComboBox();
        marriageCombox.getItems().add("single");
        marriageCombox.getItems().add("married");
        marriageCombox.getItems().add("divorced");
        marriageCombox.getItems().add("unknown");

        ComboBox educationCombox = new ComboBox();
        educationCombox.getItems().add("illiterate");
        educationCombox.getItems().add("university.degree");
        educationCombox.getItems().add("high.school");
        educationCombox.getItems().add("professional.course");
        educationCombox.getItems().add("basic");
        educationCombox.getItems().add("unknown");

        ComboBox defaultCombox = new ComboBox();
        defaultCombox.getItems().add("yes");
        defaultCombox.getItems().add("no");
        defaultCombox.getItems().add("unknown");

        ComboBox housingCombox = new ComboBox();
        housingCombox.getItems().add("yes");
        housingCombox.getItems().add("no");
        housingCombox.getItems().add("unknown");

        ComboBox loanCombox = new ComboBox();
        loanCombox.getItems().add("yes");
        loanCombox.getItems().add("no");
        loanCombox.getItems().add("unknown");

        ComboBox contactCombox = new ComboBox();
        contactCombox.getItems().add("cellular");
        contactCombox.getItems().add("telephone");

        ComboBox monthCombox = new ComboBox();
        monthCombox.getItems().add("mar");
        monthCombox.getItems().add("apr");
        monthCombox.getItems().add("jun");
        monthCombox.getItems().add("jul");
        monthCombox.getItems().add("aug");
        monthCombox.getItems().add("oct");
        monthCombox.getItems().add("nov");
        monthCombox.getItems().add("dec");

        ComboBox dayComBox = new ComboBox();
        dayComBox.getItems().add("mon");
        dayComBox.getItems().add("tue");
        dayComBox.getItems().add("wed");
        dayComBox.getItems().add("thu");
        dayComBox.getItems().add("fri");

        ComboBox preResultCombox = new ComboBox();
        preResultCombox.getItems().add("failure");
        preResultCombox.getItems().add("success");
        preResultCombox.getItems().add("nonexistent");

        predictionLayout.add(new Label("Prediction"), 1, 0);
        Button backButton2 = new Button("<=");
        backButton2.setOnAction(e -> primaryStage.setScene(mainScene));
        predictionLayout.add(backButton2,5,0);
        predictionLayout.add(new Label("SelectJob:"), 0, 1);
        predictionLayout.add(jobComBox,1,1);
        predictionLayout.add(new Label("Marital Status:"), 0, 2);
        predictionLayout.add(marriageCombox,1,2);
        predictionLayout.add(new Label("Education:"), 0, 3);
        predictionLayout.add(educationCombox,1,3);
        predictionLayout.add(new Label("Default:"), 0, 4);
        predictionLayout.add(defaultCombox,1,4);
        predictionLayout.add(new Label("Housing:"), 0, 5);
        predictionLayout.add(housingCombox,1,5);
        predictionLayout.add(new Label("Loan:"), 0, 6);
        predictionLayout.add(loanCombox,1,6);
        predictionLayout.add(new Label("Contact:"), 0, 7);
        predictionLayout.add(contactCombox,1,7);
        predictionLayout.add(new Label("Month:"), 0, 8);
        predictionLayout.add(monthCombox,1,8);
        predictionLayout.add(new Label("Day Of Week:"), 0, 9);
        predictionLayout.add(dayComBox,1,9);
        predictionLayout.add(new Label("Previous Result:"), 0, 10);
        predictionLayout.add(preResultCombox,1,10);
        Button Submit = new Button("Submit");
        predictionLayout.add(Submit,1,11);
        Submit.setOnAction(e -> {
            System.out.println("Submit clicked");
            String[] arr=new String[11];
            arr[0] = (String) jobComBox.getValue();
            arr[1] = (String) marriageCombox.getValue();
            arr[2] = (String) educationCombox.getValue();
            arr[3] = (String) defaultCombox.getValue();
            arr[4] = (String) housingCombox.getValue();
            arr[5] = (String) loanCombox.getValue();
            arr[6] = (String) contactCombox.getValue();
            arr[7] = (String) monthCombox.getValue();
            arr[8] = (String) dayComBox.getValue();
            arr[9] = (String) preResultCombox.getValue();
            arr[10] = "decision";

            d.getPrediction(arr);
        });


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