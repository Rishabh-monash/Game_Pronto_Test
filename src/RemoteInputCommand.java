import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * RobotInputCommand Class is for designing the interface for the game which provides the 
 *  "Display menu" to the user in order to play the game.  User can select different option to
 *  play or exit the game.
 * @author Rishabh Bhagat
 */
public class RobotInputCommand
{
    private static final String PATTERN = "([F,B,R,L][1-9],)+([F,B,R,L][1-9])$";
    
    //private Robot robot;
    private ArrayList<String> inputCommands;
    
    /**
     * Default Constructor
     */
    public RobotInputCommand() 
    {
    	//RobotInputCommand = new RobotInputCommand();
        //inputCommands = new ArrayList<String>();
    }
    
    /**
     * Method for Displaying Menu.
     */
    private void displayMenu()
    {
        System.out.println("**************** MENU ******************\n\n" + 
                           "A. Single Command Input\n" + 
                           "B. File Command Input\n" + 
                           "X. Close\n");
                           
        System.out.println("Please Enter Your Choice:");
    }
    /** Method for accepting the choice and allow the player to play or exit the game based on selection. */
    public void start() {
        System.out.println("*************** WELCOME ****************\n");
        Scanner console = new Scanner(System.in);
        
        boolean breakLoop = false;
        while (!breakLoop) {
            displayMenu();
            inputCommands.clear();
            String userChoice = console.nextLine();
            
            switch (userChoice) {
                case "a":
                case "A": getSingleRobotCommand();
                    break;
                    
                case "b":
                case "B": getFileRobotCommands();
                    break;
                    
                case "x":
                case "X": breakLoop = true;
                    System.out.println("\n************** THANK YOU **************");
                    break;
                    
                default: System.out.println("\nInvalid Option Entered...\n"); break;
            }
        }
    }
    /**Method for accepting the single robot command from user after selecting choice 'A' or 'a'.*/
    private void getSingleRobotCommand() {
        Scanner console = new Scanner(System.in);
        /*For filename path of the file is to be entered.
        *  For now the file path is src/Commands.txt*/
        System.out.println("\nPlease Enter Robot Command:");
        String robotCommand = console.nextLine();
        
        if (!robotCommand.matches(PATTERN)) {
            System.out.println("\nInvalid Robot Command Entered\n");
        }
        else {
           // inputCommands.add(robotCommand);
            //robot.getMinimumUnits(inputCommands);
        }
    }
    /*Method for accepting the file name containing command(s) from user after selecting choice 'B' or 'b' .*/
    private void getFileRobotCommands() {
        try
        {
            boolean invalidCommands = false;
            Scanner console = new Scanner(System.in);
            System.out.println("\nPlease Enter File Name:");
            String fileName = console.nextLine();
            
            FileReader fileReader = new FileReader(fileName);
            Scanner reader = new Scanner(fileReader);
            
            while (reader.hasNextLine())
            {
                String robotCommand = reader.nextLine();
                if (!robotCommand.matches(PATTERN)) {
                    invalidCommands = true;
                    System.out.println("\nInvalid Robot Command - " + robotCommand + "\n");
                    break;
                }
                else {
                    inputCommands.add(robotCommand);
                }
            }
            
            fileReader.close();
            if (!invalidCommands) {
               // robot.getMinimumUnits(inputCommands);
            }
        }
        catch (Exception ex)
        {
            System.out.println("\nInvalid File Name Entered\n");
        }
    }
    public static void main(String[] args)
    {
    	RobotInputCommand robotObj = new RobotInputCommand();
    	robotObj.start();
    
    }
}
