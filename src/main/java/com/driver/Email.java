package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(getPassword().equals(oldPassword)){
            if(isLength(newPassword) && isDigit(newPassword) && isUpperCase(newPassword) && isLowerCase(newPassword) && isSpecial(newPassword)){
                this.setPassword(newPassword);
            }
        }
    }
    public boolean isLength(String password){
        int len = password.length();
        if(len > 8) return true;

        return false;
    }

    public boolean isUpperCase(String password){
        for (char ch: password.toCharArray()) {
            if(Character.isUpperCase(ch)) return true;
        }
        return  false;
    }

    public boolean isLowerCase(String password){
        for (char ch: password.toCharArray()) {
            if(Character.isLowerCase(ch))
                return true;
        }
        return  false;
    }

    public  boolean isDigit(String password){
        for (char ch: password.toCharArray()) {
            if(Character.isDigit(ch)) return true;
        }
        return false;
    }

    public  boolean isSpecial(String password){
        for (char ch: password.toCharArray()) {
            if(!Character.isLetterOrDigit(ch)) return  true;
        }
        return false;
    }
}
