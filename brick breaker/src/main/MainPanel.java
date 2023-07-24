package main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import clear.*;
import game.*;
import over.*;
import start.StartPanel;

public class MainPanel extends JPanel implements Runnable{
	Dimension size;
	boolean in_game = true;
	public int state = 0;
	public int level = 1;
	int old_state = 0;
	StartPanel sp;
	GamePanel gp;
	GameClearPanel gcp;
	GameOverPanel gop;
	Thread td;
	
	// コンストラクタ
	public MainPanel(Dimension size1){
		size = size1;
		setLayout(new GridLayout(1, 1, 0, 0));
		sp = new StartPanel(size, this);
		add(sp);
		td = new Thread(this);
		td.start();
	}
	
	// ゲームの状態を変更
	public void run(){
		while (in_game) {
			try {
				td.sleep(100);
			} catch (InterruptedException e) {}
			if (state != old_state) {
				if (old_state == 0) {
					remove(sp);
				} else if (old_state == 1) {
					remove(gp);
				} else if (old_state == 2) {
					remove(gcp);
				} else {
					remove(gop);
				}
				
				if (state == 4) {
					in_game = false;
				} else {
					if (state == 0) {
						sp = new StartPanel(size, this);
						add(sp);
					} else if (state == 1) {
						gp = new GamePanel(size, this);
						add(gp);
					} else if (state == 2) {
						gcp = new GameClearPanel(size, this);
						add(gcp);
					} else {
						gop = new GameOverPanel(size, this);
						add(gop);
					}
					validate();
					old_state = state;
				}
			}
		}
	}
}