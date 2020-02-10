package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import server.SocketListener;
import service.GuiUpdaterService;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;


    public void start(String[] args) {
        Application.launch(args);
    }

    private void setHandlers(Group group, Stage primaryStage) {
        group.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        group.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            if (KeyCode.ESCAPE == e.getCode()) {
                primaryStage.close();
                System.exit(0);
            }
        });
    }

    private void startListening(GuiUpdaterService updaterService) {
        SocketListener socketListener = new SocketListener(updaterService);
        socketListener.start();
    }

    public void start(Stage primaryStage) {
        Group root = new Group();

        GuiElementHolder guiElementHolder = new GuiElementHolder(primaryStage, root);
        setHandlers(root, primaryStage);
        primaryStage.show();
        startListening(new GuiUpdaterService(guiElementHolder));
    }
}
