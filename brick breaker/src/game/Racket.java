package game;

import java.awt.Color;
import java.awt.Dimension;

import main.MainPanel;

class Racket {
	Color color = new Color(0, 255, 0); //ラケットの色
	int width; //ラケットの幅
	int height = 20; //ラケットの高さ
	int x; //ラケットの縦位置
	int y; //ラケットの縦位置
	
	public Racket(int h, Dimension size, MainPanel mp) {
		if (mp.level == 1) {
			width = 80;
		} else {
			width = 40;
		}
		x = size.width / 2 - width / 2;
		y = h - height;
	}
}