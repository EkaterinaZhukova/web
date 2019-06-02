package controller;

import model.Abonent;
import model.User;

import java.io.IOException;
import java.util.*;
import javax.websocket.Session;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndPoint {

    private static Map<User, ArrayList<String>> unsentMessages = new HashMap<>();
    /*
    0 - guest
    1 - patient
    2 - doctor
    3 - admin
     */
    @OnMessage
    public void onMessage(Session session, String ms) throws IOException {
        try {
            User sender = (User) session.getUserProperties().get("user");
            if (sender == null) {
                String userParams[] = ms.split(" ");
                User user = new User();
                user.name = userParams[0];
                user.phone = userParams[1];
                if (user.name.equals("admin") && user.phone.equals("11111")) {
                    user.setAdmin(true);
                }
                else  {
                    user.setAbonent(true);
                }
                session.getUserProperties().put("user", user);
                ArrayList<String> messages = null;
                for (User u : unsentMessages.keySet()) {
                    if (u.equals(user)) {
                        messages = unsentMessages.get(u);
                        break;
                    }
                }
                if (messages != null) {
                    for (String msg : messages) {
                        session.getBasicRemote().sendText(msg);
                    }
                    unsentMessages.remove(user);
                }
            } else {
                if (sender.isAdmin()) {
                    int endIdx = ms.indexOf(':');
                    String receiverInfo = ms.substring(0, endIdx);
                    String msg = ms.substring(endIdx + 1);
                    String receiverParams[] = receiverInfo.split(" ");
                    User receiver = new User();
                    receiver.name = receiverParams[0];
                    receiver.phone = receiverParams[1];
                    receiver.setAbonent(true);

                    boolean isReceiverOnline = false;
                    for (Session s : session.getOpenSessions()) {
                        User user = (User) s.getUserProperties().get("user");
                        if (user.equals(receiver)) {
                            isReceiverOnline = true;
                            s.getBasicRemote().sendText("Admin: " + msg);
                            break;
                        }
                    }
                    if (!isReceiverOnline) {
                        boolean contains = false;
                        for (User u : unsentMessages.keySet()) {
                            if (u.equals(receiver)) {
                                ArrayList<String> messages = unsentMessages.get(u);
                                messages.add("Admin: " + msg);
                                unsentMessages.put(u, messages);
                                contains = true;
                                break;
                            }
                        }
                        if (!contains) {
                            ArrayList<String> messages = new ArrayList<>();
                            messages.add("Admin: " + msg);
                            unsentMessages.put(receiver, messages);
                        }
                    }
                } else {
                    boolean isAdminOnline = false;
                    for (Session s : session.getOpenSessions()) {
                        User user = (User) s.getUserProperties().get("user");
                        if (user.isAdmin()) {
                            isAdminOnline = true;
                            s.getBasicRemote().sendText(ms);
                            break;
                        }
                    }
                    if (!isAdminOnline) {
                        boolean contains = false;
                        for (User u : unsentMessages.keySet()) {
                            if (u.isAdmin()) {
                                ArrayList<String> messages = unsentMessages.get(u);
                                messages.add(ms);
                                unsentMessages.put(u, messages);
                                contains = true;
                                break;
                            }
                        }
                        if (!contains) {
                            ArrayList<String> messages = new ArrayList<>();
                            messages.add(ms);
                            User u = new User();
                            u.name = "admin";
                            u.phone = "11111";
                            u.setAdmin(true);
                            unsentMessages.put(u, messages);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}