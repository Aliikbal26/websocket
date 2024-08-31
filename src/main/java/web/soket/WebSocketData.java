package main.java.web.soket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@ServerEndpoint("/data")
public class WebSocketData {
    private Timer timer;
    private Random random = new Random();

    @OnOpen
    public void onOpen(Session session) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonData = generateRandomData();
                    session.getBasicRemote().sendText(jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
    }

    private String generateRandomData() {
        int[] data = new int[10];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(10000);
        }
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < data.length; i++) {
            json.append(data[i]);
            if (i < data.length - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}
