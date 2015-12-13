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
	private ArrayList <Integer> value=new ArrayList<Integer>();
	double total=100;// The total of cash player has.
	double payout= 0;
    double bet;
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
		this.bet = bet;
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
	 * This method will convert card into actual value.
	 */
	public void  convertValue(){
		for (int line=0;line<5;line++){
			String sentence=player_hand.get(line);
			int j=0;
			String type=null;
			String color=null;
			for (int i=0;i<sentence.length();i++){
				if (Character.isWhitespace(sentence.charAt(i))){
					type=sentence.substring(0,i);
					color=sentence.substring(i+4);
				}
			}
			if (color.equals("Clubs")) //"Clubs", "Diamonds", "Hearts", "Spades"
				j+=100;
			if (color.equals("Diamonds"))
				j+=200;
			if (color.equals("Hearts"))
				j+=300;
			if (color.equals("Spades"))
				j+=400;
			if (type.equals("Aces"))
				j+=1;
			if (type.equals("2"))
				j+=2;
			if (type.equals("3"))
				j+=3;
			if (type.equals("4"))
				j+=4;
			if (type.equals("5"))
				j+=5;
			if (type.equals("6"))
				j+=6;
			if (type.equals("7"))
				j+=7;
			if (type.equals("8"))
				j+=8;
			if (type.equals("9"))
				j+=9;
			if (type.equals("10"))
				j+=10;
			if (type.equals("Jack"))
				j+=11;
			if (type.equals("Queen"))
				j+=12;
			if (type.equals("King"))
				j+=13;
			value.add(j);
			
			
		}
		
	
	}
	/****************************************************************************************************/
	/**
	 * Retrun payout
	 * @return 
	 */
	public double returnPayout(){
		return payout;
	}
	/****************************************************************************************************/
	/**
	 * Call at the end to copy hand into discard pile and update hand.
	 */
	public void NextTurn(){

		for (int i=0;i<5;i++){
			discard.add(player_hand.get(i));
			player_hand.set(i,deck_arraylist.get(0));
			deck_arraylist.remove(0);
		}
	}
	/****************************************************************************************************/
     /**
      *  
     */
    public void OneAndTwoPairs()
    {	ArrayList<Integer>temp=new ArrayList<Integer>();
    	int occurence=0;
    	for (int i=0;i<value.size();i++)
    	temp.add((value.get(i)%100));
    	Collections.sort(temp);
    	for (int i=0;i<temp.size()-1;){
    		//Pair is found
    		if (temp.get(i)==temp.get((i+1))){
    			occurence++;
    			i+=2;}
    		//Pair not found
    		else
    			i+=1;
    	}
    	if (occurence==1)
    		payout=bet*0.01;
    	if (occurence==2)
    		payout=bet*0.02;
    		
    }
    /**
     * 
     */
    public void ThreeOfAKind(){
    	ArrayList<Integer>temp=new ArrayList<Integer>();
    	for (int i=0;i<value.size();i++)
    	temp.add((value.get(i)%100));
    	Collections.sort(temp);
    	//Compare three values at the same time
    	for (int i=0;i<temp.size()-2;i++){
    		if ((temp.get(i)==temp.get(i+1))&&(temp.get(i+1)==temp.get(i+2)))
    			payout=bet*0.03;
    	}
    		
    	
    }
    /**
     * 
     */
    public void Straight(){
    	ArrayList<Integer>temp=new ArrayList<Integer>();
    	for (int i=0;i<value.size();i++)
    	temp.add((value.get(i)%100));
    	Collections.sort(temp);
    	int occurence=0;
    	for (int i=0;i<temp.size()-1;i++){
    		if (temp.get(i)==temp.get((i+1)))
    				occurence++;
    	}
    	if (occurence==4)
    		payout=bet*0.04;
    	//Special case where straight is from 10 to Ace
    	if (temp.get(0)==10&&temp.get(2)==11&&temp.get(3)==12&&temp.get(4)==13&&temp.get(5)==1)
    		payout=bet*0.04;
    	
    }
    /**
     * 
     */
    public void flush(){
    	ArrayList<Integer>temp=new ArrayList<Integer>();
    	for (int i=0;i<value.size();i++)
    	temp.add((value.get(i)/100));
    	Collections.sort(temp);
    	int occurence=0;
    	for (int i=0;i<temp.size()-1;i++){
    		if (temp.get(i)==temp.get(i+1))
    			occurence++;
    	}
    	if (occurence==4)
    		payout=bet*0.05;    	
    }
    /**
     * 
     */
    public void FullHouse(){
    	ArrayList<Integer>temp=new ArrayList<Integer>();
    	for (int i=0;i<value.size();i++)
    	temp.add((value.get(i)%100));
    	Collections.sort(temp);
    	int pair=0;
    	int triple=0;
    	for (int i=0;i<temp.size()-1;){
    		if (temp.get(i)==temp.get(i+1)){
    			if (temp.get(i+1)==temp.get(i+2)){
    				triple++;
    				i=i+3;}
    			else{
    				pair++;
    				i=2;}
    			
    		}
    		else
    			break;
    	}
    	if (pair==1&&triple==1)
    		payout=bet*0.06;
    		
    }
    /**
     *  //2 500  43 230
     */
    public void royalFlush()
    {
        convertValue();
        Collections.sort(value);
        boolean royals = false;
        if(value.get(0) == 101 && value.get(1) == 110 && value.get(2) == 111 && value.get(3) == 112 && value.get(4)==113)
        {
            royals = true;
        }
        else if(value.get(0) == 201 && value.get(1) == 210 && value.get(2) == 211 && value.get(3) == 212 && value.get(4)==213)
        {
            royals = true;
        }
        else if(value.get(0) == 301 && value.get(1) == 310 && value.get(2) == 311 && value.get(3) == 312 && value.get(4)==313)
        {
            royals = true;
        }
        else if(value.get(0) == 401 && value.get(1) == 410 && value.get(2) == 411 && value.get(3) == 412 && value.get(4)==413)
        {
            royals = true;
        }
        if(royals)
        {
            payout = bet * 2.5;
        }
        value.clear();
    }
   
	

}
