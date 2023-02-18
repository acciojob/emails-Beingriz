package com.driver;

import java.util.*;


public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)


    private int inboxSize;
    private Deque<Mail> inbox;
    private ArrayList<Mail> trash;
    private int trashSize;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new LinkedList<>();
        this.trash = new ArrayList<>();

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
        if(inbox.size()==inboxCapacity){
            trash.add(inbox.removeFirst());
        }
        inbox.add(new Mail(date,sender,message));
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
        Iterator<Mail> it = inbox.iterator();


        while (it.hasNext()) {
            Mail obj = it.next();
            if(obj.getMessage().equals(message))
            {
                trash.add(obj);
                it.remove();
                break;
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(inbox.size()==0){
            return null;
        }
        return inbox.peekLast().getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size()==0){
            return null;
        }
        return inbox.peek().getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        Iterator<Mail> it = inbox.iterator();
        int count=0;
        while (it.hasNext()) {
            Mail obj=it.next();
//           if(obj.date.after(start) && obj.date.before(end))
            if(obj.getDate().getTime()>=start.getTime() && obj.getDate().getTime()<=end.getTime())
            {
                count++;
            }

        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
