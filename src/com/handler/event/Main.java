package com.handler.event;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Come si chiama l'evento?");
        String title = in.nextLine();
        boolean dataError = false;
        Event myEvent = null;

        /* --- EVENT CREATION --- */
        while(!dataError){ //to keep asking in case of wrong date
            System.out.println("Quando?");
            LocalDate date = setDate(in);
            System.out.println("Quanti posti ci sono?");
            int totSeats = in.nextInt();
            in.nextLine();
            try{
                myEvent = new Event(title, date, totSeats);
                dataError = true;
            }catch(DateTimeException e){
                System.out.println(e.getMessage());
                dataError = false;
            }
        }

        int reservationsNum = 0;
        boolean stop = false;

        /* --- EVENT RESERVATION --- */
        System.out.println("Vuoi prenotare per questo evento? y/n\n" + myEvent);
        while(!stop) { //to keep asking in case of wrong answ
            String answ = in.nextLine();
            if (answ.equalsIgnoreCase("y")) {
                while (answ.equalsIgnoreCase("y")) { //to keep asking in case of wrong answ at
                    if (answ.equalsIgnoreCase("y")) {
                        System.out.println("Quanti posti?");
                        reservationsNum = in.nextInt();
                        in.nextLine();
                        try {
//                            for (int i = reservationsNum; i > 0; i--) {
//                                myEvent.reserveSeat();
//                            }
                            myEvent.reserveSeats(reservationsNum);
                            printSeatsStatus(myEvent);
                            answ = "end";
                            stop = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage() + "Vuoi prenotarne di meno? y/n");
                            answ = in.nextLine();
                        }
                    } else if (!answ.equalsIgnoreCase("n")) {
                        System.out.println("inserisci 'y' = yes o 'n' = no per fare una scelta");
                    }
                }
            } else if (!answ.equalsIgnoreCase("n")) {
                System.out.println("inserisci 'y' = yes o 'n' = no per fare una scelta");
            }else{
                stop = true;
            }
        }


        /* --- EVENT CANCELLATION --- */
        stop = false;
        if(reservationsNum > 0){
            System.out.println("Vuoi disdire i posti prenotati?");
            while (!stop) { //to keep asking in case of wrong answ
                String answ = in.nextLine();
                if (answ.equalsIgnoreCase("y")) {
                    while (answ.equalsIgnoreCase("y")) { //to keep asking in case of wrong answ at
                        if (answ.equalsIgnoreCase("y")) {
                            System.out.println("Ne hai prenotati " + reservationsNum + "\nQuanti posti vuoi disdire?");
                            int seatsToCancel = in.nextInt();
                            in.nextLine();
                            if(seatsToCancel <= reservationsNum){
                                try {
                                    for (int i = seatsToCancel; i > 0; i--) {
                                        myEvent.cancelSeat();
                                    }
                                    printSeatsStatus(myEvent);
                                    answ = "end";
                                    stop = true;
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        } else if (!answ.equalsIgnoreCase("n")) {
                            System.out.println("inserisci 'y' = yes o 'n' = no per fare una scelta");
                        }
                    }
                } else if (!answ.equalsIgnoreCase("n")) {
                    System.out.println("inserisci 'y' = yes o 'n' = no per fare una scelta");
                } else {
                    stop = true;
                }
            }
        }


    }

    public static LocalDate setDate(Scanner in){
        while(true){
            System.out.print("Giorno:  ");
            int day = in.nextInt();
            in.nextLine();
            System.out.print("  Mese:  ");
            int month = in.nextInt();
            in.nextLine();
            System.out.print("  Anno:  ");
            int year = in.nextInt();
            in.nextLine();
            try {
                return LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                System.out.println("Inserisci dei dati validi: giorni da 1 a 31, mesi da 1 a 12");
            }
        }
    }

    static void printSeatsStatus(Event myEvent){
        System.out.printf("Sono stati prenotati %d posti. Ne rimangono %d\n", myEvent.getReservedSeats(), (myEvent.getTotSeats()- myEvent.getReservedSeats()));
    }
}
