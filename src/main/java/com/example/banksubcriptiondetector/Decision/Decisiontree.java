package com.example.banksubcriptiondetector.Decision;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Decisiontree {
  String[][] mainDataset;
  Map<String, Integer> categoryMap = new HashMap<>();
  int dataRows=0, dataColumns;

    public void getDataRows(String file) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

      while ( bufferedReader.readLine()!= null) {
          dataRows++;
      }
  }

    public void readData(String file) throws IOException {

        getDataRows(file);
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String[] arr= new String[15];
      int count=0;
      String frow;

      while ((frow = bufferedReader.readLine())!= null){

          arr = frow.split(",");

          if(count!=0) categoryMap.put(arr[arr.length-1],0);

          if(count==0) {
              mainDataset = new String[dataRows][arr.length];
          }

          for (int i=0;i<arr.length;i++){
              if(arr[i]==null){
                  mainDataset[count][i]="no";
              }else mainDataset[count][i]=arr[i];
          }
          count+=1;
      }
      dataColumns=arr.length;
      bufferedReader.close();
  }
  
    public double calculateTotalEntropy(Map<String,Integer>categoryMap, String[][] table){

      Map<String, Integer> copyClassCategoryMap= new HashMap<>(categoryMap);

      for(int i=1; i<table.length;i++)
          copyClassCategoryMap.put(table[i][dataColumns-1], copyClassCategoryMap.get(table[i][dataColumns-1])+1);

        double entropy = 0;
        int Check=0;
        for (Map.Entry<String, Integer> entry : copyClassCategoryMap.entrySet()) {

            double catFrequency = entry.getValue();

            if(catFrequency>0) Check++;

            entropy+= -((catFrequency/(table.length-1))*(Math.log(catFrequency/(table.length-1))/Math.log(2)));

        }
        if(Check==1) return 0;

        return entropy;
    }

  public double calculateBranchEntropy(Map<String, Integer> categoryMap, double featureFrequency, int rows){

      double entropy = 0;
      for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
         double categoryFrequency = entry.getValue();
         entropy+= -((categoryFrequency/featureFrequency)*(Math.log(categoryFrequency/featureFrequency)/Math.log(2)));
      }
      return entropy*(featureFrequency/(rows-1));

  }

  public String[][] makeChildTable(String[][] parentTable, String branchName, int column){

      int rTRow=1,temp=1,trow = parentTable.length;

      for(int i=1; i<trow;i++){

          if(parentTable[i][column].equals(branchName)) rTRow++;
      }
      String[][] newTable= new String[rTRow][dataColumns];

      for(int i=0; i<dataColumns; i++) newTable[0][i] = parentTable[0][i];

      for(int i=1;i<trow;i++){
          if(parentTable[i][column].equals(branchName)){
              for (int j=0;j<dataColumns;j++){
                  newTable[temp][j]= parentTable[i][j];
              }
              temp++;
          }
      }
      return newTable;
  }

  public Node createTree(String[][] workingDataset){

      Node n = new Node();
      int rows=workingDataset.length;
      Set<String> temp = new HashSet<>();
      Set<String>features = new HashSet<>();
      int currentColumnNumber=0, newTColumn=1;
      double maxGain = 0.0;
      
      double totalEntropy= calculateTotalEntropy(categoryMap,workingDataset);

      if(totalEntropy==0){
          n.leaf=true;
          if(workingDataset[1][dataColumns-1]==null){
              n.decision = "yes";
          }
          else {
              n.decision = workingDataset[1][dataColumns-1];
          }
          return n;
      }

      while (currentColumnNumber< dataColumns-1){

          double featureEntropy=0;

          for(int i=1;i<rows;i++)
              features.add(workingDataset[i][currentColumnNumber]);

          for(String branch: features){

              double branchFrequency=0;

              Map<String, Integer> copyCat = new HashMap<>(categoryMap);

              for (int j=1;j<rows;j++){

                  if(branch.equals(workingDataset[j][currentColumnNumber])){
                      branchFrequency++;

                      copyCat.put(workingDataset[j][dataColumns-1], copyCat.get(workingDataset[j][dataColumns-1])+1);
                  }
              }

              if(Collections.frequency(copyCat.values(), 0) == copyCat.size()-1)
                  featureEntropy+=0;

              else
                  featureEntropy+= calculateBranchEntropy(copyCat,branchFrequency,rows);
              
          }
          //System.out.println("Info Gain for "+ table[0][currentColumnNumber]+": "+  d);

          if( (totalEntropy-featureEntropy) > maxGain){
              temp.clear();
              maxGain = totalEntropy-featureEntropy;
              newTColumn = currentColumnNumber;
              n.attribute = workingDataset[0][currentColumnNumber];
              n.leaf=false;

              for(String s: features)
                  temp.add(s);
          }
          currentColumnNumber++;
          features.clear();
      }
     // System.out.println("\nSelected Feature: "+n.attribute+"\n");
      for(String s: temp){
          String[][] reducedTable= makeChildTable(workingDataset, s, newTColumn);
          n.childNodes.put(s, createTree(reducedTable));
      }

      return n;
  }

  public void printTree(Node node, int depth){

      if(node.leaf){
          System.out.print(node.decision);
      }
      else {
          depth++;
          System.out.print(node.attribute);

          for(Map.Entry<String,Node> entry : node.childNodes.entrySet()){
              System.out.println("");

              for (int i=0;i<depth;i++) System.out.print("\t");

              System.out.print("--"+entry.getKey()+"--> ");

              printTree(entry.getValue(),depth+2);
          }
      }
  }

    public void parseTreeForResult(Node node, String[][] arr){
        if(node.leaf){
            System.out.println();
            System.out.println("Answer:"+node.decision);
        }

        for(int i=0;i<arr[0].length-1;i++){
            // System.out.println(node.attribute);
            if(node.attribute == null){
                continue;
            }
            if(node.attribute.equals(arr[0][i])){

                for (Map.Entry<String, Node> entry : node.childNodes.entrySet()) {
                    String key = entry.getKey();
                    Node nord = entry.getValue();
                    if(key.equals(arr[1][i])){
                        parseTreeForResult(nord,arr);
                    }
                }

            }
        }
    }


    public void StartDecisionTree(String file) throws IOException {

        readData(file);

        Node n = createTree(mainDataset);

        System.out.println("---The Tree--\n");

       printTree(n,0);

       String[][] arr=new String[2][mainDataset[0].length];
       arr[0]= mainDataset[0];
       arr[1]= mainDataset[18047];
       parseTreeForResult(n,arr);
       System.out.println(mainDataset[18047][mainDataset[0].length-1]);

       // System.out.println("*Number of data= "+(mainDataset.length-1));
    }
}
