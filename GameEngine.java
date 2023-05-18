import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe GameEngine - le moteur du jeu d'aventure Zuul.
 * 
 * @author Aloïs Fournier
 */
public class GameEngine {
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private HashMap<String, Room> aRooms;
    private Room spawnRoom;

    /**
     * Crée le jeu et initialise les salles.
     */
    public GameEngine() {
        this.aParser = new Parser();
        this.createRooms();

    }

    public void setGUI(final UserInterface pGUI) {
        this.aGui = pGUI;
        this.printWelcome();
    }

    /**
     * Crée toutes les salles et les relie entre elles.
     */
    private void createRooms() {

        // Create Initial 9 circles of Hell

        Room vLimbo = new Room("Limbo, the First Circle of Hell, Residence of all Pagans", "Images/limbo.jpeg");
        Room vLust = new Room("Lust, Second Circle of Hell, Residence of all the Lustful", "Images/lust.jpeg");
        Room vGluttony = new Room("Gluttony, Third Circle of Hell, Residence of all the Gluttonous",
                "Images/gluttony.jpeg");
        Room vAvarice = new Room("Avarice, Fourth Circle of Hell, Residence of all the Avaricious",
                "Images/avarice.jpeg");
        Room vWrath = new Room("Wrath, Fifth Circle of Hell, Residence of all the Wrathful", "Images/wrath.jpeg");
        Room vHeresy = new Room("Heresy, Sixth Circle of Hell, Residence of all the Heretics", "Images/heresy.jpeg");
        Room vViolence = new Room("Violence, Seventh Circle of Hell, Residence of all the Violent",
                "Images/violence.png");

        // FRAUD has been moved to a TransporterRoom

        TransporterRoom vFraud = new TransporterRoom("Fraud, Eighth Circle of Hell, Jail of all the Fraudulent", "Images/fraud.jpeg", false);
        
        Room vTreachery = new Room("Treachery, Ninth Circle of Hell, Residence of all the Treacherous",
                "Images/treachery.jpeg");
        Room vParadise = new Room("Paradise, your final destination for salvation", "Images/paradise.jpeg");
        Room vHell = new Room("Hell, the place where you will be condemned for eternity", "Images/game_over_2.jpeg");
        // Create additional rooms

        Room vCharonsFerry = new Room("Charon\'s Ferry", "Images/charron_s_ferry.jpeg");
        Room vLimboCitadel = new Room("Limbo Citadel, the Residence of the Virtuous Pagans",
                "Images/limbo_citadel.jpeg");

        Room vMarrakech = new Room("Marrakech, the City of the Lustful", "Images/marrakech.jpeg");

        Room vWheelOfFortune = new Room("The Wheel of Fortune, Where the fate of the earth is being decided",
                "Images/wheel_of_fortune.jpeg");

        Room vRiverStyx = new Room("The River Styx, Eternal battlefield of the wrathful and sullen",
                "Images/river_styx.jpeg");

        Room vCityOfDis = new Room("The City of Dis, Jail of all heretics and pagans", "Images/city_of_dis.jpeg");

        Room vPhlegethon = new Room("Phlegethon, blood river", "Images/lake_phlegethon.jpeg");
        Room vBurningSands = new Room("The Burning Sands, cursed home of the blasphemers", "Images/burning_sands.jpeg");

        Room vMalebolge = new Room("The City of Malebolge, residence of the bolges", "Images/malebolge_city.jpeg");

        Room vLakeCocytus = new Room("The Lake of Cocytus, frozen lake that imprisons Lucifer",
                "Images/lake_cocytus.jpeg");

        // Create array of rooms

        // Initialise room exits

        vLimbo.setExit("south", vCharonsFerry);

        vCharonsFerry.setExit("north", vLimbo);
        vCharonsFerry.setExit("east", vLimboCitadel);
        vCharonsFerry.setExit("south", vLust);

        vLimboCitadel.setExit("south", vLust);
        vLimboCitadel.setExit("west", vCharonsFerry);

        vLust.setExit("north", vLimboCitadel);
        vLust.setExit("south", vMarrakech);

        vMarrakech.setExit("north", vLust);
        vMarrakech.setExit("south", vGluttony);

        vGluttony.setExit("north", vMarrakech);
        vGluttony.setExit("south", vAvarice);

        vAvarice.setExit("north", vGluttony);
        vAvarice.setExit("south", vWheelOfFortune);

        vWheelOfFortune.setExit("north", vAvarice);
        vWheelOfFortune.setExit("south", vWrath);

        vWrath.setExit("north", vWheelOfFortune);
        vWrath.setExit("south", vRiverStyx);

        vRiverStyx.setExit("north", vWrath);
        vRiverStyx.setExit("south", vHeresy);

        vHeresy.setExit("north", vRiverStyx);
        vHeresy.setExit("south", vCityOfDis);

        vCityOfDis.setExit("north", vHeresy);
        vCityOfDis.setExit("south", vViolence);

        vViolence.setExit("north", vViolence);
        vViolence.setExit("east", vPhlegethon);
        vViolence.setExit("west", vBurningSands);

        vPhlegethon.setExit("south", vFraud);

        vBurningSands.setExit("south", vFraud);

        vFraud.setExit("south", vMalebolge);

        vMalebolge.setExit("north", vFraud);
        vMalebolge.setExit("south", vTreachery);

        vTreachery.setExit("north", vMalebolge);
        vTreachery.setExit("south", vLakeCocytus);

        vLakeCocytus.setExit("north", vTreachery);
        vLakeCocytus.setExit("up", vParadise);
        vLakeCocytus.setExit("down", vHell);

        vHell.setExit("south", vLimbo);

        Item vLordBlessing = new Item("Lord\'s blessing", "holy relic", 10, 0);
        Item vPriestRobe = new Item("Priest\'s robe", "holy relic", 5, 2);
        Item vDeathScythe = new Item("Death\'s scythe", "unholy weapon to bring death upon mankind", -5, 6);
        Item vIvoryCross = new Item("Ivory cross", "Holy cross from the holy crusades", 7, 1);
        Item vHolyWater = new Item("Holy water", "Flask of water from Jerusalem", 2, 1);
        Item vHellBible = new Item("Hell\'s bible", "Bible from the devil himself, contains evil but useful spells", -5,
                3);
        Item vBible = new Item("Bible", "Holy book from the Lord", 2, 1);
        Item vHolyGrail = new Item("Holy grail", "Holy relic from the holy crusades", 10, 2);
        Item vPurseOfCoins = new Item("Purse of coins", "The last 30 coins from the purse of Judas", -10, 1);
        Item vSplinterOfCharon = new Item("Splinter of Charon", "Splinter of the boat of the underworld", 1, 3);
        Item vViolentFire = new Item("Violent fire", "Fire from the violent, could be useful", -1, 1);
        Item vBloodFlask = new Item("Blood flask", "Flask of blood from Phlegethon, the river of blood", -1, 2);
        Item vMercyOfVirgil = new Item("Mercy of Virgil", "Mercy of the poet Virgil, the most respected pagan of hell",
                5, 0);
        Item vEyesOfTheGorgon = new Item("Eyes of the Gorgon", "Eyes of the Gorgon, the most feared monster of hell",
                -3, 3);
        Item vHeadOfLucifer = new Item("Head of Lucifer", "Head of Lucifer, the most feared demon of hell", 0, 9);

        Item vMagicCookie = new Item("magiccookie", "A magic cookie that will give you double inventory capacity", 0,
                0);

        Beamer vBeamer = new Beamer("beamer", "A beamer that will teleport you to the last room you beamed from", 20,
                10);

        // Add Items to rooms
        vLimbo.addItem(vLordBlessing.getName(), vLordBlessing);
        vLimbo.addItem(vDeathScythe.getName(), vDeathScythe);

        vLimboCitadel.addItem(vIvoryCross.getName(), vIvoryCross);

        vLust.addItem(vPriestRobe.getName(), vPriestRobe);

        vMarrakech.addItem(vHolyWater.getName(), vHolyWater);
        vMarrakech.addItem(vMagicCookie.getName(), vMagicCookie);

        vAvarice.addItem(vPurseOfCoins.getName(), vPurseOfCoins);
        vAvarice.addItem(vHolyGrail.getName(), vHolyGrail);

        vWheelOfFortune.addItem(null, null);

        vWrath.addItem(vSplinterOfCharon.getName(), vSplinterOfCharon);

        vHeresy.addItem(vHellBible.getName(), vHellBible);
        vHeresy.addItem(vBible.getName(), vBible);

        vViolence.addItem(vViolentFire.getName(), vViolentFire);
        vViolence.addItem(vEyesOfTheGorgon.getName(), vEyesOfTheGorgon);

        vPhlegethon.addItem(vBloodFlask.getName(), vBloodFlask);

        vFraud.addItem(null, null);

        vMalebolge.addItem(vMercyOfVirgil.getName(), vMercyOfVirgil);

        vTreachery.addItem(null, null);

        vLakeCocytus.addItem(vHeadOfLucifer.getName(), vHeadOfLucifer);

        vLimbo.addItem(vBeamer.getName(), vBeamer);

        // Add all Rooms to the HashMap
        this.aRooms = new HashMap<String, Room>();
        this.aRooms.put("Limbo", vLimbo);
        this.aRooms.put("LimboCitadel", vLimboCitadel);
        this.aRooms.put("Lust", vLust);
        this.aRooms.put("Marrakech", vMarrakech);
        this.aRooms.put("Avarice", vAvarice);
        this.aRooms.put("WheelOfFortune", vWheelOfFortune);
        this.aRooms.put("Wrath", vWrath);
        this.aRooms.put("Heresy", vHeresy);
        this.aRooms.put("Violence", vViolence);
        this.aRooms.put("Phlegethon", vPhlegethon);
        this.aRooms.put("BurningSands", vBurningSands);
        this.aRooms.put("Fraud", vFraud);
        this.aRooms.put("Malebolge", vMalebolge);
        this.aRooms.put("Treachery", vTreachery);
        this.aRooms.put("LakeCocytus", vLakeCocytus);
        this.aRooms.put("Hell", vHell);
        this.aRooms.put("Paradise", vParadise);

        ArrayList<Room> aRoomArray = new ArrayList<Room>(this.aRooms.values());

        vFraud.addRooms(aRoomArray);


        // Initial Room
        this.aPlayer = new Player("Gr4ve", vLimbo);

        // Spawn room
        this.spawnRoom = vLimbo;
    }

