package Mainframe;

import Queue.Queue;

public class PC {
    private String ID;
    private int port;
    private Queue.MyQueues<String> messageQueue;

    public PC(String id, int port) {
        this.ID = id;
        this.port = port;
        this.messageQueue = new Queue().new MyQueues<>();
    }

    // Define the getMessageQueue() method
    public Queue.MyQueues<String> getMessageQueue() {
        return this.messageQueue;
    }
    public String getID() {
        return ID;
    }

    public int getPort() {
        return port;
    }



}
