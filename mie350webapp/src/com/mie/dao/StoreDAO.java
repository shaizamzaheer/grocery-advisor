package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.mie.util.DbUtil;
import com.mie.model.*;
import com.mie.controller.*;
import com.mie.util.*;

public class StoreDAO {

	/**
	 * This class handles the Store objects 
	 */
	static Connection currentCon = null;
	static ResultSet rs1 = null;
	static ResultSet rs2 = null;

	/**
	 * This method attempts to find the user that is trying to log in by
	 * first retrieving the username and password entered by the user.
	 */
	public List<Store> getStoresWithinRadius(double userLat, double userLon, double radius) {
		Statement stmt = null;
		PreparedStatement pst = null;
		/**
		 * Prepare a query that searches for stores that are within a certain radius of the user's location (lat, lon)
		 */
		String searchQuery1 = "select StoreID, StoreLat, StoreLon from Location where "
				+ "(((111.2)^2)*((" + userLat + "-StoreLat)^2+((" + userLon + "-StoreLon)^2)*(cos(" + userLat + "*3.141592654/180))^2))<" + radius + "^2;";
		System.out.println("\n" + searchQuery1 + "\n");
		
		String searchQuery = "select StoreID, StoreLat, StoreLon from Location where "
				+ "(((111.2)^2)*((?-StoreLat)^2+((?-StoreLon)^2)*(cos(?*3.141592654/180))^2))<?^2;";
		System.out.println("\n" + searchQuery + "\n");
								
		List<Store> storesWithinRadius1 = new ArrayList<Store>();	
		List<Store> storesWithinRadius2 = new ArrayList<Store>();

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs1 = stmt.executeQuery(searchQuery1);
			
			pst = currentCon.prepareStatement(searchQuery);
			pst.setDouble(1, userLat);
			pst.setDouble(2, userLon);
			pst.setDouble(3, userLat);
			pst.setDouble(4, radius);
			rs2 = pst.executeQuery();

			//Make a list of stores from the results
			while (rs1.next()) {
				storesWithinRadius1.add(new Store(rs1.getInt("StoreID"), rs1.getDouble("StoreLat"), rs1.getDouble("StoreLon")));
			}
			
			while (rs2.next()) {
				storesWithinRadius2.add(new Store(rs2.getInt("StoreID"), rs2.getDouble("StoreLat"), rs2.getDouble("StoreLon")));
			}
		}

		catch (Exception ex) {
			System.out.println("Something went wrong trying to get the stores within the radius: "
					+ ex);
			System.out.println("Stacktrace: ");
			ex.printStackTrace();
		}
		//Return the list of stores that are within certain radius of user
		return storesWithinRadius1;

	}

	public Store getStoreDetails(int storeID) {
		Statement stmt = null;

		/**
		 * Prepare a query that searches for stores that are within a certain radius of the user's location (lat, lon)
		 */
		String searchQuery = "select Franchise, Street_Address, Region, Postal_Code, Phone from Store where storeID = " + storeID + ";";	
		String searchHours = "select DayOfWeek, StartTime, EndTime from Store where StoreID = " + storeID + ";";
		Store storeDetails = null;

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs1 = stmt.executeQuery(searchQuery);
			
			rs1.next();
			
			//TODO: make sure column names correspond
			storeDetails = new Store(
					storeID,
					rs1.getString("Franchise"), 
					rs1.getString("Street_Address"),
					rs1.getString("Region"),
					rs1.getString("Postal_Code"),
					rs1.getString("Phone"));
			
			stmt = currentCon.createStatement();
			rs1 = stmt.executeQuery(searchHours);
			
			//each tuple will have a day of the week (String), with corresponding start and end times in Time format
			while(rs1.next()) {
				storeDetails.addDayAndHours(rs1.getString("DayOfWeek"), new Time[]{rs1.getTime("StartTime"), rs1.getTime("EndTime")});
			}
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
		
		//Return the store object which contains details for the store
		return storeDetails;
	}
	
	
	public static void main(String[] args) {
		StoreDAO dao = new StoreDAO();
		
		/*
		Store store = dao.getStoreDetails(1);
		
		System.out.println("Franchise: " + store.getFranchise());
		System.out.println("Street_Address: " + store.getStreetAddress());
		System.out.println("Region: " + store.getRegion());
		System.out.println("Postal_Code: " + store.getPostalCode());
		System.out.println("Phone: " + store.getPhone());
		*/
		
		List<Store> stores = dao.getStoresWithinRadius(43.6588195, -79.3971833, 5);
		System.out.println(stores);
	}
	

}
