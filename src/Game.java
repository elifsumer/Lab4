import java.util.*;

public class Game {
    public static void main(String[] args) {
        Warrior warrior1 = new Warrior();
        warrior1.setName("Warrior1");
        warrior1.setHitPoint(100);
        warrior1.setGender("Male");

        Mage mage1 = new Mage();
        mage1.setName("Mage1");
        mage1.setHitPoint(80);
        mage1.setGender("Female");

        Warrior warrior2 = new Warrior();
        warrior2.setName("Warrior2");
        warrior2.setHitPoint(100);
        warrior2.setGender("Male");

        Mage mage2 = new Mage();
        mage2.setName("Mage2");
        mage2.setHitPoint(80);
        mage2.setGender("Female");

        Warrior warrior3 = new Warrior();
        warrior3.setName("Warrior3");
        warrior3.setHitPoint(100);
        warrior3.setGender("Male");

        Mage mage3 = new Mage();
        mage3.setName("Mage3");
        mage3.setHitPoint(80);
        mage3.setGender("Female");

        Player player1 = new Player("Player1", "password123", new ArrayList<>());
        Player player2 = new Player("Player2", "password456", new ArrayList<>());

        Party party1 = new Party("Party1", new ArrayList<>(), 0, 0);
        party1.addMember(warrior1);
        party1.addMember(mage1);
        party1.addMember(mage3);

        Party party2 = new Party("Party2", new ArrayList<>(), 0, 0);
        party2.addMember(warrior2);
        party2.addMember(mage2);
        party2.addMember(warrior3);


        Battle battle = new Battle(party1, party2, new ArrayList<>(), new ArrayList<>(), player1, player2);
        battle.formTeams();
        battle.startBattle();
        battle.declareWinner();

        System.out.println("Player Info After Battle:");
        player1.printPlayerInfo();
        player2.printPlayerInfo();

        findStrongestPlayer(player1, player2);
    }

    public static void findStrongestPlayer(Player player1, Player player2) {
        Player strongestPlayer = player1;
        double maxDamage = player1.computeTotalDamage();

        if (player2.computeTotalDamage() > maxDamage) {
            strongestPlayer = player2;
            maxDamage = player2.computeTotalDamage();
        }

        System.out.println("\nThe strongest player is: " + strongestPlayer.getName());
        System.out.println("Total Damage: " + maxDamage);
    }
}


class Character {
    private String name;
    private double hitPoint;
    private String gender;
    int level;
    int experience;

    public String getName() {
        return name;
    }

    public double getHitPoint() {
        return hitPoint;
    }

