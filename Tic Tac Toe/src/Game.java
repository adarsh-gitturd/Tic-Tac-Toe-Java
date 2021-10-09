import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	//second branch
	
	private static String[][] gameBoard = {
			{"| "," "," | "," "," | "," "," |"},
			{"| "," "," | "," "," | "," "," |"},
			{"| "," "," | "," "," | "," "," |"}
	};
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Integer> spotsFilled;
	private static boolean gameOver = false;
	private static int gameMode = 0;
	
	public static void main(String[] args){
		init();
		
		while(gameOver == false){
			if(gameMode == 1){
				playersMove(0);
				checkGameStatus();
				computersMove();		
				checkGameStatus();
			}
			if(gameMode == 2){
				playersMove(0);
				//computersMoveAI();		
				//checkGameStatus();
			}
			if(gameMode == 3){
				playersMove(1);
				checkGameStatus();
				playersMove(2);
				checkGameStatus();
			}
		}
	}
	private static void init(){
		spotsFilled = new ArrayList<Integer>();
		print("1:Player vs PC\n");
		print("2:Player vs PC(AI)\n");
		print("3:Player vs Player\n");
		print("Enter your choice : ");
		gameMode = sc.nextInt();
	}
	
 	private static void displayBoard(String[][] board){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				print(board[i][j] + "");
			}
			print("\n");
		}
	}
	
	private static void placePiece(int a, String turn){
		switch(a){
			case 1: gameBoard[0][1] = turn; break;
			case 2: gameBoard[0][3] = turn; break;
			case 3: gameBoard[0][5] = turn; break;
			case 4: gameBoard[1][1] = turn; break;
			case 5: gameBoard[1][3] = turn; break;
			case 6: gameBoard[1][5] = turn; break;
			case 7: gameBoard[2][1] = turn; break;
			case 8: gameBoard[2][3] = turn; break;
			case 9: gameBoard[2][5] = turn; break;
			case 10: gameBoard[1][9] = turn; break;
			default: break;
		}
	}
	
	private static int generateRandomNumber(int min, int max){
		double d = Math.random()*(max-min) + min; 
		return (int)d;
	}
	
	private static void playersMove(int p){
		if(p == 0){
			print("Enter the position : ");
			int c = sc.nextInt();
			spotsFilled.add(c);
			placePiece(c, "X");
			if(!gameOver){displayBoard(gameBoard);}
			print("-------------\n");
		}
		else{
			print("Enter Player " + p + " postion :");
			int c = sc.nextInt();
			spotsFilled.add(c);
			if(p == 1){placePiece(c, "X");}
			if(p == 2){placePiece(c, "O");}
			if(!gameOver){displayBoard(gameBoard);}
			print("-------------\n");
		}
	}
	
	private static void computersMove(){
		int q = generateRandomNumber(1, 10);
		boolean dup = false;
		
		for(Integer x : spotsFilled){
			if(q == x){
				dup = true;
			}
		}	
		if(dup){                    
			while(dup){ // repeat until dup = false
				q = generateRandomNumber(1, 10);
				dup = false;
				for(Integer x : spotsFilled){ 
					if(q == x){
						dup = true;
					}
				}
			}
		}
		if(!dup){	
			spotsFilled.add(q);
			placePiece(q, "O");
			if(!gameOver){displayBoard(gameBoard);}
		}

	}
	
	private static void checkGameStatus(){
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard[i].length; j++){
				if(gameBoard[i][1]==gameBoard[i][3] && gameBoard[i][3]==gameBoard[i][5] && gameBoard[i][3] != " "){
					gameOver = true;
					String w = gameBoard[i][1];
					checkWinner(w);
					break;
				}
				if(j == 1 || j == 3 || j == 5){
					if(gameBoard[0][j] == gameBoard[1][j] && gameBoard[1][j] == gameBoard[2][j] && gameBoard[2][j] != " "){
						gameOver = true;
						String w = gameBoard[0][j];
						checkWinner(w);
						break;
					}
				}
				if(gameBoard[0][1] == gameBoard[1][3] && gameBoard[1][3] == gameBoard[2][5] && gameBoard[2][5] != " "){
					gameOver = true;
					String w = gameBoard[0][1];
					checkWinner(w);
					break;
				}
				if(gameBoard[0][5] == gameBoard[1][3] && gameBoard[1][3] == gameBoard[2][1] && gameBoard[2][1] != " "){
					gameOver = true;
					String w = gameBoard[0][5];
					checkWinner(w);
					break;
				}
			}
		}
		if(gameOver != true && spotsFilled.size() == 9){
			print("TIE!\n");
			gameOver = true;
			spotsFilled.clear();
		}
	}
	
	private static void checkWinner(String w){
		if(gameMode == 1){
			if(w == "X"){
				print("PLAYER WINS");
				spotsFilled.clear();
			}
			if(w == "O"){
				print("COMPUTER WINS");
				spotsFilled.clear();
			}
		}
		if(gameMode == 3){
			if(w == "X"){
				print("Player 1 wins!");
				spotsFilled.clear();
			}
			if(w == "O"){
				print("Player 2 wins!");
				spotsFilled.clear();
			}
		}
		
	}
	
  	private static void print(String a){
		System.out.print(a);
	}
}
