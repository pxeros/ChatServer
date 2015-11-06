import java.util.*;

/**
 * <b> CS 180 - Project 4 - Chat Server Skeleton </b>
 * <p>
 * 
 * This is the skeleton code for the ChatServer Class. This is a private chat
 * server for you and your friends to communicate.
 * 
 * @author (Your Name) <(YourEmail@purdue.edu)>
 * 
 * @lab (Your Lab Section)
 * 
 * @version (Today's Date)
 *
 */
public class ChatServer {
	private int maxMessages;
	private User[] users;
	private CircularBuffer buffer;

	public ChatServer(User[] users, int maxMessages) {
		this.users = users;
		this.maxMessages = maxMessages;
		this.buffer = new CircularBuffer(this.maxMessages + 1);
	}
	// make Sure to add ad find out where to add the Timeout error and set the Session cookie of the user = 0
	//  when it occurs.
	public String addUser(String[] args){
		SessionCookie n = new SessionCookie(1234567890);
		if (this.users[0].equals(null)){
			this.users[0] = new User("cs180", "root", n);
		}
		String validchars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
		if (args[2].length() < 1 || args[2].length() > 20){
			return MessageFactory.makeErrorMessage(22);
		}
		for (int i = 0; i < args[2].length(); i++){
			if (validchars.indexOf(args[2].charAt(i)) == -1) {
				return MessageFactory.makeErrorMessage(22);
			}
		}
		for (int i = 0; i < users.length; i++){
			if (args[2].equals(users[i].getName())){
				return MessageFactory.makeErrorMessage(22);
			}
		}
		int position = 0;
		while (users[position] != null){
			position++;
		}
		users[position] = new User(args[3], args[2], n);
		return "SUCCESS\r\n";
	}
	public String userLogin(String[] args){
		int i = 0;
		for (i = 0; i < users.length; i++){
			if(args[1].equals(users[i].getName()) && users[i].checkPassword(args[2])){}
			break;
		}
		if (i == users.length){
			return MessageFactory.makeErrorMessage(21);
		}
		else if (users[i].getCookie() != null){
			return MessageFactory.makeErrorMessage(115, "USER ALREADY LOGGED IN\r\n");
		}
		users[i].setCookie(new SessionCookie(784292749));
		users[i].getCookie().updateTimeOfActivity();
		return "SUCCESS\t0234\r\n";
	}

	/**
	 * This method begins server execution.
	 */
	public void run() {
		boolean verbose = false;
		System.out.printf("The VERBOSE option is off.\n\n");
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.printf("Input Server Request: ");
			String command = in.nextLine();

			// this allows students to manually place "\r\n" at end of command
			// in prompt
			command = replaceEscapeChars(command);

			if (command.startsWith("kill"))
				break;

			if (command.startsWith("verbose")) {
				verbose = !verbose;
				System.out.printf("VERBOSE has been turned %s.\n\n", verbose ? "on" : "off");
				continue;
			}

			String response = null;
			try {
				response = parseRequest(command);
			} catch (Exception ex) {
				response = MessageFactory.makeErrorMessage(MessageFactory.UNKNOWN_ERROR,
						String.format("An exception of %s occurred.", ex.getMessage()));
			}

			// change the formatting of the server response so it prints well on
			// the terminal (for testing purposes only)
			if (response.startsWith("SUCCESS\t"))
				response = response.replace("\t", "\n");

			// print the server response
			if (verbose)
				System.out.printf("response:\n");
			System.out.printf("\"%s\"\n\n", response);
		}

		in.close();
	}
	public String postMessage(String[] args, String name) {
		if (args[2].length() < 1) { // does this take care of the intial white space infront

			return ""; // WHAT TO DO HERE
		}

		else {
			for (int i = 0; i < users.length; i++ ) {
				// will loop through the user array to find the specific user that is posting
				if (args[1].equals(users[i].getCookie())) {// finds matching cookie
					users[i].getCookie().updateTimeOfActivity();//?????
					String message = name + ": " + args[2];
					return message;
				}

			}

			// if the loop get here the user if not found *********

		}
		return ""; // WHAT DO HERE?????? IN EXCEPTION

	}

	public String getMessages(String[] args) {
		if (args == null) {
			// what DO HERE or NO
		}

		for (int i = 0; i < users.length; i ++ ) {
			if (args[1].equals(users[i].getCookie())) { //matches cookie to user
				if (!(Integer.parseInt(args[2]) >= 1)) { // checks if numMessages is >= 1

					// HAVE TO THROW INVALID_VALUE_ERROR************
				}

				else {
					String serverMessage = "";
					String [] messages = buffer.getNewest(Integer.parseInt(args[2]));
					serverMessage = "SUCCESS";
					// receives the sets of messages from the buffer in an array
					for (int j = 0; j < messages.length; j ++ ) {
						if (j == messages.length - 1) { // handels for the last message in the array
							serverMessage = serverMessage + "\r\n";
							return serverMessage;
						}

						serverMessage = serverMessage + "\t" + messages[j];
					}
				}
			}
		}
		return ""; // no sucess here ** IS THIS CORRECT HANDELING???
	}

	/**
	 * Replaces "poorly formatted" escape characters with their proper values.
	 * For some terminals, when escaped characters are entered, the terminal
	 * includes the "\" as a character instead of entering the escape character.
	 * This function replaces the incorrectly inputed characters with their
	 * proper escaped characters.
	 * 
	 * @param str
	 *            - the string to be edited
	 * @return the properly escaped string
	 */
	private static String replaceEscapeChars(String str) {
		str = str.replace("\\r", "\r");
		str = str.replace("\\n", "\n");
		str = str.replace("\\t", "\t");

		return str;
	}

	/**
	 * Determines which client command the request is using and calls the
	 * function associated with that command.
	 * 
	 * @param request
	 *            - the full line of the client request (CRLF included)
	 * @return the server response
	 */
	public String parseRequest(String request) {
		return request;
	}
}