    public String getGender() {
        return gender;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHitPoint(double hitPoint) {
        this.hitPoint = Math.max(hitPoint, 0);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Character(String name, double hitPoint, String gender, int level, int experience) {
        this.name = name;
        this.hitPoint = hitPoint;
        this.gender = gender;
        this.level = level;
        this.experience = experience;
    }

    public Character() {
        this.level = 1;
        this.experience = 0;
    }


    public double calculateDamage() {
        return hitPoint;
    }

    public void attack() {
        //System.out.println("Attacking... Damage is: " + calculateDamage());
        gainExperience(20);
    }

    public void regeneratedPower() {
        System.out.println("Regenerating Power.");
    }

    public void gainExperience(int xp) {
        experience += xp;
        if (experience >= 100) {
            levelUp();
        }
    }

    public void levelUp() {
        if (experience >= 100) {
            level++;
            experience = 0;
            System.out.println(name + " leveled up! New level: " + level);
        }
    }


    public void printInfo() {
        System.out.println("Character Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Level: " + level);
        System.out.println("HitPoint: " + hitPoint);
        System.out.println("Experience: " + experience);
    }
}

class Warrior extends Character {
    private int energy;
    private int defense;

    public Warrior(String name, double hitPoint, String gender, int level, int experience, int energy, int defense) {
        super(name, hitPoint, gender, level, experience);
        this.energy = energy;
        this.defense = defense;
    }


    public Warrior() {
        this.energy = 20;
        this.defense = 5;
    }

    private void rest() {
        energy += 20;
        System.out.println("Updated Energy: " + energy);
    }

    @Override
    public double calculateDamage() {
        return getHitPoint() * 1.2;
    }

    @Override
    public void attack() {
        if (energy < 10) {
            System.out.println("Not enough energy. Get rest...");
        } else {
            energy -= 10;
            super.attack();
        }
    }

    @Override
    public void regeneratedPower() {
        super.regeneratedPower();
        rest();
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Energy: " + energy);
        System.out.println("Defense: " + defense);
    }
}

class Mage extends Character {
    private int mana;
    private double criticalChance;

    public Mage(String name, double hitPoint, String gender, int level, int experience, double criticalChance) {
        super(name, hitPoint, gender, level, experience);
        this.mana = 10;
        this.criticalChance = criticalChance;
    }


    public Mage() {
        this.mana = 10;
        this.criticalChance = 0.1;
    }

    private void drinkPotion() {
        mana += 10;
        System.out.println("Updated mana: " + mana);
    }

    @Override
    public double calculateDamage() {
        return getHitPoint() * 0.8;
    }

    @Override
    public void attack() {
        if (mana < 5) {
            System.out.println("Not enough mana. Drink potion...");
        } else {
            mana -= 5;
            super.attack();
            System.out.println("Remaining mana: " + mana);
        }
    }

    @Override
    public void regeneratedPower() {
        super.regeneratedPower();
        drinkPotion();
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Mana: " + mana);
        System.out.println("Critical Chance: " + criticalChance + "%");
    }
}

class Player extends Character {
    private String name;
    private String password;
    private ArrayList<Character> characters = new ArrayList<>();

    public Player(String name, String password, ArrayList<Character> characters) {
        this.name = name;
        this.password = password;
        this.characters = characters;
    }

    public Player() {

    }


    @Override
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public void printPlayerInfo() {
        System.out.println("Player's Name: " + name);
        System.out.println("Player's Password: " + password);
        System.out.println("Characters: ");
        for (Character character : characters) {
            character.printInfo();
        }
    }

    public double computeTotalDamage() {
        double totalDamage = 0;
        for (Character character : characters) {
            if (character.getHitPoint() > 0) {
                totalDamage += character.calculateDamage();
            }
        }
        return totalDamage;
    }

    class Achievement {
        List<String> unlockedAchievements;

        public void addAchievement(String achievement) {
            unlockedAchievements.add(achievement);
        }
    }
}

class Party {
    private String partyName;
    private ArrayList<Character> members = new ArrayList<>();
    private int powerBalance;
    private int reputation;


    public Party(String partyName, ArrayList<Character> members, int powerBalance, int reputation) {
        this.partyName = partyName;
        this.members = members;
        this.powerBalance = powerBalance;
        this.reputation = reputation;
    }

    public Party() {
    }

    public String getPartyName() {
        return partyName;
    }

    public ArrayList<Character> getMembers() {
        return members;
    }

    public int getPowerBalance() {
        return powerBalance;
    }

    public int getReputation() {
        return reputation;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setMembers(ArrayList<Character> members) {
        this.members = members;
    }

    public void setPowerBalance(int powerBalance) {
        this.powerBalance = powerBalance;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public void addMember(Character character) {
        if (members.size() < 10) {
            members.add(character);
        } else System.out.println("Party is full!");
    }

    public void removeMember(Character character) {
        members.remove(character);
    }

    public int calculatePowerBalance() {
        int totalPower = 0;
        for (Character member : members) {
            totalPower += member.calculateDamage();
        }
        return totalPower;
    }

    public int calculateReputation() {
        int totalReputation = 0;
        for (Character member : members) {
            totalReputation += member.getLevel();
        }
        return totalReputation;
    }

    public void printPartyInfo() {
        System.out.println("Party Name: " + partyName);
        System.out.println("Members: ");
        for (Character member : members) {
            member.printInfo();
        }
        System.out.println("Power Balance: " + powerBalance);
        System.out.println("Reputation: " + reputation);
    }
}

class Battle {
    private Party party1;
    private Party party2;
    private List<Character> team1;
    private List<Character> team2;
    private Player player1;
    private Player player2;

    public Battle(Party party1, Party party2, List<Character> team1, List<Character> team2, Player player1, Player player2) {
        this.party1 = party1;
        this.party2 = party2;
        this.team1 = team1;
        this.team2 = team2;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void formTeams() {
        List<Character> combinedMembers = new ArrayList<>();
        combinedMembers.addAll(party1.getMembers());
        combinedMembers.addAll(party2.getMembers());
        Collections.shuffle(combinedMembers);

        team1 = new ArrayList<>();
        team2 = new ArrayList<>();

        for (int i = 0; i < combinedMembers.size(); i++) {
            if (i < combinedMembers.size() / 2) {
                team1.add(combinedMembers.get(i));
            } else {
                team2.add(combinedMembers.get(i));
            }
        }

        player1.setCharacters(new ArrayList<>(team1));
        player2.setCharacters(new ArrayList<>(team2));

        System.out.println("Team 1 (Player 1's Team):");
        for (Character member : player1.getCharacters()) {
            member.printInfo();
        }

        System.out.println("Team 2 (Player 2's Team):");
        for (Character member : player2.getCharacters()) {
            member.printInfo();
        }
    }

    public void startBattle() {
        Random r = new Random();

        System.out.println("Battle started!");

        if (team1.size() < 1 || team2.size() < 1) {
            System.out.println("One of the teams does not have enough members to start the battle.");
            return;
        }

        while (!team1.isEmpty() && !team2.isEmpty()) {
            boolean team1Attacks = r.nextBoolean();

            ArrayList<Character> attackers = (ArrayList<Character>) (team1Attacks ? team1 : team2);
            ArrayList<Character> defenders = (ArrayList<Character>) (team1Attacks ? team2 : team1);

            if (attackers.isEmpty() || defenders.isEmpty()) {
                break;
            }

            Character attacker = attackers.get(r.nextInt(attackers.size()));
            if (defenders.isEmpty()) {
                System.out.println("No defenders left. Battle over!");
                return;
            }
            Character defender = defenders.get(r.nextInt(defenders.size()));


            attack(attacker, defender);

            double damage = attacker.calculateDamage();
            defender.setHitPoint(defender.getHitPoint() - damage);

            System.out.println(attacker.getName() + " attacked! " + defender.getName() + " took " + damage + " damage!");

            if (defender.getHitPoint() <= 0) {
                System.out.println(defender.getName() + " is dead!");
                defenders.remove(defender);
            }
        }
    }

    public void attack(Character attacker, Character defender) {
        attacker.attack();
        defender.attack();

        double damage = attacker.calculateDamage();
        defender.setHitPoint(defender.getHitPoint() - damage);

        if (attacker.getClass().equals(Mage.class)) {
            System.out.println("Mage " + attacker.getName() + " casts a spell on " + defender.getName() + " causing " + damage + " damage!");
        } else {
            System.out.println(attacker.getName() + " attacked! " + defender.getName() + " took " + damage + " damage!");
        }

        if (defender.getHitPoint() <= 0) {
            System.out.println(defender.getName() + " is dead!");
        }
    }

    public void declareWinner() {
        if (!team1.isEmpty()) {
            System.out.println("Battle finished! Winner: Team 1");
        } else if (!team2.isEmpty()) {
            System.out.println("Battle finished! Winner: Team 2");
        } else {
            System.out.println("Battle ended in a draw!");
        }
    }
}