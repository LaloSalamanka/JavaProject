package index;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

// 創建背包背景
class bagBackground extends JPanel {
	public bagBackground() {
        setOpaque(false); // 設定為透明
    }
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(105, 105, 105, 125)); // 設定透明背景顏色
        g.fillRect(0, 100, getWidth(), 480); // 填充整個面板
    }
}

public class Bag{
// 變數定義
	// 定義JFrame
	private JFrame bagFrame;
	
	// 定義exitbutton
	JButton exitbutton = new JButton(new ImageIcon(System.getProperty("user.dir") + "\\image\\exit.png"));// 離開按鈕
	
	// 定義拿來當背景的JPanel
	ImageIcon originalBackgroundImageIcon = new ImageIcon("image/background.jpg");
	Image Background_icon = originalBackgroundImageIcon.getImage().getScaledInstance(400, 700, Image.SCALE_SMOOTH);
	JPanel backgroundPanel = new JPanel() {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (Background_icon != null) {
				g.drawImage(Background_icon, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}
	};
	
	// 創建背包背景
	bagBackground bag_backgroundPanel = new bagBackground();
	
	// 背包圖示
	ImageIcon Bag_icon = new ImageIcon("image/mybag.png");
	Image scaled_Bag_icon = Bag_icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	ImageIcon bag_icon = new ImageIcon(scaled_Bag_icon);
	JLabel bag_label = new JLabel(bag_icon);
	
	// 定義且初始化裝備圖片的大小
	static int Width = 100;
	static int Height = 100;

// 定義且初始化按鈕的圖片	
	// 斧頭
	static ImageIcon Axe_icon = new ImageIcon("image/items/axe.jpg");
	static Image scaled_Axe_icon = Axe_icon.getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	static ImageIcon axe_icon = new ImageIcon(scaled_Axe_icon);
	
	// 藍色魔杖
	static ImageIcon BlueMagicStick_icon = new ImageIcon("image/items/bluemagicStick.jpg");
	static Image scaled_BlueMagicStick_icon = BlueMagicStick_icon.getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	static ImageIcon blueMagicStick_icon = new ImageIcon(scaled_BlueMagicStick_icon);
	
	// 魔法書
	static ImageIcon MagicBook_icon = new ImageIcon("image/items/magicBook.jpg");
	static Image scaled_MagicBook_icon = MagicBook_icon.getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	static ImageIcon magicBook_icon = new ImageIcon(scaled_MagicBook_icon);
	
	// 魔法杖
	static ImageIcon MagicStick_icon = new ImageIcon("image/items/magicStick.jpg");
	static Image scaled_MagicStick_icon = MagicStick_icon.getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	static ImageIcon magicStick_icon = new ImageIcon(scaled_MagicStick_icon);
	
	// 紫劍
	static ImageIcon PurpleSword_icon = new ImageIcon("image/items/purpleSword.jpg");
	static Image scaled_PurpleSword_icon = PurpleSword_icon.getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	static ImageIcon purpleSword_icon = new ImageIcon(scaled_PurpleSword_icon);
	
	// 劍
	static ImageIcon Sword_icon = new ImageIcon("image/items/sword.jpg");
	static Image scaled_Sword_icon = Sword_icon.getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
	static ImageIcon sword_icon = new ImageIcon(scaled_Sword_icon);
	
	// 定義並初始化裝備按鈕
	static JButton Axe_button = new JButton(axe_icon);
	static JButton BlueMagicStick_button = new JButton(blueMagicStick_icon);
	static JButton MagicBook_button = new JButton(magicBook_icon);
	static JButton MagicStick_button = new JButton(magicStick_icon);
	static JButton PurpleSword_button = new JButton(purpleSword_icon);
	static JButton Sword_button = new JButton(sword_icon);
	JButton[] buttons = {Axe_button, BlueMagicStick_button, MagicBook_button, 
			MagicStick_button, PurpleSword_button, Sword_button};
	
	
	
	// 定義並初始化字體
	Font buttonFont = new Font("Microsoft YaHei", Font.BOLD, 15);
	Font itemFont = new Font("STXinwei", Font.BOLD | Font.ITALIC, 20);
	
	// 根據buy的值啟禁用按鈕
	static AtomicBoolean isEquipped = new AtomicBoolean(true);
	
	// 定義並初始化JDialog
	JDialog axeDialog = new JDialog(bagFrame, "", true);
	JDialog BlueMagicStickDialog = new JDialog(bagFrame, "", true);
	JDialog MagicBookDialog = new JDialog(bagFrame, "", true);
	JDialog MagicStickDialog = new JDialog(bagFrame, "", true);
	JDialog PurpleSwordDialog = new JDialog(bagFrame, "", true);
	JDialog SwordDialog = new JDialog(bagFrame, "", true);
	
	// 定義並初始化DialogPanel
	JPanel axePanel = new JPanel();
	JPanel BlueMagicStickPanel = new JPanel();
	JPanel MagicBookPanel = new JPanel();
	JPanel MagicStickPanel = new JPanel();
	JPanel PurpleSwordPanel = new JPanel();
	JPanel SwordPanel = new JPanel();
	
	// 定義並初始化DialogPanel裡面要放的Label的Panel
	JPanel axeLabelPanel = new JPanel();
	
	
	// 定義並初始化DialogPanel裡面放裝備圖片的Label
	JLabel axeLabel = new JLabel(axe_icon);
	JLabel axeNameLabel = new JLabel("屠戮之斧");
	JLabel axeStatusLabel = new JLabel("ATTACK +10");
	
	
	
	// 創建裝備按鈕的圖片
	static ImageIcon Equip_icon = new ImageIcon("image/items/gameButton.png");
	static Image scaled_equip_icon = Equip_icon.getImage().getScaledInstance(75, 25, Image.SCALE_SMOOTH);
	static ImageIcon equip_icon = new ImageIcon(scaled_equip_icon);
	
	// 是否裝備按鈕
	static JButton equipButton = new JButton("裝備", equip_icon); 
	static JButton cancelButton = new JButton("取消", equip_icon);
	
	final AtomicInteger button_num = new AtomicInteger(0);  // 使用 AtomicInteger 作為計數器
	
	public Bag() {
		setBag();
		bagFrame.setVisible(true);
	}
	
	public void setBag() {
// 初始化並設置		
		// 設置bagFrame
		bagFrame = new JFrame();
		bagFrame.setResizable(false);
		bagFrame.setSize(400, 700);
		bagFrame.add(backgroundPanel);
		
		// 設置backgroundPanel		
		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.add(exitbutton);
		backgroundPanel.add(bag_label);
		backgroundPanel.add(bag_backgroundPanel);	
		
		// 設置背包頁面上方顯示的背包圖案label
		bag_label.setBounds(200, 25, 100, 100);
		
		
		// 首頁按鈕外觀設置
		exitbutton.setBounds(10, 10, 60, 60);
		exitbutton.setOpaque(false);
		exitbutton.setBackground(new Color(0, 0, 0, 0));
		exitbutton.setBorder(null);
		exitbutton.setFocusPainted(false);
		
		// bag_backgroundPanel的按鈕佈局
		bag_backgroundPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.insets = new Insets(0, 10, 100, 10);
        gbc1.gridx = 0;
        gbc1.gridy = 0;      
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(0, 10, 100, 10);
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(0, 10, 100, 10);
        gbc3.gridx = 2;
        gbc3.gridy = 0;
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 1;
        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridx = 1;
        gbc5.gridy = 1;
        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.gridx = 2;
        gbc6.gridy = 1;
        
        // 將按鈕加入bag_backgroundPanel
		bag_backgroundPanel.add(Axe_button, gbc1);
		bag_backgroundPanel.add(BlueMagicStick_button, gbc2);
		bag_backgroundPanel.add(MagicBook_button, gbc3);
		bag_backgroundPanel.add(MagicStick_button, gbc4);
		bag_backgroundPanel.add(PurpleSword_button, gbc5);
		bag_backgroundPanel.add(Sword_button, gbc6);
		
		// 設定裝備按鈕字體及顏色        		
		equipButton.setFont(buttonFont);
		equipButton.setForeground(Color.WHITE);
		cancelButton.setFont(buttonFont);
		cancelButton.setForeground(Color.WHITE);
    	
    	// 設定裝備按鈕背景移除
    	equipButton.setOpaque(false);
    	equipButton.setBackground(new Color(0, 0, 0, 0));
    	equipButton.setBorder(null);
    	equipButton.setFocusPainted(false);
    	cancelButton.setOpaque(false);
    	cancelButton.setBackground(new Color(0, 0, 0, 0));
    	cancelButton.setBorder(null);
    	cancelButton.setFocusPainted(false);
    	
    	// 設定裝備按鈕的文字在圖片的前面
    	equipButton.setVerticalTextPosition(SwingConstants.CENTER);
    	equipButton.setHorizontalTextPosition(SwingConstants.CENTER);
    	cancelButton.setVerticalTextPosition(SwingConstants.CENTER);
    	cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
    	// 所有DialogPanel的設置
    	axePanel.setLayout(new GridBagLayout());
    	BlueMagicStickPanel.setLayout(new GridBagLayout());
    	MagicBookPanel.setLayout(new GridBagLayout());
    	MagicStickPanel.setLayout(new GridBagLayout());
    	PurpleSwordPanel.setLayout(new GridBagLayout());
    	SwordPanel.setLayout(new GridBagLayout());
    	GridBagConstraints itemIcon_DialogGridbag = new GridBagConstraints();
        itemIcon_DialogGridbag.insets = new Insets(10, 0, 10, 0);
        itemIcon_DialogGridbag.gridx = 0;
        itemIcon_DialogGridbag.gridy = 0;
    	GridBagConstraints dgbc1 = new GridBagConstraints();
		dgbc1.insets = new Insets(10, 0, 10, 0);
        dgbc1.gridx = 0;
        dgbc1.gridy = 1;
        GridBagConstraints dgbc2 = new GridBagConstraints();
        dgbc2.insets = new Insets(10, 0, 10, 0);
        dgbc2.gridx = 1;
        dgbc2.gridy = 1;
        GridBagConstraints dgbc3 = new GridBagConstraints();
        dgbc3.insets = new Insets(0, 10, 10, 0);
        dgbc3.gridx = 1;
        dgbc3.gridy = 0;
		
        // 設置DialogPanel裡面的NameLabel及StatusLabel的字體
        axeNameLabel.setFont(itemFont);
    	axeNameLabel.setForeground(new Color(0x03D79));
    	axeStatusLabel.setFont(buttonFont);
    	axeStatusLabel.setForeground(Color.RED);
    	
    	// 設置DialogPanel裡面的LabelPanel
    	axeLabelPanel.setLayout(new BoxLayout(axeLabelPanel, BoxLayout.Y_AXIS));    
    	axeLabelPanel.add(axeNameLabel);
    	axeLabelPanel.add(Box.createVerticalStrut(20));
    	axeLabelPanel.add(axeStatusLabel);
    	
    	// 設置DialogPanel加上裝備圖片以及裝備名稱
    	axePanel.add(axeLabel, itemIcon_DialogGridbag);
    	axePanel.add(axeLabelPanel, dgbc3);
    	
    	// 裝備或取消按紐
    	axePanel.add(equipButton, dgbc1);
    	axePanel.add(cancelButton, dgbc2);
    	
    	// 最後將dialog加上panel
    	axeDialog.setSize(250, 250);
    	axeDialog.add(axePanel);
    	
    	int x = bagFrame.getX() + (bagFrame.getWidth()+350);
        int y = bagFrame.getY() + (bagFrame.getHeight()-350);
        axeDialog.setLocation(x, y);
        axeDialog.setIconImage(scaled_Axe_icon);
		
		// 滑鼠進入離開效果
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton sourceButton = (JButton) e.getSource();
				sourceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton sourceButton = (JButton) e.getSource();
				sourceButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};
			
		// 添加所有裝備按鈕事件
		ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {                  	
                if (e.getSource() == exitbutton) {
                    bagFrame.dispose();
                    GameMainPage Mainpage = new GameMainPage();
                    Mainpage.frame.setVisible(true);
                } else if (e.getSource() == Axe_button) {
                	axeDialog.setVisible(true);
                    button_num.set(1);}
//                    GameFloor.attacklabel // 可設定攻擊力數值
//                 else if (e.getSource() == BlueMagicStick_button) {
//                    itemIcon = blueMagicStick_icon;
//                    itemNameString = "幽冥法杖";
//                    itemStatusString = "ATTACK +10";
//                    button_num.set(2);
//                } else if (e.getSource() == MagicBook_button) {
//                    itemIcon = magicBook_icon;
//                    itemNameString = "秘咒之典";
//                    itemStatusString = "ATTACK +10";
//                    button_num.set(3);
//                } else if (e.getSource() == MagicStick_button) {
//                    itemIcon = magicStick_icon;
//                    itemNameString = "日炬之杖";
//                    itemStatusString = "ATTACK +10";
//                    button_num.set(4);
//                } else if (e.getSource() == PurpleSword_button) {
//                    itemIcon = purpleSword_icon;
//                    itemNameString = "碎魂之刃";
//                    itemStatusString = "ATTACK +10";
//                    button_num.set(5);
//                } else if (e.getSource() == Sword_button) {
//                    itemIcon = sword_icon;
//                    itemNameString = "焰心之刃";
//                    itemStatusString = "ATTACK +10";
//                    button_num.set(6);
//                }
            }
		};
		exitbutton.addActionListener(actionListener);
		
