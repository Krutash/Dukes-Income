/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package firstcup.ejb;

import firstcup.entity.FirstcupUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DukesBirthdayBean is a stateless session bean that calculates the age
 * difference between a user and Duke, who was born on May 23, 1995.
 */
@Stateless
public class DukesIncomeBean {

    private static final Logger logger
            = Logger.getLogger("firstcup.ejb.DukesIncomeBean");

    @PersistenceContext
    private EntityManager em;

    public Double getAverageIncomeDifference() {
        // Insert code here
        Double avgIncomeDiff = (Double) em.createNamedQuery("findAverageIncomeDifferenceOfAllUsers")
                .getSingleResult();
        logger.log(Level.INFO, "Average IncomeDifference is {0}", avgIncomeDiff);
        return avgIncomeDiff;
    }

    public int getIncomeDifference(Integer Income) {
        // Insert code here
        Integer theirIncome = Income;
        Integer dukesIncome = 120000;
        int incomeDifference = dukesIncome - theirIncome;

        logger.log(Level.INFO, "Raw incomeDifference is: {0}", incomeDifference);
        FirstcupUser user = new FirstcupUser(Income, incomeDifference);

        em.persist(user);
        logger.log(Level.INFO, "Final incomeDifference is: {0}", incomeDifference);

        return incomeDifference;
    }
}
