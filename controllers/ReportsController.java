package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;

import Client.GoNatureClient;
import Client.ClientUI;
import Protocol.ClientMessage;
import Protocol.ServerMessage;
import entities.ManagerRequest;
import entities.ReportType;

public class ReportsController {

	/**
	 * this function gets report from the database and checks if its exist already
	 * 
	 * @param month
	 * @param year
	 * @param reportType
	 * @return report data as a String
	 */
	public static String getReport(Date date, ReportType reportType, String ParkName) {

		ArrayList<Object> msg = new ArrayList<Object>();

		msg.add(reportType.toString());
		msg.add(date);
		msg.add(ParkName);

		ClientMessage msg2 = new ClientMessage("GetReport", msg, 3);
		ClientUI.chat.accept(msg2);
		ServerMessage srMessage = GoNatureClient.messageRecievedFromServerEvents.get(msg2.getMethodName());
		return (String) srMessage.getData();

	}

	/**
	 * this function adds report data to the database
	 * 
	 * @param reportType
	 * @param month
	 * @param year
	 * @param parameterStrings
	 * @return boolean value true/false if it added successfully or not
	 */
	public static void addReportToDB(ReportType reportType, Date date, ArrayList<String> parameterStrings,
			String ParkName) {

		ArrayList<Object> msg = new ArrayList<Object>();
		msg.add(reportType.toString());
		msg.add(date);
		msg.add(parameterStrings);
		msg.add(ParkName);
		ClientMessage msg2 = new ClientMessage("InsertReports", msg, 4);
		ClientUI.chat.accept(msg2);

	}

	/**
	 * this function check if the received report with the specific month and year
	 * is exist in the database or not
	 * 
	 * @param reportType
	 * @param month
	 * @param year
	 * @return boolean number true/false if it exist or not
	 */
	public static boolean CheckReportExistInDB(ReportType reportType, Date date, String ParkName) {

		ArrayList<Object> msg = new ArrayList<Object>();
		msg.add(reportType.toString());
		msg.add(date);
		msg.add(ParkName);

		ClientMessage msg2 = new ClientMessage("IsReportExisted", msg, 3);
		ClientUI.chat.accept(msg2);
		ServerMessage srMessage = GoNatureClient.messageRecievedFromServerEvents.get(msg2.getMethodName());
		boolean result = (boolean) srMessage.getData();
		return result;

	}

	/**
	 * GetAllRequests returns all the requests of the park from the database as
	 * ManagerRequest
	 * 
	 * 
	 * @param ParkName
	 * @return arraylist that contains all the requests
	 */
	public static ArrayList<ManagerRequest> GetAllRequests(String ParkName) {
		ArrayList<Object> msg = new ArrayList<Object>();
		msg.add(ParkName);
		ClientMessage msg2 = new ClientMessage("getAllRequests", msg, msg.size());
		ClientUI.chat.accept(msg2);
		ServerMessage srMessage = GoNatureClient.messageRecievedFromServerEvents.get(msg2.getMethodName());
		ArrayList<ManagerRequest> result = (ArrayList<ManagerRequest>) srMessage.getData();
		return result;
	}

}