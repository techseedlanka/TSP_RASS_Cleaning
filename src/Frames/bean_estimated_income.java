/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

/**
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class bean_estimated_income {

    /**
     * @return the worked_shift
     */
    public int getWorked_shift() {
        return worked_shift;
    }

    private String loc_code;
    private String loc_name;
    private String rate;
    private String amount;
    private int contracted_shift;
    private String rank;
    private int worked_shift;

    /**
     * @return the loc_code
     */
    public String getLoc_code() {
        return loc_code;
    }

    /**
     * @param loc_code the loc_code to set
     */
    public void setLoc_code(String loc_code) {
        this.loc_code = loc_code;
    }

    /**
     * @return the loc_name
     */
    public String getLoc_name() {
        return loc_name;
    }

    /**
     * @param loc_name the loc_name to set
     */
    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the contracted_shift
     */
    public int getContracted_shift() {
        return contracted_shift;
    }

    /**
     * @param contracted_shift the contracted_shift to set
     */
    public void setContracted_shift(int contracted_shift) {
        this.contracted_shift = contracted_shift;
    }

    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * @param worked_shift the worked_shift to set
     */
    public void setWorked_shift(String worked_shift) {
        this.setWorked_shift(worked_shift);
    }

    /**
     * @param worked_shift the worked_shift to set
     */
    public void setWorked_shift(int worked_shift) {
        this.worked_shift = worked_shift;
    }

}
