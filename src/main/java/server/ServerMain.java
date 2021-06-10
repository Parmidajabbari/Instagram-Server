package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    public static Server server;
    public static List<User> users = new ArrayList<>();
    public static Map<User, Socket> onlineUsers = new HashMap<>();
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        server = new Server();

        while (true) {
            server.setConnection();

            TaskListener listener = new TaskListener(server.getOutput(), server.getInput());
            pool.execute(listener);
        }

    }

}
