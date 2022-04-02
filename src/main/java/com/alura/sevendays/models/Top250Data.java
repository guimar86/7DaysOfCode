package com.alura.sevendays.models;

import java.util.List;

public class Top250Data {

    public List<Top250DataDetail> Items;

    public String ErrorMessage;

    public List<Top250DataDetail> getItems() {
        return Items;
    }

    public void setItems(List<Top250DataDetail> items) {
        Items = items;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Top250Data{" +
                "Items=" + Items +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                '}';
    }
}
