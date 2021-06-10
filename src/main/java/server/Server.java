package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private ServerSocket server;
    private DataOutputStream output;
    private DataInputStream input;
    private static int connectionNumber = 0;
    private static Map<Integer, Socket> connectedClients = new HashMap<>();

    public Server() throws IOException {
        int PORT = 9090;
        server = new ServerSocket(PORT);
    }

    public void setConnection() throws IOException {
        Socket socket = server.accept();
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        connectionNumber++;
        connectedClients.put(connectionNumber, socket);

        output.writeInt(connectionNumber);

    }

    public DataOutputStream getOutput() {
        return output;
    }

    public DataInputStream getInput() {
        return input;
    }

    public static Map<Integer, Socket> getConnectedClients() {
        return connectedClients;
    }

}
