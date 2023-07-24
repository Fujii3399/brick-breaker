package start;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.MainPanel;

public class StartPanel extends JPanel implements ActionListener{
	boolean in_game = true;
	Dimension size;
	MainPanel mp;
	JButton bt;
	
	public StartPanel(Dimension size1, MainPanel mp1) {
		size = size1;
		mp = mp1;
		setLayout(null);
		setBackground(Color.white);
		Font f = new Font("SansSerif", Font.BOLD, 20);
		FontMetrics fm = getFontMetrics(f);
		String str = "遊び方";
		int w = fm.stringWidth(str) + 40;
		int h = fm.getHeight() + 10;
		bt = new JButton(str);
		bt.setFont(f);
		bt.addActionListener(this);
		bt.setSize(w, h);
		bt.setLocation(size.width/2-w/2, 5);
		add(bt);
		//マウスリスナの追加
		addMouseListener(new Mouse());
	}
	//描画
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		FontMetrics fm;
		Font f;
		String str;
		int w, h;
		
		f = new Font("SansSerif", Font.BOLD, 40);
		fm = g.getFontMetrics(f);
		str = "ブロック崩し";
		w = fm.stringWidth(str);
		h = fm.getHeight();
		g.setFont(f);
		g.drawString(str, size.width/2-w/2, size.height/2);
		
		f = new Font("Serif", Font.PLAIN, 20);
		fm = g.getFontMetrics(f);
		str = "ゲーム開始：ダブルクリック";
		w = fm.stringWidth(str);
		h = size.height - fm.getHeight() - 10;
		g.setFont(f);
		g.drawString(str, size.width/2-w/2, h);
	}
	
	//ボタンがクリックされたときの処理
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt) {
			Method db = new Method();
			db.setVisible(true);
			requestFocusInWindow();
		}
	}
	
	//ダブルクリックされたときの処理
	class Mouse extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				mp.state = 1;
			}
		}
	}
}

//ゲームの遊び方
class Method extends JDialog {
	Method(){
		setTitle("ゲームの遊び方");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout(FlowLayout.CENTER));
		cp.setBackground(new Color(220, 255, 220));
		Font f = new Font("ＭＳ 明朝", Font.PLAIN, 20);
		setSize(550, 160);
		JTextArea ta = new JTextArea(5, 50);
		ta.setFont(f);
		ta.setEditable(false);
		ta.setLineWrap(true);
		ta.setText("・ゲーム開始： 画面上でダブルクリック\n");
		
		ta.append("・ラケットの移動： 「左」，または，「右」ボタンをクリックする\n");
		JScrollPane scroll = new JScrollPane(ta);
		cp.add(scroll);
		addWindowListener(new WinEnd());
	}
	//終了処理
	class WinEnd extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			setVisible(false);
		}
	}
}
