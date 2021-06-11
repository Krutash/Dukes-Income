/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package firstcup.entity;

/**
 * auth
 */
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@Entity
// Insert code here
@NamedQuery(name="findAverageIncomeDifferenceOfAllUsers",
        query="SELECT AVG(u.incomeDifference) FROM FirstcupUser u")

public class FirstcupUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Calendar nowInst;
    protected Integer income;
    protected int incomeDifference;

    public FirstcupUser() {
    }

    public FirstcupUser(Integer income, int difference) {
        this.income = income;
        incomeDifference = difference;
        nowInst = Calendar.getInstance();
        
    }

    /**
     * Get the value of incomeDifference
     *
     * @return the value of incomeDifference
     */
    public int getIncomeDifference() {
        return incomeDifference;
    }

    /**
     * Set the value of incomeDifference
     *
     * @param incomeDifference new value of ageDifference
     */
    public void setIncomeDifference(int incomeDifference) {
        this.incomeDifference = incomeDifference;
    }

    /**
     * Get the value of income
     *
     * @return the value of income
     */
    public Integer getIncome() {
        return income;
    }

    /**
     * Set the value of income
     *
     * @param income new value of income
     */
    public void setIncome(Integer income) {
        this.income = income;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FirstcupUser)) {
            return false;
        }
        FirstcupUser other = (FirstcupUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "firstcup.entity.FirstcupUser[id=" + id + "]";
    }
}
