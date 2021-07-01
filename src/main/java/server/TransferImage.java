package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class TransferImage {

    DataInputStream input;
    DataOutputStream output;
    private SSSocket ssSocket;

    public SSSocket getSsSocket() {
        return ssSocket;
    }

    public void setSsSocket(SSSocket ssSocket) {
        this.ssSocket = ssSocket;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public TransferImage(DataInputStream input, DataOutputStream output ) {
        this.output = output;
        this.input = input;
        this.ssSocket = new SSSocket(input,output);
    }



}
