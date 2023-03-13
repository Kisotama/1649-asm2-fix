package Chat;
import Mainframe.PC;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

import static sun.rmi.registry.RegistryImpl.getID;


public class What {


        private Queue<String> user1ToUser2 = new LinkedList<>();
        private Queue<String> user2ToUser1 = new LinkedList<>();
        private Stack<String> history = new Stack<String>();

        public void sendUser1ToUser2(String message) {
            PC receiver = new PC("pc2", 5678);
            sendMessage(receiver, message);
        }

        public void sendUser2ToUser1(String message) {
            enqueueMessage(user2ToUser1, message);
        }

        private int segmentNumber = 1;

        private void enqueueMessage(Queue<String> queue, String message) {
            String[] words = message.split("\\s+");
            int chunkSize = 250;
            for (int i = 0; i < words.length; i += chunkSize) {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < Math.min(i + chunkSize, words.length); j++) {
                    sb.append(words[j]).append(" ");
                }
                String segmentedMessage = String.format("%s [Segment %d]: %s", getID(), segmentNumber++, sb.toString().trim());
                queue.add(segmentedMessage);
            }
        }

        public String receiveUser1() {
            return dequeueMessage(user2ToUser1, "User 2");
        }

        public String receiveUser2() {
            return dequeueMessage(user1ToUser2, "User 1");
        }

        private String dequeueMessage(Queue<String> queue, String sender) {
            if (queue.isEmpty()) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                String message = queue.remove();
                history.push(sender + ": " + message);
                String[] parts = message.split(": ");
                String segment = parts[1];
                sb.append(segment).append(" ");
            }
            return sb.toString().trim();
        }

        public void printHistory() {
            System.out.println("Chat History:");
            while (!history.isEmpty()) {
                String message = history.pop();
                System.out.println(message);
            }
        }
        private void sendMessage(PC receiver, String message) {
            // send message to receiver.getPort() using socket programming or other means
        }
    }


