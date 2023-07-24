package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.MainPanel;

public class GamePanel extends JPanel implements ActionListener, Runnable {
	Dimension size;
	MainPanel mp;
	JButton left, right;
	Block blk;
	Racket rk;
	Ball bl;
	Random rand;
	Thread td;
	boolean in_game = true;
	
	public GamePanel(Dimension size1, MainPanel mp1) {
		size = size1;
		mp = mp1;
		setLayout(null);
		setBackground(Color.white);
		blk = new Block(mp);
		Font f = new Font("SansSerif", Font.BOLD, 15);
		FontMetrics fm = getFontMetrics(f);
		String str1 = "左";
		int w1 = fm.stringWidth(str1) + 40;
		int h1 = fm.getHeight() + 10;
		left = new JButton(str1);
		left.setFont(f);
		left.addActionListener(this);
		left.setSize(w1, h1);
		
		String str2 = "右";
		int w2 = fm.stringWidth(str2) + 40;
		int h2 = fm.getHeight() + 10;
		right = new JButton(str2);
		right.setFont(f);
		right.addActionListener(this); 
		right.setSize(w2, h2);
		
		int w = size.width / 2 - (w1 + w2 + 5) / 2;
		int h = size.height - h1 - 10;
		left.setLocation(w, h);
		add(left);
		right.setLocation(w+w1+5, h);
		add(right);
		rand = new Random();
		rk = new Racket(h-10, size, mp);
		bl = new Ball(blk, size, rand);
		td = new Thread(this);
		td.start();
	}
	
	//スレッドの実行
	public void run() {
		while (in_game) {
			try {
				td.sleep(50);
			} catch (InterruptedException e) {}
			bl.x += bl.vx;
			bl.y += bl.vy;
			//壁に衝突したときの処理
			int sw = 0;
			//下へ移動中
			if (bl.vy > 0) {
				if (bl.y >= rk.y-bl.r*2) { // ラケットの位置より下か確認
					if (bl.x+bl.r >= rk.x && bl.x+bl.r <= rk.x+rk.width) {
						bl.y  = rk.y - bl.r * 2;
						bl.vy = -bl.vy;
					} else { //　ゲームオーバー
						in_game  = false;
						mp.state = 3;
					}
					sw = 1;
				}
			} else { // 上に移動中
				if (bl.y <= blk.row*blk.height) {
					int k = -1;
					// 横方向のブロック位置
					for (int i1 = 1; i1 < blk.col && k < 0; i1++) {
						if (bl.x+bl.r <= i1*blk.width) {
							k = i1 - 1;
						}
					}
					
					if (k < 0) {
						k = blk.col - 1;
					}
					// ブロックとの衝突
					for (int i1 = blk.row; i1 >= 0 && sw == 0; i1--) {
						if (bl.y <= i1*blk.height) {
							if (i1 == 0 || blk.ex[i1-1][k]) {
								bl.y  = i1 * blk.height;
								bl.vy = -bl.vy;
								sw = 1;
								if (i1 > 0) {
									blk.ex[i1-1][k] = false;
									blk.number--;
									if (blk.number == 0) { // ゲームクリア
										in_game  = false;
										mp.state = 2;
									}
								}
							}
						}
					}
				}
			}
			//上下の壁に衝突していない場合
			if (sw == 0) { // 右方向へ移動中
				if (bl.vx > 0) {
					if (bl.x >= size.width-bl.r*2) { // 右の壁に衝突
						bl.x  = size.width - bl.r * 2;
						bl.vx = -bl.vx;
					}
				} else { //左方向へ移動中
					if (bl.x <= 0) { // 左の壁に衝突
						bl.x  = 0;
						bl.vx = -bl.vx;
					}
				}
			}
			// 再描画
			repaint();
		}
	}
	//描画
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i1 = 0; i1 < blk.row; i1++) {
			for (int i2 = 0; i2 < blk.col; i2++) {
				if (blk.ex[i1][i2]) {
					g.drawImage(blk.block, blk.width*i2, blk.height*i1, this);
				}
			}
		}
		
		//ラケット
		g.setColor(rk.color);
		g.fillRect(rk.x, rk.y, rk.width, rk.height);
		//ボール
		g.setColor(bl.color);
		g.fillOval(bl.x, bl.y, bl.r*2, bl.r*2);
	}
	// ボタンがクリックされたときの処理
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == left) { //ラケットを左へ移動
			rk.x -= (rk.width - 5);
		} else { //ラケットを右へ移動
			rk.x += (rk.width - 5);
		}
	}
}
