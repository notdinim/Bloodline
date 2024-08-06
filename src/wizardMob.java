import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;


public class wizardMob
{
	private ImageIcon wizard, wizardLeft, wizardRight, wizardAttackRight, wizardAttackLeft, wizardDead;
	private double xPos, yPos;
	// IS DEAD BOOLEAN TO HANDLE DEATH
	private boolean isDead, canDamage;
	private Rectangle2D wizardMask, redHP, greenHP;
	private int wizardHealth, wizardHpWidth, wizardDmg;
	private int wizardReward;
	private Random rand;
	private int index, maxHP;
	
	public wizardMob()
	{
		isDead = false;
		canDamage = true;
		
		rand = new Random();
		index = rand.nextInt(2) + 1;
		wizard = new ImageIcon("images\\mobAreas\\wizardVariant" + index + ".gif");
		wizardLeft = new ImageIcon("images\\mobAreas\\wizardLeftVariant" + index + ".gif");
		wizardRight = new ImageIcon("images\\mobAreas\\wizardVariant" + index + ".gif");
		wizardAttackRight = new ImageIcon("images\\mobAreas\\wizardAttackVariant" + index + ".gif");
		wizardAttackLeft = new ImageIcon("images\\mobAreas\\wizardLeftAttackVariant" + index + ".gif");
		wizardDead = new ImageIcon("images\\mobAreas\\wizardDeadVariant" + index + ".png");
		wizardHealth = 80;
		wizardDmg = 10;
		wizardReward = 0;
		maxHP = 80;
		wizardHpWidth = 100;
		
		xPos = 0;
		yPos = 0;
		
		wizardMask = new Rectangle2D.Double(xPos, yPos, wizard.getIconWidth(), wizard.getIconHeight());
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
			wizard = wizardDead;
		}
		
		if (isDead != true)
		{
			if (attackRadius.intersects(wizardMask))
			{
				if (xPos > plrX)
				{
					wizard = wizardAttackLeft;
				}
			}
			
			if (attackRadius.intersects(wizardMask))
			{
				if (xPos <= plrX + plrWidth/2)
				{
					wizard = wizardAttackRight;
				}
			}

		}

		return wizard;
	}

	public Rectangle2D getwizardMask()
	{
		if (isDead == false)
		{
			wizardMask = new Rectangle2D.Double(xPos, yPos, wizard.getIconWidth(), wizard.getIconHeight());

			if (wizardHealth <= 0)
			{
				// CHANGING ISDEAD TO TRUE AND REMOVING MASK SO PLAYER CAN'T COLLIDE AND LOSE HEALTH ANYMORE
				isDead = true;
				wizardMask = new Rectangle2D.Double(-200, -200, 5, 5);
			}
		}
		return wizardMask;
	}

	
	public int getReward()
	{
		if (wizardHealth <= 0)
		{
			// REWARD
			wizardReward = 8;
		}
		else
		{
			wizardReward = 0;
		}
		return wizardReward;
	}
	public Rectangle2D drawRedHP()
	{
		redHP = new Rectangle2D.Double(xPos + 2, yPos + wizard.getIconHeight(), 100, 10);
		return redHP;
	}
	
	public Rectangle2D drawGreenHP()
	{
		greenHP = new Rectangle2D.Double(xPos + 2, yPos + wizard.getIconHeight(), wizardHpWidth, 10);
		return greenHP;
	}
	
	public int getHealth()
	{
		return wizardHealth;
	}
	
	// METHOD FOR WHEN THE wizard IS ATTACKED
	// IT CHECKS WHICH WEAPON AND DEALS OUT THE APPROPRIATE DAMAGE
	public void wizardAttacked(String weapon)
	{
		if (weapon.equals("knifeWeapon"))
		{
			wizardHealth -= 20;
			
			wizardHpWidth -= 25;;
		}
		else if (weapon.equals("swordWeapon"))
		{
			wizardHealth -= 40;
			wizardHpWidth -= 80;
		}
		else if (weapon.equals("cleaverWeapon"))
		{
			wizardHealth -= 60;
			wizardHpWidth -= 75;
		}
		else if (weapon.equals("rapierWeapon"))
		{
			wizardHealth -= 100;
			wizardHpWidth -= 100;
		}
		else if (weapon.equals("axeWeapon"))
		{
			wizardHealth -= 140;
			wizardHpWidth -= 140;
		}
		else if (weapon.equals("sytheWeapon"))
		{
			wizardHealth -= 195;
			wizardHpWidth -= 195;
		}
		

	}

	public void enemyMove(Ellipse2D plrRadius, Ellipse2D attackRadius, double plrX, double plrY, double plrWidth, double plrHeight) {

		if (isDead != true)
		{
			if (plrRadius.intersects(wizardMask))
			{
				if (xPos >= plrX)
				{
					wizard = wizardLeft;
					xPos -= 0.5;
				}
				if (xPos < plrX + plrWidth/2)
				{
					wizard = wizardRight;
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

			// WRITE CODE FOR wizard ATTACK HERE
		}

	}
}
