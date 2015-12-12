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
    public void pairs()
    {
        int occurence = 0;
        ArrayList<String> holder = player_hand;
        ArrayList<Integer> num = new ArrayList<Integer>();
        for(int i = 0; i < holder.size(); i++)
        {
            for(int j = 0; j < holder.size(); j++)
            {
                if(j!=i)
                {
                    if(!player_hand.get(i).substring(0,2).equals("10") && player_hand.get(i).substring(0,1).equals(player_hand.get(j).substring(0,1)))
                    {
                        occurence++;
                        holder.remove(j);
                    }
                    else if(player_hand.get(i).substring(0,2).equals("10") && player_hand.get(j).substring(0,2).equals("10"))
                    {
                        occurence++;
                        holder.remove(j);
                    }
                }
            }
            if(occurence != 0)
            {
                num.add(occurence);
                occurence=0;
            }
            holder.remove(i);
        }
        if(num.size()==0)//no pair
        {
            payout = 0;
        }
        else if(num.size()==1) // only one pair, three of a kind, or four of a kind
        {
            if(num.get(0)==1)//one pair
            {
                payout = bet * 0.01;
            }
            else if(num.get(0)==2)//three of a kind
            {
                payout = bet * 0.03;
            }
            else if(num.get(0) == 3) // four of a kind
            {
                payout = bet * 0.25;
            }
        }
        else if(num.size() ==2)
        {
            if(num.get(0)==1 && num.get(1)==1) // two pairs
            {
                payout = bet * 0.02;
            }
            else if((num.get(0)==2 && num.get(1) ==1) || (num.get(0)==1 && num.get(1) ==2) )//full house
            {
                payout = bet * 0.06;
            }
        }
    }
    /**
     * 
     */
    public void royalFlush()
    {
        ArrayList<String> holder = player_hand;
        ArrayList<String> suitHolder = new ArrayList<String>();
        String suitName = "";
        int diamonds = 0;
        int spades = 0;
        int clubs = 0;
        int hearts = 0;
        boolean royal = false;
        for(int i = 0; i < holder.size(); i++)
        {
            if(!holder.get(i).substring(0,2).equals("10") || !holder.get(i).substring(0,3).equals("Ace") || !holder.get(i).substring(0,4).equals("Jack") 
                || !holder.get(i).substring(0,4).equals("King") || !holder.get(i).substring(0,5).equals("Queen"))
            {
                break;
            }
            else 
            {
                royal = true;
                for(int j = 0; j < holder.get(i).length(); j++)
                {
                    if(holder.get(i).substring(j,j+1).equals("D"))
                    {
                        diamonds++;
                    }
                    else if(holder.get(i).substring(j,j+1).equals("S"))
                    {
                        spades++;
                    }
                    else if(holder.get(i).substring(j,j+1).equals("C"))
                    {
                        clubs++;
                    }
                    else
                    {
                        hearts++;
                    }
                }
            }
        }
        if(royal && (diamonds == 5 || spades ==5 || clubs == 5|| hearts == 5))
        {
            payout = bet * 2.5;
        }
    }
    /**
     * 
     */
    public void flush()
    {
        ArrayList<String> holder = player_hand;
        int diamonds = 0;
        int spades = 0;
        int clubs = 0;
        int hearts = 0;
        for(int i = 0; i < holder.size(); i++)
        {
            for(int j = 0; j < holder.get(i).length(); j++)
            {
                if(holder.get(i).substring(j,j+1).equals("D"))
                    {
                        diamonds++;
                    }
                    else if(holder.get(i).substring(j,j+1).equals("S"))
                    {
                        spades++;
                    }
                    else if(holder.get(i).substring(j,j+1).equals("C"))
                    {
                        clubs++;
                    }
                    else
                    {
                        hearts++;
                    }
            }
        }
        if(diamonds == 5 || spades ==5 || clubs == 5|| hearts == 5)
        {
            payout = bet * 0.05;
        }
    }
	

}
