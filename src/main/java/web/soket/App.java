
import org.glassfish.tyrus.server.Server;
import main.java.web.socket.WebSocketData;

public class App {
    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/", null, WebSocketData.class);
        try {
            server.start();
            System.out.println("Stop...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
