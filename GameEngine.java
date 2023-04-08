/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Aloïs Fournier
 */
public class GameEngine
{
    private Room aCurrentRoom;
    private Room aPreviousRoom;
    private Parser aParser;
    private UserInterface aGui;
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

        Room vLimbo = new Room("First Circle of Hell, Residence of all Pagans");
        Room vLust = new Room("Second Circle of Hell, Residence of all the Lustful");
        Room vGluttony = new Room("Third Circle of Hell, Residence of all the Gluttonous");
        Room vAvarice = new Room("Fourth Circle of Hell, Residence of all the Avaricious");
        Room vWrath = new Room("Fifth Circle of Hell, Residence of all the Wrathful");
        Room vHeresy = new Room("Sixth Circle of Hell, Residence of all the Heretics");
        Room vViolence = new Room("Seventh Circle of Hell, Residence of all the Violent");
        Room vFraud = new Room("Eighth Circle of Hell, Residence of all the Fraudulent");
        Room vTreachery = new Room("Ninth Circle of Hell, Residence of all the Treacherous");
        Room vParadise = new Room("Paradise, your final destination for salvation");
         
        // Create additional rooms
        
        Room vCharonsFerry = new Room("Charon\'s Ferry, the River Styx");
        Room vLimboCitadel = new Room("Limbo Citadel, the Residence of the Virtuous Pagans");

        Room vMarrakech = new Room("Marrakech, the City of the Lustful");
    
        Room vWheelOfFortune = new Room("The Wheel of Fortune, Where the fate of the earth is being decided");
        
        Room vRiverStyx = new Room("The River Styx, Eternal battlefield of the wrathful and sullen");
        
        Room vCityOfDis = new Room("The City of Dis, Jail of all heretics and pagans");
        
        Room vPhlegethon = new Room("The blood river of Phlegethon");
        Room vBurningSands = new Room("The Burning Sands, cursed home of the blasphemers");

        Room vMalebolge = new Room("The city of Malebolge, residence of the bolges");

        Room vLakeCocytus = new Room("The Lake of Cocytus, frozen lake that imprisons Lucifer");


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


        vFraud.setExit("north", vViolence);
        vFraud.setExit("south", vMalebolge);
        
        vMalebolge.setExit("north", vFraud);
        vMalebolge.setExit("south", vTreachery);

        vTreachery.setExit("north", vMalebolge);
        vTreachery.setExit("south", vLakeCocytus);

        vLakeCocytus.setExit("north", vTreachery);
        vLakeCocytus.setExit("east", vParadise);
        vLakeCocytus.setExit("west", vLimbo);

        // Initial Room
        this.aCurrentRoom = vLimbo;
        this.aPreviousRoom = vLimbo;
    }
    
    /**
     * Change de salle
     * @param pRoom La nouvelle salle
     */
    private void goRoom(final Command pCommand) {
        if (!CommandWords.isDirection(pCommand.getSecondWord())) {
            System.out.println("Unknown direction !");
            return;
        }

        if (!pCommand.hasSecondWord()) {
            System.out.println("Go Where ?");
            return;
        }
        
        Room vNextRoom = null;
        
        String vDirection = pCommand.getSecondWord();
        
        if (pCommand.getCommandWord().equals("back")) {
            this.aCurrentRoom = this.aPreviousRoom;
            this.printLocationInfo();
            return;
        }
        vNextRoom = this.aCurrentRoom.getExit(vDirection);
        
        if (vNextRoom == null) {
            System.out.println("There is no door !");
            return;
        } else {
            System.out.println("Moving " + vDirection + "...\n"); 
        } 
        if (vNextRoom == this.aCurrentRoom) {
            System.out.println("You are already in " + this.aCurrentRoom.getDescription());
        } else {
            this.aPreviousRoom = this.aCurrentRoom;
            this.aCurrentRoom = vNextRoom;
            this.printLocationInfo();
        }
    }


    /**
     * Affiche les informations de la salle courante
     */
    private void printLocationInfo() {
        System.out.println(this.aCurrentRoom.getLongDescription());
        System.out.println(this.aParser.getCommandString());
    }
    
    /**
     * Message de bienvenue du jeu
     */
    private void printWelcome() {
        System.out.println("Welcome to the World of Zuul! \nWorld of Zuul is a new, incredibly boring adventure game. \nType \'help\' if you need help.");
        
        this.printLocationInfo();
    }
    
    /**
     * Affiche l'aide du jeu
     */
    private void printHelp() {
        System.out.println(this.aParser.getCommandString());
    }
    
    /**
     * Quitte le jeu
     * @param pCommand La commande
     * @return true si le jeu doit être quitté, false sinon
     */
    private boolean quit(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            System.out.println("Quit What ?");
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
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                this.endGame();
        }
    }

    private void look() {
        System.out.println(this.aCurrentRoom.getLongDescription());
    }

    private void pray() {
        System.out.println("You pray to the gods in hope that they will help you escape this place.");
        // Add hints
        // Taint soul (?)
    }

    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }
} // Game
