package com.scleroid.financematic.model;

import java.util.Date;

/**
 * Created by scleroid on 6/3/18.
 */




public class Personal_summery_loan_details {
    private String summery_descpription, summery_amount;
    private Date summeryDate;

    public Personal_summery_loan_details(String summery_descpription, String summery_amount, Date summeryDate) {
        this.summery_descpription = summery_descpription;
        this.summery_amount = summery_amount;
        this.summeryDate = summeryDate;
    }

    public Personal_summery_loan_details() {
    }

    public Date getSummeryDate() {
        return summeryDate;
    }

    public void setSummeryDate(Date summeryDate) {
        this.summeryDate = summeryDate;
    }

    public String getSummery_descpription() {
        return summery_descpription;
    }

    void setSummery_descpription(String summery_descpription) {
        this.summery_descpription = summery_descpription;
    }

    public String getSummery_amount() {
        return summery_amount;
    }

    void setSummery_amount(String summery_amount) {
        this.summery_amount = summery_amount;
    }
}
