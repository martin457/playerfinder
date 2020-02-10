package gui;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class GuiElementHolder {

    private static final String DEFAULT_SKIN = "74";
    private static final String MAP = "map";
    private static final String RESOURCE_ROOT = "gui/";
    private static final String PNG = ".png";
    private static final String SKIN_PREFIX = "Skin_";
    private static final String TITLE = "Playerfinder";

    private Label name = new Label();
    private Label distance = new Label();
    private Label hp = new Label();
    private Label weapon = new Label();
    private ImageView skin = new ImageView();
    private ImageView map = new ImageView();
    private BorderPane borderPane = new BorderPane();
    private VBox text = new VBox();
    private StackPane sp = new StackPane();
    private Rectangle marker;
    private String skinModel = "";


    public GuiElementHolder(Stage primaryStage, Group root) {
        setUpLabels();
        initMap();
        setUpPanes();
        setUpScene(primaryStage, root);
    }

    private Image loadImageByName(String name) {
        String path = RESOURCE_ROOT + name + PNG;
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
    }

    private void setUpScene(Stage primaryStage, Group root) {
        Scene scene = new Scene(root);
        root.getChildren().add(sp);
        primaryStage.setResizable(false);
        primaryStage.setHeight(325.0);
        primaryStage.setWidth(300.0);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setAlwaysOnTop(true);
    }

    private void initMap() {
        updateSkin(DEFAULT_SKIN);
        setImage(map, MAP);
        initMarker();
        sp.setMaxSize(300, 300);
        map.setScaleX(3);
        map.setScaleY(3);
        map.setTranslateX(-350);
        map.setTranslateY(-350);
    }

    private void initMarker() {
        marker = new Rectangle(145, 145, 10, 10);
        marker.setStroke(Color.BLACK);
        marker.setFill(Color.RED);
    }

    private void setUpLabels() {
        setUpLabel(name);
        setUpLabel(distance);
        setUpLabel(hp);
        setUpLabel(weapon);
    }

    private void setUpLabel(Label label) {
        label.setFont(Font.font(20));
        label.setStyle("-fx-font-weight: bold");
        label.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setTextFill(Color.WHITE);
    }

    private void setUpPanes() {

        HBox hBox = new HBox();
        text.getChildren().addAll(name, distance, hp, weapon, skin);
        hBox.getChildren().addAll(text);

        borderPane.setTop(hBox);
        borderPane.getChildren().add(marker);
        sp.getChildren().addAll(map, borderPane);
    }

    public String getDistanceText() {
        return this.distance.getText();
    }

    public void setDistanceText(String distance) {
        this.distance.setText(distance);
    }

    public void setHpText(String hp) {
        this.hp.setText(hp);
    }

    public void setWeaponText(String weapon) {
        this.weapon.setText(weapon);
    }

    public void setNameText(String text) {
        this.name.setText(text);
    }

    public void updateMarkerPosition(Double x, Double y) {
        map.setTranslateX(x);
        map.setTranslateY(y);
    }

    public void updateSkin(String id) {
        if (id != null && !id.equals(skinModel) && id.chars().allMatch(Character::isDigit)) {
            skinModel = id;
            setImage(skin, SKIN_PREFIX + id);
        }
    }

    private void setImage(ImageView imageView, String img) {
        imageView.setImage(loadImageByName(img));
    }

    public void resetValues() {
        setHpText("");
        setDistanceText("");
        setWeaponText("");
        updateSkin(DEFAULT_SKIN);
        updateMarkerPosition(-350.0, -350.0);
    }
}
