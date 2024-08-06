// UML SHEET
// SHEET PROVIDES THE MAPS AND AREAS FOR BLOODLINE GAME

// IMPORTS
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

// MAIN CLASS
public class BloodlineAreas {

	// FIELDS
	// BUTTON CLICK DEBOUCE
	private boolean canClick;
	// STARTING AREA VALUES
	private int startingAreaX, startingAreaY;
	private int startingAreaWidth, startingAreaHeight;
	private ImageIcon startingArea;
	
	// PLAYER VALUES
	private ImageIcon player;
	private double playerX, playerY;
	private String weapon_name;
	private ImageIcon currentWeapon;
	private Rectangle2D weaponMask;
	private String armour_name;
	private int lives;
	private int currency;
	private String gender;
	
	// HP BAR PROPERTIES
	private double hpWidth, hpHeight;
	private int health, maxHealth;
	
	// PLAYER ANIMS
	private ImageIcon boyWalkLeftAnim;
	private ImageIcon boyWalkRightAnim;
	private ImageIcon boyWalkUpAnim;
	private ImageIcon boyWalkDownAnim; 	
	private ImageIcon girlWalkLeftAnim;
	private ImageIcon girlWalkRightAnim;
	private ImageIcon girlWalkUpAnim;
	private ImageIcon girlWalkDownAnim;
	
	private ImageIcon boyWalkLeftAnim_leather;
	private ImageIcon boyWalkRightAnim_leather;
	private ImageIcon boyWalkUpAnim_leather;
	private ImageIcon boyWalkDownAnim_leather;
	
	private ImageIcon girlWalkLeftAnim_leather;
	private ImageIcon girlWalkRightAnim_leather;
	private ImageIcon girlWalkUpAnim_leather;
	private ImageIcon girlWalkDownAnim_leather;
	
	// CLICK TO INTERACT IMAGE ON NPC
	private ImageIcon clickToInteract;
	public ImageIcon transparentImg;
	
	// CLICKED NPC PROMT UI
	private ImageIcon currentUI, skeletonFightUI, werewolfLockedFightUI, werewolfFightUI, wizardLockedFightUI, wizardFightUI, yokaiLockedFightUI, yokaiFightUI, shopUI;
	
	// NPC UI FIGHT PROMT HANDLER
	private boolean werewolfAreaPurchased, wizardAreaPurchased, yokaiAreaPurchased;
	
	// SPEED
	private int speed;
	
	// STARTING AREA MASKS (26)
	private Rectangle2D townMask1, townMask2, townMask3, townMask4, townMask5, townMask6, townMask7, townMask8, townMask9, townMask10,
	townMask11, townMask12, townMask13, townMask14, townMask15, townMaskBorderUp, townMaskBorderDown, townMaskBorderLeft, townMaskBorderRight, playerMask,
	reaperNpcMask, skeletonNpcMask, werewolfNpcMask, wizardNpcMask, yokaiNpcMask, shopMask;
	
	// WEAPON IMAGES
	private ImageIcon knifeWeaponRightImg, knifeWeaponLeftImg,
	swordWeaponRightImg, swordWeaponLeftImg,
	cleaverWeaponRightImg, cleaverWeaponLeftImg,
	rapierWeaponRightImg, rapierWeaponLeftImg,
	axeWeaponRightImg, axeWeaponLeftImg,
	sytheWeaponRightImg, sytheWeaponLeftImg;
	
	// WEAPON MASKS
	private Rectangle2D knifeWeapon, swordWeapon, cleaverWeapon, rapierWeapon, axeWeapon, sytheWeapon;

	//  SKELETON CLASS
	private skeletonMob skeleton[];
	
