
/**
 * Write a description of class HelperFunctions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HelperFunctions
{
    public static void clearScreen()
    {
        try
        {
            Runtime.getRuntime().exec("clear");
        } catch (Exception e)
        {
            // print five blank lines if clear screen fails
            System.out.println("\n\n\n\n");
        }
    }

    public static void clearScreenBlueJ()
    {
        System.out.print('\u000c');
    }
}
