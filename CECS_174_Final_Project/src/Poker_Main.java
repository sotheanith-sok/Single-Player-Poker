/** 
 * This will be the main class of the poker game. 
 */

import java.util.*;
public class Poker_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Variable control;
		double bet=0; //The amount player bet
		String Index;
		boolean checker=false; //Check input
		
		ArrayList<Integer> index=new ArrayList<Integer>(); //This array will be used to save index chosen by player of the card to be remove. 
		Scanner in=new Scanner(System.in);
		Poker_Method method=new Poker_Method ();
		boolean continues=true;
		System.out.println("Your innitial balance will bet : $5000" );
		method.innitialzing(5000);
		
		
		//Create deck 
		method.Creating_Deck();
		//Copy to player hand
		//method.player_hand();
		//Begin
	
		while (method.getBalance()>0&&continues==true){
			method.TEST();
			//Bet validation
			/*System.out.print("Please input your bet: ");
			if (in.hasNextDouble()){
				bet=in.nextDouble();
				checker=method.input_validation(bet,-5);	
				if (checker==false){
					System.out.println("Input Error.");
					System.exit(1);}
				}
			else{
				System.out.println("Input Error.");
				System.exit(1);	}*/
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
						
					checker=method.input_validation(-5000,(Integer.parseInt(Index.substring(i,i+1)))-1);
					
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
			int count=0;
			method.printHand();
			method.convertValue();
			//Call all the win condition method. 
			if(method.returnPayout()==0){
				count++;				
				method.royalFlush();}
		
			if(method.returnPayout()==0){
				count++;				
				method.straightFlush();}
			
			if(method.returnPayout()==0){
				count++;				
				method.fourOfAKind();}
			
			if(method.returnPayout()==0){
				count++;
				method.FullHouse();}
			if(method.returnPayout()==0){
				count++;				
				method.flush();}
			
			if(method.returnPayout()==0){
				count++;			
				method.Straight();}	
			
			if(method.returnPayout()==0){
				count++;				
				method.ThreeOfAKind();}			
			
			if(method.returnPayout()==0){
				count++;				
				method.OneAndTwoPairs();}
			
			//Display win amount and balance.
			if (count==8&&method.returnPayout()==0)
				System.out.println("No parttern dectected. You lost: $"+bet);
			else
				System.out.println("Payout: $" +method.returnPayout());
			System.out.println("Current balance: $"+method.getBalance());
		
			//Ready for new turn
			method.NextTurn();
			if (method.getBalance()<0.001){
				System.out.println("You run out of money!!");
				System.out.println("Thank you for playing.");
				System.exit(1);
			}
			System.out.println("Do you wish to continue? Y/N ");
			String answer=in.next();
			index.clear();
			if (answer.equals("N")){
				System.out.println("Final balance: $"+method.getBalance());
				System.out.println("Thank you for playing.");
				continues=false;
				
			}
			
		}
	
	}

}
