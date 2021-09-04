package com.example.banksubcriptiondetector.Decision;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Decisiontree {
  String[][] allData;
  Map<String, Integer> categoryMap = new HashMap<>();
  ArrayList<String> classes = new ArrayList<>();

  int dataRows=0, dataColumns;

  public void readData(String file) throws IOException {

      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String[] arr= new String[30];
      String frow; int r=0;

      while ( bufferedReader.readLine()!= null) dataRows++;

      bufferedReader = new BufferedReader(new FileReader(file));

      while ((frow = bufferedReader.readLine())!= null){

          arr = frow.split(",");

          if(r!=0) categoryMap.put(arr[arr.length-1],0);

          if(r==0) {
              allData = new String[dataRows][arr.length];
          }

          for (int i=0;i<arr.length;i++){
              if(arr[i]==null){
                  allData[r][i]="no";
              }else allData[r][i]=arr[i];
          }
          r+=1;
      }
      dataColumns=arr.length;
      bufferedReader.close();
  }
    public double calculateTotalEntropy(Map<String,Integer>categoryMap, String[][] table){

      Map<String, Integer> copyCat= new HashMap<>(categoryMap);

      for(int i=1; i<table.length;i++)
          copyCat.put(table[i][dataColumns-1], copyCat.get(table[i][dataColumns-1])+1);

        double entropy = 0; int hCheck=0;

        for (Map.Entry<String, Integer> entry : copyCat.entrySet()) {

            double catFrequency = entry.getValue();

            if(catFrequency>0) hCheck++;

            entropy+= -((catFrequency/(table.length-1))*(Math.log(catFrequency/(table.length-1))/Math.log(2)));

        }
        if(hCheck==1) return 0;

        return entropy;
    }

  public double calculateBranchEntropy(Map<String, Integer> catMap, double branchFreq, int rows){

      double entropy = 0;
      for (Map.Entry<String, Integer> entry : catMap.entrySet()) {

         double catFrequency = entry.getValue();

         entropy+= -((catFrequency/branchFreq)*(Math.log(catFrequency/branchFreq)/Math.log(2)));

      }
      return entropy*(branchFreq/(rows-1));

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
              for (int j=0;j<dataColumns;j++)
                  newTable[temp][j]= parentTable[i][j];
              temp++;
          }
      }
      return newTable;
  }

  public Node createTree(String[][] table){

      Node n = new Node();
      int rows=table.length;
      Set<String> temp = new HashSet<>();
      Set<String>branches = new HashSet<>();
      int currentColumnNumber=0, newTColumn=1;
      double maxGain = 0.0;
      
      double totalEntropy= calculateTotalEntropy(categoryMap,table);

      if(totalEntropy==0){

          n.leaf=true;
          if(table[1][dataColumns-1]==null){
              n.decision = "no";
          }
          else n.decision = table[1][dataColumns-1];
          return n;
      }

      while (currentColumnNumber< dataColumns-1){

          double featureEntropy=0;

          for(int i=1;i<rows;i++)
              branches.add(table[i][currentColumnNumber]);

          for(String branch: branches){

              double branchFrequency=0;

              Map<String, Integer> copyCat = new HashMap<>(categoryMap);

              for (int j=1;j<rows;j++){

                  if(branch.equals(table[j][currentColumnNumber])){
                      branchFrequency++;

                      copyCat.put(table[j][dataColumns-1], copyCat.get(table[j][dataColumns-1])+1);
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
              n.attribute = table[0][currentColumnNumber];
              n.leaf=false;

              for(String s: branches)
                  temp.add(s);
          }
          currentColumnNumber++;
          branches.clear();
      }
     // System.out.println("\nSelected Feature: "+n.attribute+"\n");
      for(String s: temp){
          String[][] reducedTable= makeChildTable(table, s, newTColumn);
          n.nodes.put(s, createTree(reducedTable));
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

          for(Map.Entry<String,Node> entry : node.nodes.entrySet()){
              System.out.println("");

              for (int i=0;i<depth;i++) System.out.print("\t");

              System.out.print("--"+entry.getKey()+"--> ");

              printTree(entry.getValue(),depth+2);
          }
      }
  }

    public void matchMaking(Node node,String[][] arr){
        if(node.leaf){
            System.out.println("Answer:"+node.decision);
        }

        for(int i=0;i<arr[0].length-1;i++){
            // System.out.println(node.attribute);
            if(node.attribute == null){
                continue;
            }
            if(node.attribute.equals(arr[0][i])){

                for (Map.Entry<String, Node> entry : node.nodes.entrySet()) {
                    String key = entry.getKey();
                    Node nord = entry.getValue();
                    if(key.equals(arr[1][i])){
                        matchMaking(nord,arr);
                    }
                }

            }
        }
    }


    public void StartDecisionTree(String file) throws IOException {

        readData(file);

        Node n = createTree(allData);

        System.out.println("---The Tree--\n");

       printTree(n,0);

       String[][] arr=new String[2][allData[0].length];
       arr[0]=allData[0];
       arr[1]=allData[18047];
       matchMaking(n,arr);
       System.out.println(allData[18047][allData[0].length-1]);

       System.out.println("*Total data= "+(allData.length-1));
    }
}
