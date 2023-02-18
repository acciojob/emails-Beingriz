package com.driver;

import java.util.*;


public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)


    private int inboxSize;
    private Queue<Mail> inbox;
    private Queue<Mail> trash;
    private int trashSize;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new LinkedList<>();
        this.trash = new LinkedList<>();
        this.trashSize = 0;
        this.inboxSize = 0;
    }
    public void setInboxSize(int inboxSize) {
        this.inboxSize = inboxSize;
    }

    public void setTrashSize(int trashSize) {
        this.trashSize = trashSize;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(getInboxSize() == getInboxCapacity()){
            Mail m = inbox.remove();
            this.inboxSize--;
            trash.add(m);
            this.trashSize++;
            Mail n = new Mail(date, sender,message);
            inbox.add(n);
            this.inboxSize++;
        }else{
            Mail m = new Mail(date, sender,message);
            inbox.add(m);
            this.inboxSize++;
        }
    }
    public boolean searchMail(String message){
        for(Mail mail : inbox){
            if(mail.getMessage().equals(message))
                return true;
        }
        return false;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(searchMail(message)){
            Mail m = inbox.remove();
            this.inboxSize--;
            trash.add(m);
            this.trashSize++;
            System.out.println("Mail Moved to Trash!");
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        int size = inbox.size();
        for (int i = 0; i < size-1; i++) {
            inbox.remove();
        }
        Mail m = inbox.peek();
        return m.getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        Mail m = inbox.peek();
        return m.getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for (Mail m: inbox ) {

            if(start.compareTo(m.getDate()) <= 0 && end.compareTo(m.getDate())==0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return this.inboxSize;

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return this.trashSize;

    }

    public void emptyTrash(){
        // clear all mails in the trash
        int size = trash.size();
        for (int i = 0; i < size; i++) {
            trash.remove();
        }

        if (trash.isEmpty()){
            this.trashSize=0;
            System.out.println("Trash is Empty");
        }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
