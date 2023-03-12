package Chat;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;


public class What {


        private Queue<String> user1ToUser2 = new LinkedList<>();
        private Queue<String> user2ToUser1 = new LinkedList<>();
        private Stack<String> history = new Stack<String>();

        public void sendUser1ToUser2(String message) {
            enqueueMessage(user1ToUser2, message);
        }

        public void sendUser2ToUser1(String message) {
            enqueueMessage(user2ToUser1, message);
        }

        private void enqueueMessage(Queue<String> queue, String message) {
            String[] words = message.split("\\s+");
            int chunkSize = 10;
            for (int i = 0; i < words.length; i += chunkSize) {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < Math.min(i + chunkSize, words.length); j++) {
                    sb.append(words[j]).append(" ");
                }
                queue.add(sb.toString().trim());
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
            String message = queue.remove();
            history.push(sender + ": " + message);
            return message;
        }

        public void printHistory() {
            System.out.println("Chat History:");
            while (!history.isEmpty()) {
                String message = history.pop();
                System.out.println(message);
            }
        }
    }


