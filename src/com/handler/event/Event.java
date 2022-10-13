package com.handler.event;

import org.w3c.dom.ls.LSOutput;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private int totSeats;
    private int reservedSeats = 0;

    public Event(String title, LocalDate date, int totSeats) {
        checkNotElapsed(date);
        seatsNumIsPositive(totSeats);
        this.title = title;
        this.date = date;
        this.totSeats = totSeats;
    }

    protected void checkNotElapsed(LocalDate date) throws DateTimeException {
        if(LocalDate.now().isAfter(date))
            throw new DateTimeException("Non puoi creare un evento nel passato!\nLa data di partenza è antecedente a oggi");
    }
    protected void seatsNumIsPositive(int totSeats) throws IllegalArgumentException {
        if(totSeats <= 0){
            throw new IllegalArgumentException("Dev'esserci per forza almeno un posto, se no che evento è??");
        }
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        checkNotElapsed(date);
        this.date = date;
    }
    public int getTotSeats() {
        return totSeats;
    }
    public int getReservedSeats() {
        return reservedSeats;
    }

    public void reserveSeat() throws IllegalArgumentException {
        if(reservedSeats < totSeats) {
            checkNotElapsed(date);
            reservedSeats++;
        }else throw new IllegalArgumentException("L'evento è al completo");
    }
    public void reserveSeats(int numSeats) throws IllegalArgumentException {
        if(reservedSeats < totSeats) {
            if(reservedSeats+numSeats <= totSeats) {
                checkNotElapsed(date);
                reservedSeats += numSeats;
            }else throw new IllegalArgumentException("I posti prenotabili non sono sufficienti per tutti. Ne sono rimasti " + (totSeats-reservedSeats));
        }else throw new IllegalArgumentException("L'evento è al completo");
    }

    public void cancelSeat() throws IllegalArgumentException{
        if(reservedSeats != 0){
            checkNotElapsed(date);
            reservedSeats--;
        }else throw new IllegalArgumentException("Non c'è nessun posto prenotato");
    }

    @Override
    public String toString() {
        return String.format(
                "Evento: %s il %s",
                title,
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
    }

    //    protected void checkTimeline(LocalDate departureDay, LocalDate returnDay) throws DateTimeException{
//        if(departureDay.isAfter(returnDay))
//            throw new DateTimeException("Non puoi andar via prima di arrivare!\nLa data di arrivo e' antecedente la data di partenza");
//    }
    //    protected void checkNotNull(LocalDate departureDay, LocalDate returnDay) throws NullPointerException{
//        if(departureDay == null || returnDay == null)
//            throw new NullPointerException("Devi inserire entrambe le date");
//    }


}
