import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static List<Pieces> plays = new ArrayList<>();

    public static void main(String[] args)
    {
        char player1 = 'X';
        char player2 = 'Y';
        Scanner in = new Scanner(System.in);

        Board gameBoard = new Board(3, 3);

        // making plays
        char turn = player1;
        boolean stop = false;

        String winner = "";
        gameBoard.printBoard();
        while (!stop)
        {
            boolean goodPlay = false;
            if (turn == player1)
            {
                // make sure a play is available
                if (gameBoard.spaceAvailable())
                {                
                    // make a play
                    do 
                    {
                        System.out.print("Please enter row col:" );
                        int row = in.nextInt();
                        int col = in.nextInt();

                        goodPlay = gameBoard.play(turn, row - 1, col - 1);
                    } while (!goodPlay);
                } else
                {
                    goodPlay = false;
                }
            } else
            {
                goodPlay = gameBoard.autoPlay(turn);
            }

            if (goodPlay)
            {
                // autoplay successful. check for winner
                if (gameBoard.haveAWinner())
                {
                    // we have a winner so stop playing
                    winner = "Player " + turn + " is our winner!!!";
                    stop = true;
                } else
                {
                    // we don't have a winner so switch players
                    turn = (turn == player1) ? player2 : player1;
                }
            } else
            {
                // a player could not make a play
                winner = "We do not have a winner :-(";
                stop = true;
            }

            gameBoard.printBoard();
            
            try
            {
                Thread.sleep(1000);
            } catch (Exception e)
            {
                
            }
        }

        gameBoard.printBoard(winner);
        
        System.out.println("\n*** Plays Made ***");
        plays.forEach(x -> System.out.println(x));
    }
}