		// 點擊裝備後按鈕就會標示已裝備
		equipButton.addActionListener(closeEvent -> {
						
			Axe_button.setText("已裝備");
//			BlueMagicStick_button.setText("已裝備");
//			MagicBook_button.setText("已裝備");
//			MagicStick_button.setText("已裝備");
//			PurpleSword_button.setText("已裝備");
//			Sword_button.setText("已裝備");

        	// 裝備後將 isEquipped 設置為 false
            isEquipped.set(false);
            
            // 根據新的狀態重新設置按鈕的啟用狀態
            equipButton.setEnabled(isEquipped.get());
            
            axeDialog.setVisible(false);
        });
		
		cancelButton.addActionListener(closeEvent -> {
			axeDialog.setVisible(false);
        });
		
		// 所有裝備按鈕的共同設置
		for (JButton button:buttons) {
			 
			// 加入滑鼠效果
			button.addMouseListener(mouseAdapter);
			
			// 調整裝備按鈕圖片位置
			
			button.setVerticalTextPosition(SwingConstants.BOTTOM);
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setBackground(Color.white);
			button.setBorder(null);
			button.setFocusPainted(false);
			
			// 加入按鈕事件
			button.addActionListener(actionListener);
		}
		
		// 將 JFrame 設定為在視窗中央顯示
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) ((screenSize.getWidth() - bagFrame.getWidth()) / 2);
		int centerY = (int) ((screenSize.getHeight() - bagFrame.getHeight()) / 2);
		bagFrame.setLocation(centerX, centerY);
				
		bagFrame.addWindowListener(new WindowAdapter() {// 用匿名類別設定window事件將視窗關閉
			public void windowClosing(WindowEvent event) {// 視窗關閉開啟主視窗
				bagFrame.dispose();
				GameMainPage i = new GameMainPage();
				i.frame.setVisible(true);
			}
		});
	}
}