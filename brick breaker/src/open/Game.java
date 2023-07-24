package open;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import main.MainPanel;

public class Game {
	public static void main (String[] args){
		Win win = new Win("ブロック崩し");
	}
}

class Win extends JFrame{
	//コンストラクタ
	Win(String name){
		super(name);
		setSize(360, 490);
		Dimension size = getSize();
		size.width  -=60;
		size.height -=90;
		Container CP = getContentPane();
		CP.setLayout(null);
		CP.setBackground(new Color(220, 255, 220));
		MainPanel pn = new MainPanel(size);
		CP.add(pn);
		pn.setSize(size.width, size.height);
		pn.setLocation(10, 10);
		setVisible(true);
		addWindowListener(new WinEnd());
	}

	//上，左，下，右の余白の設定
	public Insets getInsets(){
		return new Insets(50, 20, 20, 20);
	}

	//終了処理
	class WinEnd extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
}