package Chat;

import Mainframe.PC;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;



public class ChatFunction {

    private Queue<String> user1ToUser2 = new LinkedList<>();
    private Stack<String> history = new Stack<String>();
    private Stack<String> mergedata = new Stack<String>();
    public void sendUser1ToUser2(String message) {

        enqueueMessage(user1ToUser2, message);
    }


    private void enqueueMessage(Queue<String> queue, String message) {
        queue.add(message.toString());
    }

    public String receiveUser2(PC sender) {
        return dequeueMessage(user1ToUser2,sender.getID()+"[Port: "+sender.getPort()+"]" , 0);
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

    public String printHistory(PC sender) {
        return dequeueMessage(user1ToUser2, sender.getID()+"[Port: "+sender.getPort()+"]", 1);
    }
}


