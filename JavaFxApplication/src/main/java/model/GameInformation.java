package model;

public class GameInformation {

    private String health;
    private String name;
    private String distance;
    private String skin;
    private Double x;
    private Double y;
    private String weapon;

    public GameInformation(String name) {
        this.name = name;
    }

    public GameInformation(Double x, Double y, String name, String distance, String skin, String health, String weapon) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.distance = distance;
        this.skin = skin;
        this.health = health;
        this.weapon = weapon;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
