/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team14.se.maintenanceapp;
import java.sql.*;
import java.util.*;


/**
 *
 * @author domal
 */
public class Main { //behave as home: connection created only here

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Connection conn = new MyConnection("jdbc:postgresql://localhost/maintenanceDB", "team14", "team14").getConnection();
            System.out.println("Connession to " + conn + " successfully created");
            
            /*Queste funzioni vanno poi spostate tutte nelle interfacce. La connessione al database deve essere unica*/
            // try to show users in database 
            LinkedList<User> users = User.getUsers(conn);
            System.out.println("Users:");
            for(User u : users){
                System.out.println(u.getName());
            }
            // try to show notes in database 
            LinkedList<Note> notes = Note.getNotes(conn); 
            System.out.println("\nNotes:");
            for(Note n : notes){
                System.out.println(n.getText());
            }
            // try to show materials in database 
            LinkedList<Material> materials = Material.getMaterials(conn);   
            System.out.println("\nMaterials:");
            for(Material m : materials){
                System.out.println(m.getName());
            }
            // try to show materials in database 
            LinkedList<Typology> typologies = Typology.getTypologies(conn);   
            System.out.println("\nTypologies:");
            for(Typology t : typologies){
                System.out.println(t.getName());
            }
            // try to show sites in database 
            LinkedList<Site> sites = Site.getSites(conn);   
            System.out.println("\nSites:");
            for(Site s : sites){
                System.out.println(s.getName());
            }
            // try to show competences in database 
            LinkedList<Competence> competences = Competence.getCompetences(conn);   
            System.out.println("\nCompetences:");
            for(Competence c : competences){
                System.out.println(c.getName());
            }
            // try to show procedures in database 
            LinkedList<Procedure> procedures = Procedure.getProcedures(conn);   
            System.out.println("\nProcedures:");
            for(Procedure p : procedures){
                System.out.println(p.getName());
            }
            // try to show logs in database 
            LinkedList<Log> logs = Log.getLogs(conn);   
            System.out.println("\nLogs:");
            for(Log l : logs){
                System.out.println(l.getUsername() + " " + l.getTimestamp());
            }
             
             
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
