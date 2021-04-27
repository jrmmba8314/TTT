import java.util.Arrays;

/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    private final int MAX_ROWS;
    private final int MAX_COLS;
    private Pieces[][] gameboard;

    public Board(int max_rows, int max_cols)
    {
        this.MAX_ROWS = max_rows;
        this.MAX_COLS = max_cols;

        gameboard = new Pieces[MAX_ROWS][MAX_COLS];
    }

    public int getMaxRows()
    {
        return MAX_ROWS;
    }

    public int getMaxCols()
    {
        return MAX_COLS;
    }

    public void printBoard()
    {
        printBoard("");
    }
    
    public void printBoard(String msg)
    {
        HelperFunctions.clearScreenBlueJ();

        String colHeader = "T  1 | 2 | 3 ";
        System.out.println(colHeader);
        System.out.println("-".repeat(colHeader.length()));
        for (int row=0; row < MAX_ROWS; row++)
        {
            String printStr = "";
            for (int col=0; col < MAX_COLS; col++)
            {
                char playerName;
                if (gameboard[row][col] == null)
                {
                    playerName = ' ';
                } else
                {
                    playerName = gameboard[row][col].getName();
                }
                printStr = printStr + "|" + " " + playerName + " ";
            }
            System.out.println((row + 1) + " " + printStr.substring(1));
            if (row < (MAX_ROWS - 1))
            {
                System.out.println("-".repeat(printStr.length() + 1));
            }
        }
        
        System.out.println("\n" + msg);
    }

    private boolean isSpaceFree(int row, int col)
    {
        if ((row >= MAX_ROWS) || (col >= MAX_COLS))
        {
            return false;
        }
        
        if (gameboard[row][col] == null)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean play(char player, int row, int col)
    {
        if (isSpaceFree(row, col))
        {
            gameboard[row][col] = new Pieces(player, row, col);
            Main.plays.add(gameboard[row][col]);
            return true;
        }
        {
            return false;
        }

    }

    public boolean autoPlay(char player)
    {
        boolean played = false;
        a:
        for (int row = 0; row < MAX_ROWS; row++)
        {
            for (int col = 0; col < MAX_COLS; col++)
            {
                if (play(player, row, col))
                {
                    played = true;
                    break a;
                }
            }
        }

        return played;
    }

    public boolean haveAWinner()
    {
        boolean winner = false;

        // winner in rows
        for (int row = 0; row < MAX_ROWS; row++)
        {
            if (!Arrays.stream(gameboard[row]).anyMatch(obj -> obj == null))
            {
                winner = Arrays.stream(gameboard[row]).map(x -> x.getName())
                .distinct().count() <= 1;
            }
        }

        // winner in cols
        if (!winner)
        {
            a:
            for (int col = 0; col < MAX_COLS; col++)
            {
                Pieces[] column = new Pieces[MAX_ROWS];
                for (int row = 0; row < MAX_ROWS; row++)
                {
                    column[row] = gameboard[row][col];
                }

                if (!Arrays.stream(column).anyMatch(obj -> obj == null))
                {
                    winner = Arrays.stream(column).map(x -> x.getName())
                    .distinct().count() <= 1;
                    if (winner)
                    {
                        break a;
                    }
                }
            }
        }    

        // winner in diagonals

        if (!winner)
        {
            Pieces[] diagonals = new Pieces[MAX_ROWS];
            for (int diag = 0; diag < MAX_ROWS; diag++)
            {
                diagonals[diag] = gameboard[diag][diag];
            }
            
            if (!Arrays.stream(diagonals).anyMatch(obj -> obj == null))
            {
                winner = Arrays.stream(diagonals).map(x -> x.getName())
                .distinct().count() <= 1;
            }

        }
        return winner;
    }
    
    public boolean spaceAvailable()
    {
        return Arrays.stream(gameboard).flatMap(x -> Arrays.stream(x)) .anyMatch(obj -> obj == null);
    }
}
