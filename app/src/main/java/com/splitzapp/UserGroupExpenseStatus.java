package com.splitzapp;

public class UserGroupExpenseStatus {
    private String userName;
    private Integer amount;
    private Boolean isSelected;

    public UserGroupExpenseStatus(String userName, Integer amount, Boolean isSelected) {
        this.userName = userName;
        this.amount = amount;
        this.isSelected = isSelected;
    }

    public UserGroupExpenseStatus() {
        this.userName = "";
        this.amount = 0;
        this.isSelected = false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
