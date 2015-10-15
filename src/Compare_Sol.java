import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Author: Anuja Nagare
 * Email: anuja.nagare@uga.edu 
 * */

@SuppressWarnings("unused")
public class Compare_Sol {

	public static String inputFile1 = new File("F:/MS Sem 1/Algo/Project/CasinoDiceOutput.txt");
	public static String inputFile2 = new File("F:/MS Sem 1/Algo/Project/CasinoDice_States.txt");
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Compare_Sol obj = new Compare_Sol();

		File file1 = new File(inputFile1);
		Scanner scan1 = new Scanner(file1);
		String input1;
		//input takes in a line of numbers
		input1 = scan1.nextLine();
		scan1.close();


		File file2 = new File(inputFile2);
		Scanner scan2 = new Scanner(file2);
		String input2;
		//input takes in a line of numbers
		input2 = scan2.nextLine();
		scan2.close();

		int count=0;
		
		for (int i = 0;i < input1.length(); i++){
		if(input1.charAt(i)==input2.charAt(i)){
			count++;
		}   
		}
		
		System.out.println("Accuracy:"+(float)count/input1.length()*100);
//		System.out.println(input1.length());
//		System.out.println(input2.length());

	}


}
