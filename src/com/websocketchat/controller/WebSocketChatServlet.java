package com.websocketchat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.mem.model.MemService;

@ServerEndpoint("/WebSocketChatServlet/{userName}")
public class WebSocketChatServlet {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		
		List<String> old = JedisHandleMessage.getHistoryMsg("room");
		String oldMessage = "";
		if (old.size() != 0) {
			for (int i = 0; i < old.size() - 1; i++) {
				oldMessage = oldMessage + (old.get(i) + ",");
			}
			oldMessage = "[" + oldMessage + old.get(old.size() - 1) + "]";
			if (userSession.isOpen()) {
				System.out.println(oldMessage);
				userSession.getAsyncRemote().sendText(oldMessage);
			}
		} 
		
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {

		JedisHandleMessage.saveChatMessage("room", message);
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);

	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
//		String userNameClose = null;
//		Set<String> userNames = sessionsMap.keySet();
//		for (String userName : userNames) {
//			if (sessionsMap.get(userName).equals(userSession)) {
//				userNameClose = userName;
//				sessionsMap.remove(userName);
//				break;
//			}
//		}
//
//		if (userNameClose != null) {
//			State stateMessage = new State("close", userNameClose, userNames);
//			String stateMessageJson = gson.toJson(stateMessage);
//			Collection<Session> sessions = sessionsMap.values();
//			for (Session session : sessions) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
//		}
//
//		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
//				reason.getCloseCode().getCode(), userNames);
//		System.out.println(text);
	}
}
