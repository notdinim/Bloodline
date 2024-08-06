import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;


public class skeletonMob
{
	private ImageIcon skeleton, skeletonLeft, skeletonRight, skeletonAttackRight, skeletonAttackLeft, skeletonDead;
	private double xPos, yPos;
	// IS DEAD BOOLEAN TO HANDLE DEATH
	private boolean isDead, canDamage;
	private Rectangle2D skeletonMask, redHP, greenHP;
	private int skeletonHealth, skeletonHpWidth, skeletonDmg;
	private int skeletonReward;
	private Random rand;
	private int index, maxHP;
	
	public skeletonMob()
	{
		isDead = false;
		canDamage = true;
		
		rand = new Random();
		index = rand.nextInt(2) + 1;
		skeleton = new ImageIcon("images\\mobAreas\\skeletonVariant" + index + ".gif");
		skeletonLeft = new ImageIcon("images\\mobAreas\\skeletonLeftWalkVariant" + index + ".gif");
		skeletonRight = new ImageIcon("images\\mobAreas\\skeletonVariant" + index + ".gif");
		skeletonAttackRight = new ImageIcon("images\\mobAreas\\skeletonAttackVariant" + index + ".gif");
		skeletonAttackLeft = new ImageIcon("images\\mobAreas\\skeletonLeftAttackVariant" + index + ".gif");
		skeletonDead = new ImageIcon("images\\mobAreas\\skeletonDeadVariant" + index + ".png");
		skeletonHealth = 80;
		skeletonDmg = 10;
		skeletonReward = 0;
		maxHP = 80;
		skeletonHpWidth = 100;
		
		xPos = 0;
		yPos = 0;
	}

	public void setLocation(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	public double getX()
	{
		return xPos;
	}
	public double getY()
	{
		return yPos;
	}
	public ImageIcon getNode(Ellipse2D attackRadius, double plrX, boolean right, boolean left, double plrWidth)
	{
		if (isDead == true)
		{
			skeleton = skeletonDead;
		}
		
		if (isDead != true)
		{
			if (attackRadius.intersects(skeletonMask))
			{
				if (xPos > plrX)
				{
					skeleton = skeletonAttackLeft;
				}
			}
			
			if (attackRadius.intersects(skeletonMask))
			{
				if (xPos <= plrX + plrWidth/2)
				{
					skeleton = skeletonAttackRight;
				}
			}

		}

		return skeleton;
	}

	public Rectangle2D getSkeletonMask()
	{
		if (isDead == false)
		{
			skeletonMask = new Rectangle2D.Double(xPos, yPos, skeleton.getIconWidth(), skeleton.getIconHeight());

			if (skeletonHealth <= 0)
			{
				// CHANGING ISDEAD TO TRUE AND REMOVING MASK SO PLAYER CAN'T COLLIDE AND LOSE HEALTH ANYMORE
				isDead = true;
				skeletonMask = new Rectangle2D.Double(-200, -200, 5, 5);
			}
		}
		return skeletonMask;
	}

	
	public int getReward()
	{
		if (skeletonHealth <= 0)
		{
			// REWARD
			skeletonReward = 4;
		}
		else
		{
			skeletonReward = 0;
		}
		return skeletonReward;
	}
	public Rectangle2D drawRedHP()
	{
		redHP = new Rectangle2D.Double(xPos + 2, yPos + skeleton.getIconHeight(), 100, 10);
		return redHP;
	}
	
	public Rectangle2D drawGreenHP()
	{
		greenHP = new Rectangle2D.Double(xPos + 2, yPos + skeleton.getIconHeight(), skeletonHpWidth, 10);
		return greenHP;
	}
	
	public int getHealth()
	{
		return skeletonHealth;
	}
	
	// METHOD FOR WHEN THE SKELETON IS ATTACKED
	// IT CHECKS WHICH WEAPON AND DEALS OUT THE APPROPRIATE DAMAGE
	public void skeletonAttacked(String weapon)
	{
		if (weapon.equals("knifeWeapon"))
		{
			skeletonHealth -= 20;
			
			skeletonHpWidth -= 25;;
		}
		else if (weapon.equals("swordWeapon"))
		{
			skeletonHealth -= 40;
			skeletonHpWidth -= 80;
		}
		else if (weapon.equals("cleaverWeapon"))
		{
			skeletonHealth -= 60;
			skeletonHpWidth -= 75;
		}
		else if (weapon.equals("rapierWeapon"))
		{
			skeletonHealth -= 100;
			skeletonHpWidth -= 100;
		}
		else if (weapon.equals("axeWeapon"))
		{
			skeletonHealth -= 140;
			skeletonHpWidth -= 140;
		}
		else if (weapon.equals("sytheWeapon"))
		{
			skeletonHealth -= 195;
			skeletonHpWidth -= 195;
		}
		

	}

	public void enemyMove(Ellipse2D plrRadius, Ellipse2D attackRadius, double plrX, double plrY, double plrWidth, double plrHeight) {

		if (isDead != true)
		{
			if (plrRadius.intersects(skeletonMask))
			{
				if (xPos > plrX)
				{
					skeleton = skeletonLeft;
					xPos -= 0.5;
				}
				if (xPos < plrX + plrWidth/2)
				{
					skeleton = skeletonRight;
					xPos += 0.5;
				}

				if (yPos > plrY)
				{
					yPos -= 0.5;
				}
				if (yPos < plrY - plrHeight/2)
				{
					yPos += 0.5;
				}
			}

			// WRITE CODE FOR SKELETON ATTACK HERE
		}

	}
}