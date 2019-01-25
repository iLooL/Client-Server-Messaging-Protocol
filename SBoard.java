package board;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SBoard {	
	// args starts reading from index 0 meaning the port number is the first argument
    public static void main(String[] args) throws Exception {
    	
    	// assign port number
    	int portNumber = Integer.parseInt(args[0]);
    	//assign dimensions
    	int width = Integer.parseInt(args[1]);
    	int height = Integer.parseInt(args[2]);
    	int colourLen = args.length - 3;
    	String[] colours = new String[colourLen]; 
    	// create colour array
    	for(int i = 0; i < colourLen; i++) {
    		colours[i] = args[i + 3];
    	}
    	
        System.out.println("The board server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(portNumber);

        try {
            while (true) {
                new Board(listener.accept(), width, height, colours).start();
            }
        } finally {
            listener.close();
        }
    }
    
    public class Pin{
		private int x;
		private int y;
		
		public Pin(int x,int y) {
			this.x = x;
			this.y = x;
		}
	}
    
    public class Note{
    	private String content;
    	private boolean isPinned;
    	private ArrayList<Pin> points;
    	private String colour;
    	
    	public void note(String content, String colour, int x, int y, int width, int height) {
    		this.content = content;
    		this.isPinned = false;
    		Pin p = new Pin(x,y);
    		//this.points.add(Pin(x,y));
    	}
    	
    }
    
    private static class Board extends Thread {
        private Socket socket;
        private int width;
        private int height;
        private String[] colours;
        private ArrayList<Pin> pins;
        private ArrayList<Note> notes;

        public Board(Socket socket, int width, int height, String[] colours) {
            this.socket = socket;
            this.width = width;
            this.height = height;
            this.colours = colours.clone(); // clones one array into another
            this.pins = new ArrayList<Pin>(); // holds all the pins
            this.notes = new ArrayList<Note>(); // holds all notes
        }
        
        public String[] getColours() {
        	return this.colours;
        }
        
        public int getHeight() {
        	return this.height;
        }
        
        public int getWidth() {
        	return this.width;
        }
        
        
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    String input = in.readLine();
                    
                }
            } catch (IOException e) {
            	// put print error statement
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                	// put print error statement
                }
                // put print error statement
            }
        }
    }
}


