package efs.task.syntax;
import java.util.Scanner;
import java.util.Random;


// jakas zmiana halo

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
            if ( M<2 || M>UsefulConstants.MAX_UPPER_BOUND){
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
        if (proba>=1) {
            for (int i=0; i<proba; i++) {
                System.out.print('.');
            }
        }
        System.out.println(']');
    }
    public void play() {
        //TODO: Implement the method that executes the game session
        System.out.println("<1,"+M+">");    //wyswietlenie przedzialu
        int proby = (int) (Math.abs(Math.log(M) / Math.log(2))) + 1;    //wzor na ilosc prob
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);   // scannner
        int poprawna_liczba = random.nextInt(M)+1;
        while (proby>=0) {
            pasek(proby, licznik);  //wyswietlenie paska
            proby--;
            licznik++;
            System.out.println(UsefulConstants.GIVE_ME);
            input = scanner.nextLine();
            try {
                liczba = Integer.parseInt(input);
                //Nie mozna po prostu zrobic
                // liczba = scanner.nextInt(); ???

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
                proby=-1;
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