	// CONSTRUCTOR
	public BloodlineAreas()
	{
		// INITIATE MOBS, ENEMIES AND MAPS
		skeleton = new skeletonMob[6];


		// INITIALIZE BOOLEAN
		canClick = true;

		// INITIATING PLAYER VALUES
		player = new ImageIcon("images\\boyWalkDownAnim.gif");
		playerX = 775;
		playerY = 410;
		lives = 3;
		currency = 2100;
		gender = "";
		armour_name = "";

		// INITIATING HP BAR PROPERTIES
		health = 100;
		maxHealth = 100;
		hpWidth = 600;
		hpHeight = 40;
				
		// INITIATING PLAYER ANIMS
		boyWalkLeftAnim = new ImageIcon("images\\boyWalkLeftAnim.gif");
		boyWalkRightAnim = new ImageIcon("images\\boyWalkRightAnim.gif");
		boyWalkUpAnim = new ImageIcon("images\\boyWalkUpAnim.gif");
		boyWalkDownAnim = new ImageIcon("images\\boyWalkDownAnim.gif");	
		girlWalkLeftAnim = new ImageIcon("images\\girlWalkLeftAnim.gif");
		girlWalkRightAnim = new ImageIcon("images\\girlWalkRightAnim.gif");
		girlWalkUpAnim = new ImageIcon("images\\girlWalkUpAnim.gif");
		girlWalkDownAnim = new ImageIcon("images\\girlWalkDownAnim.gif");
		
		boyWalkLeftAnim_leather = new ImageIcon("images\\boyWalkLeftAnim_leather.gif");
		boyWalkRightAnim_leather = new ImageIcon("images\\boyWalkRightAnim_leather.gif");
		boyWalkUpAnim_leather = new ImageIcon("images\\boyWalkUpAnim_leather.gif");
		boyWalkDownAnim_leather = new ImageIcon("images\\boyWalkDownAnim_leather.gif");
		girlWalkLeftAnim_leather = new ImageIcon("images\\girlWalkLeftAnim_leather.gif");
		girlWalkRightAnim_leather = new ImageIcon("images\\girlWalkRightAnim_leather.gif");
		girlWalkUpAnim_leather = new ImageIcon("images\\girlWalkUpAnim_leather.gif");
		girlWalkDownAnim_leather = new ImageIcon("images\\girlWalkDownAnim_leather.gif");
		
		// INITIATING WEAPONS
		knifeWeaponRightImg = new ImageIcon("images\\knifeWeaponRight.png");
		knifeWeaponLeftImg = new ImageIcon("images\\knifeWeaponLeft.png");
		swordWeaponRightImg = new ImageIcon("images\\swordWeaponRight.png");
		swordWeaponLeftImg = new ImageIcon("images\\swordWeaponLeft.png");
		cleaverWeaponRightImg = new ImageIcon("images\\cleaverWeaponRight.png");
		cleaverWeaponLeftImg = new ImageIcon("images\\cleaverWeaponLeft.png");
		rapierWeaponRightImg = new ImageIcon("images\\rapierWeaponRight.png");
		rapierWeaponLeftImg = new ImageIcon("images\\rapierWeaponLeft.png");
		axeWeaponRightImg = new ImageIcon("images\\axeWeaponRight.png");
		axeWeaponLeftImg = new ImageIcon("images\\axeWeaponLeft.png");
		sytheWeaponRightImg = new ImageIcon("images\\sytheWeaponRight.png");
		sytheWeaponLeftImg = new ImageIcon("images\\sytheWeaponLeft.png");
		
		// INITIATE STARTING WEAPON
		weapon_name = "knifeWeapon";
		currentWeapon = knifeWeaponRightImg;
		
		// CLICK TO INTERACT
		clickToInteract = new ImageIcon("images\\clickToInteract.png");
		transparentImg = new ImageIcon("images\\transparentImg.png");
		
		// INITIALIZING FIGHT UI FOR NPCS
		skeletonFightUI = new ImageIcon("images\\skeletonFightUI.png");
		werewolfLockedFightUI = new ImageIcon("images\\werewolfLockedFightUI.png");
		werewolfFightUI = new ImageIcon("images\\werewolfFightUI.png");
		wizardLockedFightUI = new ImageIcon("images\\wizardLockedFightUI.png");
		wizardFightUI = new ImageIcon("images\\wizardFightUI.png");
		yokaiLockedFightUI = new ImageIcon("images\\yokaiLockedFightUI.png");
		yokaiFightUI = new ImageIcon("images\\yokaiFightUI.png");
		shopUI = new ImageIcon("images\\shopUI.png");
		
		// INITIATE FIGHT HANDLERS
		werewolfAreaPurchased = true;
		wizardAreaPurchased = true;
		yokaiAreaPurchased = true;
		
		// INITIATING STARTING AREA VALUES
		startingArea = new ImageIcon("images\\startingArea.png");
		startingAreaX = -1350;
		startingAreaY = -980;
		
		// INITIATING SPEED
		speed = 10;

		// INITIATING MASKS
		playerMask = new Rectangle2D.Double(playerX, playerY, player.getIconWidth(), player.getIconHeight());
		// STARTING AREA MASKS
		townMask1 = new Rectangle2D.Double(2008.5, 997.6, 305.9, 198.2);
		townMask2 = new Rectangle2D.Double(1510.8, 613.2, 361.3, 152.4);
		townMask3 = new Rectangle2D.Double(507.3, 1011, 568.3, 175.9);
		townMask4 = new Rectangle2D.Double(1304.1, 1186.8, 361.3, 152.4);
		townMask5 = new Rectangle2D.Double(507.3, 419.2, 180.5, 319.6);
		townMask6 = new Rectangle2D.Double(2, 1186.8, 821, 167.1);
		townMask7 = new Rectangle2D.Double(2314.4, 942.1, 80.3, 677.9);
		townMask8 = new Rectangle2D.Double(507.3, 738.7, 705.3, 272.2);
		townMask9 = new Rectangle2D.Double(1397.3, 332.5, 764.2, 280.7);
		townMask10 = new Rectangle2D.Double(2394.7, 738.7, 235, 1059.6);
		townMask11 = new Rectangle2D.Double(1102.4, 1339.3, 816.7, 280.7);
		townMask12 = new Rectangle2D.Double(802.5, 1.6, 1566.2 , 330.8);
		townMask13 = new Rectangle2D.Double(574.9, 1620, 1819.9, 178.4);
		townMask14 = new Rectangle2D.Double(2629.7, 1.6, 470.3, 1798.4);
		townMask15 = new Rectangle2D.Double(0, 1.6, 507.3, 1185.2);
		
		townMaskBorderUp = new Rectangle2D.Double(startingAreaX, startingAreaY, 3100, 10);
		townMaskBorderDown = new Rectangle2D.Double(startingAreaX, startingAreaY + startingArea.getIconHeight(), 3100, 10);
		townMaskBorderLeft = new Rectangle2D.Double(startingAreaX, startingAreaY, 10, 1800);
		townMaskBorderRight = new Rectangle2D.Double(startingAreaX + startingArea.getIconWidth(), startingAreaY, 10, 1800);
		
		// NPC MASKS
		reaperNpcMask = new Rectangle2D.Double(1580.6, 937.8, 116.5, 143.9);
		skeletonNpcMask = new Rectangle2D.Double(2409.9, 0, 97.9, 131.8);
		werewolfNpcMask = new Rectangle2D.Double(0, 1678.4, 167.5, 121.6);
		wizardNpcMask = new Rectangle2D.Double(643.5, 0, 95.1, 131.8);
		yokaiNpcMask = new Rectangle2D.Double(488.5, 270.4, 122.3, 143.5);
		
		// WEAPON MASK
		
		// SHOP MASK
		shopMask = new Rectangle2D.Double(1256.4, 330.1, 140.9, 102.5);
			
	}
	// SET THE GENDER OF CHARACTER
	public void setGender(String e)
	{
		if (e.equals("male"))
		{
			gender = e;
			player = boyWalkDownAnim;
		}
		
		else if (e.equals("female"))
		{
			gender = e;
			player = girlWalkDownAnim;

		}
	}
	
