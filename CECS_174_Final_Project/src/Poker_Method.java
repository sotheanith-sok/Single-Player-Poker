/**This class will contain all the necessary  methods required by the class "Poker_Main."
 * 
 * @author:
 *
 */

import java.util.*;
	public class Poker_Method {
	/****************************************************************************************************/
	//Variable Control
	private ArrayList<String> deck_arraylist=new ArrayList<String>(); // The shuffled deck will store in here
	private ArrayList<String> player_hand=new ArrayList<String>();// Cards that player has
	private ArrayList<String> discard=new ArrayList<String>(); //Discarded card will save into here
	double total=100;// The total of cash player has.
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
		for (int i=0;i<52;i++)
			deck_arraylist.add(deck[i]);
	}
	/****************************************************************************************************/
	/**
	 * This method is used to randomize player hand at the beginning of the turn
	 */
	
	public  void player_hand(  )//deals 5 random cards to player.
	{
	 // ArrayList<String> hand = new ArrayList<String>(5);
	  Random rc = new Random();
	  for ( int j = 0; j < 5; j++)
	      player_hand.add(deck_arraylist.remove(rc.nextInt(deck_arraylist.size())));
	}
	/****************************************************************************************************/
	/**
	 * 
	 * @return the player balance
	 */
	public double getBalance(){
		return total;
	}
	/****************************************************************************************************/
	/**
	 * Print out the player hand
	 */
	public void printHand(){
		System.out.println("|**********************************************************************************************|");
		System.out.print("Your cards: (INDEX:CardType) ");
		for (int i=0;i<5;i++)
			System.out.print ((i+1)+" : "+player_hand.get(i)+"  ");
		System.out.println();
	}
	
	/****************************************************************************************************/
	/**
	 * This method used to update the player hand.
	 * @param in is the index for the which to swap player_hand and deck
	 */
	public void updateHand(ArrayList<Integer>index){
		for (int i=0;i<index.size();i++){
		deck_arraylist.add(player_hand.get(index.get(i)));
		player_hand.set(index.get(i),deck_arraylist.get(0));
		deck_arraylist.remove(0);}
	}
	
	/****************************************************************************************************/
	/**
	 * This method should be the win condition. It will return payout and update balance.
	 */
	public double win_condition(){
		
	return payout;	
	}
	/****************************************************************************************************/
	public void NextTurn(){

		for (int i=0;i<5;i++){
			discard.add(player_hand.get(i));
			player_hand.set(i,deck_arraylist.get(0));
			deck_arraylist.remove(0);
		}
	}
	
	

}
