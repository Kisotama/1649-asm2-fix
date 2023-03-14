package Chat;

import Mainframe.PC;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

import static sun.rmi.registry.RegistryImpl.getID;


public class ChatFunction {


    private Queue<String> user1ToUser2 = new LinkedList<>();
    private Queue<String> user2ToUser1 = new LinkedList<>();
    private Stack<String> history = new Stack<String>();
    private Stack<String> mergedata = new Stack<String>();
    public void sendUser1ToUser2(String message) {
//            PC receiver = new PC("pc2", 5678);
//            sendMessage(receiver, message);
        enqueueMessage(user1ToUser2, message);
    }

    public void sendUser2ToUser1(String message) {
        enqueueMessage(user2ToUser1, message);
    }

    private int segmentNumber = 1;

    private void enqueueMessage(Queue<String> queue, String message) {
        queue.add(message.toString());
    }

    public String receiveUser1() {
        return dequeueMessage(user2ToUser1, "User 2", 0);
    }

    public String receiveUser2() {
        return dequeueMessage(user1ToUser2, "User 1", 0);
    }

    private String dequeueMessage(Queue<String> queue, String sender, int type) {

        StringBuilder sb = new StringBuilder();
        String message;
        if (type == 0) {
            if (queue.isEmpty()) {
                return null;
            }
            while (!queue.isEmpty()) {
                message = queue.remove();
                history.push(sender + ": " + message);
                mergedata.push(sender + ": " + message);
            }
            while (!history.isEmpty()) {
                message = history.pop().split(":")[2];
                sb.insert(0, message);
            }
        } else {
            System.out.println("\nChat History:");
            while (!mergedata.isEmpty()) {
                 message = mergedata.pop();
                sb.insert(0, message+"\n");
                try {
                    Thread.sleep(10); // delay for 1 second (1000 milliseconds)
                } catch (InterruptedException e) {
                    // handle the exception
                }
            }
        }
        return sb.toString().trim();
    }

    public String printHistory() {
        return dequeueMessage(user1ToUser2, "User 1 :", 1);
    }

    private void sendMessage(PC receiver, String message) {
        // send message to receiver.getPort() using socket programming or other means
    }
}