    /**
     * Change de salle
     * 
     * @param pCommand Commande de changement de salle
     */
    private void goRoom(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Go Where ?");
            return;
        }

        if (!CommandWords.isDirection(pCommand.getSecondWord())) {
            this.aGui.println("Unknown direction !");
            return;
        }

        String vDirection = pCommand.getSecondWord();

        if (this.aPlayer.getCurrentRoom().getExit(vDirection) == null) {
            this.aGui.println("There is no door !");
            return;
        }

        if (this.aPlayer.getCurrentRoom().getClass().equals(TransporterRoom.class)) {
            TransporterRoom vTransporterRoom = (TransporterRoom) this.aPlayer.getCurrentRoom();
            this.aPlayer.setCurrentRoom(vTransporterRoom.getRandomExit());
            // On vide la liste des salles précédentes
            this.aPlayer.getPreviousRooms().clear();
        }
        
        this.aPlayer.goRoom(vDirection);
        this.printLocationInfo();
    }

    /**
     * Affiche les informations de la salle courante
     */
    private void printLocationInfo() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        this.aGui.println(this.aPlayer.getCurrentRoom().getItemsString());
        if (this.aPlayer.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        }
    }

    /**
     * Message de bienvenue du jeu
     */
    private void printWelcome() {
        this.aGui.println(
                "Welcome to the World of Zuul! \nWorld of Zuul is a new, incredibly boring adventure game. \nType \'help\' if you need help.");

        this.printLocationInfo();
    }

    /**
     * Affiche l'aide du jeu
     */
    private void printHelp() {
        this.aGui.println(this.aParser.getCommandString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param pCommandLine The command to be processed.
     */
    public void interpretCommand(final String pCommandLine) {
        this.aGui.println("> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        vCommandWord = vCommandWord.toLowerCase();
        switch (vCommandWord) {
            case "help":
                this.printHelp();
                break;
            case "look":
                this.look();
                break;
            case "pray":
                this.pray();
                break;
            case "go":
                this.goRoom(vCommand);
                break;
            case "back":
                this.back(vCommand);
                break;
            case "take":
                this.take(vCommand);
                break;
            case "drop":
                this.drop(vCommand);
                break;
            case "quit":
                if (vCommand.hasSecondWord())
                    this.aGui.println("Quit what?");
                else
                    this.endGame();
                break;
            case "items":
                this.printItems();
                break;
            case "eat":
                this.eat(vCommand);
                break;
            case "test":
                this.test(vCommand);
                break;
            case "respawn":
                this.respawn();
                break;
            case "charge":
                this.charge(vCommand);
                break;
            case "fire":
                this.fire(vCommand);
                break;
            default:
                this.aGui.println("Command not implemented yet");
                break;
        }

        this.runMechanics();
    }

    /**
     * Charge the beamer
     * 
     * @param pCommand
     */
    public void charge(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Charge what ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        if (!(this.aPlayer.getItem(vItemName) instanceof Beamer)) {
            this.aGui.println("You can't charge " + vItemName + " !");
            return;
        }
        Item vItem = this.aPlayer.getItem(vItemName);
        Beamer vBeamer = (Beamer) vItem;
        if (vBeamer.isCharged()) {
            this.aGui.println("The beamer is already charged !");
            return;
        }

        this.aPlayer.charge(this.aPlayer.getCurrentRoom(), vBeamer);
        this.aGui.println("The beamer is charged !");
        // TODO: DISABLE CHARGE AND ENABLE FIRE BUTTON?

    }

    /**
     * Fire the beamer
     * 
     * @param pCommand
     */
    public void fire(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Fire what ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        if (!(this.aPlayer.getItem(vItemName) instanceof Beamer)) {
            this.aGui.println("You can't fire " + vItemName + " !");
            return;
        }

        Beamer vBeamer = (Beamer) this.aPlayer.getItem(vItemName);
        if (!vBeamer.isCharged()) {
            this.aGui.println("The beamer is not charged !");
            return;
        }

        this.aPlayer.fire(vBeamer);
        this.aGui.println("The beamer is fired !");
    }

    /**
     * Respawn the player
     */
    public void respawn() {
        this.aPlayer.setCurrentRoom(this.spawnRoom);
        this.printLocationInfo();
        this.aGui.println("You respawned");
    }

    /**
     * Mechanics of the game
     */
    private void runMechanics() {

    }

    /**
     * Eat an item
     * 
     * @param pCommand
     */
    private void eat(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Eat What ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        // Item vItem = this.aPlayer.getCurrentRoom().getItem(vItemName);

        this.aPlayer.eat(vItemName);
        if (vItemName.equals("magiccookie")) {
            this.aGui.println("You feel a strange power in you.");
            this.aPlayer.setMaxWeight(this.aPlayer.getMaxWeight() * 2);
        }
        this.aGui.println("You eat " + vItemName + ".");
    }

    /**
     * Print the items in the player's inventory
     */
    private void printItems() {
        this.aGui.println(this.aPlayer.getInventoryString());
    }

    /**
     * Drop an item
     * 
     * @param pCommand
     */
    private void drop(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Drop What ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        this.aPlayer.drop(vItemName);
    }

    /**
     * Take an item
     */
    private void take(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Take What ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.getCurrentRoom().hasItem(vItemName)) {
            this.aGui.println("There is no " + vItemName + " in this room.");
            return;
        }

        if (!this.aPlayer.canCarry(this.aPlayer.getCurrentRoom().getItem(vItemName).getWeight())) {
            this.aGui.println("You can't carry " + vItemName + " because it's too heavy. Drop some Items first.");
            return;
        }

        this.aPlayer.take(vItemName);

    }

    private void look() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }

    private void pray() {
        this.aGui.println("You pray to the gods in hope that they will help you escape this place.");
        // Add hints
        // Taint soul (?)
    }

    private void back(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("Back what ?");
            return;
        }

        if (this.aPlayer.getPreviousRooms().empty()) {
            this.aGui.println("You can't go back.");
            return;
        }

        if (!this.aPlayer.getPreviousRooms().peek().isExit()) {
            this.aGui.println("You can't go back. It\'s a trap !");
            return;
        }

        this.aGui.println("You go back to the previous room.");
        this.aPlayer.back();
        this.printLocationInfo();
    }

    private void endGame() {
        this.aGui.println("Thank you for playing.  Goodbye.");
        this.aGui.enable(false);
    }

    private void test(final Command pCommand) {
        if (!pCommand.hasSecondWord()) { // Verification de la présence d'un second mot
            this.aGui.println("What do you want to test ?");
            return;
        }

        String vFileToScan = pCommand.getSecondWord();
        try {
            Scanner vScanner = new Scanner(new File("" + vFileToScan + ".txt"));
            this.aGui.println("Testing " + vFileToScan);
            while (vScanner.hasNextLine()) {
                this.interpretCommand(vScanner.nextLine());
            }
        } catch (final FileNotFoundException pErr) {
            this.aGui.println("Error, FNF " + pErr.toString());
        }
    }
} // Game
