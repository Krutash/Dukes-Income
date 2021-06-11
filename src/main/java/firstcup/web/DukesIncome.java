/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package firstcup.web;

import firstcup.ejb.DukesIncomeBean;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@SessionScoped
public class DukesIncome implements Serializable {

    @EJB
    private DukesIncomeBean dukesIncomeBean;
    protected Integer income;
    @NotNull
    protected Integer yourIncome;
    protected int incomeDiff;
    protected int absIncomeDiff;
    protected Double averageIncomeDifference;
    private static final Logger logger = Logger.getLogger("firstcup.web.DukesIncome");

    /**
     * Creates a new instance of DukesBDay
     */
    public DukesIncome() {
    }

    public String processIncome() {
        // Insert code here
        this.setIncomeDiff(dukesIncomeBean.getIncomeDifference(yourIncome));
        logger.log(Level.INFO, "age diff from dukesIncome {0}", incomeDiff);
        this.setAbsIncomeDiff(Math.abs(this.getIncomeDiff()));
        logger.log(Level.INFO, "abs incomeDifference");
        this.setAverageIncomeDifference(dukesIncomeBean.getAverageIncomeDifference());
        logger.log(Level.INFO, "averageIncomeDifference {0}", averageIncomeDifference);
        return "/response.xhtml";

    }

    /**
     * Get the value of income
     *
     * @return the value of income
     */
    public Integer getIncome() {
        // Insert code here
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target
                    = client.target("http://localhost:8080/dukes-income/webapi/dukesIncome");
            String response = target.request().get(String.class);
            income = Integer.parseInt(response);
        } catch (IllegalArgumentException | NullPointerException | WebApplicationException ex) {
            logger.severe("Processing of HTTP response failed");
        }
        return income;
    }

    public void setIncome(Integer age) {
        this.income = age;
    }

    public DukesIncomeBean getDukesIncomeBean() {
        return dukesIncomeBean;
    }

    public void setDukesIncomeBean(DukesIncomeBean dukesIncomeBean) {
        this.dukesIncomeBean = dukesIncomeBean;
    }

    public Integer getYourIncome() {
        return yourIncome;
    }

    public void setYourIncome(Integer yourIncome) {
        this.yourIncome = yourIncome;
    }

    public int getIncomeDiff() {
        return incomeDiff;
    }

    public void setIncomeDiff(int incomeDiff) {
        this.incomeDiff = incomeDiff;
    }

    public int getAbsIncomeDiff() {
        return absIncomeDiff;
    }

    public void setAbsIncomeDiff(int absIncomeDiff) {
        this.absIncomeDiff = absIncomeDiff;
    }

    public Double getAverageIncomeDifference() {
        return averageIncomeDifference;
    }

    public void setAverageIncomeDifference(Double averageIncomeDifference) {
        this.averageIncomeDifference = averageIncomeDifference;
    }

}
