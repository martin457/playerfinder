package server;

import service.GuiUpdaterService;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketListener {

    private GuiUpdaterService updaterService;

    public SocketListener(GuiUpdaterService updaterService) {
        this.updaterService = updaterService;
    }

    public void start() {
        new Thread(() -> {
            try (var listener = new ServerSocket(5230)) {
                ExecutorService executor = Executors.newFixedThreadPool(20);
                System.out.println("Server running...");
                while (true) {
                    executor.execute(new SocketConnection(listener.accept(), updaterService));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}