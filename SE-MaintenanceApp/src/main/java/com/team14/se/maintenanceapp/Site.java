/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team14.se.maintenanceapp;
import java.util.*;
import java.sql.*;

/**
 *
 * @author domal
 */
public class Site {
    private String name;
    
  
    public Site(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Site{" + "name=" + name + '}';
    }
    
    //Access to database methods
    //Acquire list of site
    public static LinkedList<Site> getSites (Connection conn) throws SQLException{
        LinkedList<Site> sites = new LinkedList<>();
        String query = "SELECT * FROM sito";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while(rst.next()){
            sites.add(new Site(rst.getString("nome")));
        }
        return sites;    
    }

}
