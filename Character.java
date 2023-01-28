package GameProject;

import java.util.LinkedList;

public class Character {

    private String nickname;
    private int health;
    private int mana;
    private int stamina;
    private int shield;
    private int exp;
    private int valueOfMagic;
    private int valueOfPhysical;
    Item item;
    private LinkedList<Item> items;




    public Character(String nickname, int health, int mana, int stamina) {
        this.nickname = nickname;
        this.health = health;
        this.mana = mana;
        this.stamina = stamina;
        this.shield = 0;
        this.exp = 0;
        this.valueOfMagic = -2;
        this.valueOfPhysical = -1;
        this.item = new Item();
        this.items = new LinkedList<>();
        items.add(item);
    }



    public void magicAttack(Monster monster) {// bohater atakuje potwora

            if (getMana() >= -valueOfMagic){
                monster.addHealth(valueOfMagic);
                addMana(valueOfMagic);

            }
//            else {
//                System.out.println("hero, not enough mana");
//            }
    }

    public void physicalAttack(Monster monster) {// bohater atakuje potwora

        if (getStamina() >= -valueOfPhysical){
            monster.addHealth(valueOfPhysical);
            addStamina(valueOfPhysical);

        }
//        else {
//            System.out.println("hero, not enough stamina");
//        }
    }

    public void useItem(int number, Monster monster) {
       for (int i = 0; i < items.size(); i++) {
           if (number == 5 && items.get(i).getType().equals("stamina")) {
               System.out.print("old stamina: " + getStamina());
               addStamina(4);
               System.out.println(" new stamina: " + getStamina());
               items.remove(i);
               break;
           } else if (number == 6 && items.get(i).getType().equals("mana")) {
               System.out.print("old mana: " + getMana());
               addMana(4);
               System.out.println(" new mana: " + getMana());
               items.remove(i);
           } else if (number == 7 && items.get(i).getType().equals("health")) {
               System.out.print("old hp: " + getHealth());
               addHealth(4);
               System.out.println(" new hp: " + getHealth());
               items.remove(i);
           }else if (number == 8 && items.get(i).getType().equals("shield")) {
               physicalAttack(monster);
               System.out.println("shield armored and attacking physical -> monster");
               items.remove(i);

           }
       }


    }

    public void increaseAttack(int numberOfDefeatedMonsters , int allOfMonsters) {
        if (allOfMonsters / 2 == (numberOfDefeatedMonsters - 1)) {
            setValueOfMagic(-4);
            setValueOfPhysical(-2);
            System.out.println("Your attacks increased: magic: -4, physical: -2");
        }
    }

    public void printItemCount() {
        int healthCount = 0;
        int staminaCount = 0;
        int manaCount = 0;
        int shieldCount = 0;
        for (Item item : items) {
            switch (item.getType()) {
                case "health" -> healthCount++;
                case "stamina" -> staminaCount++;
                case "mana" -> manaCount++;
                case "shield" -> shieldCount++;
            }
        }
        System.out.println("hp items: " + healthCount + ", Stamina items: " +
                staminaCount + ", Mana items: " + manaCount + ", Shield items: " + shieldCount);
    }

    public void extraPoints(int number) {
        switch (number) {
            case 1 -> {
                addMana(5);
                System.out.println("Added 5 points to mana");
            }
            case 2 -> {
                addStamina(5);
                System.out.println("Added 5 points to stamina");
            }
            case 3 -> {
                addHealth(5);
                System.out.println("Added 5 points to hp");
            }
        }
        setExp(0);

    }

    public void addItemFromMonster(Monster monster) {
        while (!monster.monstersItems.isEmpty()) {
            items.addLast(monster.monstersItems.getFirst());
            System.out.println("Added " + monster.monstersItems.getFirst() + " to your eq" );
            monster.monstersItems.removeFirst();
        }
    }


    public int addExp(Monster monster) {
        return monster.getExptogive();
    }


    public void addItem(Item item) {
        items.add(item);
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void addMana(int mana) {
        this.mana += mana;
    }

    public void addStamina(int stamina) {
        this.stamina += stamina;
    }

    public void addShield(int shield) {
        this.shield += shield;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getStamina() {
        return stamina;
    }

    public int getValueOfMagic() {
        return valueOfMagic;
    }

    public int getValueOfPhysical() {
        return valueOfPhysical;
    }

    public void setValueOfMagic(int valueOfMagic) {
        this.valueOfMagic = valueOfMagic;
    }

    public void setValueOfPhysical(int valueOfPhysical) {
        this.valueOfPhysical = valueOfPhysical;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return
                 nickname + '\'' +
                ", health=" + health +
                ", mana=" + mana +
                ", stamina=" + stamina +
                         ", exp=" + exp
                ;
    }
    public String toString(Monster monster) {
        if (monster.isFriendly()) {
            return "Allay" +
                    ", hp=" + getHealth() +
                    ", mana=" + getMana() +
                    ", stamina=" + getStamina();
        }
        return null;
    }


}
