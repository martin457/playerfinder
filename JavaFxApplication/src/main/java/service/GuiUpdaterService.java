package service;

import gui.GuiElementHolder;
import javafx.application.Platform;
import model.GameInformation;

public class GuiUpdaterService {

    private GuiElementHolder holder;

    public GuiUpdaterService(GuiElementHolder holder) {
        this.holder = holder;
    }

    public void updateName(GameInformation gameInformation) {
        if (gameInformation.getName() != null) {
            Platform.runLater(() -> holder.setNameText(gameInformation.getName()));
        }
        if (!holder.getDistanceText().isBlank()) {
            Platform.runLater(() -> {
                holder.resetValues();
            });
        }
    }

    public void updateValues(GameInformation gameInformation) {
        Platform.runLater(() -> {
            holder.updateMarkerPosition(convertX(gameInformation.getX()), convertY(gameInformation.getY()));
            holder.setNameText(gameInformation.getName());
            holder.setWeaponText(gameInformation.getWeapon());
            holder.setDistanceText(gameInformation.getDistance());
            holder.setHpText(gameInformation.getHealth());
            if (!gameInformation.getSkin().equals("74")) {
                holder.updateSkin(gameInformation.getSkin());
            }
        });
    }

    private Double convertX(Double x) {
        return x > 0.00 ? -350 - x / 2 + Math.abs(0.002 * x) : -350 - x / 2 - Math.abs(0.002 * x);
    }

    private Double convertY(Double y) {
        return y > 0.00 ? -350 + y / 1.958 - Math.abs(0.01 * y) : -350 + y / 1.958 + Math.abs(0.01 * y);
    }
}
