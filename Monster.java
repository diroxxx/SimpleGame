package GameProject;

import java.util.LinkedList;

public class Monster extends Character{

    private int exptogive;
    private boolean isFriendly;
    private int attackPh;
    private int attackMg;
    private int queueNumber;
    LinkedList<Item> monstersItems;


    public Monster(String nickname, int health, int mana, int stamina, boolean isFriendly, int queueNumber) {
        super(nickname, health, mana, stamina);
        this.exptogive = 3;
        this.isFriendly = isFriendly;
        this.attackPh = -1;
        this.attackMg = -2;
        this.queueNumber = queueNumber;
        this.monstersItems = new LinkedList<>();
        addItemsToList(queueNumber);

    }



    public void magicAttack(Character character) {
        if (getMana() >= -attackMg){
            character.addHealth(attackMg);
              addMana(attackMg);
            }
        }


    public void physicalAttack(Character character) {// potwór atakuje bohatera

        if (getStamina() >= -attackPh){
            character.addHealth(attackPh);
            addStamina(attackPh);

        }
    }

    public void addItemsToList(int queueNumber) {
        if (queueNumber <= 4 ) {
            monstersItems.add(new Item());
        } else if (queueNumber <= 7) {
            monstersItems.add(new Item());
            monstersItems.add(new Item());
            setAttackMg(-3);
            setAttackPh(-2);

        } else {
            monstersItems.add(new Item());
            monstersItems.add(new Item());
            monstersItems.add(new Item());
            setAttackMg(-4);
        }
    }

    @Override
    public void addHealth(int health) {
        super.addHealth(health);
    }

    @Override
    public void addMana(int mana) {
        super.addMana(mana);
    }

    @Override
    public void addStamina(int stamina) {
        super.addStamina(stamina);
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public int getMana() {
        return super.getMana();
    }

    @Override
    public int getStamina() {
        return super.getStamina();
    }


    public void setAttackPh(int attackPh) {
        this.attackPh = attackPh;
    }

    public void setAttackMg(int attackMg) {
        this.attackMg = attackMg;
    }


    public boolean isFriendly() {
        return isFriendly;
    }

    public int getAttackPh() {
        return attackPh;
    }

    public int getAttackMg() {
        return attackMg;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public int getExptogive() {
        return exptogive;
    }

    @Override
    public String toString() {
        return "Monster" +
                "EXPtoGive=" + exptogive +
                ", hp=" + getHealth() +
                ", isFriendly=" + isFriendly +
                ", attackPh=" + attackPh +
                ", attackMg=" + attackMg +
                ", queueNumber=" + queueNumber +
                ", monstersItems=" + monstersItems +
                '}';
    }

    public String infoOfMonsterPerRound() {
            return "Monster " + getQueueNumber()  +
                    ", health=" + getHealth() +
                    ", mana=" + getMana() +
                    ", stamina=" + getStamina()
                    ;

    }

}
