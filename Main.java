package PhoneStation;

public class Main {

    public static void main(String []args){

        printTask();
        String line = List.scan();
        while(isLineIncorrect(line)){
            line = List.scan();
        }

        int N = Integer.parseInt(line);

        for(int i = 0; i < N; i++){
            printAddTask();
            List.addPerson();
            if(List.SIZE == i){
                i--;
            }
        }
        List.printList();
        printSwitchTasks();
        doTasks(List.scan());
    }

    static boolean isLineIncorrect(String line){

        try{
            Integer.parseInt(line);
        }catch(Exception e){
            System.out.println("It's not a number");
            return true;
        }
        return false;
    }

    static void printTask(){
        System.out.println("Enter number of people");
    }

    static void printAddTask(){
        System.out.println("Enter " + (List.SIZE + 1) + " people");
    }

    static void printSwitchTasks(){
        System.out.println("\nFor search surname by number enter  -  Surname");
        System.out.println("For search numbers by surname enter -  Number");
        System.out.println("For exit enter - EXIT");
    }

    static void printSwitchError(){
        System.out.println("Line is incorrect. Try again\n");
    }

    static void doTasks(String line){

        while(!line.equals("EXIT")) {

            switch (line) {
                case ("Surname"):
                    System.out.println("Enter a number");
                    List.findSurnameByNumber(List.scan());
                    break;
                case ("Number"):
                    System.out.println("Enter a surname");
                    List.findNumbersBySurname(List.scan());
                    break;
                default:
                    printSwitchError();
                    break;
            }
            printSwitchTasks();
            line = List.scan();
        }
    }
}
