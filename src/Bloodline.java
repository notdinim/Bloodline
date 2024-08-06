/**
 * Made by: Dinim Onyige
 * DATE: Tuesday, November 14 2023
 * COURSE CODE: ICS3U1
 * PROGRAM DESCRIPTION: 
 * 
 * 
 * 
 * 
 * 
 */

// IMPORTS
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.UUID;

// MONOCHROME CLASS
public class Bloodline extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
	
	// FIELDS
	// BOOLEAN CONTITION HANDLERS
	private boolean titleBool, menuBool, pickGenderBool, startingAreaBool, skeletonAreaBool, werewolfAreaBool, wizardAreaBool, yokaiAreaBool, showWeapon ; // WIP
	// STARTING AREA MAP MOVEMENTS
	private boolean up, down, left, right;
	// PLAYER MOVEMENTS
	private boolean plrUp, plrDown, plrLeft, plrRight;
	
	// BOOLEAN BUTTON CLICK HANDLER
	private boolean canClick;
	// BOOLEAN LOAD GAME TRIGGER HANDLER
	private boolean canLoad;
	
	// BOOLEAN THAT CHECKS IF PLAYER IS ATTACKED
	private boolean isAttacked;
	
	// BOOLEAN SHOP HANDLER
	private boolean leatherArmourPurchased, bronzeArmourPurchased, ironArmourPurchased, silverArmourPurchased;
	private boolean knifeWeaponPurchased, swordWeaponPurchased, cleaverWeaponPurchased,
					rapierWeaponPurchased, axeWeaponPurchased, sytheWeaponPurchased;
	
	// SHOP ITEMS PRICE
	private int bronzeArmourCost, ironArmourCost, silverArmourCost;
	private int knifeWeaponCost, swordWeaponCost, cleaverWeaponCost,
				rapierWeaponCost, axeWeaponCost, sytheWeaponCost;
	
	
	// IMAGES
	// TITLE SCREEN
	private ImageIcon titleImg;
	// MENU SCREEN
	private ImageIcon menuImg;
	// PICK GENDER SCREEN
	private ImageIcon charCustomization1;
	private ImageIcon maleCharacterIcon;
	private ImageIcon femaleCharacterIcon;
	// STARTING AREA SCREEN
	private ImageIcon reaperNpcIcon, skullIcon, currencyIcon;
	// MOB MAPS
	private ImageIcon mobMaps;
	private int mapIndex;
	private ImageIcon underworld;
	private boolean underworldBool;
	
	// BUTTONS (MASKS)
	// TITLE SCREEN MASKS
	private Rectangle2D startButton;
	// MENU SCREEN MASKS
	private Rectangle2D loadGameButton, newGameButton, tutorialButton, quitButton, settingsButton;
	// GENDER PICK MASKS
	private Rectangle2D maleButton, femaleButton;

	// FONTS
	private Font f;
	private FontMetrics fm;
	
	// MOBS AND ENEMIES
	private skeletonMob[] skeleton;
	private werewolfMod[] werewolf;
	private wizardMob[] wizard;
	private yokaiMob[] yokai;
	private Random rand;
	
	// DATASTORE TO LOAD GAME
	private int saved_slot;
	
	// SOUNDS/MUSIC
	private static Clip menuBgMusic;
	
	// GLOBAL JFRAME
	private JFrame frame;
	
	// CHARACTER STATS
	private int currency;
	public int reward;
	private String plr_name;
	private String plr_gender;
	private int plrLives, maxLives;
	private String weaponName;
	private int plr_health;
	private int skeletonHealthPointer[];
	// CUSTOM PLAYER X AND Y WHEN FIGHTING MOBS
	private double playerX;
	private double playerY;
	
	// DECLARING TIMERS
	private Timer startingAreaTimer, plrTimer, skeletonTimer, werewolfTimer, wizardTimer, yokaiTimer, mobFightTimer; 
	private int seconds;
	// DEFAULT WEAPON AND ARMOUR (EQUIPPED)
	private ImageIcon defaultArmour;
	private ImageIcon defaultWeapon;
	
	// LOAD UML CLASSES
	private BloodlineAreas maps;
	
	// CONSTRUCTOR
	public Bloodline()
	{

		// INITATING UML CLASS
		maps = new BloodlineAreas();

		currency = 20;
		playerX = 732.7;
		playerY = 740;
		
		// BOOLEAN STARTUP
		titleBool = true;
		menuBool = false;
		pickGenderBool = false;
		startingAreaBool = false;
		skeletonAreaBool = false;
		werewolfAreaBool = false;
		wizardAreaBool = false;
		yokaiAreaBool = false;
		underworldBool = false;
		
		// BUTTON BOOLEAN DEBOUNCE
		canClick = true;
		// LOAD CHARACTER TRIGGER
		canLoad = false;
		
		// INITIATE BOOLEAN FOR STARTING AREA AND CHARTACTER MOVEMENTS
		up = false;
		down = false;
		left = false;
		right = false;
		
		plrUp = false;
		plrDown = false;
		plrLeft = false;
		plrRight = false;
		
		// SETTING PLAYER MAX LIVES
		maxLives = 3;
		
		// INITIALIZE SHOP HANDLERS
		leatherArmourPurchased = true;
		bronzeArmourPurchased = false;
		ironArmourPurchased = false;
		silverArmourPurchased = false;
		
		knifeWeaponPurchased = true;
		swordWeaponPurchased = false;
		cleaverWeaponPurchased = false;
		rapierWeaponPurchased = false;
		axeWeaponPurchased = false;
		sytheWeaponPurchased = false;
		weaponName = "knifeWeapon";
		
		// ARMOUR AND WEAPON COSTS
		bronzeArmourCost = 50;
		ironArmourCost = 150;
		silverArmourCost = 500;
		
		swordWeaponCost = 25;
		cleaverWeaponCost = 75;
		rapierWeaponCost = 150;
		axeWeaponCost = 450;
		sytheWeaponCost = 750;
		

		// INITIALIZE IMAGES
		titleImg = new ImageIcon("images\\title.gif");
		menuImg = new ImageIcon("images\\menu.gif");
		charCustomization1 = new ImageIcon("images\\charPickGender.png");
		maleCharacterIcon = new ImageIcon("images\\maleCharacterIcon.png");
		femaleCharacterIcon = new ImageIcon("images\\femaleCharacterIcon.png");
		skullIcon = new ImageIcon("images\\skullIconUI.png");
		currencyIcon = new ImageIcon("images\\currencyIconUI.png");
		reaperNpcIcon = new ImageIcon("images\\reaperNpcIcon.png");
		
		// INITIATE MENU BUTTONS (MASKS)
		// MENU SCREEN
		loadGameButton = new Rectangle2D.Double(380, 513.4, 240, 51);
		newGameButton = new Rectangle2D.Double(380, 582.3, 240, 51);
		tutorialButton = new Rectangle2D.Double(380, 651.3, 240, 51);
		quitButton = new Rectangle2D.Double(380, 720.4, 240, 51);
		settingsButton = new Rectangle2D.Double(920, 720.4, 55.1, 55.1);
		// PICK GENDER SCREEN
		maleButton = new Rectangle2D.Double(292, 274.5, 329.4, 372.1);
		femaleButton = new Rectangle2D.Double(909.4, 274.2, 329.4, 372.1);
		
		// INITIATE MOBS, ENEMIES AND MAPS
		skeleton = new skeletonMob[6];
		werewolf = new werewolfMod[6];
		wizard = new wizardMob[6];
		yokai = new yokaiMob[6];
		mobMaps = new ImageIcon("images\\mobAreas\\mobArea2.png");
		underworld = new ImageIcon("images\\mobAreas\\underworld.png");
		rand = new Random();

		// IMPLEMENT LISTENERS
		setBackground(Color.BLACK);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
		
		// JFRAME
		frame = new JFrame();
		frame.setTitle("BLOODLINE: STARTUP");
		frame.setContentPane(this);
		frame.setSize(900, 820);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// INITIALIZE TIMERS
		startingAreaTimer = new Timer(50, this);
		plrTimer = new Timer (50, this);
		skeletonTimer = new Timer(4000, this);
		werewolfTimer = new Timer(3100, this);
		wizardTimer = new Timer(2600, this);
		yokaiTimer = new Timer(1900, this);
		mobFightTimer = new Timer(1000, this);
		seconds = 60;
		
	}

	// MAIN
	public static void main(String[] args) {
		new Bloodline();
	}

	// PAINT
	public void paint(Graphics g)
	{
		// INITIALIZING PAINT
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		// SETTING TITLE BOOL CONDITION TO TRUE
		if (titleBool == true)
		{
			// DRAW TITLE SCREEN
			g2.drawImage(titleImg.getImage(), 0, 0, this);	
			
			// INITIALIZE START BUTTON (MASKS)
			startButton = new Rectangle2D.Double(360.1, 494.1, 173, 76.9);
		}
		
		// DRAWING MAIN MENU SCREEN
		if (menuBool == true)
		{
			// JFRAME CHANGES
			canClick = true;
			frame.setTitle("BLOODLINE: MENU");
			frame.setSize(1015, 820);
			frame.setLocationRelativeTo(null);
			
			// DRAW MENU SCREEN
			g2.drawImage(menuImg.getImage(), 0, 0, this);
			
			// INITIATE MENU BUTTONS (MASKS)
			loadGameButton = new Rectangle2D.Double(380, 513.4, 240, 51);
			newGameButton = new Rectangle2D.Double(380, 582.3, 240, 51);
			tutorialButton = new Rectangle2D.Double(380, 651.3, 240, 51);
			quitButton = new Rectangle2D.Double(380, 720.4, 240, 51);
			settingsButton = new Rectangle2D.Double(920, 720.4, 55.1, 55.1);

		}

		if (pickGenderBool == true)
		{
			canClick = true;
			
			// DRAWING PICK CHARACTER GENDER SCREEN
			g2.drawImage(charCustomization1.getImage(), 0, 0, this);
			maleButton = new Rectangle2D.Double(292, 274.5, 329.4, 372.1);
			femaleButton = new Rectangle2D.Double(909.4, 274.2, 329.4, 372.1);

			// JFRAME CHANGES
			frame.setTitle("BLOODLINE: PICK YOUR GENDER");
			frame.setSize(1550, 820);
			//frame.setContentPane(this);
			frame.setLocationRelativeTo(null);

		}

		if (startingAreaBool == true)
		{
			// JFRAME CHANGES
			frame.setTitle("BLOODLINE: MAIN GAME");
			frame.setSize(1550, 820);
			frame.setLocationRelativeTo(null);
			setBackground(Color.BLACK);
			
			// Declare and initialize Font and Color objects
			f = new Font("Monospaced", Font.BOLD, 20);
			g2.setFont(f);
			
			// GET FONT METRICS TO BE ABLE TO EDIT AND GET PROPERTIES
			fm = getFontMetrics(f);

			// ACCESSING THE MAPS CLASS TO GET STARTING AREA
			g2.drawImage(maps.getMap().getImage(), maps.getX(), maps.getY(), this);
			g2.drawImage(maps.getPlayer().getImage(), (int) maps.getPlayerX(), (int) maps.getPlayerY(), this);
			g2.drawImage(maps.clickNpc().getImage(), (int) maps.getPlayerX() + maps.getPlayer().getIconWidth(), (int) maps.getPlayerY(), this);
			
			// DRAWING HEALTH BAR
			g2.setColor(new Color(255,0,0));
			Rectangle2D redHP = new Rectangle2D.Double(475, 725, 600, 40);
			g2.fill(redHP);
			g2.setColor(new Color(34,139,34));
			Rectangle2D greenHP = new Rectangle2D.Double(475, 725, maps.getHpWidth(), maps.getHpHeight());
			g2.fill(greenHP);
			
			// DRAWING OTHER PLAYER UI
			
			// NAME
			g2.setColor(new Color(255, 255, 255));
			int strWidth = fm.stringWidth(plr_name);	
			g2.drawString(plr_name, 475, 715);
			
			// HEALTH #
			strWidth = fm.stringWidth(maps.getHealth() + " / " + maps.getMaxHealth());
			g2.drawString(maps.getHealth() + " / " + maps.getMaxHealth(), (int) (getWidth() / 2 - strWidth / 2), 750);
			
			// LIVES
			g2.drawImage(skullIcon.getImage(), 775, 692, this);
			g2.drawString("" + maps.getLives(), 805, 717);
			
			//CURRENCY
			g2.drawImage(currencyIcon.getImage(), 940, 698, this);
			g2.drawString("" + maps.getCurrency(), 974, 717);
			
			// DRAW NPC UI
			g2.drawImage(maps.getUI().getImage(), 0, 0, this);
		}
		
		// SKELETON MOB AREA
		if (skeletonAreaBool == true)
		{
			// START TIMERS FOR PLAYER AND SKELETON MOVEMENT
			plrTimer.start();
			skeletonTimer.start();
			// START MOB TIMER
			mobFightTimer.start();

			
			// DRAWING MAP, PLAYER AND SKELETON VARIANTS
			g2.drawImage(mobMaps.getImage(), 0, 0, this);
			g2.drawImage(maps.getPlayer().getImage(), (int) playerX, (int) playerY, this);
			//g2.draw(maps.getPlrMask());
			Ellipse2D plrRadius = new Ellipse2D.Double(playerX - 353.45, playerY - 353.45, 706.9, 706.9);
			Ellipse2D attackRadius = new Ellipse2D.Double(playerX - 223.35, playerY - 223.35, 446.7, 446.7);
			//g2.draw(plrRadius);
			//g2.draw(attackRadius);
			
			for (int i = 0; i < skeleton.length; i++)
			{
				g2.drawImage(skeleton[i].getNode(attackRadius, playerX, plrRight, plrLeft, maps.getPlayer().getIconWidth()).getImage(), (int) skeleton[i].getX(), (int) skeleton[i].getY(), this);
				//g2.draw(skeleton[i].getSkeletonMask());
				skeleton[i].enemyMove(plrRadius, attackRadius, playerX, playerY, maps.getPlayer().getIconWidth(), maps.getPlayer().getIconHeight());
				g2.setColor(new Color(255,0,0));
				g2.fill(skeleton[i].drawRedHP());
				g2.setColor(new Color(34,139,34));
				g2.fill(skeleton[i].drawGreenHP());
				
				// GET REWARD FOR KILLING SKELETONS
				//reward += skeleton[i].getReward();
			}

			// DRAWING HEALTH BAR
			g2.setColor(new Color(255,0,0));
			Rectangle2D redHP = new Rectangle2D.Double(475, 725, 600, 40);
			g2.fill(redHP);
			g2.setColor(new Color(34,139,34));
			Rectangle2D greenHP = new Rectangle2D.Double(475, 725, maps.getHpWidth(), maps.getHpHeight());
			g2.fill(greenHP);

			// DRAWING OTHER PLAYER UI
			// Declare and initialize Font and Color objects
			f = new Font("Monospaced", Font.BOLD, 20);
			g2.setFont(f);

			// DRAWING COUNTDOWN
			// DRAW COUNTER AT THE TOP RIGHT OF THE SCREEN
			g2.drawString(Integer.toString(seconds), getWidth() - fm.stringWidth(Integer.toString(seconds)), getHeight());
			// NAME
			g2.setColor(new Color(255, 255, 255));
			int strWidth = fm.stringWidth(plr_name);	
			g2.drawString(plr_name, 475, 715);

			// HEALTH #
			strWidth = fm.stringWidth(maps.getHealth() + " / " + maps.getMaxHealth());
			g2.drawString(maps.getHealth() + " / " + maps.getMaxHealth(), (int) (getWidth() / 2 - strWidth / 2), 750);
			plr_health = maps.getHealth();

			// LIVES
			g2.drawImage(skullIcon.getImage(), 775, 692, this);
			g2.drawString("" + maps.getLives(), 805, 717);
			plrLives = maps.getLives();
			
			if (maxLives > plrLives)
			{
				maxLives -= 1;
				seconds = 0;
				promptStartingAreaScreen();
			}

			//CURRENCY
			g2.drawImage(currencyIcon.getImage(), 940, 698, this);
			g2.drawString("" + maps.getCurrency(), 974, 717);

			// DRAW WEAPONS
			if (showWeapon == true)
			{
				if (plrRight)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}
					
					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}
					
					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}
					
					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 10, this);
					}
					
					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
					
					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}
				else if (plrLeft)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}
					
					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}
					
					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}
					
					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth(), (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}
					
					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
					
					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}

				if (isAttacked)
				{
					for (int i = 0; i < skeleton.length; i++)
					{
						if (maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY).intersects(skeleton[i].getSkeletonMask()))
						{
							maps.plrDamaged(10);
							
							if (plr_health == 0)
							{
								promptStartingAreaScreen();
							}
							isAttacked = false;

						}
					}
				}
				g2.draw(maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY));
				//	showWeapon = false;

			}
		}

		// WEREWOLF MOB AREA
		if (werewolfAreaBool == true)
		{
			// START TIMERS FOR PLAYER AND werewolf MOVEMENT
			plrTimer.start();
			werewolfTimer.start();
			// START MOB TIMER
			mobFightTimer.start();


			// DRAWING MAP, PLAYER AND werewolf VARIANTS
			g2.drawImage(mobMaps.getImage(), 0, 0, this);
			g2.drawImage(maps.getPlayer().getImage(), (int) playerX, (int) playerY, this);
			g2.draw(maps.getPlrMask());
			Ellipse2D plrRadius = new Ellipse2D.Double(playerX - 353.45, playerY - 353.45, 706.9, 706.9);
			Ellipse2D attackRadius = new Ellipse2D.Double(playerX - 223.35, playerY - 223.35, 446.7, 446.7);
			g2.draw(plrRadius);
			g2.draw(attackRadius);

			for (int i = 0; i < werewolf.length; i++)
			{
				g2.drawImage(werewolf[i].getNode(attackRadius, playerX, plrRight, plrLeft, maps.getPlayer().getIconWidth()).getImage(), (int) werewolf[i].getX(), (int) werewolf[i].getY(), this);
				//g2.draw(werewolf[i].getwerewolfMask());
				werewolf[i].enemyMove(plrRadius, attackRadius, playerX, playerY, maps.getPlayer().getIconWidth(), maps.getPlayer().getIconHeight());
				g2.setColor(new Color(255,0,0));
				g2.fill(werewolf[i].drawRedHP());
				g2.setColor(new Color(34,139,34));
				g2.fill(werewolf[i].drawGreenHP());

				// GET REWARD FOR KILLING werewolfS
				//reward += werewolf[i].getReward();
			}

			// DRAWING HEALTH BAR
			g2.setColor(new Color(255,0,0));
			Rectangle2D redHP = new Rectangle2D.Double(475, 725, 600, 40);
			g2.fill(redHP);
			g2.setColor(new Color(34,139,34));
			Rectangle2D greenHP = new Rectangle2D.Double(475, 725, maps.getHpWidth(), maps.getHpHeight());
			g2.fill(greenHP);

			// DRAWING OTHER PLAYER UI
			// Declare and initialize Font and Color objects
			f = new Font("Monospaced", Font.BOLD, 20);
			g2.setFont(f);

			// DRAWING COUNTDOWN
			// DRAW COUNTER AT THE TOP RIGHT OF THE SCREEN
			g2.drawString(Integer.toString(seconds), getWidth() - fm.stringWidth(Integer.toString(seconds)), getHeight());
			// NAME
			g2.setColor(new Color(255, 255, 255));
			int strWidth = fm.stringWidth(plr_name);	
			g2.drawString(plr_name, 475, 715);

			// HEALTH #
			strWidth = fm.stringWidth(maps.getHealth() + " / " + maps.getMaxHealth());
			g2.drawString(maps.getHealth() + " / " + maps.getMaxHealth(), (int) (getWidth() / 2 - strWidth / 2), 750);
			plr_health = maps.getHealth();

			// LIVES
			g2.drawImage(skullIcon.getImage(), 775, 692, this);
			g2.drawString("" + maps.getLives(), 805, 717);
			plrLives = maps.getLives();

			if (maxLives > plrLives)
			{
				maxLives -= 1;
				seconds = 0;
				promptStartingAreaScreen();
			}

			//CURRENCY
			g2.drawImage(currencyIcon.getImage(), 940, 698, this);
			g2.drawString("" + maps.getCurrency(), 974, 717);

			// DRAW WEAPONS
			if (showWeapon == true)
			{
				if (plrRight)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}

					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 10, this);
					}

					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}

					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}
				else if (plrLeft)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}

					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth(), (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}

					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}

				if (isAttacked)
				{
					for (int i = 0; i < werewolf.length; i++)
					{
						if (maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY).intersects(werewolf[i].getwerewolfMask()))
						{
							maps.plrDamaged(10);

							if (plr_health == 0)
							{
								promptStartingAreaScreen();
							}
							isAttacked = false;

						}
					}
				}
				g2.draw(maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY));
				//	showWeapon = false;

			}
		}
		
		// wizard MOB AREA
		if (wizardAreaBool == true)
		{
			// START TIMERS FOR PLAYER AND wizard MOVEMENT
			plrTimer.start();
			wizardTimer.start();
			// START MOB TIMER
			mobFightTimer.start();


			// DRAWING MAP, PLAYER AND wizard VARIANTS
			g2.drawImage(mobMaps.getImage(), 0, 0, this);
			g2.drawImage(maps.getPlayer().getImage(), (int) playerX, (int) playerY, this);
			g2.draw(maps.getPlrMask());
			Ellipse2D plrRadius = new Ellipse2D.Double(playerX - 353.45, playerY - 353.45, 706.9, 706.9);
			Ellipse2D attackRadius = new Ellipse2D.Double(playerX - 223.35, playerY - 223.35, 446.7, 446.7);
			g2.draw(plrRadius);
			g2.draw(attackRadius);

			for (int i = 0; i < wizard.length; i++)
			{
				g2.drawImage(wizard[i].getNode(attackRadius, playerX, plrRight, plrLeft, maps.getPlayer().getIconWidth()).getImage(), (int) wizard[i].getX(), (int) wizard[i].getY(), this);
				//g2.draw(wizard[i].getwizardMask());
				wizard[i].enemyMove(plrRadius, attackRadius, playerX, playerY, maps.getPlayer().getIconWidth(), maps.getPlayer().getIconHeight());
				g2.setColor(new Color(255,0,0));
				g2.fill(wizard[i].drawRedHP());
				g2.setColor(new Color(34,139,34));
				g2.fill(wizard[i].drawGreenHP());

				// GET REWARD FOR KILLING wizardS
				//reward += wizard[i].getReward();
			}

			// DRAWING HEALTH BAR
			g2.setColor(new Color(255,0,0));
			Rectangle2D redHP = new Rectangle2D.Double(475, 725, 600, 40);
			g2.fill(redHP);
			g2.setColor(new Color(34,139,34));
			Rectangle2D greenHP = new Rectangle2D.Double(475, 725, maps.getHpWidth(), maps.getHpHeight());
			g2.fill(greenHP);

			// DRAWING OTHER PLAYER UI
			// Declare and initialize Font and Color objects
			f = new Font("Monospaced", Font.BOLD, 20);
			g2.setFont(f);

			// DRAWING COUNTDOWN
			// DRAW COUNTER AT THE TOP RIGHT OF THE SCREEN
			g2.drawString(Integer.toString(seconds), getWidth() - fm.stringWidth(Integer.toString(seconds)), getHeight());
			// NAME
			g2.setColor(new Color(255, 255, 255));
			int strWidth = fm.stringWidth(plr_name);	
			g2.drawString(plr_name, 475, 715);

			// HEALTH #
			strWidth = fm.stringWidth(maps.getHealth() + " / " + maps.getMaxHealth());
			g2.drawString(maps.getHealth() + " / " + maps.getMaxHealth(), (int) (getWidth() / 2 - strWidth / 2), 750);
			plr_health = maps.getHealth();

			// LIVES
			g2.drawImage(skullIcon.getImage(), 775, 692, this);
			g2.drawString("" + maps.getLives(), 805, 717);
			plrLives = maps.getLives();

			if (maxLives > plrLives)
			{
				maxLives -= 1;
				seconds = 0;
				promptStartingAreaScreen();
			}

			//CURRENCY
			g2.drawImage(currencyIcon.getImage(), 940, 698, this);
			g2.drawString("" + maps.getCurrency(), 974, 717);

			// DRAW WEAPONS
			if (showWeapon == true)
			{
				if (plrRight)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}

					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 10, this);
					}

					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}

					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}
				else if (plrLeft)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}

					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth(), (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}

					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}

				if (isAttacked)
				{
					for (int i = 0; i < wizard.length; i++)
					{
						if (maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY).intersects(wizard[i].getwizardMask()))
						{
							maps.plrDamaged(10);

							if (plr_health == 0)
							{
								promptStartingAreaScreen();
							}
							isAttacked = false;

						}
					}
				}
				g2.draw(maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY));
				//	showWeapon = false;

			}
		}
		
		// yokai MOB AREA
		if (yokaiAreaBool == true)
		{
			// START TIMERS FOR PLAYER AND yokai MOVEMENT
			plrTimer.start();
			yokaiTimer.start();
			// START MOB TIMER
			mobFightTimer.start();


			// DRAWING MAP, PLAYER AND yokai VARIANTS
			g2.drawImage(mobMaps.getImage(), 0, 0, this);
			g2.drawImage(maps.getPlayer().getImage(), (int) playerX, (int) playerY, this);
			g2.draw(maps.getPlrMask());
			Ellipse2D plrRadius = new Ellipse2D.Double(playerX - 353.45, playerY - 353.45, 706.9, 706.9);
			Ellipse2D attackRadius = new Ellipse2D.Double(playerX - 223.35, playerY - 223.35, 446.7, 446.7);
			g2.draw(plrRadius);
			g2.draw(attackRadius);

			for (int i = 0; i < yokai.length; i++)
			{
				g2.drawImage(yokai[i].getNode(attackRadius, playerX, plrRight, plrLeft, maps.getPlayer().getIconWidth()).getImage(), (int) yokai[i].getX(), (int) yokai[i].getY(), this);
				//g2.draw(yokai[i].getyokaiMask());
				yokai[i].enemyMove(plrRadius, attackRadius, playerX, playerY, maps.getPlayer().getIconWidth(), maps.getPlayer().getIconHeight());
				g2.setColor(new Color(255,0,0));
				g2.fill(yokai[i].drawRedHP());
				g2.setColor(new Color(34,139,34));
				g2.fill(yokai[i].drawGreenHP());

				// GET REWARD FOR KILLING yokaiS
				//reward += yokai[i].getReward();
			}

			// DRAWING HEALTH BAR
			g2.setColor(new Color(255,0,0));
			Rectangle2D redHP = new Rectangle2D.Double(475, 725, 600, 40);
			g2.fill(redHP);
			g2.setColor(new Color(34,139,34));
			Rectangle2D greenHP = new Rectangle2D.Double(475, 725, maps.getHpWidth(), maps.getHpHeight());
			g2.fill(greenHP);

			// DRAWING OTHER PLAYER UI
			// Declare and initialize Font and Color objects
			f = new Font("Monospaced", Font.BOLD, 20);
			g2.setFont(f);

			// DRAWING COUNTDOWN
			// DRAW COUNTER AT THE TOP RIGHT OF THE SCREEN
			g2.drawString(Integer.toString(seconds), getWidth() - fm.stringWidth(Integer.toString(seconds)), getHeight());
			// NAME
			g2.setColor(new Color(255, 255, 255));
			int strWidth = fm.stringWidth(plr_name);	
			g2.drawString(plr_name, 475, 715);

			// HEALTH #
			strWidth = fm.stringWidth(maps.getHealth() + " / " + maps.getMaxHealth());
			g2.drawString(maps.getHealth() + " / " + maps.getMaxHealth(), (int) (getWidth() / 2 - strWidth / 2), 750);
			plr_health = maps.getHealth();

			// LIVES
			g2.drawImage(skullIcon.getImage(), 775, 692, this);
			g2.drawString("" + maps.getLives(), 805, 717);
			plrLives = maps.getLives();

			if (maxLives > plrLives)
			{
				maxLives -= 1;
				seconds = 0;
				promptStartingAreaScreen();
			}

			//CURRENCY
			g2.drawImage(currencyIcon.getImage(), 940, 698, this);
			g2.drawString("" + maps.getCurrency(), 974, 717);

			// DRAW WEAPONS
			if (showWeapon == true)
			{
				if (plrRight)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}

					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 10, this);
					}

					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}

					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX + maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}
				else if (plrLeft)
				{
					if (weaponName.equals("knifeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("swordWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() + 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("cleaverWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 20, (int) playerY + maps.getPlayer().getIconHeight()/2 - 15, this);
					}

					else if (weaponName.equals("rapierWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth(), (int) playerY + maps.getPlayer().getIconHeight()/2 - 25, this);
					}

					else if (weaponName.equals("axeWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}

					else if (weaponName.equals("sytheWeapon"))
					{
						g2.drawImage(maps.getWeapon(plrRight, plrLeft).getImage(), (int) playerX - maps.getPlayer().getIconWidth() - 10, (int) playerY + maps.getPlayer().getIconHeight()/2 - 40, this);
					}
				}

				if (isAttacked)
				{
					for (int i = 0; i < yokai.length; i++)
					{
						if (maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY).intersects(yokai[i].getyokaiMask()))
						{
							maps.plrDamaged(10);

							if (plr_health == 0)
							{
								promptStartingAreaScreen();
							}
							isAttacked = false;

						}
					}
				}
				g2.draw(maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY));
				//	showWeapon = false;

			}
		}
		if (underworldBool == true)
		{
			g2.drawImage(underworld.getImage(), 0, 0, this);
			Rectangle2D underworldLeftWall = new Rectangle2D.Double(0, 0, 607, 820);
			g2.draw(underworldLeftWall);
			Rectangle2D underworldRightWall = new Rectangle2D.Double(0, 0, 607, 820);
			g2.draw(underworldRightWall);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startingAreaTimer)
		{
			maps.mapMove(left, right, up, down, plr_gender);
			maps.maskUpdate();
			repaint();
		}

		if (e.getSource() == plrTimer)
		{
			if (plrRight && playerX + maps.getPlayer().getIconWidth() <= 1530)
			{
				maps.faceChange(plrRight, plrLeft, plrUp, plrDown, plr_gender);
				playerX += 10;
			}

			if (plrLeft && playerX > 0)
			{
				maps.faceChange(plrRight, plrLeft, plrUp, plrDown, plr_gender);
				playerX -= 10;
			}
			
			if (plrUp && playerY > 0)
			{
				maps.faceChange(plrRight, plrLeft, plrUp, plrDown, plr_gender);
				playerY -= 10;
			}
			
			if (plrDown && playerY + maps.getPlayer().getIconHeight() <= 790)
			{
				maps.faceChange(plrRight, plrLeft, plrUp, plrDown, plr_gender);
				playerY += 10;
			}
			repaint();
		}

		if (e.getSource() == skeletonTimer)
		{
			isAttacked = true;
			repaint();

			if (seconds == 0)
			{
				reward = skeleton[0].getReward() + skeleton[1].getReward() + skeleton[2].getReward() +
						skeleton[3].getReward() + skeleton[4].getReward() + skeleton[5].getReward();
			}
		}

		if (e.getSource() == werewolfTimer)
		{
			isAttacked = true;
			repaint();
			
			if (seconds == 0)
			{
				reward = werewolf[0].getReward() + werewolf[1].getReward() + werewolf[2].getReward() +
						werewolf[3].getReward() + werewolf[4].getReward() + werewolf[5].getReward();
			}
		}
		
		if (e.getSource() == wizardTimer)
		{
			isAttacked = true;
			repaint();
			
			if (seconds == 0)
			{
				reward = wizard[0].getReward() + wizard[1].getReward() + wizard[2].getReward() +
						wizard[3].getReward() + wizard[4].getReward() + wizard[5].getReward();
			}
		}
		
		if (e.getSource() == yokaiTimer)
		{
			isAttacked = true;
			repaint();
			
			if (seconds == 0)
			{
				reward = yokai[0].getReward() + yokai[1].getReward() + yokai[2].getReward() +
						yokai[3].getReward() + yokai[4].getReward() + yokai[5].getReward();
			}
		}
		if (e.getSource() == mobFightTimer)
		{

			seconds--;

			if (seconds == 0)
			{
				mobFightTimer.stop();
				skeletonTimer.stop();
				werewolfTimer.stop();
				promptStartingAreaScreen();
				
				// reward = skeleton[0].getReward() + skeleton[1].getReward() + skeleton[2].getReward() +
						//skeleton[3].getReward() + skeleton[4].getReward() + skeleton[5].getReward();
				
				maps.addReward(reward);

			}

			repaint();
		}

	}


	@Override
	public void keyTyped(KeyEvent e) {

		// TODO Auto-generated method stub

	}


	public void keyPressed(KeyEvent e) {
		
		if (startingAreaBool == true)
		{
			if (e.getKeyCode() == KeyEvent.VK_D)
			{
				right = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_A)
			{
				left = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_W)
			{
				up = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_S)
			{
				down = true;
			}
		}
		
		else if (skeletonAreaBool == true || werewolfAreaBool == true || wizardAreaBool == true || yokaiAreaBool)
		{
			if (e.getKeyCode() == KeyEvent.VK_D)
			{
				plrRight = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_A)
			{
				plrLeft = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_W)
			{
				plrUp = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_S)
			{
				plrDown = true;
			}
		}
	}


	public void keyReleased(KeyEvent e) {

		if (startingAreaBool == true)
		{
			if (e.getKeyCode() == KeyEvent.VK_D)
			{
				right = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_A)
			{
				left = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_W)
			{
				up = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_S)
			{
				down = false;
			}
		}
		
		else if (skeletonAreaBool == true || werewolfAreaBool == true || wizardAreaBool == true || yokaiAreaBool)
		{
			if (e.getKeyCode() == KeyEvent.VK_D)
			{
				plrRight = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_A)
			{
				plrLeft = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_W)
			{
				plrUp = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_S)
			{
				plrDown = false;
			}
		}
	}
		// TODO Auto-generated method stub
		
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// CHECKING IF THE CURRENT SCREEN DRAWN IS THE TITLE SCREEN
		if (titleBool == true)
		{
			// CHECKING IF THE MOUSE CLICKED WITHIN THE "START" BUTTON DIMENSIONS
			if (e.getX() >= startButton.getX() && e.getX() <= startButton.getX() + startButton.getWidth() && canClick == true)
			{
				if (e.getY() >= startButton.getY() && e.getY() <= startButton.getY() + startButton.getHeight())
				{
					// CHANGING TITLE BOOL TO FALSE
					titleBool = false;

					// REPAINTING AND SETTING MENU BOOLEAN TO TRUE
					repaint();
					canClick = false;
					menuBool = true;
				}
			}
			
			try
			{
				// Open an audio input stream.
				File backgroundMusic = new File("sounds\\menuBgMusic.wav");
				AudioInputStream audioIn2 =	AudioSystem.getAudioInputStream(backgroundMusic);
				menuBgMusic = AudioSystem.getClip();
				menuBgMusic.open(audioIn2);
				menuBgMusic.start();
				menuBgMusic.loop(menuBgMusic.LOOP_CONTINUOUSLY);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "File not Found");
			}
		}

		// CHECKING IF THE CURRENT SCREEN IS THE MENU SCREEN
		if (menuBool == true)
		{
			
			if (canLoad == true)
			{
				canClick = true;
				// CHECKING IF THE MOUSE CLICKED WITHIN THE "LOAD GAME" BUTTON DIMENSIONS
				if (e.getX() >= loadGameButton.getX() && e.getX() <= loadGameButton.getX() + loadGameButton.getWidth() && canClick == true)
				{
					if (e.getY() >= loadGameButton.getY() && e.getY() <= loadGameButton.getY() + loadGameButton.getHeight())
					{
		
					}
				}
			}

			// CHECKING IF THE MOUSE CLICKED WITHIN THE "NEW GAME" BUTTON DIMENSIONS
			if (e.getX() >= newGameButton.getX() && e.getX() <= newGameButton.getX() + newGameButton.getWidth() && canClick == true)
			{
				if (e.getY() >= newGameButton.getY() && e.getY() <= newGameButton.getY() + newGameButton.getHeight())
				{
					// PROMPTING TO MOVE TO THE PICK CHARACTER SCREEN AND REPAINTING
					menuBool = false;
					repaint();
					pickGenderBool = true;
					
					// SETTING CLICK DEBOUNCE TO FALSE
					canClick = false;
				}
			}

			// CHECKING IF THE MOUSE CLICKED WITHIN THE "TUTORIALS" BUTTON DIMENSIONS
			if (e.getX() >= tutorialButton.getX() && e.getX() <= tutorialButton.getX() + tutorialButton.getWidth() && canClick == true)
			{
				if (e.getY() >= tutorialButton.getY() && e.getY() <= tutorialButton.getY() + tutorialButton.getHeight())
				{
					int response;

					// CREATING BUTTONS ARRAY FOR TURORIAL
					String buttons[] = new String[] {"NEXT", "CANCEL"};

					canClick =false;
					response = JOptionPane.showOptionDialog(null, "TUTORIAL", "HOW TO PLAY", JOptionPane.PLAIN_MESSAGE, 0, null, buttons, null);

					if (response == 0)
					{

					}

					else if (response == 1)
					{

					}
				}
			}

			// CHECKING IF THE MOUSE CLICKED WITHIN THE "QUIT" BUTTON DIMENSIONS
			if (e.getX() >= quitButton.getX() && e.getX() <= quitButton.getX() + quitButton.getWidth() && canClick == true)
			{
				if (e.getY() >= quitButton.getY() && e.getY() <= quitButton.getY() + quitButton.getHeight())
				{
					System.exit(0);
				}
			}

			// CHECKING IF THE MOUSE CLICKED WITHIN THE "SETTINGS" BUTTON DIMENSIONS
			if (e.getX() >= settingsButton.getX() && e.getX() <= settingsButton.getX() + settingsButton.getWidth() && canClick == true)
			{
				if (e.getY() >= settingsButton.getY() && e.getY() <= settingsButton.getY() + settingsButton.getHeight())
				{
					canClick = false;
					JOptionPane.showMessageDialog(null, "SETTINGS", "LOADING GAME", JOptionPane.PLAIN_MESSAGE);
					canClick = true;
				}
			}

		}

		if (pickGenderBool == true)
		{

			// CHECKING IF THE PLAYER PICKS MALE GENDER
			if (e.getX() >= maleButton.getX() && e.getX() <= maleButton.getX() + maleButton.getWidth() && canClick == true)
			{
				if (e.getY() >= maleButton.getY() && e.getY() <= maleButton.getY() + maleButton.getHeight())
				{

					while (true)
					{
						int response;

						// SETTING PLAYER GENDER TO MALE
						plr_gender = "male";

						// ASKING PLAYER WHAT THEY WANT THEIR PLAYER NAME TO BE
						plr_name = (String) JOptionPane.showInputDialog(null, "Enter a name for your character:\n(Has to be between 3 - 15 characters)", "MALE CHARACTER", JOptionPane.PLAIN_MESSAGE, maleCharacterIcon, null, "");

						if (plr_name != null && plr_name.length() >= 3 && plr_name.length() <= 15)
						{
							// CONFIRMING CHOICE
							response = JOptionPane.showConfirmDialog(null, "Are you sure you want to pick this name and gender?\nYou won't be able to change until your character wipes", "", JOptionPane.OK_CANCEL_OPTION, 0, maleCharacterIcon);

							// PROMPTING NEW GAME
							if (response == JOptionPane.OK_OPTION)
							{
								// TRANSITION INTO GAME
								maps.setGender(plr_gender);
								pickGenderBool = false;
								menuBgMusic.stop();
								menuBgMusic.close();
								repaint();
								startingAreaBool = true;
								// START TIMER FOR STARTING AREA
								startingAreaTimer.start();
								break;
							}

							// REASSIGNING PLAYER NAME AND GENDER TO NULL AND GIVING USER OPPORTUNITY TO PICK AGAIN
							else if (response == JOptionPane.CLOSED_OPTION)
							{
								JOptionPane.showMessageDialog(null, "YOU NEED TO PICK A GENDER AND NAME TO CONTINUE", null, JOptionPane.PLAIN_MESSAGE);
								plr_gender = null;
								plr_name = null;
							}

							// REASSIGNING PLAYER NAME AND GENDER TO NULL AND GIVING USER OPPORTUNITY TO PICK AGAIN
							else if (response == JOptionPane.CANCEL_OPTION)
							{
								JOptionPane.showMessageDialog(null, "Please choose again", null, JOptionPane.PLAIN_MESSAGE);
								plr_gender = null;
								plr_name = null;
								break;
							}
						}

						else
						{
							continue;
						}	
					}
				}
			}


			else if (e.getX() >= femaleButton.getX() && e.getX() <= femaleButton.getX() + femaleButton.getWidth() && canClick == true)
			{
				if (e.getY() >= femaleButton.getY() && e.getY() <= femaleButton.getY() + femaleButton.getHeight())
				{
					while (true)
					{
						int response;

						// SETTING PLAYER GENDER TO MALE
						plr_gender = "female";

						// ASKING PLAYER WHAT THEY WANT THEIR PLAYER NAME TO BE
						plr_name = (String) JOptionPane.showInputDialog(null, "Enter a name for your character:\n(Has to be between 3 - 15 characters)", "FEMALE CHARACTER", JOptionPane.PLAIN_MESSAGE, femaleCharacterIcon, null, "");

						if (plr_name != null && plr_name.length() >= 3 && plr_name.length() <= 15)
						{
							// CONFIRMING CHOICE
							response = JOptionPane.showConfirmDialog(null, "Are you sure you want to pick this name and gender?\nYou won't be able to change until your character wipes", "", JOptionPane.OK_CANCEL_OPTION, 0, femaleCharacterIcon);

							// PROMPTING NEW GAME
							if (response == JOptionPane.OK_OPTION)
							{
								// TRANSITION INTO GAME
								maps.setGender(plr_gender);
								pickGenderBool = false;
								menuBgMusic.stop();
								menuBgMusic.close();
								repaint();
								startingAreaBool = true;
								// START TIMER FOR STARTING AREA
								startingAreaTimer.start();
								break;
							}

							// REASSIGNING PLAYER NAME AND GENDER TO NULL AND GIVING USER OPPORTUNITY TO PICK AGAIN
							else if (response == JOptionPane.CLOSED_OPTION)
							{
								JOptionPane.showMessageDialog(null, "YOU NEED TO PICK A GENDER AND NAME TO CONTINUE", null, JOptionPane.PLAIN_MESSAGE);
								plr_gender = null;
								plr_name = null;
							}

							// REASSIGNING PLAYER NAME AND GENDER TO NULL AND GIVING USER OPPORTUNITY TO PICK AGAIN
							else if (response == JOptionPane.CANCEL_OPTION)
							{
								JOptionPane.showMessageDialog(null, "Please choose again", null, JOptionPane.PLAIN_MESSAGE);
								plr_gender = null;
								plr_name = null;
								break;
							}
						}

						else
						{
							continue;
						}	
					}
				}
			}
		}
		
		
		// CHECKING IF ANY BUTTONS ARE CLICKED IN THE STARTING AREA
		if (startingAreaBool == true)
		{
			String buttons[] = new String[] {"Who are you?", "next", "cancel"};
			String dialogueButtons[] = new String[] {"next", "cancel"};
			int choice;
			
			// CHECKING FOR REAPER NPC CLICKED
			if (e.getX() >= maps.getReaperMask().getX() && e.getX() <= maps.getReaperMask().getX() + maps.getReaperMask().getWidth() && canClick == true)
			{
				if (e.getY() >= maps.getReaperMask().getY() && e.getY() <= maps.getReaperMask().getY() + maps.getReaperMask().getHeight())
				{
					if (maps.getPlrMask().intersects(maps.getReaperMask()))
					{
						choice = JOptionPane.showOptionDialog(null, "Welcome stranger", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, buttons, null);
						
						if (choice == 0)
						{
							choice = JOptionPane.showOptionDialog(null, "Who I am is of no importance right now\n .. \nJust know that our paths may cross again soon", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
							
							if (choice == 0)
							{
								choice = JOptionPane.showOptionDialog(null, "I'm not sure how you got here\nbut since you're here, you\nmight aswell help me out", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
								
								if (choice == 0)
								{
									choice = JOptionPane.showOptionDialog(null, "You cannot leave this cursed town..\nSo you might aswell get comfortable\nI want you to take the path to the right\nand get rid of the skeletons for me", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
									
									if (choice == 0)
									{
										choice = JOptionPane.showOptionDialog(null, "If you're successful then i might consider\nhaving you fight the stronger monsters for me.\nAlthough those ones are pretty strong", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
										
										if (choice == 0)
										{
											choice = JOptionPane.showOptionDialog(null, "You would need to take the path up to the lighthouse\nif you want to upgrade your weapon and armour\nto be capable of fighting stronger monsters", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
											
											if (choice == 0)
											{
												choice = JOptionPane.showOptionDialog(null, "goodluck stranger", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
											}
										}
									}
								}
							}
						}
						
						if (choice == 1)
						{
							choice = JOptionPane.showOptionDialog(null, "I'm not sure how you got here\nbut since you're here, you\nmight aswell help me out", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
							
							if (choice == 0)
							{
								choice = JOptionPane.showOptionDialog(null, "You cannot leave this cursed town..\nSo you might aswell get comfortable\nI want you to take the path to the right\nand get rid of the skeletons for me", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
								
								if (choice == 0)
								{
									choice = JOptionPane.showOptionDialog(null, "If you're successful then i might consider\nhaving you fight the stronger monsters for me.\nAlthough those ones are pretty strong", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
									
									if (choice == 0)
									{
										choice = JOptionPane.showOptionDialog(null, "You would need to take the path up to the lighthouse\nif you want to upgrade your weapon and armour\nto be capable of fighting stronger monsters", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
										
										if (choice == 0)
										{
											choice = JOptionPane.showOptionDialog(null, "goodluck stranger", "Malachi: The Grim Reaper", JOptionPane.PLAIN_MESSAGE, 0, reaperNpcIcon, dialogueButtons, null);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// CHECKING FOR SKELETON NPC CLICKED
			if (e.getX() >= 692.1 && e.getX() <= 692.1 + 165.7 && canClick == true)
			{
				if (e.getY() >= 545 && e.getY() <= 545 + 59)
				{
					if (maps.getPlrMask().intersects(maps.getSkeletonMask()))
					{
						for (int i = 0; i < skeleton.length; i++)
						{
							repaint();
							skeletonAreaBool = true;
							startingAreaBool = false;
							startingAreaTimer.stop();
							skeleton[i] = new skeletonMob();
							skeleton[i].setLocation(rand.nextInt(1530), rand.nextInt(570));
							seconds = 60;
						}
					}
				}
			}

			// CHECKING FOR WEREWOLF NPC CLICKED
			if (e.getX() >= 692.1 && e.getX() <= 692.1 + 165.7 && canClick == true)
			{
				if (e.getY() >= 545 && e.getY() <= 545 + 59)
				{
					if (maps.getPlrMask().intersects(maps.getWerewolfMask()))
					{
						for (int i = 0; i < werewolf.length; i++)
						{
							repaint();
							werewolfAreaBool = true;
							startingAreaBool = false;
							startingAreaTimer.stop();
							werewolf[i] = new werewolfMod();
							werewolf[i].setLocation(rand.nextInt(1530), rand.nextInt(570));
							seconds = 60;
						}
					}
				}
			}

			// CHECKING FOR WIZARD NPC CLICKED
			if (e.getX() >= 692.1 && e.getX() <= 692.1 + 165.7 && canClick == true)
			{
				if (e.getY() >= 545 && e.getY() <= 545 + 59)
				{
					if (maps.getPlrMask().intersects(maps.getWizardMask()))
					{
						for (int i = 0; i < wizard.length; i++)
						{
							repaint();
							wizardAreaBool = true;
							startingAreaBool = false;
							startingAreaTimer.stop();
							wizard[i] = new wizardMob();
							wizard[i].setLocation(rand.nextInt(1530), rand.nextInt(570));
							seconds = 60;
						}
					}
				}
			}

			// CHECKING FOR YOKAI NPC CLICKED
			if (e.getX() >= 692.1 && e.getX() <= 692.1 + 165.7 && canClick == true)
			{
				if (e.getY() >= 545 && e.getY() <= 545 + 59)
				{
					if (maps.getPlrMask().intersects(maps.getYokaiMask()))
					{
						for (int i = 0; i < yokai.length; i++)
						{
							repaint();
							yokaiAreaBool = true;
							startingAreaBool = false;
							startingAreaTimer.stop();
							yokai[i] = new yokaiMob();
							yokai[i].setLocation(rand.nextInt(1530), rand.nextInt(570));
							seconds = 60;
						}
					}
				}
			}
			if (maps.getPlrMask().intersects(maps.getShopMask()))
			{
				// CHECKING FOR BASIC ARMOUR CLICKED
				if (e.getX() >= 290.2 && e.getX() <= 290.2 + 82.5 && leatherArmourPurchased)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						JOptionPane.showMessageDialog(null, "Equipped: Leather Armour", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						maps.setArmour("leatherArmour");
					}
				}

				// CHECKING FOR BRONZE ARMOUR 
				if (e.getX() >= 426.8 && e.getX() <= 426.8 + 82.5)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						if (bronzeArmourPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Bronze Armour", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the bronze armour for: " + bronzeArmourCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= bronzeArmourCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Bronze Armour", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									bronzeArmourPurchased = true;
									maps.setArmour("bronzeArmour");
									maps.setHealth();
									maps.promtPurchase(bronzeArmourCost);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
				// CHECKING FOR IRON ARMOUR 
				if (e.getX() >= 556 && e.getX() <= 556 + 82.5)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						if (ironArmourPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Iron Armour", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the iron armour for: " + ironArmourCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= ironArmourCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Iron Armour", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									ironArmourPurchased = true;
									maps.setArmour("ironArmour");
									maps.setHealth();
									maps.promtPurchase(ironArmourCost);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
				// CHECKING FOR SILVER ARMOUR 
				if (e.getX() >= 680.8 && e.getX() <= 680.8 + 82.5)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						if (silverArmourPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Silver Armour", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the silver armour for: " + silverArmourCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= silverArmourCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Silver Armour", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									silverArmourPurchased = true;
									maps.setArmour("silverArmour");
									maps.setHealth();
									maps.promtPurchase(silverArmourCost);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
				// CHECKING FOR KNIFE WEAPON
				if (e.getX() >= 804.8 && e.getX() <= 804.8 + 82.5 && knifeWeaponPurchased)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						JOptionPane.showMessageDialog(null, "Equipped: Knife Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						weaponName = "knifeWeapon";
						maps.setWeapon("knifeWeapon");
					}
				}
				
				// CHECKING FOR SWORD WEAPON
				if (e.getX() >= 920.1 && e.getX() <= 920.1 + 82.5)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						if (swordWeaponPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Sword Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the sword weapon for: " + swordWeaponCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= swordWeaponCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Sword Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									swordWeaponPurchased = true;
									maps.promtPurchase(swordWeaponCost);
									weaponName = "swordWeapon";
									maps.setWeapon("swordWeapon");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
				// CHECKING FOR CLEAVER WEAPON
				if (e.getX() >= 1040.3 && e.getX() <= 1040.3 + 82.5)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						if (cleaverWeaponPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Cleaver Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the cleaver weapon for: " + cleaverWeaponCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= cleaverWeaponCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Cleaver Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									cleaverWeaponPurchased = true;
									maps.promtPurchase(cleaverWeaponCost);
									weaponName = "cleaverWeapon";
									maps.setWeapon("cleaverWeapon");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
				// CHECKING FOR RAPIER WEAPON
				if (e.getX() >= 1152.8 && e.getX() <= 1152.8 + 82.5)
				{
					if (e.getY() >= 257.9 && e.getY() <= 257.9 + 76.5)
					{
						if (rapierWeaponPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Rapier Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the rapier weapon for: " + rapierWeaponCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= rapierWeaponCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Rapier Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									rapierWeaponPurchased = true;
									maps.promtPurchase(rapierWeaponCost);
									weaponName = "rapierWeapon";
									maps.setWeapon("rapierWeapon");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
				// CHECKING FOR AXE WEAPON
				if (e.getX() >= 804.8 && e.getX() <= 804.8 + 82.5)
				{
					if (e.getY() >= 371.8 && e.getY() <= 371.8 + 76.5)
					{
						if (axeWeaponPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Axe Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the axe weapon for: " + axeWeaponCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= axeWeaponCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Axe Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									axeWeaponPurchased = true;
									maps.promtPurchase(axeWeaponCost);
									weaponName = "axeWeapon";
									maps.setWeapon("axeWeapon");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
				// CHECKING FOR SYTHE WEAPON
				if (e.getX() >= 927.3 && e.getX() <= 927.3 + 82.5)
				{
					if (e.getY() >= 371.8 && e.getY() <= 371.8 + 76.5)
					{
						if (sytheWeaponPurchased)
						{
							JOptionPane.showMessageDialog(null, "Equipped: Sythe Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
						}
						else
						{
							choice = JOptionPane.showConfirmDialog(null, "Would you like to buy the sythe weapon for: " + sytheWeaponCost + " silver?", "LIGHTHOUSE SHOP", JOptionPane.OK_CANCEL_OPTION);
							
							if (choice == JOptionPane.OK_OPTION)
							{
								if (maps.getCurrency() >= sytheWeaponCost)
								{
									JOptionPane.showMessageDialog(null, "Equipped: Sythe Weapon", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
									sytheWeaponPurchased = true;
									maps.promtPurchase(sytheWeaponCost);
									weaponName = "sytheWeapon";
									maps.setWeapon("sytheWeapon");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Not enough money to purchase this", "LIGHTHOUSE SHOP", JOptionPane.OK_OPTION);
								}
							}
						}
					}
				}
				
			}
		}
		
		if (skeletonAreaBool == true)
		{
			if (e.getX() >= 0 && e.getX() <= getWidth())
			{
				if (e.getY() >= 0 && e.getY() <= getWidth())
				{
					showWeapon = true;
					
					for (int i = 0; i < skeleton.length; i++)
					{
						if (maps.getWeaponMask(weaponName, plrRight, plrLeft, playerX, playerY).intersects(skeleton[i].getSkeletonMask()))
						{
							skeleton[i].skeletonAttacked(weaponName);
							repaint();
						}
					}
				}
			}
		}
	}

	
	public void promptStartingAreaScreen()
	{
		skeletonAreaBool = false;
		werewolfAreaBool = false;
		wizardAreaBool = false;
		yokaiAreaBool = false;
		startingAreaBool = true;
		startingAreaTimer.start();
		playerX = 775;
		playerY = 700;

		maps.setHealth();
		maps.setHpWidth();
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

