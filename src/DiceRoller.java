import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Author: Anuja Nagare
 * 		   Likhita Kode
 * 		
 * Email: anuja.nagare@uga.edu
 * 		  likhita.kode25@uga.edu
 * */

public class DiceRoller {

		public static String inputFilehmm = "F:/MS Sem 1/Algo/Project/hmm.txt";
		public static File outputFile = new File("F:/MS Sem 1/Algo/Project/CasinoDiceInput.txt");
		public static File outputFile_S = new File("F:/MS Sem 1/Algo/Project/CasinoDice_States.txt");
		
		static List<String> rolls = new ArrayList<String>();
		static List<Integer> diceNum = new ArrayList<Integer>();
		double r1, r2;
		static Scanner in = new Scanner(System.in);
		static int n=in.nextInt();		
		static double Btf;
		static double Ftf;
		static double Ltl;
		static double ld6;


		int randf,randl;
		Random rand = new Random();
		double randomNum1 = (float) (0 + (Math.random()*1));
		static String inhmm = "";
		
/**
 * First Roll uses transition probability distribution for Btf=0.5 from Hmm
 */
		public void firstRoll()
		{
			r1 =0 + (Math.random()*1);
			if (r1<Btf)
				FairRand();
			else
				LoadRand();		
		}//firstRoll

		/**
		 * For the next possible fair roll
		 */		
		public void NextFairRoll()
		{
			if (diceNum.size()<=n-1)
			{	
			r1 =0 + (Math.random()*1);
			if (r1<Ftf)
				FairRand();
			else
				LoadRand();	
			}//if
		}//NextFairRoll
		
		/**
		 * For the next possible loaded roll
		 */
		public void NextLoadedRoll()
		{
			if (diceNum.size()<=n-1)
			{	
			r1 =0 + (Math.random()*1);
			if (r1<Ltl)
				LoadRand();
			else
				FairRand();	
			}//if
		}//NextLoadedRoll
		
		/**
		 * Select Number between 1 to 6 which has fair probability 
		 */
		public void FairRand()
		{
			randf=rand.nextInt(6)+1;
			diceNum.add(randf);
			rolls.add("F");
			NextFairRoll();
		}//FairRand

		/**
		 * uses HMM model probability distribution for F
		 */		
		@SuppressWarnings("unused")
		public int Loadprob()
		{
//			System.out.println("******:"+ld6);

			double m=n*ld6;
			int divisions = (int)m/5;

			randl=rand.nextInt(100)+1;
			
			if (randl > m)
				randl=6;
			else 
				randl=rand.nextInt(5)+1;;
				
			return randl;
		}

		/**
		 * Number between 1 to 6 which has loaded probability
		 * uses Loadprob function to roll
		 */
		public void LoadRand()
		{
			DiceRoller obj1 = new DiceRoller();
			randl=obj1.Loadprob();

			diceNum.add(randl);
			rolls.add("L");
			NextLoadedRoll();		
		}//LoadRand

		/**
		 * Reads a file which consists of HMM 
		 */
		public String fileread() throws FileNotFoundException

		{
			File file = new File(inputFilehmm);
			Scanner scan = new Scanner(file);
			String input = "";

			while(scan.hasNextLine())
			{
			input =inhmm.concat(scan.nextLine());
			input.split(" ");
			}
			scan.close();
			
			return input;
		}//fileread

		/**
		 * Writes numbers between 1 to 6 
		 */
		public void filewrite() throws IOException
		{
			String output;
			FileWriter out = new FileWriter(outputFile);
						
			for (Integer str : diceNum)
			{
				output=str;
				//writes to the output file	
				out.write(output);			
			}
			out.close();

			String outputS;
			FileWriter outS = new FileWriter(outputFile_S);

			for (String str : rolls)
			{
				outputS=str;
				//writes to the output file	
				outS.write(outputS);			
			}
			outS.close();

			
		}//filewrite	
		

		@SuppressWarnings({ "unused" })
		public static void main(String[] args) throws IOException {
			DiceRoller obj = new DiceRoller();
			double [] Num = new double [6] ;

			double inDouble=0;
			
			for(int j=0;j<4;j++)
			{				
			Num[j]=Double.parseDouble(obj.fileread().split(" ")[j]);
			}
					
			Btf=Num[0];
			Ftf=Num[1];
			Ltl=Num[2];
			ld6=Num[3];

			obj.firstRoll();		

			//input takes in a line of numbers
			obj.fileread();
			obj.filewrite();		
			
			
//			System.out.println(obj.diceNum);
//			System.out.println(obj.rolls);
		
		}//main
	}//DiceRoller