import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Results {
       public static void main(String[] args) throws IOException {

              BufferedReader textFileReader = null;

                //  check that two arguments have been supplied on the command line
                if (args.length != 1) {
                    System.out.println("Results usage: One parameter which must contain the name of the source file only");
                    System.exit(1);
                } 

              try {

                     // Create file reader
                     textFileReader = new BufferedReader(new FileReader(args[0]));
                     boolean finished = false;
                     String inputString = null;
                     String outputString = "";
                     boolean firstOne = true;

 

                     // read lines in file until none left
                     while (!finished) {
                           try {
                                  inputString = textFileReader.readLine();
                           } catch (IOException ioe) {
                                  System.out.println("Error reading the text file " + args[0]);
                                  System.exit(4);
                           }

                           // Check if end of file reached

                           if (inputString == null) {
                                  finished = true;
                           } else {
                                 // append to string
                                  if (firstOne) {
                                         outputString = inputString;
                                         firstOne = false;   
                                  } else {
                                         outputString = outputString + " " + inputString;
                                  }
                           }
                     }

 

                     // Break line into separate words
                     String[] textArray = outputString.split("\\s+"); // \\s splits by all whitespace chars
                     int splitArraysLength = textArray.length / 3;
                     String[] surnameArray = new String[splitArraysLength];
                     String[] initialArray = new String[splitArraysLength];
                     int[] markArray = new int[splitArraysLength];
                     int posCounter = 0;

                     // Split into 3 arrays for sorting -Surname, initial, mark
                     for (int i = 0; i < textArray.length; i += 3) {
                           surnameArray[posCounter] = textArray[i];
                           initialArray[posCounter] = textArray[i + 1];
                           markArray[posCounter] = Integer.parseInt(textArray[i + 2]);

                           posCounter++;

                     }

                     // sort alphabetically
                     String tempSurname, tempInitial;
                     int tempMark;
                     for (int j = 0; j < surnameArray.length - 1; j++) {
                           for (int i = 0; i < surnameArray.length - j - 1; i++) {
                                  if (surnameArray[i + 1].compareTo(surnameArray[i]) < 0) {
                                         tempSurname = surnameArray[i];
                                         tempInitial = initialArray[i];
                                         tempMark = markArray[i];
                                         surnameArray[i] = surnameArray[i + 1];
                                         initialArray[i] = initialArray[i + 1];
                                         markArray[i] = markArray[i + 1];
                                         surnameArray[i + 1] = tempSurname;
                                         initialArray[i + 1] = tempInitial;
                                         markArray[i + 1] = tempMark;
                                  } else if (surnameArray[i + 1].compareTo(surnameArray[i]) == 0) {
                                         if (initialArray[i + 1].compareTo(initialArray[i]) < 0) {
                                                tempSurname = surnameArray[i];
                                                tempInitial = initialArray[i];
                                                tempMark = markArray[i];
                                                surnameArray[i] = surnameArray[i + 1];
                                                initialArray[i] = initialArray[i + 1];
                                                markArray[i] = markArray[i + 1];
                                                surnameArray[i + 1] = tempSurname;
                                                initialArray[i + 1] = tempInitial;
                                                markArray[i + 1] = tempMark;
                                         }
                                  }
                           }
                     }
                   
                     String line = String.valueOf((char)95);
                     String underLine = line + line + line + line;
                     int namePad = getNamePad(surnameArray, initialArray);
                     String title = String.format("%-" + namePad + "s%-7s \n", "Name", "Mark");
                     String lines = String.format("%-" + namePad + "s%-7s \n", underLine, underLine);
                     String markLine = "";
                     System.out.print(title);
                     System.out.println(lines);
                    
                     for (int i = 0; i < surnameArray.length; i++) {
                           markLine = String.format("%-" + namePad + "s%-7d \n", initialArray[i] + " " + surnameArray[i], markArray[i]);
                           System.out.print(markLine);
                     }

 
              } catch (FileNotFoundException ioe) {
                     System.out.println("The file " + args[0] + " could not be opened");
                     System.exit(3);
              } finally {
                  textFileReader.close();
              }
       }
     
       public static int getNamePad(String [] nameArray, String [] initialArray) {
              int maxNameLength = 10;
              int thisNameLength = 0;
            
              // Loop through - get the max possible name length and add 5 t it
              for (int j = 0; j < nameArray.length; j++) {
                     thisNameLength = nameArray[j].length() + initialArray[j].length();
                     if (maxNameLength < thisNameLength) {
                           maxNameLength = thisNameLength;
                     }
              }
              return (maxNameLength + 5);
       }

}

