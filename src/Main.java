import Chat.What;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);
                What chatBox = new What();

                System.out.println("Welcome to the chat box!");
                System.out.println("Enter 'exit' at any time to end the chat.");

                while (true) {
                    String message;

                    // User 1 sends a message
                    System.out.print("User 1: ");
                    message = scanner.nextLine();
                    if (message.equals("exit")) {
                        break;
                    }
                    chatBox.sendUser1ToUser2(message);

                    // Split message into chunks of 5 words
                    String[] words = message.split("\\s+");
                    String chunk = "";
                    for (int i = 0; i < words.length; i++) {
                        chunk += words[i] + " ";
                        if ((i + 1) % 5 == 0 || i == words.length - 1) {
                            chatBox.sendUser1ToUser2(chunk.trim());
                            System.out.println("\nMessage has reach 5 word : " + chunk.trim());
                            chunk = "";
                        }
                    }

                    // User 2 receives User 1's message
                    message = chatBox.receiveUser2();
                    if (message == null) {
                        continue;
                    }
                    words = message.split("\\s+");
                    chunk = "";
                    for (int i = 0; i < words.length; i++) {
                        chunk += words[i] + " ";
                        if ((i + 1) % 5 == 0 || i == words.length - 1) {
                            System.out.println("\nMessage received reach 5 word : " + chunk.trim());
                            chunk = "";
                        }
                    }
                    System.out.println("\nUser 2: " + message);

                }
    }
}