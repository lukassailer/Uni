package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The barrier surrounds the game area and spawns new elements of increasing
 * number (based on the level) within the game area. The first constructor sets
 * the base block at (-1,-1) and initializes the rest of the field with the
 * other private constructor.
 */
public class Barrier extends LinkEntity
{
	private Rectangle borderArea;

	public Barrier(Rectangle _gameArea, int level)
	{
		super(-1, -1);
		col = SnakeGame.arenaBarrier;
		next = this;

		borderArea = new Rectangle(_gameArea.x / MainFrame.tileSize, _gameArea.y / MainFrame.tileSize,
				_gameArea.width / MainFrame.tileSize, _gameArea.height / MainFrame.tileSize);

		for (int y = 0; y < borderArea.height; y++)
		{
			for (int x = 0; x < borderArea.width; x++)
			{
				if (y == 0 || x == 0 || y == (borderArea.height - 1) || x == (borderArea.width - 1))
				{
					extend(x - 1, y - 1);
				}
			}
		}

		for (int i = 0; i < SnakeGame.barrierPerLevel*level; i++)
		{
			int tempx;
			int tempy;
			do
			{
				tempx = (int) ((Math.random() * (borderArea.width - 2) + 1));
				tempy = (int) ((Math.random() * (borderArea.height - 2) + 1));
			}
			while (isOccupied(tempx, tempy) || (tempx == borderArea.width / 2 && tempy == borderArea.height / 2));
			extend(tempx, tempy);
		}

	}

	private Barrier(int _x, int _y)
	{
		super(_x, _y);
		col = SnakeGame.arenaBarrier;
	}

	public void extend(int _x, int _y)
	{
		if (isLast())
		{
			next = setNext(new Barrier(_x, _y));
		}
		else
		{
			next = getNext();
			((Barrier) next).extend(_x, _y);
		}
	}
}
