import java.util.ArrayList;

/**
 * Robot Class is for defining the direction, distance traveled of the robot based on the 
 * commands passed. In addition, it calculates the minimum distance required for the robot 
 * to get back to the original position.
 * @author Rishabh Bhagat
 */
public class Robot
{
    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
    
    private int northUnits = 0;
    private int southUnits = 0;
    private int eastUnits = 0;
    private int westUnits = 0;
    /*For calculating the distance that the robot is required to get back to the original position.*/
    public void getMinimumUnits(ArrayList<String> inputCommands) {
        Direction currentDirection = Direction.NORTH;
        
        for (String eachCommand : inputCommands) {
            String[] inputArray = eachCommand.split(",");
            
            for (String eachInput : inputArray) {
                int currentUnits = 0;
                
                if (eachInput.charAt(0) == 'F') {
                    currentUnits = Character.getNumericValue(eachInput.charAt(1));
                    
                    switch (currentDirection) {
                        case NORTH: northUnits += currentUnits;
                            break;
                            
                        case SOUTH: southUnits += currentUnits;
                            break;
                            
                        case EAST: eastUnits += currentUnits;
                            break;
                            
                        case WEST: westUnits += currentUnits;
                            break;
                            
                        default: break;
                    }
                }
                else if (eachInput.charAt(0) == 'B') {
                    currentUnits = Character.getNumericValue(eachInput.charAt(1));
                    
                    switch (currentDirection) {
                        case NORTH: southUnits += currentUnits;
                            break;
                            
                        case SOUTH: northUnits += currentUnits;
                            break;
                            
                        case EAST: westUnits += currentUnits;
                            break;
                            
                        case WEST: eastUnits += currentUnits;
                            break;
                            
                        default: break;
                    }
                }
                else {
                    currentDirection = getCurrentDirection(eachInput, currentDirection);
                }
            }
            
            System.out.println("\nFor Command: " + eachCommand + " - Minimum Amount of distance to get back to the starting point: " 
                + (Math.abs(northUnits - southUnits) + Math.abs(eastUnits - westUnits)) + "\n");
            northUnits = southUnits = eastUnits = westUnits = 0;
        }
    }
    /* For defining the direction of the robot.*/
    private Direction getCurrentDirection(String input, Direction currentDirection) {
        if (input.charAt(0) == 'R') {
            Direction returnDirection = currentDirection;
            
            for (int index = 0; index < Character.getNumericValue(input.charAt(1)); index++) {
                switch (returnDirection) {
                    case NORTH: returnDirection = Direction.EAST;
                        break;
                        
                    case SOUTH: returnDirection = Direction.WEST;
                        break;
                        
                    case EAST: returnDirection = Direction.SOUTH;
                        break;
                        
                    case WEST: returnDirection = Direction.NORTH;
                        break;
                        
                    default: break;
                }
            }
            
            return returnDirection;
        }
        else {
            Direction returnDirection = currentDirection;
            
            for (int index = 0; index < Character.getNumericValue(input.charAt(1)); index++) {
                switch (returnDirection) {
                    case NORTH: returnDirection = Direction.WEST;
                        break;
                        
                    case SOUTH: returnDirection = Direction.EAST;
                        break;
                        
                    case EAST: returnDirection = Direction.NORTH;
                        break;
                        
                    case WEST: returnDirection = Direction.SOUTH;
                        break;
                        
                    default: break;
                }
            }
            
            return returnDirection;
        }
    }
}
