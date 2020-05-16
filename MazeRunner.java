import java.util.Scanner;

public class MazeRunner {
    Maze myMap = new Maze();
    public static void main(String[] args){
        MazeRunner mazeRunner = new MazeRunner();
        //intro method to welcome user to the game and print initial maze
        mazeRunner.intro();
        int moves=0;
        //create boolean winStatus that indicates whether the user has solved the maze
        boolean winStatus=mazeRunner.myMap.didIWin();
        while(winStatus!=true) {
            //run userMove method, save output in String direction for debugging purposes
            String direction=mazeRunner.userMove();
            //after a successful move, increase move counter by 1 and print map
            moves++;
            mazeRunner.myMap.printMap();
            movesMessage(moves);
            //evaluate whether the user has won the game, used in while loop
            winStatus=mazeRunner.myMap.didIWin();
        }
        if(winStatus) {
            System.out.println("Congratulations, you made it out alive!");
            System.out.println("and you did it in " + moves + "moves");
            winStatus=mazeRunner.myMap.didIWin();
        }
    }

    //this is an introduction to the game 
    public void intro(){
        System.out.println("Welcome to Maze Runner! Here is your current position: ");
        myMap.printMap();
    }

    //this method takes an input value from the user to make moves in the maze
    public String userMove() {
        Scanner in = new Scanner(System.in);
        String myDirection = "";
        System.out.print("Where would you like to move? (R, L U, D) ");
        String direction = in.nextLine();
        String invalidDirection = "Sorry, you've hit a wall.";

        if (direction.equalsIgnoreCase("R")) {
            myDirection = "Right";
            if (myMap.canIMoveRight()) {
                myMap.moveRight();
                return myDirection;
            }  else {
                if (myMap.isThereAPit("R")) {
                    navigatePit("R");
                    return myDirection;
                } else {
                    System.out.println(invalidDirection);
                    return userMove();
                }
            }
        } else if (direction.equalsIgnoreCase("L")) {
            myDirection="Left";
            if (myMap.canIMoveLeft()) {
                myMap.moveLeft();
                return myDirection;
            } else {
                if (myMap.isThereAPit("L")) {
                    navigatePit("L");
                    return myDirection;
                } else {
                    System.out.println(invalidDirection);
                    return userMove();
                }
            }
        } else if (direction.equalsIgnoreCase("U")) {
            myDirection = "Up";
            if (myMap.canIMoveUp()) {
                myMap.moveUp();
                return myDirection;
            } else {
                if (myMap.isThereAPit("U")) {
                    navigatePit("U");
                    return myDirection;
                } else {
                    System.out.println(invalidDirection);
                    return userMove();
                }
            }
        } else if (direction.equalsIgnoreCase("D")) {
            myDirection = "Down";
            if (myMap.canIMoveDown()) {
                myMap.moveDown();
                return myDirection;
            } else {
                if (myMap.isThereAPit("D")) {
                    navigatePit("D");
                    return myDirection;
                } else {
                    System.out.println(invalidDirection);
                    return userMove();
                }
            }
        } else {
            System.out.println("Invalid selection");
            return userMove();
        }
    }

    //this method creates messages for certain milestones in the maze
    public static void movesMessage(int moves){
        if(moves==50){
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        } else if(moves==75){
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if(moves==90){
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        } else if(moves==100){
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
            System.out.println("Sorry, but you didn't escape in time- you lose!");
            System.exit(0);
        }
    }

    //this helps to navigate pits using the given jumpOverPit method
    public void navigatePit(String dir) {
        Scanner in = new Scanner(System.in);
        System.out.print("Watch out! There is a pit ahead. Jump it? ");
        String decision=in.nextLine();
        if(decision.startsWith("y")||decision.startsWith("Y")) {
            myMap.jumpOverPit(dir);
        } else {
            navigatePit(dir);
        }
    }
}