	// RETURN PLAYER VALUES
	public ImageIcon getPlayer()
	{
		return player;
	}
	
	
	public double getPlayerX()
	{
		return playerX;
	}
	
	public double getPlayerY()
	{
		return playerY;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public void addReward(int reward)
	{
		
		currency += reward;
	}
	public int getCurrency()
	{
		return currency;
	}

	public void setArmour(String e)
	{
		armour_name = e;

		if (armour_name.equals("leatherArmour") && gender.equals("male"))
		{
			// HEALTH FOR LEATHER ARMOUR
			health = 150;
			maxHealth = 150;
			player = boyWalkUpAnim_leather;
		}
		else if (armour_name.equals("leatherArmour") && gender.equals("female"))
		{
			// HEALTH FOR LEATHER ARMOUR
			health = 150;
			maxHealth = 150;
			player = girlWalkUpAnim_leather;
		}
		
		if (armour_name.equals("bronzeArmour") && gender.equals("male"))
		{
			// HEALTH FOR BRONZE ARMOUR
			health = 210;
			maxHealth = 210;
		}
		
		if (armour_name.equals("ironArmour") && gender.equals("male"))
		{
			// HEALTH FOR IRON ARMOUR
			health = 150;
			maxHealth = 150;
		}
		
		if (armour_name.equals("silverArmour") && gender.equals("male"))
		{
			// HEALTH FOR SILVER ARMOUR
			health = 240;
			maxHealth = 240;
		}
	}
	
	public void setHealth()
	{
		if (armour_name.equals("leatherArmour"))
		{
			// HEALTH FOR LEATHER ARMOUR
			health = 150;
			maxHealth = 150;

		}
		else if (armour_name.equals("bronzeArmour"))
		{
			// HEALTH FOR BRONZE ARMOUR
			health = 210;
			maxHealth = 210;
		}
		else if (armour_name.equals("ironArmour"))
		{
			// HEALTH FOR IRON ARMOUR
			health = 150;
			maxHealth = 150;
		}
		else if (armour_name.equals("silverArmour"))
		{
			// HEALTH FOR SILVER ARMOUR
			health = 240;
			maxHealth = 240;
		}
		else
		{
			health = 100;
			maxHealth = 100;
		}
	}

	public void plrDamaged(int dmg)
	{
		if (health != 0 && health > 0)
		{
			final int hpBarFactor = health/dmg;
			int reduceBarFactor = (int) (hpWidth/hpBarFactor);

			health -= dmg;
			hpWidth -= reduceBarFactor;
		}
		else if (health == 0)
		{
			lives -= 1;
		}
	}

	public void setWeapon(String weapon)
	{
		weapon_name = weapon;
	}
	
	public ImageIcon getWeapon(boolean right, boolean left)
	{
		if (currentWeapon != null)
		{
			if (right == true)
			{
				if (weapon_name.equals("knifeWeapon"))
				{
					currentWeapon = knifeWeaponRightImg;
				}
				if (weapon_name.equals("swordWeapon"))
				{
					currentWeapon = swordWeaponRightImg;
				}
				if (weapon_name.equals("cleaverWeapon"))
				{
					currentWeapon = cleaverWeaponRightImg;
				}
				if (weapon_name.equals("rapierWeapon"))
				{
					currentWeapon = rapierWeaponRightImg;
				}
				if (weapon_name.equals("axeWeapon"))
				{
					currentWeapon = axeWeaponRightImg;
				}
				if (weapon_name.equals("sytheWeapon"))
				{
					currentWeapon = sytheWeaponRightImg;
				}
			}
			else if (left == true)
			{
				if (weapon_name.equals("knifeWeapon"))
				{
					currentWeapon = knifeWeaponLeftImg;
				}
				if (weapon_name.equals("swordWeapon"))
				{
					currentWeapon = swordWeaponLeftImg;
				}
				if (weapon_name.equals("cleaverWeapon"))
				{
					currentWeapon = cleaverWeaponLeftImg;
				}
				if (weapon_name.equals("rapierWeapon"))
				{
					currentWeapon = rapierWeaponLeftImg;
				}
				if (weapon_name.equals("axeWeapon"))
				{
					currentWeapon = axeWeaponLeftImg;
				}
				if (weapon_name.equals("sytheWeapon"))
				{
					currentWeapon = sytheWeaponLeftImg;
				}
			}
		}

//		if (right != true && left != true)
//		{
//			currentWeapon = transparentImg;
//		}
		return currentWeapon;
	}
	
	public Rectangle2D getWeaponMask(String weapon, boolean right, boolean left, double plrX, double plrY)
	{
		if (right == true)
		{
			if (weapon.equals("knifeWeapon"))
			{
				knifeWeapon = new Rectangle2D.Double(plrX + player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 25, 34.8, 30.3);
				weaponMask = knifeWeapon;
			}
			if (weapon.equals("swordWeapon"))
			{
				swordWeapon = new Rectangle2D.Double(plrX + player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 25, 37.4, 34.1);
				weaponMask = swordWeapon;
			}
			if (weapon.equals("cleaverWeapon"))
			{
				cleaverWeapon = new Rectangle2D.Double(plrX + player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 15, 68.3, 30.2);
				weaponMask = cleaverWeapon;
			}
			if (weapon.equals("rapierWeapon"))
			{
				rapierWeapon = new Rectangle2D.Double(plrX + player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 25, 41.5, 40.4);
				weaponMask = rapierWeapon;
			}
			if (weapon.equals("axeWeapon"))
			{
				axeWeapon = new Rectangle2D.Double(plrX + player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 40, 47.7, 47.9);
				weaponMask = axeWeapon;
			}
			if (weapon.equals("sytheWeapon"))
			{
				sytheWeapon = new Rectangle2D.Double(plrX + player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 40, 50.3, 52.1);
				weaponMask = sytheWeapon;
			}
		}
		else if (left == true)
		{
			if (weapon.equals("knifeWeapon"))
			{
				knifeWeapon = new Rectangle2D.Double(plrX - player.getIconWidth() + 10, plrY + player.getIconHeight()/2 - 25, 34.8, 30.3);
				weaponMask = knifeWeapon;
			}
			if (weapon.equals("swordWeapon"))
			{
				swordWeapon = new Rectangle2D.Double(plrX - player.getIconWidth(), plrY + player.getIconHeight()/2 - 25, 37.4, 34.1);
				weaponMask = swordWeapon;
			}
			if (weapon.equals("cleaverWeapon"))
			{
				cleaverWeapon = new Rectangle2D.Double(plrX - player.getIconWidth() - 20, plrY + player.getIconHeight()/2 - 15, 68.3, 30.2);
				weaponMask = cleaverWeapon;
			}
			if (weapon.equals("rapierWeapon"))
			{
				rapierWeapon = new Rectangle2D.Double(plrX - player.getIconWidth(), plrY + player.getIconHeight()/2 - 25, 41.5, 40.4);
				weaponMask = rapierWeapon;
			}
			if (weapon.equals("axeWeapon"))
			{
				axeWeapon = new Rectangle2D.Double(plrX - player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 40, 47.7, 47.9);
				weaponMask = axeWeapon;
			}
			if (weapon.equals("sytheWeapon"))
			{
				sytheWeapon = new Rectangle2D.Double(plrX - player.getIconWidth() - 10, plrY + player.getIconHeight()/2 - 40, 50.3, 52.1);
				weaponMask = sytheWeapon;
			}

		}
		else
		{
			weaponMask = new Rectangle2D.Double(0,0,1,1);
		}
		return weaponMask;
	}
	// GET HEALTH BAR PROPERTIES
	public double getHpWidth()
	{
		return hpWidth;
	}
	
	public void setHpWidth()
	{
		hpWidth = 600;
	}
	
	public double getHpHeight()
	{
		return hpHeight;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	// RETURN X AND Y DIMENSIONS OF STARTING AREA
	public int getX()
	{
		return startingAreaX;
	}
	
	public int getY()
	{
		return startingAreaY;
	}
	
	// RETURN DIMENSIONS OF STARTING AREA
	public int getWidth()
	{
		return startingArea.getIconWidth();
	}
	public int getHeight()
	{
		return startingArea.getIconHeight();
	}
	
	// STARTING A NEW GAME AND LOADING THE STARTER AREA AND PLAYER DATA
	public ImageIcon getMap()
	{
		return startingArea;
	}

	// RETURN MASKS TO MAIN CLASS
	public Rectangle2D getPlrMask()
	{
		return playerMask;
	}
	
	public Rectangle2D getReaperMask()
	{
		return reaperNpcMask;
	}
	
	public Rectangle2D getSkeletonMask()
	{
		return skeletonNpcMask;
	}
	
	public Rectangle2D getWerewolfMask()
	{
		return werewolfNpcMask;
	}
	
	public Rectangle2D getWizardMask()
	{
		return wizardNpcMask;
	}
	
	public Rectangle2D getYokaiMask()
	{
		return yokaiNpcMask;
	}
	
	// RETURN SHOP MASK
	public Rectangle2D getShopMask()
	{
		return shopMask;
	}
	
	public void promtPurchase(int e) {

		currency -= e;
		
	}

	// PROXIMITY CHECKS FOR NPCS
	public ImageIcon clickNpc()
	{
		if (playerMask.intersects(reaperNpcMask))
		{
			clickToInteract = new ImageIcon("images\\clickToInteract.png");
		}

		else 
		{
			clickToInteract = transparentImg;
		}
		return clickToInteract;
	}

	public ImageIcon getUI()
	{
		if (playerMask.intersects(skeletonNpcMask))
		{
			currentUI = skeletonFightUI;
		}
		
		else if (playerMask.intersects(werewolfNpcMask) && werewolfAreaPurchased == false)
		{
			currentUI = werewolfLockedFightUI;
		}
		else if (playerMask.intersects(werewolfNpcMask) && werewolfAreaPurchased)
		{
			currentUI = werewolfFightUI;
		}
		
		else if (playerMask.intersects(wizardNpcMask) && wizardAreaPurchased == false)
		{
			currentUI = wizardLockedFightUI;
		}
		else if (playerMask.intersects(wizardNpcMask) && wizardAreaPurchased)
		{
			currentUI = wizardFightUI;
		}
		
		else if (playerMask.intersects(yokaiNpcMask) && yokaiAreaPurchased == false)
		{
			currentUI = yokaiLockedFightUI;
		}
		else if (playerMask.intersects(yokaiNpcMask) && yokaiAreaPurchased)
		{
			currentUI = yokaiFightUI;
		}
		
		else if (playerMask.intersects(shopMask))
		{
			currentUI = shopUI;
		}
			
		else
		{
			currentUI = transparentImg;
		}
		
		return currentUI;
	}

	// TRIGGER EVENT FOR MAP MOVEMENT
	public void mapMove(boolean left, boolean right, boolean up, boolean down, String plr_gender) {

		// CHECKING IF PLAYER ISN'T TOUCHING ANY MASKS
		if (!playerMask.intersects(townMask1) && !playerMask.intersects(townMask2) && !playerMask.intersects(townMask3) &&
				!playerMask.intersects(townMask6) &&
				!playerMask.intersects(townMask7) && !playerMask.intersects(townMask8) && !playerMask.intersects(townMask9) &&
				!playerMask.intersects(townMask10)
				&& !playerMask.intersects(townMask14) && !playerMask.intersects(skeletonNpcMask)
				&& !playerMask.intersects(wizardNpcMask)
				&& !playerMask.intersects(townMaskBorderUp) && !playerMask.intersects(townMaskBorderLeft) &&
				!playerMask.intersects(townMaskBorderRight))
		{		

			if (up == true)
			{
				if (plr_gender.equals("male") && armour_name == "")
				{
					player = boyWalkUpAnim;
				}
				else if (plr_gender.equals("female") && armour_name == "")
				{
					player = girlWalkUpAnim;
				}

				if (plr_gender.equals("male") && armour_name == "leatherArmour")
				{
					player = boyWalkUpAnim_leather;
				}

				else if (plr_gender.equals("female") && armour_name == "leatherArmour")
				{
					player = girlWalkUpAnim_leather;
				}
				startingAreaY += speed;
			}
		}
		
		// CHECKING IF PLAYER ISN'T TOUCHING ANY MASKS
		if (!playerMask.intersects(townMask1) && 
				!playerMask.intersects(townMask4) && !playerMask.intersects(townMask5) && 
				!playerMask.intersects(townMask7) && !playerMask.intersects(townMask8) &&
				!playerMask.intersects(townMask10) && !playerMask.intersects(townMask11) &&
				!playerMask.intersects(townMask13) && !playerMask.intersects(townMask14) &&
				!playerMask.intersects(townMaskBorderDown) && !playerMask.intersects(townMaskBorderLeft) && !playerMask.intersects(werewolfNpcMask)
				&& !playerMask.intersects(yokaiNpcMask) && !playerMask.intersects(townMaskBorderRight))
		{
			
			if (down == true)
			{
				if (plr_gender.equals("male") && armour_name == "")
				{
					player = boyWalkDownAnim;
				}
				else if (plr_gender.equals("female") && armour_name == "")
				{
					player = girlWalkDownAnim;
				}

				if (plr_gender.equals("male") && armour_name == "leatherArmour")
				{
					player = boyWalkDownAnim_leather;
				}
				else if (plr_gender.equals("female") && armour_name == "leatherArmour")
				{
					player = girlWalkDownAnim_leather;
				}
				startingAreaY -= speed;
			}
		}

		// CHECKING IF PLAYER ISN'T TOUCHING ANY MASKS
		if (!playerMask.intersects(townMask2) && !playerMask.intersects(townMask3) &&
				!playerMask.intersects(townMask4) && !playerMask.intersects(townMask5) && !playerMask.intersects(townMask6) &&
				!playerMask.intersects(townMask8) && !playerMask.intersects(townMask9) && !playerMask.intersects(skeletonNpcMask)
				&& !playerMask.intersects(wizardNpcMask) && !playerMask.intersects(townMask11) && !playerMask.intersects(townMask12) && !playerMask.intersects(reaperNpcMask)
				&& !playerMask.intersects(townMask15) && !playerMask.intersects(werewolfNpcMask) && !playerMask.intersects(yokaiNpcMask) &&
				!playerMask.intersects(townMaskBorderUp) && !playerMask.intersects(townMaskBorderDown) && !playerMask.intersects(townMaskBorderLeft))
		{
			if (left == true)
			{
				if (plr_gender.equals("male") && armour_name == "")
				{
					player = boyWalkLeftAnim;
				}
				else if (plr_gender.equals("female") && armour_name == "")
				{
					player = girlWalkLeftAnim;
				}

				if (plr_gender.equals("male") && armour_name == "leatherArmour")
				{
					player = boyWalkLeftAnim_leather;
				}
				else if (plr_gender.equals("female") && armour_name == "leatherArmour")
				{
					player = girlWalkLeftAnim_leather;
				}
				startingAreaX += speed;
			}
		}

		// CHECKING IF PLAYER ISN'T TOUCHING ANY MASKS
		if (!playerMask.intersects(townMask1) && !playerMask.intersects(townMask2) && 
				!playerMask.intersects(townMask4) &&
				!playerMask.intersects(townMask7) && !playerMask.intersects(townMask9) &&
				!playerMask.intersects(townMask10) && !playerMask.intersects(townMask11) && !playerMask.intersects(townMask12) &&
				!playerMask.intersects(townMask13) && !playerMask.intersects(townMask14) && !playerMask.intersects(wizardNpcMask) &&
				!playerMask.intersects(townMaskBorderUp) && !playerMask.intersects(townMaskBorderDown) && !playerMask.intersects(reaperNpcMask)
				&& !playerMask.intersects(townMaskBorderRight))
		{
			if (right == true)
			{
				if (plr_gender.equals("male") && armour_name == "")
				{
					player = boyWalkRightAnim;
				}
				else if (plr_gender.equals("female") && armour_name == "")
				{
					player = girlWalkRightAnim;
				}

				if (plr_gender.equals("male") && armour_name == "leatherArmour")
				{
					player = boyWalkRightAnim_leather;
				}

				else if (plr_gender.equals("female") && armour_name == "leatherArmour")
				{
					player = girlWalkRightAnim_leather;
				}
				startingAreaX -= speed;
			}
		}

	}

	// UPDATING MAKKS TO ALIGHT WITH BACKGROUND MOVEMENT
	public void maskUpdate()
	{

		playerMask = new Rectangle2D.Double(playerX, playerY, player.getIconWidth(), player.getIconHeight());
		// STARTING AREA MASKS
		townMask1 = new Rectangle2D.Double(2008.5 + startingAreaX, 997.6 + startingAreaY, 305.9, 198.2);
		townMask2 = new Rectangle2D.Double(1510.8 + startingAreaX, 613.2 + startingAreaY, 361.3, 152.4);
		townMask3 = new Rectangle2D.Double(507.3 + startingAreaX, 1011 + startingAreaY, 568.3, 175.9);
		townMask4 = new Rectangle2D.Double(1304.1 + startingAreaX, 1186.8 + startingAreaY, 361.3, 152.4);
		townMask5 = new Rectangle2D.Double(507.3 + startingAreaX, 419.2 + startingAreaY, 180.5, 319.6);
		townMask6 = new Rectangle2D.Double(2 + startingAreaX, 1186.8 + startingAreaY, 821, 167.1);
		townMask7 = new Rectangle2D.Double(2314.4 + startingAreaX, 942.1 + startingAreaY, 80.3, 677.9);
		townMask8 = new Rectangle2D.Double(507.3 + startingAreaX, 738.7 + startingAreaY, 705.3, 272.2);
		townMask9 = new Rectangle2D.Double(1397.3 + startingAreaX, 332.5 + startingAreaY, 764.2, 280.7);
		townMask10 = new Rectangle2D.Double(2394.7 + startingAreaX, 738.7 + startingAreaY, 235, 1059.6);
		townMask11 = new Rectangle2D.Double(1102.4 + startingAreaX, 1339.3 + startingAreaY, 816.7, 280.7);
		townMask12 = new Rectangle2D.Double(802.5 + startingAreaX, 1.6 + startingAreaY, 1566.2 , 330.8);
		townMask13 = new Rectangle2D.Double(574.9 + startingAreaX, 1620 + startingAreaY, 1819.9, 178.4);
		townMask14 = new Rectangle2D.Double(2629.7 + startingAreaX, 1.6 + startingAreaY, 470.3, 1798.4);
		townMask15 = new Rectangle2D.Double(0 + startingAreaX, 1.6 + startingAreaY, 507.3, 1185.2);
		
		reaperNpcMask = new Rectangle2D.Double(1580.6 + startingAreaX, 937.8 + startingAreaY, 116.5, 143.9);
		skeletonNpcMask = new Rectangle2D.Double(2409.9 + startingAreaX, 0 + startingAreaY, 97.9, 131.8);
		werewolfNpcMask = new Rectangle2D.Double(0 + startingAreaX, 1678.4 + startingAreaY, 167.5, 121.6);
		wizardNpcMask = new Rectangle2D.Double(643.5 + startingAreaX, 0 + startingAreaY, 95.1, 131.8);
		yokaiNpcMask = new Rectangle2D.Double(488.5 + startingAreaX, 270.4 + startingAreaY, 122.3, 143.5);
		
		shopMask = new Rectangle2D.Double(1256.4 + startingAreaX, 330.1 + startingAreaY, 140.9, 102.5);
		
		townMaskBorderUp = new Rectangle2D.Double(startingAreaX, startingAreaY, 3100, 10);
		townMaskBorderDown = new Rectangle2D.Double(startingAreaX, startingAreaY + startingArea.getIconHeight(), 3100, 10);
		townMaskBorderLeft = new Rectangle2D.Double(startingAreaX, startingAreaY, 10, 1800);
		townMaskBorderRight = new Rectangle2D.Double(startingAreaX + startingArea.getIconWidth(), startingAreaY, 10, 1800);
	}
	
	// FACE CHANGES FOR PLAYER MOVEMENT DURING MOB FIGHTS
	public void faceChange(boolean right, boolean left, boolean up, boolean down, String gender)
	{
		if (right == true)
		{
			if (gender.equals("male") && armour_name == "")
			{
				player = boyWalkRightAnim;
			}
			else if (gender.equals("female") && armour_name == "")
			{
				player = girlWalkRightAnim;
			}

			if (gender.equals("male") && armour_name == "leatherArmour")
			{
				player = boyWalkRightAnim_leather;
			}

			else if (gender.equals("female") && armour_name == "leatherArmour")
			{
				player = girlWalkRightAnim_leather;
			}
		}
		
		else if (left)
		{
			if (gender.equals("male") && armour_name == "")
			{
				player = boyWalkLeftAnim;
			}
			else if (gender.equals("female") && armour_name == "")
			{
				player = girlWalkLeftAnim;
			}
			
			if (gender.equals("male") && armour_name == "leatherArmour")
			{
				player = boyWalkLeftAnim_leather;
			}
			else if (gender.equals("female") && armour_name == "leatherArmour")
			{
				player = girlWalkLeftAnim_leather;
			}
		}
		
		else if (up)
		{
			if (gender.equals("male") && armour_name == "")
			{
				player = boyWalkUpAnim;
			}
			else if (gender.equals("female") && armour_name == "")
			{
				player = girlWalkUpAnim;
			}
			
			if (gender.equals("male") && armour_name == "leatherArmour")
			{
				player = boyWalkUpAnim_leather;
			}
			else if (gender.equals("female") && armour_name == "leatherArmour")
			{
				player = girlWalkUpAnim_leather;
			}
		}
		
		else if (down)
		{
			if (gender.equals("male") && armour_name == "")
			{
				player = boyWalkDownAnim;
			}
			else if (gender.equals("female") && armour_name == "")
			{
				player = girlWalkDownAnim;
			}
			
			if (gender.equals("male") && armour_name == "leatherArmour")
			{
				player = boyWalkDownAnim_leather;
			}

			else if (gender.equals("female") && armour_name == "leatherArmour")
			{
				player = girlWalkDownAnim_leather;
			}
		}
	}
	
}
