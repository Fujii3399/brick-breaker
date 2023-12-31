package over;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.MainPanel;

public class GameOverPanel extends JPanel implements ActionListener {
	Dimension size;
	MainPanel mp;
	JButton bt1, bt2, bt3;
	
	public GameOverPanel(Dimension size1, MainPanel mp1) {
		size = size1;
		mp   = mp1;
		setLayout(null);
		setBackground(Color.white);
		Font f = new Font("SansSerif", Font.BOLD, 10);
		FontMetrics fm = getFontMetrics(f);
		String str1 = "終了";
		int w1 = fm.stringWidth(str1) + 40;
		int h1 = fm.getHeight() + 10;
		bt1 = new JButton(str1);
		bt1.setFont(f);
		bt1.addActionListener(this);
		bt1.setSize(w1, h1);
		
		String str2 = "現在のレベルで再開";
		int w2 = fm.stringWidth(str2) + 40;
		int h2 = fm.getHeight() + 10;
		bt2 = new JButton(str2);
		bt2.setFont(f);
		bt2.addActionListener(this);
		bt2.setSize(w2, h2);
		
		String str3 = "最初から再開";
		int w3 = fm.stringWidth(str3) + 40;
		int h3 = fm.getHeight() + 10;
		bt3 = new JButton(str3);
		bt3.setFont(f);
		bt3.addActionListener(this);
		bt3.setSize(w3, h3);
		
		int w = size.width / 2 - (w1 + w2 + w3 + 10) / 2;
		bt1.setLocation(w, size.height-h1-20);
		add(bt1);
		w += (w1 + 5);
		bt2.setLocation(w, size.height-h1-20);
		add(bt2);
		w += (w2 + 5);
		bt3.setLocation(w, size.height-h1-20);
		add(bt3);
	}
	
	//描画
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		FontMetrics fm = g.getFontMetrics(f);
		String str = "Game Over!";
		int w = fm.stringWidth(str);
		g.setFont(f);
		g.drawString(str, size.width/2-w/2, size.height/2);
	}
	
	//ボタンがクリックされたときの処理
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt1) { //ゲーム終了
			mp.state = 4;
			bt1.setEnabled(false);
			bt2.setEnabled(false);
			bt3.setEnabled(false);
		} else if (e.getSource() == bt2) { //// 現在のレベルでゲーム再開
			mp.state = 1;
		} else { //最初からゲーム再開
			mp.state = 0;
			mp.level = 1;
		}
	}
}
