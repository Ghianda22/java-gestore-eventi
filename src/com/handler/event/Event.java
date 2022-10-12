package com.handler.event;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Event {
    private String title;
    private LocalDate date;
    private int totSeats;
    private int reservedSeats = 0;

    public Event(String title, LocalDate date, int totSeats) throws Exception {
        checkNotElapsed(date);
        checkSeatsNum(totSeats);
        this.title = title;
        this.date = date;
        this.totSeats = totSeats;
    }

    protected void checkSeatsNum(int totSeats) throws ArgumentOutOfRangeException {
        if(totSeats <= 0){
            throw new IllegalArgumentException()
        }
    }

//    protected void checkNotNull(LocalDate departureDay, LocalDate returnDay) throws NullPointerException{
//        if(departureDay == null || returnDay == null)
//            throw new NullPointerException("Devi inserire entrambe le date");
//    }

    protected void checkNotElapsed(LocalDate date) throws DateTimeException {
        if(LocalDate.now().isAfter(date))
            throw new DateTimeException("Non puoi partire nel passato!\nLa data di partenza è antecedente a oggi");
    }

    protected void checkTimeline(LocalDate departureDay, LocalDate returnDay) throws DateTimeException{
        if(departureDay.isAfter(returnDay))
            throw new DateTimeException("Non puoi andar via prima di arrivare!\nLa data di arrivo e' antecedente la data di partenza");
    }

}
