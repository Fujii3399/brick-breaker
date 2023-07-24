package game;

import java.awt.Image;

import main.MainPanel;

class Block {
	Image block;
	int width = 75;
	int height = 38;
	int row = 2;
	int col = 4;
	int number = row * col;
	boolean ex[][];
	
	public Block(MainPanel mp) {
		block = mp.getToolkit().getImage("game/image/block.jpg");
		ex = new boolean [row][col];
		for (int i1 = 0; i1 < row; i1++) {
			for (int i2 = 0; i2 < col; i2++) {
				ex[i1][i2] = true;
			}
		}
	}
}