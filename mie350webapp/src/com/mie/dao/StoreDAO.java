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

// this class is a DAO that accesses the Store and StoreHours tables to get information about stores
// also used to find the stores that are within a certain radius of a given locatin (lat/lon)

public class StoreDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;

	/**
	 * This method gets all the stores within a certain radius of the user's location (lat, lon).
	 */
	public List<Store> getStoresWithinRadius(double userLat, double userLon, double radius) {
		PreparedStatement pst = null;
		
		/**
		 * Prepare a query that searches for stores that are within a certain radius of the user's location (lat, lon)
		 */
		
		String searchQuery = 
				"select StoreID, Lat, Long, "
				+ "Sqr(((111.2)^2)*((?-Lat)^2+((?-Long)^2)*(cos(?*3.141592654/180))^2)) as Distance "
				+ "from Store where (((111.2)^2)*((?-Lat)^2+((?-Long)^2)*(cos(?*3.141592654/180))^2))<?^2;";						
		List<Store> storesWithinRadius = new ArrayList<Store>(); //list to store the stores

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			
			pst = currentCon.prepareStatement(searchQuery);
			pst.setDouble(1, userLat);
			pst.setDouble(2, userLon);
			pst.setDouble(3, userLat);
			pst.setDouble(4, userLat);
			pst.setDouble(5, userLon);
			pst.setDouble(6, userLat);
			pst.setDouble(7, radius);
			rs = pst.executeQuery();

			//Make a list of stores from the ResultSet
			
			while (rs.next()) {
				int storeID = rs.getInt("StoreID");
				double storeLat = rs.getDouble("Lat");
				double storeLon = rs.getDouble("Long");
				double distance = rs.getDouble("Distance");
//				System.out.println("StoreID: " + storeID);
//				System.out.println("StoreLat: " + storeLat);
//				System.out.println("StoreLon: " + storeLon);
//				System.out.println("Store distance: " + distance);
				storesWithinRadius.add(new Store(storeID, storeLat, storeLon, distance));
				
			}
		}

		catch (Exception ex) {
			System.out.println("Something went wrong trying to get the stores within the radius: "
					+ ex);
			System.out.println("Stacktrace: ");
			ex.printStackTrace();
		}
		//Return the list of stores that are within certain radius of user
		return storesWithinRadius;

	}

	/**
	 * This method gets the details of a particular store
	 */
	public Store getStoreDetails(int storeID) {
		Statement stmt = null;

		/**
		 * Prepare a query that retrieves singular details of a certain store and another query that retrieves store hours
		 */
		String searchQuery = "select Franchise, Street_Address, Region, Postal_Code, Phone from Store where storeID = " + storeID + ";";	
		String searchHours = "select DayOfWeek, StartTime, EndTime from StoreHours where StoreID = " + storeID + ";";
		Store storeDetails = null;

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			
			rs.next(); //expecting only one tuple...
			
			storeDetails = new Store(
					storeID,
					rs.getString("Franchise"), 
					rs.getString("Street_Address"),
					rs.getString("Region"),
					rs.getString("Postal_Code"),
					rs.getString("Phone"));
			
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchHours);
			
			//each tuple will have a day of the week (String), with corresponding start and end times in Time format
			while(rs.next()) {
				storeDetails.addDayAndHours(rs.getString("DayOfWeek"), new Time[]{rs.getTime("StartTime"), rs.getTime("EndTime")});
			}
			
			// converts tuple-like HashMap mapping each day to corresponding hours, to a shortened easy-to-read form
			// e.g. Mon-Sun : 09:00 - 22:00 in one line, rather than 7 lines for each day of the week
			storeDetails.makeHoursCompact(); 
		}

		catch (Exception ex) {
			System.out.println("Failed to get store details: An Exception has occurred! "
					+ ex);
		}
		
		//Return the store object which contains details for the store
		return storeDetails;
	}
	

}
