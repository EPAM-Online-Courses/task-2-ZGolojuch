package efs.task.syntax;
import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {
    public int M;
    public int liczba;
    public boolean zgadles = false;
    public String input;
    public int licznik = 1;


    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        // zmienic string na liczbe + dodac wyjatki (za duza liczba, za mała lub nie liczba)

        try {
            M = Integer.parseInt(argument);
            if ( M<1 || M>UsefulConstants.MAX_UPPER_BOUND){
                System.out.println(UsefulConstants.WRONG_ARGUMENT); // lub za duza za mala, nie wiem
                throw new IllegalArgumentException();
            }

        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT); // lub NOT_A_NUMBER
            throw new IllegalArgumentException();
        }
    }

    public void pasek(int proba, int licznik) {
        System.out.print('[');
        for (int i=0; i<licznik; i++) {
            System.out.print('*');
        }
        for (int i=0; i<proba-1; i++) {
            System.out.print('.');
        }
        System.out.println(']');
    }

    public void play() {
        //TODO: Implement the method that executes the game session
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);   // scannner

        int proba = (int) (Math.abs(Math.log(M) / Math.log(2))) + 1;    //wzor na ilosc prob
        int poprawna_liczba = random.nextInt(M)+1;

        System.out.println("<1,"+M+">");    //wyswietlenie przedzialu

        while (proba>0) {
            pasek(proba, licznik);  //wyswietlenie paska

            proba--;
            licznik++;

            System.out.println(UsefulConstants.GIVE_ME);
            input = scanner.nextLine();

            try {
                liczba = Integer.parseInt(input);

            } catch (NumberFormatException e){
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                continue;
            }

            if (liczba>poprawna_liczba){
                System.out.println(UsefulConstants.TO_MUCH);
            }
            if (liczba<poprawna_liczba){
                System.out.println(UsefulConstants.TO_LESS);
            }
            if (liczba==poprawna_liczba){
                System.out.println(UsefulConstants.YES);
                proba=-1;
                zgadles = true;
            }
        }

        if (zgadles){
            System.out.println(UsefulConstants.CONGRATULATIONS);
        }
        else {
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }
    }
}
