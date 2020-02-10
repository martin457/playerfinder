package server;

import model.GameInformation;
import service.GuiUpdaterService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection implements Runnable {
    private Socket socket;
    private GuiUpdaterService updaterService;

    public SocketConnection(Socket socket, GuiUpdaterService updaterService) {
        this.socket = socket;
        this.updaterService = updaterService;
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        try {
            System.out.println("scanning");
            var in = new Scanner(socket.getInputStream());
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println("received line: " + line);
                parseLine(line);
            }
        } catch (Exception e) {
            System.out.println("Error:" + socket);
        } finally {
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Unable to close connection");
        }
        System.out.println("Closed: " + socket);
    }
    private void parseLine(String line) {
        String[] splitLine = line.split(";");
        if (splitLine.length == 1) {
            updaterService.updateName(new GameInformation(splitLine[0]));
        } else if (splitLine.length == 7) {
            try {
                updaterService.updateValues(createGameInformation(splitLine));
            } catch (Exception e) {
                System.out.println("Error parsing line");
            }
        }
    }

    private GameInformation createGameInformation(String[] line) {
        return new GameInformation(Double.parseDouble(line[0]), Double.parseDouble(line[1]), line[2], line[3], line[4], line[5], line[6]);
    }
}
