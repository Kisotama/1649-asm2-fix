package Mainframe;

import Queue.Queue;

public class PC {
    private String ID;
    private int port;


    public PC(String id, int port) {
        this.ID = id;
        this.port = port;

    }

    // Define the getMessageQueue() method

    public String getID() {
        return ID;
    }

    public int getPort() {
        return port;
    }





}
