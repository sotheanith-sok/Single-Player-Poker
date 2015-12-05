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
	 * 
	 */
	
	public void Shuffling_Card(){
		
		
	}
	/****************************************************************************************************/
	
	public void win_condition_1(){
		
	}
	
	
	

}
