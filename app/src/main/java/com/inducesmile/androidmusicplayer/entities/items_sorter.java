package com.inducesmile.androidmusicplayer.entities;

import java.util.ArrayList;

/**
 * Public class items_sorter that will hold list of couuntries
 */
public class items_sorter {
    private int id = -1;
    private String name ="";
    private ArrayList<status_sorter> country = null ;
    private int flag = -1 ;

    /**
     * Public constructor of items_sorter class
     * @param id
     * @param name
     * @param country
     * @param flag
     */
    public items_sorter(int id, String name, ArrayList<status_sorter> country, int flag) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.flag = flag ;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * set id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * return name of continent
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * method used to continent name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return list of countries within a continent
     * @return
     */
    public ArrayList<status_sorter> getCountry() {
        return country;
    }

    /**
     * method used to countries for a continent
     * @param country
     */
    public void setCountry(ArrayList<status_sorter> country) {
        this.country = country;
    }

    /**
     * return drawable image id for continent
     * @return
     */
    public int getFlag() {
        return flag;
    }

    /**
     * set flag for continent
     * @param flag
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
