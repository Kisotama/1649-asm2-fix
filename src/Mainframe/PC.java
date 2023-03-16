package Mainframe;

public class PC {
    private String ID;
    private int port;


    public PC(String id, int port) {
        this.ID = id;
        this.port = port;

    }
    public String getID() {
        return ID;
    }

    public int getPort() {
        return port;
    }

}
// tuấn PC ChatFunction(senduser1touser2)
// me ChatFunction(dequeueMessage) Main(Splitting)
// Hoà Main(Show_menu, interface 43-67, check_num)
// Hiếu ChatFunction(enqueueMessage, receiveUser2, printHistory, continue)