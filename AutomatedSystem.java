/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parking_lot;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class AutomatedSystem {
    private int id;



    private Parking_Lot parkingLot;
    HashMap<Integer,Ticket> currentTickets;

    public AutomatedSystem(int id){
        this.id = id;
        currentTickets = new HashMap<>();
    }

    public Parking_Lot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(Parking_Lot parkingLot) {
        this.parkingLot = parkingLot;
    }
    private ParkingSpot fetchAvailableSpot(){
        return this.parkingLot.getAvailableSpot();
    }

    public Ticket generateTicket(Customer customer){
        ParkingSpot availableSpot = fetchAvailableSpot();
        Vehicle vehicle = customer.getVehicle();
        Ticket ticket = new Ticket(vehicle,availableSpot);
        currentTickets.put(ticket.getId(),ticket);
        return ticket;
    }
    public Ticket scanTicket(){
        // Code for scanning the ticket and return ticketId
        // Here we are assuming that scanned ticket id is 1234
        return currentTickets.get(1234);
    }

    public double caluclateCharges(){
         Ticket ticket= scanTicket();
         long duration = Duration.between(LocalDateTime.now(),ticket.getArrivalTime()).toHours();
         double charges = duration * parkingLot.getParkingCharges();
         return charges;
    }

    public void openEntryBarrier(){
        // Code for opening Entry Barrier
        this.parkingLot.getDisplayBoard().update(Status.FULL);
    }
    public void closeEntryBarrier(){
        // Code for closing Entry Barrier
    }
    public void openExitBarrier(){
        // Code for opening Entry Barrier
        this.parkingLot.getDisplayBoard().update(Status.AVAILABLE);
    }
    public void closeExitBarrier(){
        // Code for closing Entry Barrier
    }

}
