import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Aloïs Fournier
 */
public class GameEngine
{
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;

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

        Room vLimbo = new Room("First Circle of Hell, Residence of all Pagans", "Images/limbo.jpeg");
        Room vLust = new Room("Second Circle of Hell, Residence of all the Lustful", "Images/lust.jpeg");
        Room vGluttony = new Room("Third Circle of Hell, Residence of all the Gluttonous", "Images/gluttony.jpeg");
        Room vAvarice = new Room("Fourth Circle of Hell, Residence of all the Avaricious", "Images/avarice.jpeg");
        Room vWrath = new Room("Fifth Circle of Hell, Residence of all the Wrathful", "Images/wrath.jpeg");
        Room vHeresy = new Room("Sixth Circle of Hell, Residence of all the Heretics", "Images/heresy.jpeg");
        Room vViolence = new Room("Seventh Circle of Hell, Residence of all the Violent", "Images/violence.jpeg");
        Room vFraud = new Room("Eighth Circle of Hell, Jail of all the Fraudulent", "Images/fraud.jpeg");
        Room vTreachery = new Room("Ninth Circle of Hell, Residence of all the Treacherous", "Images/treachery.jpeg");
        Room vParadise = new Room("Paradise, your final destination for salvation", "Images/paradise.jpeg");
        Room vHell = new Room("Hell, the place where you will be condemned for eternity", "Images/game_over2.jpeg");
        // Create additional rooms
        
        Room vCharonsFerry = new Room("Charon\'s Ferry", "Images/charon_s_ferry.jpeg");
        Room vLimboCitadel = new Room("Limbo Citadel, the Residence of the Virtuous Pagans", "Images/limbo_citadel.jpeg");

        Room vMarrakech = new Room("Marrakech, the City of the Lustful", "Images/marrakech.jpeg");
    
        Room vWheelOfFortune = new Room("The Wheel of Fortune, Where the fate of the earth is being decided", "Images/wheel_of_fortune.jpeg");
        
        Room vRiverStyx = new Room("The River Styx, Eternal battlefield of the wrathful and sullen", "Images/river_styx.jpeg");
        
        Room vCityOfDis = new Room("The City of Dis, Jail of all heretics and pagans", "Images/city_of_dis.jpeg");
        
        Room vPhlegethon = new Room("The cold blood river of Phlegethon", "Images/phlegethon.jpeg");
        Room vBurningSands = new Room("The Burning Sands, cursed home of the blasphemers", "Images/burning_sands.jpeg");

        Room vMalebolge = new Room("The city of Malebolge, residence of the bolges", "Images/malebolge.jpeg");

        Room vLakeCocytus = new Room("The Lake of Cocytus, frozen lake that imprisons Lucifer", "Images/lake_cocytus.jpeg");


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


        //image.pngvFraud.setExit("north", vViolence);
        vFraud.setExit("south", vMalebolge);
        
        vMalebolge.setExit("north", vFraud);
        vMalebolge.setExit("south", vTreachery);

        vTreachery.setExit("north", vMalebolge);
        vTreachery.setExit("south", vLakeCocytus);

        vLakeCocytus.setExit("north", vTreachery);
        vLakeCocytus.setExit("east", vParadise);
        vLakeCocytus.setExit("west", vHell);
        
        vHell.setExit("south", vLimbo);

        
        Item vLordBlessing = new Item("Lord\'s blessing", "holy relic", 10);
        Item vPriestRobe = new Item("Priest\'s robe", "holy relic", 5);
        Item vDeathScythe = new Item("Death\'s scythe", "unholy weapon to bring death upon mankind", -5);
        Item vIvoryCross = new Item("Ivory cross", "Holy cross from the holy crusades", 7);
        Item vHolyWater = new Item("Holy water", "Flask of water from Jerusalem", 2);
        Item vHellBible = new Item("Hell\'s bible", "Bible from the devil himself, contains evil but useful spells", -5);
        Item vBible = new Item("Bible", "Holy book from the Lord", 2);
        Item vHolyGrail = new Item("Holy grail", "Holy relic from the holy crusades", 10);
        Item vPurseOfCoins = new Item("Purse of coins", "The last 30 coins from the purse of Judas", -10);
        Item vSplinterOfCharon = new Item("Splinter of Charon", "Splinter of the boat of the underworld", 1);
        Item vViolentFire = new Item("Violent fire", "Fire from the violent, could be useful", -1);
        Item vBloodFlask = new Item("Blood flask", "Flask of blood from Phlegethon, the river of blood", -1);
        Item vMercyOfVirgil = new Item("Mercy of Virgil", "Mercy of the poet Virgil, the most respected pagan of hell", 5);
        Item vEyesOfTheGorgon = new Item("Eyes of the Gorgon", "Eyes of the Gorgon, the most feared monster of hell", -3);
        Item vHeadOfLucifer = new Item("Head of Lucifer", "Head of Lucifer, the most feared demon of hell", 0);


        // Add Items to rooms
        vLimbo.addItem(vLordBlessing.getName(), vLordBlessing);
        vLimbo.addItem(vDeathScythe.getName(), vDeathScythe);
        
        vLimboCitadel.addItem(vIvoryCross.getName(), vIvoryCross);

        vLust.addItem(vPriestRobe.getName(), vPriestRobe);

        vMarrakech.addItem(vHolyWater.getName(), vHolyWater);

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


        // Initial Room
        this.aPlayer = new Player("Gr4ve", vLimbo);
    
    }
    
    /**
     * Change de salle
     * @param pRoom La nouvelle salle
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
        this.aGui.println("Welcome to the World of Zuul! \nWorld of Zuul is a new, incredibly boring adventure game. \nType \'help\' if you need help.");
        
        this.printLocationInfo();
    }
    
    /**
     * Affiche l'aide du jeu
     */
    private void printHelp() {
        this.aGui.println(this.aParser.getCommandString());
    }
    
    /**
     * Quitte le jeu
     * @param pCommand La commande
     * @return true si le jeu doit être quitté, false sinon
     */
    private boolean quit(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("Quit What ?");
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "help" ) )
            this.printHelp();
        else if ( vCommandWord.equals( "look" ) )
            this.look();
        else if ( vCommandWord.equals( "pray" ) )
            this.pray();
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom(vCommand);
        else if ( vCommandWord.equals( "back" ) )
            this.back();
        else if ( vCommand.equals("take"))
            this.take(vCommand);
        else if ( vCommand.equals("drop"))
            this.drop(vCommand);
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                this.endGame();
        } else {
            this.aGui.println("Command not implemented yet");
        }
    }

    /**
     * Drop an item
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
        
        if (!this.aPlayer.getCurrentRoom().hasItem(vItemName) && !this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("There is no " + vItemName + " in this room.");
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

    private void back() {
        this.aGui.println("You go back to the previous room.");
        
        if (this.aPlayer.getPreviousRooms().empty()) {
            this.aGui.println("You can't go back.");
            return;
        }

        this.aPlayer.back();
        this.printLocationInfo();
    }

    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Goodbye." );
        this.aGui.enable( false );
    }

    /*
    private void test(String pFile) 
        throws IOException {
            if (pFile == null) {
                throw new IllegalArgumentException("File name cannot be null");
            } else if (pFile.contains(".txt")) {
                pFile = pFile.substring(0, pFile.length() - 4);
            }

            Path path = Paths.get("./" + pFile + ".txt");
        
            List<String> read = Files.readAllLines(path);
            
            for (String line : read) {
                this.interpretCommand(line);
            }
        }
    */
} // Game
