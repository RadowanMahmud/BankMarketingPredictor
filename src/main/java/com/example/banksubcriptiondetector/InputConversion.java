package com.example.banksubcriptiondetector;

import javafx.scene.control.ComboBox;

public class InputConversion {

    public double convertJobsForClassifier(String job){
        if(job.equals("technician")){
            return 1;
        }else if(job.equals("services")){
            return 2;
        }else if(job.equals("business")){
            return 3;
        }else if(job.equals("retired")){
            return 4;
        }else if(job.equals("unemployed")){
            return 5;
        }else if(job.equals("housemaid")){
            return 6;
        }else if(job.equals("management")){
            return 7;
        }else if(job.equals("student")){
            return 8;
        }else if (job.equals("admin")){
            return 9;
        }
        else return 0;
    }

    public double convertEducation(String edu){
        if(edu.equals("professional")){
            return 1;
        }else if(edu.equals("school")){
            return 2;
        }else if(edu.equals("university")){
            return 3;
        }else if(edu.equals("basic")){
            return 4;
        }
        else return 0;
    }

    public double convertDay(String day){
        if(day.equals("mon")){
            return 1;
        }else if(day.equals("tue")){
            return 2;
        }else if(day.equals("wed")){
            return 3;
        }else if(day.equals("thu")){
            return 4;
        }else if(day.equals("fri")) return 5;
        return 0;
    }

    public double convertMaritalStatus(String status){
        if(status.equals("married")){
            return 1;
        }else if(status.equals("single")){
            return 2;
        }
        else return 0;
    }

    public double convertbool(String bool){
        if(bool.equals("yes")){
            return 1;
        }else return 0;
    }
}
