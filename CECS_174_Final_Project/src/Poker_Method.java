/**This class will contain all the necessary  methods required by the class "Poker_Main."
 * 
 * @author:
 *
 */

import java.util.*;
	public class Poker_Method {
	/****************************************************************************************************/
	//Variable Control
	private ArrayList<String> deck=new ArrayList<String>(); // The shuffled deck will store in here
	private ArrayList<String> player_hand=new ArrayList<String>();// Cards that player has
	private ArrayList<String> discard=new ArrayList<String>(); //Discarded card will save into here
	double total;// The total of cash player has.
	/****************************************************************************************************/
	
	/**This method is used to valid two input at the same time. It makes sure that player input the correct amount of money to bet for each play and the correct index for the card to remove from their hand.
	 * 
	 * @param bet= the amount of money player bet. It is remove from the total
	 * @param index= the position of card for which to be remove. Since player has 5 cards, the index will go from 0 to 4;
	 * @return if both inputs are valid, this method will return "true" else it will return "false."
	 */
	public boolean input_validation (double bet, int index){
		boolean checker=false;
		if (bet>=1 &&bet <=total){
			checker=true;
			total-=bet;
		}
		if (index>=0&&index<=4){
			checker=true;
		}

		return checker;
	}
	/****************************************************************************************************/
	/**
	 * This method is used to create and shuffle a deck of cards.
	 */
	public void Creating_Deck(){
		String[] suit= {"Clubs", "Diamonds", "Hearts", "Spades"};
		String[] rank = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		
		int Suits= suit.length;
		int Ranks= rank.length;
		int I= Suits*Ranks;
		
		String[] deck = new String[I];
		for(int i=0; i<Ranks;i++){
			for(int j=0;j<Suits;j++){
				deck[Suits*i+j]= rank[i]+ " of "+ suit[j];
			}
		}
		for(int i=0;i<I;i++){
			int r= i+ (int)(Math.random()*(I-i));
			String t= deck[r];
			deck[r]= deck[i];
			deck[i]=t;
		}
		for(int i= 0; i<I;i++){
			System.out.println(deck[i]+", ");
		}
		return deck[i];
		
	}
	public void Shuffling_Card(){
		
		
	}
	/****************************************************************************************************/
	
	public void win_condition_1(){
		
	}
	
	
	

}
