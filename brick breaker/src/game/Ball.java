package game;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

class Ball {
	Color color = new Color(255, 0, 0); //ボールの色
	int r = 7; //ボールの半径
	int x; //ボールの横位置
	int y; //ボールの縦位置
	int v = 8; //ボールの速度
	int vx; //ボールの横方向速度成分
	int vy; //ボールの縦方向速度成分
	
	public Ball(Block blk, Dimension size, Random rn) {
		x = rn.nextInt(size.width - 2 * r);
		y = blk.height * blk.row + 10;
		double a = v * Math.cos(45.0 * Math.PI / 180.0);
		vy = (int)a;
		if (x < size.width / 2 - r) {
			vx = (int)a;
		} else {
			vx = -(int)a;
		}
	}
}