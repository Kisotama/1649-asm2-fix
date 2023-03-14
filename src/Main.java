import Chat.ChatFunction;
import Mainframe.PC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ChatFunction chatBox = new ChatFunction();
        int limit = 250;


        PC pc1 = new PC("pc1", 1234);
        PC pc2 = new PC("pc2", 5678);
        PC pc3 = new PC("pc3", 9101);
        PC pc4 = new PC("pc4", 9102);
        PC pc5 = new PC("pc5", 9103);
        PC pc6 = new PC("pc6", 9104);
        PC pc7 = new PC("pc7", 9105);

        List<PC> pcList = Arrays.asList(pc1, pc2, pc3, pc4, pc5, pc6, pc7);

        PC Default = pc1;
        int count = 0;
        StringBuilder messageBuilder = new StringBuilder();
        System.out.println("Welcome to the chat box!");
        System.out.println("Enter 'exit' at any time to end the chat.");

        String message;

        // Set the default selected PC to pc1
        PC select = pc1;

        System.out.println("Select a PC to send the message to (default: pc1):");
        for (int i = 0; i < pcList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, pcList.get(i).getID());
        }
        while (true) {

            System.out.printf("Input here:");

            int choice = scanner.nextInt();

            if (choice > 1 && choice <= pcList.size()) {
                select = pcList.get(choice - 1);


                scanner.nextLine();
                // User 1 sends a message
                System.out.printf("Sending message to %s: ", select.getID());
                message = scanner.nextLine();
                if (message.equals("exit")) {
                    break;
                }
                messageBuilder.append(message);
                count += message.length();

                if (count > limit) {
                    // Split message into chunks of 5 characters
                    String chunk = "";
                    int segmentnumber = 1;
                    for (int i = 0; i < messageBuilder.length(); i++) {
                        chunk += messageBuilder.charAt(i);
                        if ((i + 1) % limit == 0 || i == messageBuilder.length() - 1) {
                            chatBox.sendUser1ToUser2(String.format("%d:%s", segmentnumber, chunk));
                            System.out.println(String.format("\n%s[%d] sends to %s[%d] [%d]: %s", Default.getID(), Default.getPort(), select.getID(), select.getPort(), segmentnumber, chunk.trim()));
                            chunk = "";
                            segmentnumber++;
                            // Wait for 1 second before checking for User 2's response
                        }
                    }
                } else {
                    chatBox.sendUser1ToUser2(String.format("%d:%s", 1, messageBuilder.toString()));
                    System.out.println(String.format("\n%s[%d] sends to %s[%d] [%d]: %s", Default.getID(), Default.getPort(), select.getID(), select.getPort(), 1, messageBuilder.toString()));
                }

                message = chatBox.receiveUser2();
                if (message != null) {
                    String historymessage = chatBox.printHistory();
                    System.out.println(historymessage);
                    System.out.println("Merging....");
                    try {
                        Thread.sleep(1000); // delay for 1 second (1000 milliseconds)
                    } catch (InterruptedException e) {
                        // handle the exception
                    }
                    System.out.println("\n" + select.getID() + " received: " + message + "\n");


//                String[] segments = message.split(":");
//                int segmentNum = Integer.parseInt(segments[0]);
//                String segment = segments[1];
//                if (segmentNum % 5 == 0 || segmentNum == message.length()) {
//                    messageBuilder1.append(segment);
//                    String receivedMessage = messageBuilder.toString();
//                    System.out.println("\n" + select.getID() + " \nreceives from " + Default.getID() + ": " + receivedMessage.trim());
//                    messageBuilder1 = new StringBuilder();
                    //}
//                else {
//                    messageBuilder1.append(segment);
//                }
                    // System.out.println("\n" + select.getID() + ": " + message);
                }
            }
            else
                System.out.println("Please enter from 2 to 7");
        }


    }
}

