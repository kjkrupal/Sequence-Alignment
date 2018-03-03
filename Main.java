import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

public class Main {

  public static void main(String[] args){

    try{

      if(args.length == 0 || args.length == 1 || args.length == 2)
        throw new InputArgumentException("Specify Sequence File path, 0 or 1 for Global or local alignment and Parameters Configuration file path in order.");

      String sequenceFileName = args[0];
      if(!new File(sequenceFileName).exists())
        throw new InputArgumentException("Specify proper file name and path for Sequence file");

      int algorithmId = Integer.parseInt(args[1]);
      if(algorithmId > 1 || algorithmId < 0 )
        throw new InputArgumentException("Enter 0 for Global and 1 for Local");

      String configFileName = args[2];
      if(!new File(configFileName).exists())
        throw new InputArgumentException("Specify proper file name and path for Parameters Configuration file");

      @SuppressWarnings("unchecked")
      ArrayList<ArrayList> list = readSequenceFile(sequenceFileName);

      @SuppressWarnings("unchecked")
      HashMap<String, Integer> map = readConfigFile(configFileName);

      Alignment alignment = new Alignment((String)list.get(0).get(0), (String)list.get(0).get(1),
                                          (String)list.get(1).get(0), (String)list.get(1).get(1),
                                          map.get("match"), map.get("mismatch"),
                                          map.get("h"), map.get("g"));
      switch(algorithmId){
        case 0: alignment.globalAlignment(alignment); break;
        case 1: alignment.localAlignment(alignment); break;
        default: System.out.println("Alignment completed");
      }
    }
    catch(Exception exception){
      System.out.println(exception);
    }
  }

  static ArrayList<ArrayList> readSequenceFile(String fileName) throws FileNotFoundException {
    Scanner scanFile = new Scanner(new File(fileName));
    ArrayList<String> keyList = new ArrayList<String>();
    ArrayList<String> sequenceList = new ArrayList<String>();
    ArrayList<ArrayList> list = new ArrayList<ArrayList>();
    String sequenceName = "";
    String sequence = "";

    while(scanFile.hasNext()){
      String line = scanFile.nextLine().toString();
      if(line.contains(">")){
        if(!sequence.equals(""))
          sequenceList.add(sequence);
        sequenceName = line.substring(1);
        keyList.add(sequenceName);
        sequence = "";
      }
      else{
        sequence = sequence + line;
      }
    }
    sequenceList.add(sequence);
    list.add(sequenceList);
    list.add(keyList);
    return list;
  }
  static HashMap readConfigFile(String fileName) throws FileNotFoundException{
    Scanner scanFile = new Scanner(new File(fileName));
    HashMap<String,Integer> map = new HashMap<String,Integer>();

    while(scanFile.hasNext()){
      String line = scanFile.nextLine().toString();
      String[] word = line.split("\\s");
      map.put(word[0], Integer.parseInt(word[2]));
    }
    return map;
  }

}
