/**This class will contain all the necessary  methods required by the class "Poker_Main."
 * 
 * @author: Sotheanith Sok, Kunal Patel, Carlos Hurtado, Simon Lee
 * 	
 *
 */
import java.util.*;
	public class Poker_Method {
	//Variable Control
	private ArrayList<String> deck_arraylist=new ArrayList<String>(); // The shuffled deck will store in here
	private ArrayList<String> player_hand=new ArrayList<String>();// Cards that player has
	private ArrayList<String> discard=new ArrayList<String>(); //Discarded card will save into here
	private ArrayList <Integer> value=new ArrayList<Integer>();
	double total;// The total of cash player has.
	double payout;
    double bet=500;
	
	/**This method is used to valid two input at the same time. It makes sure that player input the correct amount of money to bet for each play and the correct index for the card to remove from their hand.
	 * 
	 * @param bet= the amount of money player bet. It is remove from the total
	 * @param index= the position of card for which to be remove. Since player has 5 cards, the index will go from 0 to 4;
	 * @return if both inputs are valid, this method will return "true" else it will return "false."
	 */
   public void TEST(){
    	player_hand.add("Ace of Diamonds");
    	player_hand.add("10 of Diamonds");
    	player_hand.add("Jack of Diamonds");
    	player_hand.add("Queen of Diamonds");
    	player_hand.add("King of Diamonds");
    }
	public boolean input_validation (double bet1, int index){
		boolean checker=false;
		if (bet1>0 &&bet1 <=total){
			checker=true;
			total=total-bet1;
		}
		if (index>=0&&index<=4){
			checker=true;
		}
		if(bet1!=-5000)
		bet = bet1;
		return checker;
	}

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
	/**
	 * This method is used to return the balance;
	 * @return the player balance
	 */
	public double getBalance(){
		total+=payout;
		total=Math.round(total*1000);
		total=total/1000;
		return total;
	}
	
	/**
	 * This method is used to print player hand. 
	 */	
	public void printHand(){
		System.out.println("|**********************************************************************************************|");
		System.out.print("Your cards: (INDEX:CardType) ");
		for (int i=0;i<5;i++)
			System.out.print ((i+1)+" : "+player_hand.get(i)+"  ");
		System.out.println();
	}
	
	/**
	 * This method used to update the player hand.
	 * @param in is the index for the which to swap the card in player_hand and top cards of the deck.
	 */
	public void updateHand(ArrayList<Integer>index){
		for (int i=0;i<index.size();i++){
		deck_arraylist.add(player_hand.get(index.get(i)));
		player_hand.set(index.get(i),deck_arraylist.get(0));
		deck_arraylist.remove(0);}
	}
	
	/**
	 * This method will convert cards into integer value.
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
					break;
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
			if (type.equals("Ace"))
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
	
	/**
	 * This method is used to return payout;
	 * @return 
	 */
	public double returnPayout(){
		payout=Math.round(payout*1000);
		payout=payout/1000;
		return payout;
	}
	/**
	 * This method is called at the end of the turn in order to update player hand and make sure there is a card in the deck.
	 */
	public void NextTurn(){
		payout=0;
		//Update player hand
		for (int i=0;i<5;i++){
			discard.add(player_hand.get(i));
			player_hand.set(i,deck_arraylist.get(0));
			deck_arraylist.remove(0);
		}
		//Reshuffling the card when deck is low. 
		if (deck_arraylist.size()<=5){
			Collections.shuffle(discard);
			for (int i=0;i<discard.size();i++)
				deck_arraylist.add(discard.get(i));
			discard.clear();
		}
		value.clear();
		
	}
	/**
	 * This method is used to get the current balance
	 * @param balance
	 */
	public void innitialzing (double balance){
		total=balance;		
	}

   /**
    * This method is used to check for pairs.
    */
    public void OneAndTwoPairs()
    {  	ArrayList<Integer>temp=new ArrayList<Integer>();
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
    	if (occurence==1){
    		payout=bet*1.01;
    		System.out.println("Result: One pair detected.");}
    	if (occurence==2){
    		payout=bet*1.02;
    		System.out.println("Result: Two pairs detected.");}
    		
    }
    /**
     * This method is used to check for three of a kind.
     */
    public void ThreeOfAKind(){
    	ArrayList<Integer>temp=new ArrayList<Integer>();
    	for (int i=0;i<value.size();i++)
    	temp.add((value.get(i)%100));
    	Collections.sort(temp);
    	//Compare three values at the same time
    	for (int i=0;i<temp.size()-2;i++){
    		if ((temp.get(i)==temp.get(i+1))&&(temp.get(i+1)==temp.get(i+2))){
    			payout=bet*1.03;
    			System.out.println("Result: Three of a kind detected.");}
    	}
    		
    	
    }
    /**
     * This method is used to check for straight
     */
    public void Straight(){
    	ArrayList<Integer>temp=new ArrayList<Integer>();
    	for (int i=0;i<value.size();i++){
    		temp.add((value.get(i)%100));}
    	Collections.sort(temp);
    	int occurence=0;
    	
    	for (int i=0;i<temp.size()-1;i++){
    		if (temp.get(i)==temp.get(i+1)-1)
    			occurence++;
    	}
    	if (occurence==4){
    		payout=bet*1.04;
    		System.out.println("Result: Straight detected.");}
    	//Special case where straight is from 10 to Ace
    	if (temp.get(0)==10&&temp.get(2)==11&&temp.get(3)==12&&temp.get(4)==13&&temp.get(5)==1){
    		payout=bet*1.04;
    		System.out.println("Result: Straight detected.");}
    }
    /**
     * This method is used to check for flush
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
    	if (occurence==4){
    		payout=bet*1.05;    	
    		System.out.println("Result: Flush detected.");}
    }
    /**
     * This method is used to check for Fullhouse
     */
    public void FullHouse(){
    	
    	ArrayList<Integer>temp=new ArrayList<Integer>();
    	for (int i=0;i<value.size();i++)
    	temp.add((value.get(i)%100));
    	Collections.sort(temp);
    	boolean fullhouse=false;
    
    	if (temp.get(0)==temp.get(1)&&temp.get(1)==temp.get(2)){
    		if (temp.get(3)==temp.get(4))
    			fullhouse=true;
    		}
    	if (temp.get(2)==temp.get(3)&&temp.get(3)==temp.get(4)){
    		if (temp.get(0)==temp.get(1))
    			fullhouse=true;
    
    		}
    	if (fullhouse==true){
    		System.out.println("Result: FullHouse detected.");
    		payout=bet*1.06;}
    	
    }
    /**
     * This method is used to check for Four of a Kind.
     */
    public void fourOfAKind()
    {
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0; i < value.size(); i++)
        {
            temp.add(value.get(i)%100);
        }
        Collections.sort(temp);
        if(temp.get(0)==temp.get(1) && temp.get(1) == temp.get(2) && temp.get(2) == temp.get(3))
        {	System.out.println("Result: Four of a kind detected.");
            payout = bet * 1.25;
        }
        else if(temp.get(1)==temp.get(2) && temp.get(2) == temp.get(3) && temp.get(3) == temp.get(4))
        {	System.out.println("Result: Four of a kind detected.");
            payout = bet * 1.25;
        }
    }
    /**
     * This method is used to check for Straight Flush
     */
    public void straightFlush()
    {
        ArrayList<Integer> temp1 = new ArrayList<Integer>();
        ArrayList<Integer> temp2 = new ArrayList<Integer>();
        for(int i = 0; i < value.size(); i++)
        {
            temp1.add(value.get(i)/100);
            temp2.add(value.get(i)%100);
        }
        Collections.sort(temp1);
        Collections.sort(temp2);
        if((temp1.get(0)==temp1.get(1))&&(temp1.get(1)==temp1.get(2))&&(temp1.get(2)==temp1.get(3))&&(temp1.get(3)==temp1.get(4)))// same suit
        {
            if(temp2.get(0)==temp2.get(1)-1 && temp2.get(1) == temp2.get(2)-1 && temp2.get(2) == temp2.get(3)-1 && temp2.get(3) == temp2.get(4)-1)//consecutive from highest to lowest
            {	System.out.println("Result: Straight Flush detected.");
                payout = bet * 1.50;
            }
        }
    }
    
    /**
     *  This method is used to check for Royal Flush.
     */
    public void royalFlush()
    { 
    	ArrayList<Integer> temp = new ArrayList<>();
    for(int i = 0; i < value.size(); i++)
    {
        temp.add(value.get(i));
    }
    Collections.sort(temp);
        
        boolean royals = false;
        if(temp.get(0) == 101 && temp.get(1) == 110 && temp.get(2) == 111 && temp.get(3) == 112 && temp.get(4)==113)
        {
            royals = true;
        }
        else if(temp.get(0) == 201 && temp.get(1) == 210 && temp.get(2) == 211 && temp.get(3) == 212 && temp.get(4)==213)
        {
            royals = true;
        }
        else if(temp.get(0) == 301 && temp.get(1) == 310 && temp.get(2) == 311 && temp.get(3) == 312 && temp.get(4)==313)
        {
            royals = true;
        }
        else if(temp.get(0) == 401 && temp.get(1) == 410 && temp.get(2) == 411 && temp.get(3) == 412 && temp.get(4)==413)
        {
            royals = true;
        }
        if(royals)
        {	System.out.println("Result: Royal Flush detected.");
            payout = bet * 2.5;
        }
    }
    
}
