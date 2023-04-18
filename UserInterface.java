import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) edited (2023)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JPanel  aEastPanel;
    private JPanel  aWestPanel;

    private JButton aNorthButton;
    private JButton aSouthButton;
    private JButton aEastButton;
    private JButton aWestButton;

    private JButton aTakeButton;
    private JButton aDropButton;
    private JButton aPrayButton;
    private JButton aLookButton;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL);
            Image image = vIcon.getImage(); 
            image = image.getScaledInstance(600,600, Image.SCALE_SMOOTH);
            vIcon = new ImageIcon(image);
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        // The current aMyFrame customization
        this.aMyFrame = new JFrame( "Hellfire Odyssey" ); // change the title
        this.aEntryField = new JTextField( 34 );
    
        // icons
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("Images/favicon.jpeg");
            TrayIcon trayIcon = new TrayIcon(image, "Hellfire Odyssey");
            trayIcon.setImageAutoSize(true);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
        }

        this.aMyFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/favicon.jpeg"));

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();

        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add 2 other panes, East and West.
        this.aEastPanel = new JPanel();
        this.aWestPanel = new JPanel();

        // set grid layout, 4x1 for both
        this.aEastPanel.setLayout(new GridLayout(4, 1));
        this.aWestPanel.setLayout(new GridLayout(4, 1));


        // initialize buttons and applies them to their respective panels. East will be for direction, west for actions. (temp)
        
        // North Direction
        this.aNorthButton = new JButton("North", null);
        this.aNorthButton.addActionListener(this);
        this.aNorthButton.setBackground(Color.black);
        this.aEastPanel.add(this.aNorthButton);

        // South Direction
        this.aSouthButton = new JButton("South", null);
        this.aSouthButton.addActionListener(this);
        this.aSouthButton.setBackground(Color.black);
        this.aEastPanel.add(this.aSouthButton);

        // East Direction
        this.aEastButton = new JButton("East", null);
        this.aEastButton.addActionListener(this);
        this.aEastButton.setBackground(Color.black);
        this.aEastPanel.add(this.aEastButton);

        // West Direction
        this.aWestButton = new JButton("West", null);
        this.aWestButton.addActionListener(this);
        this.aWestButton.setBackground(Color.black);
        this.aEastPanel.add(this.aWestButton);

        // Look Action
        this.aLookButton = new JButton("Look", null);
        this.aLookButton.addActionListener(this);
        this.aLookButton.setBackground(Color.ORANGE);
        this.aWestPanel.add(this.aLookButton);

        // Take Action
        this.aTakeButton = new JButton("Take", null);
        this.aTakeButton.addActionListener(this);
        this.aTakeButton.setBackground(Color.ORANGE);
        this.aWestPanel.add(this.aTakeButton);

        // Drop Action
        this.aDropButton = new JButton("Drop", null);
        this.aDropButton.addActionListener(this);
        this.aDropButton.setBackground(Color.ORANGE);
        this.aWestPanel.add(this.aDropButton);

        // Pray Action
        this.aPrayButton = new JButton("Pray", null);
        this.aPrayButton.addActionListener(this);
        this.aPrayButton.setBackground(Color.ORANGE);
        this.aWestPanel.add(this.aPrayButton);

        vPanel.add(this.aWestPanel, BorderLayout.WEST);
        vPanel.add(this.aEastPanel, BorderLayout.EAST);
        // add some event listeners to some components
        this.aEntryField.addActionListener( this );

        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        if ( pE.getSource() == this.aEntryField )
            this.processCommand();
        else if ( pE.getSource() == this.aNorthButton )
            this.aEngine.interpretCommand("go north");
        else if ( pE.getSource() == this.aSouthButton )
            this.aEngine.interpretCommand("go south");
        else if ( pE.getSource() == this.aEastButton )
            this.aEngine.interpretCommand("go east");
        else if ( pE.getSource() == this.aWestButton )
            this.aEngine.interpretCommand("go west");
        else if ( pE.getSource() == this.aLookButton )
            this.aEngine.interpretCommand("look");
        else if ( pE.getSource() == this.aTakeButton )
            this.aEngine.interpretCommand("take");
        else if ( pE.getSource() == this.aDropButton )
            this.aEngine.interpretCommand("drop");
        else if ( pE.getSource() == this.aPrayButton )
            this.aEngine.interpretCommand("pray");
        else
            System.out.println( "Unexpected action event source.");
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
