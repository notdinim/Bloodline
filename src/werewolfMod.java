import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;


public class werewolfMod
{
	private ImageIcon werewolf, werewolfLeft, werewolfRight, werewolfAttackRight, werewolfAttackLeft, werewolfDead;
	private double xPos, yPos;
	// IS DEAD BOOLEAN TO HANDLE DEATH
	private boolean isDead, canDamage;
	private Rectangle2D werewolfMask, redHP, greenHP;
	private int werewolfHealth, werewolfHpWidth, werewolfDmg;
	private int werewolfReward;
	private Random rand;
	private int index, maxHP;
	
	public werewolfMod()
	{
		isDead = false;
		canDamage = true;
		
		rand = new Random();
		index = rand.nextInt(2) + 1;
		werewolf = new ImageIcon("images\\mobAreas\\werewolfVariant" + index + ".gif");
		werewolfLeft = new ImageIcon("images\\mobAreas\\werewolfLeftVariant" + index + ".gif");
		werewolfRight = new ImageIcon("images\\mobAreas\\werewolfVariant" + index + ".gif");
		werewolfAttackRight = new ImageIcon("images\\mobAreas\\werewolfAttackVariant" + index + ".gif");
		werewolfAttackLeft = new ImageIcon("images\\mobAreas\\werewolfLeftAttackVariant" + index + ".gif");
		werewolfDead = new ImageIcon("images\\mobAreas\\werewolfDeadVariant" + index + ".png");
		werewolfHealth = 80;
		werewolfDmg = 10;
		werewolfReward = 0;
		maxHP = 80;
		werewolfHpWidth = 100;
		
		xPos = 0;
		yPos = 0;
		
		werewolfMask = new Rectangle2D.Double(xPos, yPos, werewolf.getIconWidth(), werewolf.getIconHeight());
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
			werewolf = werewolfDead;
		}
		
		if (isDead != true)
		{
			if (attackRadius.intersects(werewolfMask))
			{
				if (xPos > plrX)
				{
					werewolf = werewolfAttackLeft;
				}
			}
			
			if (attackRadius.intersects(werewolfMask))
			{
				if (xPos <= plrX + plrWidth/2)
				{
					werewolf = werewolfAttackRight;
				}
			}

		}

		return werewolf;
	}

	public Rectangle2D getwerewolfMask()
	{
		if (isDead == false)
		{
			werewolfMask = new Rectangle2D.Double(xPos, yPos, werewolf.getIconWidth(), werewolf.getIconHeight());

			if (werewolfHealth <= 0)
			{
				// CHANGING ISDEAD TO TRUE AND REMOVING MASK SO PLAYER CAN'T COLLIDE AND LOSE HEALTH ANYMORE
				isDead = true;
				werewolfMask = new Rectangle2D.Double(-200, -200, 5, 5);
			}
		}
		return werewolfMask;
	}

	
	public int getReward()
	{
		if (werewolfHealth <= 0)
		{
			// REWARD
			werewolfReward = 8;
		}
		else
		{
			werewolfReward = 0;
		}
		return werewolfReward;
	}
	public Rectangle2D drawRedHP()
	{
		redHP = new Rectangle2D.Double(xPos + 2, yPos + werewolf.getIconHeight(), 100, 10);
		return redHP;
	}
	
	public Rectangle2D drawGreenHP()
	{
		greenHP = new Rectangle2D.Double(xPos + 2, yPos + werewolf.getIconHeight(), werewolfHpWidth, 10);
		return greenHP;
	}
	
	public int getHealth()
	{
		return werewolfHealth;
	}
	
	// METHOD FOR WHEN THE werewolf IS ATTACKED
	// IT CHECKS WHICH WEAPON AND DEALS OUT THE APPROPRIATE DAMAGE
	public void werewolfAttacked(String weapon)
	{
		if (weapon.equals("knifeWeapon"))
		{
			werewolfHealth -= 20;
			
			werewolfHpWidth -= 25;;
		}
		else if (weapon.equals("swordWeapon"))
		{
			werewolfHealth -= 40;
			werewolfHpWidth -= 80;
		}
		else if (weapon.equals("cleaverWeapon"))
		{
			werewolfHealth -= 60;
			werewolfHpWidth -= 75;
		}
		else if (weapon.equals("rapierWeapon"))
		{
			werewolfHealth -= 100;
			werewolfHpWidth -= 100;
		}
		else if (weapon.equals("axeWeapon"))
		{
			werewolfHealth -= 140;
			werewolfHpWidth -= 140;
		}
		else if (weapon.equals("sytheWeapon"))
		{
			werewolfHealth -= 195;
			werewolfHpWidth -= 195;
		}
		

	}

	public void enemyMove(Ellipse2D plrRadius, Ellipse2D attackRadius, double plrX, double plrY, double plrWidth, double plrHeight) {

		if (isDead != true)
		{
			if (plrRadius.intersects(werewolfMask))
			{
				if (xPos >= plrX)
				{
					werewolf = werewolfLeft;
					xPos -= 0.5;
				}
				if (xPos < plrX + plrWidth/2)
				{
					werewolf = werewolfRight;
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

			// WRITE CODE FOR werewolf ATTACK HERE
		}

	}
}
