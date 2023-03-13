package Mainframe;

import Queue.Queue;

public class PC {
    private String ID;
    private int port;

    public PC(String ID, int port) {
        this.ID = ID;
        this.port = port;
    }

    public String getID() {
        return ID;
    }

    public int getPort() {
        return port;
    }


}
