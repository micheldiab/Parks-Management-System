package controllers;

import java.util.ArrayList;

import Client.GoNatureClient;
import Client.ClientUI;
import Protocol.ClientMessage;
import Protocol.ServerMessage;
import entities.Worker;

public class WorkerLoginCC {
	/**
	 * 
	 * checkWorker sends message to server to search after a worker with the input
	 * username and password and returns instance of the worker.
	 * 
	 * @param username
	 * @param password
	 * @return worker if exist else null
	 */
	public static Worker checkWorker(String username, String password) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(username);
		list.add(password);

		ClientMessage msgFromClient = new ClientMessage("checkWorker", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = GoNatureClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		Worker worker = (Worker) msgFromServer.getData();
		return worker;
	}

}
