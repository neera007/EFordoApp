package com.example.e_fordoapp.Model;

public class CustomerAutoComplete {
    private String AccountNumber;

    public CustomerAutoComplete(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    @Override
    public String toString() {
        return AccountNumber;
    }

}
