import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;


public class yokaiMob
{
	private ImageIcon yokai, yokaiLeft, yokaiRight, yokaiAttackRight, yokaiAttackLeft, yokaiDead;
	private double xPos, yPos;
	// IS DEAD BOOLEAN TO HANDLE DEATH
	private boolean isDead, canDamage;
	private Rectangle2D yokaiMask, redHP, greenHP;
	private int yokaiHealth, yokaiHpWidth, yokaiDmg;
	private int yokaiReward;
	private Random rand;
	private int index, maxHP;
	
	public yokaiMob()
	{
		isDead = false;
		canDamage = true;
		
		rand = new Random();
		index = rand.nextInt(2) + 1;
		yokai = new ImageIcon("images\\mobAreas\\yokaiVariant" + index + ".gif");
		yokaiLeft = new ImageIcon("images\\mobAreas\\yokaiLeftVariant" + index + ".gif");
		yokaiRight = new ImageIcon("images\\mobAreas\\yokaiVariant" + index + ".gif");
		yokaiAttackRight = new ImageIcon("images\\mobAreas\\yokaiAttackVariant" + index + ".gif");
		yokaiAttackLeft = new ImageIcon("images\\mobAreas\\yokaiLeftAttackVariant" + index + ".gif");
		yokaiDead = new ImageIcon("images\\mobAreas\\yokaiDeadVariant" + index + ".png");
		yokaiHealth = 80;
		yokaiDmg = 10;
		yokaiReward = 0;
		maxHP = 80;
		yokaiHpWidth = 100;
		
		xPos = 0;
		yPos = 0;
		
		yokaiMask = new Rectangle2D.Double(xPos, yPos, yokai.getIconWidth(), yokai.getIconHeight());
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
			yokai = yokaiDead;
		}
		
		if (isDead != true)
		{
			if (attackRadius.intersects(yokaiMask))
			{
				if (xPos > plrX)
				{
					yokai = yokaiAttackLeft;
				}
			}
			
			if (attackRadius.intersects(yokaiMask))
			{
				if (xPos <= plrX + plrWidth/2)
				{
					yokai = yokaiAttackRight;
				}
			}

		}

		return yokai;
	}

	public Rectangle2D getyokaiMask()
	{
		if (isDead == false)
		{
			yokaiMask = new Rectangle2D.Double(xPos, yPos, yokai.getIconWidth(), yokai.getIconHeight());

			if (yokaiHealth <= 0)
			{
				// CHANGING ISDEAD TO TRUE AND REMOVING MASK SO PLAYER CAN'T COLLIDE AND LOSE HEALTH ANYMORE
				isDead = true;
				yokaiMask = new Rectangle2D.Double(-200, -200, 5, 5);
			}
		}
		return yokaiMask;
	}

	
	public int getReward()
	{
		if (yokaiHealth <= 0)
		{
			// REWARD
			yokaiReward = 8;
		}
		else
		{
			yokaiReward = 0;
		}
		return yokaiReward;
	}
	public Rectangle2D drawRedHP()
	{
		redHP = new Rectangle2D.Double(xPos + 2, yPos + yokai.getIconHeight(), 100, 10);
		return redHP;
	}
	
	public Rectangle2D drawGreenHP()
	{
		greenHP = new Rectangle2D.Double(xPos + 2, yPos + yokai.getIconHeight(), yokaiHpWidth, 10);
		return greenHP;
	}
	
	public int getHealth()
	{
		return yokaiHealth;
	}
	
	// METHOD FOR WHEN THE yokai IS ATTACKED
	// IT CHECKS WHICH WEAPON AND DEALS OUT THE APPROPRIATE DAMAGE
	public void yokaiAttacked(String weapon)
	{
		if (weapon.equals("knifeWeapon"))
		{
			yokaiHealth -= 20;
			
			yokaiHpWidth -= 25;;
		}
		else if (weapon.equals("swordWeapon"))
		{
			yokaiHealth -= 40;
			yokaiHpWidth -= 80;
		}
		else if (weapon.equals("cleaverWeapon"))
		{
			yokaiHealth -= 60;
			yokaiHpWidth -= 75;
		}
		else if (weapon.equals("rapierWeapon"))
		{
			yokaiHealth -= 100;
			yokaiHpWidth -= 100;
		}
		else if (weapon.equals("axeWeapon"))
		{
			yokaiHealth -= 140;
			yokaiHpWidth -= 140;
		}
		else if (weapon.equals("sytheWeapon"))
		{
			yokaiHealth -= 195;
			yokaiHpWidth -= 195;
		}
		

	}

	public void enemyMove(Ellipse2D plrRadius, Ellipse2D attackRadius, double plrX, double plrY, double plrWidth, double plrHeight) {

		if (isDead != true)
		{
			if (plrRadius.intersects(yokaiMask))
			{
				if (xPos >= plrX)
				{
					yokai = yokaiLeft;
					xPos -= 0.5;
				}
				if (xPos < plrX + plrWidth/2)
				{
					yokai = yokaiRight;
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

			// WRITE CODE FOR yokai ATTACK HERE
		}

	}
}
