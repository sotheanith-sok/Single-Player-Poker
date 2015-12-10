/** 
 * This will be the main class of the poker game. 
 */

import java.util.*;
public class Poker_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Variable control;
		double bet; //The amount player bet
		double payout; //The amount player payout
		String Index;
		boolean checker=false; //Check input
		ArrayList<Integer> index=new ArrayList<Integer>(); //This array will be used to save index chosen by player of the card to be remove. 
		Scanner in=new Scanner(System.in);
		Poker_Method method=new Poker_Method ();
		
		
		//Create deck 
		method.Creating_Deck();
		//Copy to player hand
		method.player_hand();

		
		//Begin
		
		while (method.getBalance()>0){
			//Bet validation
			System.out.println("Please input your bet.");
			if (in.hasNextDouble()){
				bet=in.nextDouble();
				checker=method.input_validation(bet,-5);	
				if (checker==false){
					System.out.println("Input Error.");
					System.exit(1);}
				}
			else{
				System.out.println("Input Error.");
				System.exit(1);	}
			method.printHand();
			
			//Index validation
			System.out.println("Please input the index of cards that you do not want. (Ex: 1,2,3,4) ");
			System.out.println("	Enter -None- if you do not wish to remove any card. ");
			System.out.println("	Enter -All- if you do  wish to remove all of the card. ");
			Index=in.next();
			checker=false;
			if (Index.equals("All")||Index.equals("None")){
				if (Index.equals("All")){
						for (int i=0;i<5;i++)
							index.add((i));}
				}
			else{
				for (int i=0;i<Index.length();i++){
					
					if (Character.isDigit(Index.charAt(i))){
					if(Integer.parseInt(Index.substring(i,i+1))>5){
						System.out.println("Input Error.");
						System.exit(1);	}
						
					checker=method.input_validation(0,(Integer.parseInt(Index.substring(i,i+1)))-1);
					
						if(checker==true){
							index.add((Integer.parseInt(Index.substring(i,i+1)))-1);
							checker=false;
						}
					}
				}
				
			}
			//Update hand. 
			for (int i=0;i<index.size();i++){
				method.updateHand(index);
			}
			method.printHand();
			
			//Win Condition
			System.out.println("Payout:" +method.win_condition());
			System.out.println("Current balance: "+method.getBalance());
			
			//Ready for new turn
			method.NextTurn();
			
			
			
			
				
				
			
			
		
				
			
		
	
	
	
		}
		
		
		
		
	}

}
