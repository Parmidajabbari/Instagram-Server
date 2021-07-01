package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class SSSocket
{
    public Socket socket;
    byte[] byteBuffer;
    int bufferLength = 0;
    OutputStream out;
    InputStream in;

    public SSSocket (String host, int port) throws IOException {
        socket =  new Socket(host, port);
        byteBuffer = new byte[0];
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }

    public SSSocket (Socket s) throws IOException {
        socket = s;
        byteBuffer = new byte[0];
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }

    public SSSocket (DataInputStream in, DataOutputStream out ){
        this.out = out;
        this.in = in;
        byteBuffer = new byte[0];
    }

    public byte[] readMessage() throws IOException {

        byte[] ret = byteBuffer.clone();
        boolean lengthFound = false;
        int length = 0;
        int lastLengthByte = 0;
        int lastMask = 1;
        int bufferLength = this.bufferLength;
        while(true){

            while ((!lengthFound) && (bufferLength > lastLengthByte)){
                byte currLengthByte = ret[lastLengthByte];
                if (currLengthByte >= 0) {
                    lengthFound = true;
                    length += lastMask * currLengthByte;
                }
                else {
                    length = lastMask * (currLengthByte + 128);
                    lastMask *= 128;
                }
                lastLengthByte++;
            }

            if(lengthFound){
                if(bufferLength >= length + lastLengthByte){
                    byte[] tempB
                            = new byte[bufferLength - length - lastLengthByte];
                    for (int i = 0; i < tempB.length; i++)
                        tempB[i] = ret[i + length + lastLengthByte];
                    byteBuffer = tempB;
                    this.bufferLength = tempB.length;

                    byte[] realRetThisTime = new byte[length];
                    System.arraycopy(ret, lastLengthByte
                            , realRetThisTime, 0, length);
                    return realRetThisTime;
                }
            }

            byte[] tempB = new byte[512];
            int numOfBytesRead = in.read(tempB, 0, 512);
            byte[] tempRet = ret.clone();
            bufferLength += numOfBytesRead;
            ret = new byte[bufferLength];
            System.arraycopy(tempRet, 0, ret, 0, tempRet.length);
            if (numOfBytesRead >= 0)
                System.arraycopy(tempB, 0, ret, tempRet.length, numOfBytesRead);

        }
    }

    public void sendMessage(byte[] message) throws IOException {
        int messageLen = message.length;
        ArrayList<Byte> lengthHelperBytes = new ArrayList<>();

        while(messageLen > 0){
            byte t = (byte) (messageLen % 128);
            messageLen /= 128;
            if (messageLen > 0)
                t = (byte) (t + 128);
            lengthHelperBytes.add(t);
        }

        byte[] fullMessage = new byte[message.length + lengthHelperBytes.size()];
        for (int i = 0; i < lengthHelperBytes.size(); i++)
            fullMessage[i] = lengthHelperBytes.get(i);
        for(int i =0; i < message.length; i++){
            fullMessage[i + lengthHelperBytes.size()] = message[i];
        }


        out.write(fullMessage);
        out.flush();
    }


    public void close() throws IOException {
        socket.close();

    }
}
