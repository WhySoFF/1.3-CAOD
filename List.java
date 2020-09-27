package PhoneStation;
import java.util.Scanner;

class List {

    private static Node root = null;
    static int SIZE = 0;

    static void addPerson(){
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        String [] arr = new String[4];
        for(int i = 0; i < 4; i++){
            arr[i] = "";
        }
        int j = 0;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == ' '){
                j++;
            }else{
                arr[j] += line.charAt(i);
            }
        }
        if(!isFullNameIncorrect(arr[0], arr[1], arr[2]) && !isNumberIncorrect(arr[3])){
            int number = Integer.parseInt(arr[3]);
            root = addNode(root, arr[0], arr[1], arr[2], number);
            SIZE++;
        }
    }

    static boolean isNumberIncorrect(String line){
        if(line.length() != 7){
            writeNumberError();
            return true;
        }
        try{
            Integer.parseInt(line);
        }catch(Exception e){
            writeNumberError();
            return true;
        }
        return false;
    }

    static boolean isFullNameIncorrect(String surname, String name, String patronymic){
        String line = surname + name + patronymic;
        for(int i = 0; i < line.length(); i++){
            if(!Character.isLetter(line.charAt(i))){
                writeFullNameError();
                return true;
            }
        }
        return false;
    }

    static Node addNode(Node current, String surname, String name, String patronymic, int number){
        if (current == null) {
            return new Node(surname, name, patronymic, number);
        }else{
            Node temp = new Node(surname, name, patronymic, number);
            if(current.next == null){
                Node compare = comparePersons(current, temp);
                if(compare == current){
                    current.next = temp;
                }else{
                    compare = current;
                    current = temp;
                    current.next = compare;
                }
            }else{
                Node compare = comparePersons(current, temp);
                if(compare == current){
                    current.next = addNode(current.next, surname, name, patronymic, number);
                }else{
                    compare = current.next;
                    Node help = current;
                    current = temp;
                    current.next = help;
                    help.next = compare;
                }
            }
        }
        return current;
    }

    private static Node comparePersons(Node first, Node second){
        String firstLine = first.surname + first.name + first.patronymic;
        String secondLine = second.surname + second.name + second.patronymic;
        int length = findSmallestLength(firstLine, secondLine);
        for(int i = 0; i < length; i++){
            if(firstLine.toLowerCase().charAt(i) < secondLine.toLowerCase().charAt(i)){
                return first;
            }
            if(firstLine.toLowerCase().charAt(i) > secondLine.toLowerCase().charAt(i)){
                return second;
            }
        }
        return first;
    }

    static int findSmallestLength(String first, String second){
        if(first.length() <= second.length()){
            return first.length();
        }
        return second.length();
    }

    static void printList(){

        System.out.println("\nPhoneStation\n");
        Node current = root;
        while(current.next != null){
            System.out.printf("%s\t%s\t%s\t%d\n", current.surname, current.name, current.patronymic, current.number);
            current = current.next;
        }
        System.out.printf("%s\t%s\t%s\t%d\n\n\n", current.surname, current.name, current.patronymic, current.number);
    }

    private static void writeNumberError(){
        System.out.println("Phone number is Incorrect");
    }

    private static void writeFullNameError(){
        System.out.println("Full Name is Incorrect");
    }

    static void findNumbersBySurname(String surname){
        while(isFullNameIncorrect(surname, "", "")){
            surname = scan();
        }
        Node current = root;
        int check = 0;
        while(current != null){
            if(current.surname.equals(surname)){
                System.out.printf("%s%s\t%s%d\n", "Surname - ", surname, "has a number - ", current.number);
                check++;
            }
            current = current.next;
        }
        if (check == 0){
            System.out.println("There is no such surname in the list");
        }
    }

    static void findSurnameByNumber(String line){
        while(isNumberIncorrect(line)){
            line = scan();
        }
        int number = Integer.parseInt(line);

        Node current = root;
        int check = 0;
        while(current != null && check == 0){
            if(number == current.number){
                System.out.printf("%s%d%s%s\n", "Number ", number, " has - ", current.surname);
                check++;
            }
            current = current.next;
        }
        if (check == 0){
            System.out.println("There is no surnames with this number");
        }
    }

    static String scan(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    static class Node {

        String surname;
        String name;
        String patronymic;
        int number;
        Node next;

        Node(String surname, String name, String patronymic, int number){

            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.number = number;
            next = null;
        }
    }
}
