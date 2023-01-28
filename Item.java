package GameProject;

import java.util.Arrays;

public class Item {

    private String name;
    private String type;
    private String[] arrString = {"health","stamina", "mana", "shield"};
    private int randtype = (int) (Math.random() * 4);


    public Item() {
        this.type = arrString[randtype];
        this.name = "Potion";
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
