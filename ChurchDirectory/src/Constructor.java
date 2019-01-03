import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Constructor {

  private FileReader fileReader;
  private BufferedReader bufferedReader;
  private PrintStream printStream;
  private Scanner console = new Scanner(System.in);
  
  
  public static void main(String[] args) {
    new Constructor();
  }
  
  public Constructor() {
    String input = "";
    
    File fileInput = new File("C:/Users/Tyler/Documents/Constructor.txt");
    try {
      printStream = new PrintStream(fileInput);
    } catch (FileNotFoundException g) {
      try {
        fileInput.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      fileReader = new FileReader("C:/Users/Tyler/Documents/Constructor.txt");
      bufferedReader = new BufferedReader(fileReader);
    } catch (IOException g) {
      g.printStackTrace();
    }
    while(true){
      input = "";
      System.out.print("Date: ");
      input = "" + console.nextLine() + "\t";
      if (input.equalsIgnoreCase("quit\t")){
        break;
      }
      System.out.print("Speaker: ");
      input = input + console.nextLine() + "\t";
      System.out.print("Bible Verse: ");
      input = input + console.nextLine() + "\n";
      try {
        System.out.println(this.writeToText(input));
      } catch (IOException g) {
        System.out.println("Sermon not added.");
      }
    }
  }
  
  public String writeToText(String line) throws IOException {
    String nextLine = "";
    while((nextLine = bufferedReader.readLine()) != null) {
      if (nextLine.equalsIgnoreCase(line)) {
        return "Sermon is already added.";
      } else {
      }
    }
    printStream.println(line);
    return "Sermon is added";
  }
}