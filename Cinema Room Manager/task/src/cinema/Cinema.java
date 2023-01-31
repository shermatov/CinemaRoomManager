package cinema;

import java.util.Scanner;


public class Cinema {

    public static String[][] field;
    public static Scanner scanner = new Scanner(System.in);

    public static int numberOfBoughtTickets = 0;
    public static int income = 0;

    public static void main(String[] args) {
        //  Write your code here

        System.out.println("Enter the number of rows:");
        int number = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int number2 = scanner.nextInt();

        field = new String[number + 1][number2 + 1];
        createField();

        boolean flag = true;
        while(flag) {
            menu();
            int num = scanner.nextInt();
            System.out.println();
            switch (num) {
                case 0 -> flag = false;
                case 1 -> printField();
                case 2 -> placeAnOrder();
                case 3 -> statistics();
            }

        }
    }

    public static void createField() {
        for(int i = 0; i < field[0].length; i++) {
            field[0][i] = String.valueOf(i);
            if(i == 0) {
                field[0][i] = " ";
            }
        }
        for(int i = 1; i < field.length; i++) {
            for(int j = 1; j < field[0].length; j++) {
                field[i][j] = "S";

            }
            field[i][0] = String.valueOf(i);
        }
    }
    public static void printField() {
        System.out.println("Cinema:");

        for (String[] strings : field) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
        System.out.println();

    }
    public static void placeAnOrder() {
        System.out.println("Enter a row number:");
        int number = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int number2 = scanner.nextInt();

        if(field.length-1 < number || field[0].length-1 < number2) {
            System.out.println();
            System.out.println("Wrong input!");
            System.out.println();
            placeAnOrder();



        } else {
            if(!field[number][number2].equals("B")){
                field[number][number2] = "B";
                System.out.println();

                numberOfBoughtTickets++;

                if(field.length * field[0].length > 60) {
                    if(4 >= number) {
                        System.out.println("Ticket price: $10");
                        income += 10;
                    } else {
                        System.out.println("Ticket price: $8");
                        income += 8;
                    }
                } else {
                    System.out.println("Ticket price: $10");
                    income += 10;
                }

                System.out.println();
            } else {
                System.out.println();
                System.out.println("That ticket has already been purchased!\n");
                placeAnOrder();
            }



        }

    }

    public static void totalIncome(int number, int number2) {
        System.out.println("Total income:");
        int half = number / 2;
        if(number * number2 > 60) {
            System.out.println("$" + ((half * 10 * number2) + ((number - half)* 8 * number2)));
        } else
            System.out.println("$" + (number * number2 * 10));
    }

    public static int totalPrice(int number, int number2) {
        int half = (number-1) / 2;

        int full;
        if((number-1) * (number2-1) > 60) {
            full = ((half * 10 * (number2-1)) + (((number-1) - half)* 8 * (number2-1)));
        } else
            full = ((number-1) * (number2-1) * 10);

        return full;
    }

    public static void menu() {
        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
    }

    public static void statistics() {
        System.out.println("Number of purchased tickets: " + numberOfBoughtTickets);
        System.out.printf("Percentage: %.2f",numberOfBoughtTickets * 100.0 / ((field.length - 1) * (field[0].length - 1)));
        System.out.println("%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalPrice(field.length, field[0].length));
        System.out.println();
    }


}