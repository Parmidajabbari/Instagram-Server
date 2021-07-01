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

    public TransferImage(DataInputStream input, DataOutputStream output ) {
        this.ssSocket = new SSSocket(input,output);
    }



}
