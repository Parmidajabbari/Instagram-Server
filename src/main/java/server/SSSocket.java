package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class SSSocket
{
    public Socket socket;
    byte[] byteBuffer;
    int bufferLength = 0;
    DataOutputStream out;
    DataInputStream in;

    public SSSocket (String host, int port) throws IOException {
        socket =  new Socket(host, port);
        byteBuffer = new byte[0];
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    public SSSocket (Socket s) throws IOException {
        socket = s;
        byteBuffer = new byte[0];
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    public SSSocket (DataInputStream in, DataOutputStream out ){
        this.out = out;
        this.in = in;
        byteBuffer = new byte[0];
    }

    public byte[] readMessage() throws IOException {
        byte[] byteBuffer = new byte[1024];
        int length;
        int messageLength = in.readInt();
        if (messageLength < 0) {
            return new byte[0];
        }
        byte[] message = new byte[messageLength];
        int read = 0;
        while (read < messageLength) {
            if ((length = in.read(byteBuffer)) != -1) {
                int readableLength = read + length <= messageLength ? length : messageLength - read;
                if (readableLength >= 0) System.arraycopy(byteBuffer, 0, message, read, readableLength);
                read += length;
            } else {
                break;
            }
        }

        return message;
    }

    public void sendMessage(byte[] message) throws IOException {
        out.writeInt(message.length);
        out.write(message);
    }


    public void close() throws IOException {
        socket.close();

    }
}
