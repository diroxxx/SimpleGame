package GameProject;

import java.util.*;

public class Battlefield {

    public static void main(String[] args) {


        System.out.println("*".repeat(10) + "INSTRUCTION FOR PLAYER" + "*".repeat(10));
        System.out.println("""
                The game is about defeating the largest number of monsters,
                The player has at his disposal physical and magical attacks,
                Performing attacks inflicts damage and decreases the amount of stamina and mana, -1 and -2 respectively,
                After defeating half of the opponents, the player's damage increases -2, -4,
                If the player chooses an attack for which he has no stamina or mana, his turn is lost,
                Attack with shield protects player from damage, attacks always physical,
                A player can have a helper, but it can only appear after half of the monsters have been defeated, the number of monsters is drawn from a range of 7-13,
                A monster always attacks with a magic attack if it has enough mana, otherwise it attacks physically.
                """);
        System.out.println("*".repeat(10) + "END OF INSTRUSCION" + "*".repeat(10));
        System.out.println("To start, allocate your character's points\n" +
                "example syntax: 'mana 5','hp 5', 'stamina 5' (with space)");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        int stamina = 0;
        int mana = 0;
        int hp = 0;
        int box = 15;
        int number;
        String name;

        while (box != 0) {
            try {
                name = scanner.next().toLowerCase();
                number = scanner.nextInt();

                if (number <= box && number > 0) {
                    switch (name) {
                        case "mana" -> {
                            mana += number;
                            box -= number;
                        }
                        case "stamina" -> {
                            stamina += number;
                            box -= number;
                        }
                        case "hp" -> {
                            hp += number;
                            box -= number;
                        }
                        default -> System.out.println("entered data outside the range");
                    }
                    if (box != 0) {
                        System.out.println(box + " points left to give away");
                    }
                } else {
                    System.out.println("The number is greater than the other points or smaller than 0, try again");
                }

            } catch (InputMismatchException e) {
                System.out.println("invalid data, try again");
            }
        }


        int numberOfMonsters = (int) (7 + Math.random() *(13 - 7 + 1));
        Character hero = new Character("Hero", hp, mana, stamina);
        LinkedList<Monster> arrOfMonsters = new LinkedList<>();

        int rand = (int) (4 + Math.random() * (10 - 4 + 1));
        for (int i = 1; i <= numberOfMonsters; i++) {

            if (i == (numberOfMonsters / 2)) {
                arrOfMonsters.add(new Monster("Allay" + i, 3, 5, 5, true, i));
            } else if (i > 7) {
                arrOfMonsters.add(new Monster("Monster" + i, 4, 5, 5, false, i));
            } else if (i <= 4) {
                arrOfMonsters.add(new Monster("Monster" + i, 3, 3, 6, false, i));
            } else  if (i > 4 && i < 7){
                arrOfMonsters.add(new Monster("Monster" + i, 2, 6, 7, false, i));
            }
        }

        System.out.println("-".repeat(10) + "time to start the fight!!!" + "-".repeat(10));
        System.out.println();
        System.out.println("Numbers of monsters:" + numberOfMonsters);

        boolean isAlive = true;
        Character[] friendlyMonster = new Character[1];


        while (isAlive && arrOfMonsters.size() != 0) {// Main loop
            boolean allayaIsHere = friendlyMonster[0] != null;
            if (!arrOfMonsters.getFirst().isFriendly()) {
                hero.increaseAttack(arrOfMonsters.getFirst().getQueueNumber(),numberOfMonsters);
                if (hero.getExp() >= 5) {
                    System.out.println("Your hero unlocked  extra 5 points add to one of attributes \nType number: 1-mana, 2-stamina, 3-hp");
                    int number2 = scanner.nextInt();
                    hero.extraPoints(number2);


                }
                System.out.println("Monster stats: " + arrOfMonsters.getFirst().toString());
                hero.printItemCount();
                System.out.println("choose your move: \n1-magic attack\n2-physical attack\n3-ask allay to help ("+ allayaIsHere +")\n4-use items");

                while (arrOfMonsters.getFirst().getHealth() > 0 && hero.getHealth() > 0) {
                    System.out.println("*".repeat(20));
                    if (allayaIsHere && friendlyMonster[0].getHealth() > 0) {
                    System.out.println(friendlyMonster[0].toString((Monster) friendlyMonster[0]));
                    }
                    System.out.println(hero);
                    System.out.println(arrOfMonsters.getFirst().infoOfMonsterPerRound());
                    System.out.println("*".repeat(20));
                    int attack = scanner.nextInt();
                    boolean fight = true;

                    switch (attack) {
                        case 1 -> {
                            if (hero.getMana() >= -hero.getValueOfMagic()){
                                hero.magicAttack(arrOfMonsters.getFirst());
                                System.out.println("hero -> " + hero.getValueOfMagic() +
                                        " magic damage -> monster");
                            } else {
                                System.out.println("Not enough mana");
                            }


                        }case 2 -> {
                            if (hero.getStamina() >= -hero.getValueOfPhysical()) {
                            hero.physicalAttack(arrOfMonsters.getFirst());
                            System.out.println("hero -> " + hero.getValueOfPhysical() +
                                    " physical damage -> monster");
                            } else {
                                System.out.println("Not enough stamina");
                            }

                        }case 3 -> {
                            fight = false;
                            if (friendlyMonster[0] != null && friendlyMonster[0].getHealth() > 0) {
                                if (friendlyMonster[0].getMana() >= 3 &&  friendlyMonster[0].getHealth() > 0) {
                                    arrOfMonsters.getFirst().physicalAttack(friendlyMonster[0]);
                                    System.out.println("allay -> " + friendlyMonster[0].getValueOfMagic() +
                                        " magical damage -> Monster hp: " + arrOfMonsters.getFirst().getHealth());
                                    friendlyMonster[0].magicAttack((arrOfMonsters.getFirst()));
                                    System.out.println("Monster ->" + arrOfMonsters.getFirst().getValueOfPhysical() +
                                            " physical damage ->, allay hp: " + friendlyMonster[0].getHealth());
                                } else  {
                                    arrOfMonsters.getFirst().physicalAttack(friendlyMonster[0]);
                                    System.out.println("allay ->" + friendlyMonster[0].getValueOfPhysical() +
                                            " physical damage -> monster hp: " + arrOfMonsters.getFirst().getHealth());
                                    friendlyMonster[0].magicAttack((arrOfMonsters.getFirst()));
                                    System.out.println("Monster " + arrOfMonsters.getFirst().getValueOfPhysical() +
                                            "physical damage -> allay hp: " + friendlyMonster[0].getHealth());
                                }

                            } else {
                                System.out.println("you don't have a allay");
                            }

                        }case 4 -> {
                            hero.printItemCount();
                            System.out.println("Choose which item you wanna use:\n5-stamina\n6-mana\n7-hp\n8-shield");
                            int itemsChose =scanner.nextInt();
                            hero.useItem(itemsChose, arrOfMonsters.getFirst());
                            fight = false;
                        }

                    }

                    if (arrOfMonsters.getFirst().getHealth() > 0  && fight ) {
                            if (arrOfMonsters.getFirst().getMana() >= -arrOfMonsters.getFirst().getAttackMg()){
                                arrOfMonsters.getFirst().magicAttack(hero);
                                System.out.println("Monster -> " + arrOfMonsters.getFirst().getAttackMg() +
                                        " magic damage -> hero");
                            }else {
                                arrOfMonsters.getFirst().physicalAttack(hero);
                                System.out.println("Monster -> " + arrOfMonsters.getFirst().getAttackPh() +
                                    " physical damage -> hero");
                            }
                        }
                        fight = true;
                    }

                    if (hero.getHealth() <= 0){
                        System.out.println("You lose, defeated monsters " + ((arrOfMonsters.getFirst().getQueueNumber()) -1) + " of " + (arrOfMonsters.getLast().getQueueNumber()-1));
                        isAlive = false;

                    }
                    if (arrOfMonsters.getFirst().getHealth() <= 0) {
                        System.out.println("you won against Monster " + arrOfMonsters.getFirst().getQueueNumber());
                        hero.addItemFromMonster(arrOfMonsters.getFirst());
                        hero.addExp(hero.addExp(arrOfMonsters.getFirst()));
                        arrOfMonsters.removeFirst();
                    }

                    if (friendlyMonster[0] != null && friendlyMonster[0].getHealth() <= 0) {
                        System.out.println("Your allay died in fight!");
                        friendlyMonster[0] = null;

                }


            }else{
                    System.out.println("you met ally!!!!");
                    friendlyMonster[0] = arrOfMonsters.getFirst();
                    arrOfMonsters.removeFirst();
            }
        }

        if (arrOfMonsters.isEmpty()) {
            System.out.println("You have defeated all the monsters");
        }

    }
}