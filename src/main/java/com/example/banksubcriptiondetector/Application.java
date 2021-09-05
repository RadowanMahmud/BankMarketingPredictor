package com.example.banksubcriptiondetector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        k.startMethod();

        Decisiontree d= new Decisiontree();
        d.StartDecisionTree("data.csv");

        InputConversion convertInput = new InputConversion();

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
            // System.out.println("Submit clicked");
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

            String res = d.getPrediction(arr);
            if(res.equals("yes")){
                Alert alert =  new Alert(Alert.AlertType.CONFIRMATION, "Yes! Our System Identifies The person as a potential client", ButtonType.OK);
                alert.show();
            }else if(res.equals("no")){
                Alert alert =  new Alert(Alert.AlertType.INFORMATION, "The chances are low for the user to subscribe", ButtonType.OK);
                alert.show();
            } else{
                Alert alert =  new Alert(Alert.AlertType.INFORMATION, "This is an exceptional case. So the chances are equal", ButtonType.OK);
                alert.show();
            }

        });


        classificationlayout.add(new Label("Classification"), 1, 0);
        Button backButton = new Button("<=");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
        classificationlayout.add(backButton,5,0);

        ComboBox jobComBoxclassifir = new ComboBox();
        jobComBoxclassifir.getItems().add("management");
        jobComBoxclassifir.getItems().add("student");
        jobComBoxclassifir.getItems().add("housemaid");
        jobComBoxclassifir.getItems().add("admin");
        jobComBoxclassifir.getItems().add("technician");
        jobComBoxclassifir.getItems().add("retired");
        jobComBoxclassifir.getItems().add("services");
        jobComBoxclassifir.getItems().add("business");
        jobComBoxclassifir.getItems().add("unemployed");

        ComboBox marriageComboxClassifier = new ComboBox();
        marriageComboxClassifier.getItems().add("single");
        marriageComboxClassifier.getItems().add("married");
        marriageComboxClassifier.getItems().add("divorced");

        ComboBox educationComboxClassifier = new ComboBox();
        educationComboxClassifier.getItems().add("university");
        educationComboxClassifier.getItems().add("school");
        educationComboxClassifier.getItems().add("professional");
        educationComboxClassifier.getItems().add("basic");

        ComboBox defaultComboxClassfier = new ComboBox();
        defaultComboxClassfier.getItems().add("yes");
        defaultComboxClassfier.getItems().add("no");

        ComboBox housingComboxClassifier = new ComboBox();
        housingComboxClassifier.getItems().add("yes");
        housingComboxClassifier.getItems().add("no");

        ComboBox loanComboxClassifier = new ComboBox();
        loanComboxClassifier.getItems().add("yes");
        loanComboxClassifier.getItems().add("no");

        ComboBox dayComBoxClassifier = new ComboBox();
        dayComBoxClassifier.getItems().add("mon");
        dayComBoxClassifier.getItems().add("tue");
        dayComBoxClassifier.getItems().add("wed");
        dayComBoxClassifier.getItems().add("thu");
        dayComBoxClassifier.getItems().add("fri");

        classificationlayout.add(new Label("Age:"), 0, 1);
        TextField age = new TextField();
        classificationlayout.add(age, 1, 1);
        classificationlayout.add(new Label("SelectJob:"), 0, 2);
        classificationlayout.add(jobComBoxclassifir,1,2);
        classificationlayout.add(new Label("Marital Status:"),0,3);
        classificationlayout.add(marriageComboxClassifier,1,3);
        classificationlayout.add(new Label("Education:"),0,4);
        classificationlayout.add(educationComboxClassifier,1,4);
        classificationlayout.add(new Label("Default:"),0,5);
        classificationlayout.add(defaultComboxClassfier,1,5);
        classificationlayout.add(new Label("Housing:"),0,6);
        classificationlayout.add(housingComboxClassifier,1,6);
        classificationlayout.add(new Label("Loan:"),0,7);
        classificationlayout.add(loanComboxClassifier,1,7);
        classificationlayout.add(new Label("Day Of Week:"),0,8);
        classificationlayout.add(dayComBoxClassifier,1,8);
        classificationlayout.add(new Label("Previous Contacts(0-4):"), 0, 9);
        TextField pc = new TextField();
        classificationlayout.add(pc, 1, 9);
        classificationlayout.add(new Label("Price Index:"), 0, 10);
        TextField pi = new TextField();
        classificationlayout.add(pi, 1, 10);
        classificationlayout.add(new Label("Confidence Index:"), 0, 11);
        TextField ci = new TextField();
        classificationlayout.add(ci, 1, 11);
        Button SubmitClass = new Button("Submit");
        classificationlayout.add(SubmitClass,1,12);
        SubmitClass.setOnAction(e -> {
            System.out.println("Submit clicked");
            double[] arr=new double[11];
            arr[0] = (Double.parseDouble(age.getText()))/10;
            arr[1] = convertInput.convertJobsForClassifier((String) jobComBoxclassifir.getValue());
            arr[2] = convertInput.convertMaritalStatus((String) marriageComboxClassifier.getValue());
            arr[3] = convertInput.convertEducation((String) educationComboxClassifier.getValue());
            arr[4] = convertInput.convertbool((String) defaultComboxClassfier.getValue());
            arr[5] = convertInput.convertbool((String) housingComboxClassifier.getValue());
            arr[6] = convertInput.convertbool((String) loanComboxClassifier.getValue());
            arr[7] = convertInput.convertDay((String) dayComBoxClassifier.getValue());
            arr[8] = (Double.parseDouble(pc.getText()));
            arr[9] = (Double.parseDouble(pi.getText()))/100;
            arr[10] = (Double.parseDouble(ci.getText()));

            try {
                String res =k.test(arr);
                if(res.equals("yes")){
                    Alert alert =  new Alert(Alert.AlertType.CONFIRMATION, "Yes! Our System Identifies The person as a potential client", ButtonType.OK);
                    alert.show();
                }else{
                    Alert alert =  new Alert(Alert.AlertType.INFORMATION, "The chances are low, You may give a try", ButtonType.OK);
                    alert.show();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // String input =
        });

        mainlayout.add(new Label("Welcome To Bank TeleMarketing Success Prediction Software"), 0, 0);
        Button predictionButton = new Button("Classification");
        predictionButton.setOnAction(e -> primaryStage.setScene(classificationScene));
        mainlayout.add(predictionButton,0,1);
        Button decisionButton = new Button("Prediction");
        decisionButton.setOnAction(e -> primaryStage.setScene(predictionScene));
        mainlayout.add(decisionButton,0,3);
        //mainlayout.getChildren().addAll(label1, button);

        primaryStage.setTitle("Bank Telemarketing Predictions");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}