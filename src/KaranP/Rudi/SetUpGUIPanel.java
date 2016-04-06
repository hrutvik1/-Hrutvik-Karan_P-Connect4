package KaranP.Rudi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class contains the buttons and labels and determines how the GUI will be displayed
 * This class has a setup panel embedded in the main panel  
 * @author hrutvik and karan
 *
 */
public class SetUpGUIPanel extends JPanel {
	private static JButton twoPlayersBtn, playAIBtn, newGameBtn, randomPlayerBtn, playerGoFirstBtn, compGoFirstBtn;
	private static JLabel player1Lbl, player2Lbl, AILbl, startScreen;
	private JPanel mainPanel, setUpPanel;
	private ConnectFourPanel startConnectFour;
	
	private boolean firstStart = true;

	public SetUpGUIPanel() {
		// Buttons
		twoPlayersBtn = new JButton("Play With Two Player");
		twoPlayersBtn.setFont(new Font("Sans Serif", Font.ITALIC, 14));
		twoPlayersBtn.setHorizontalAlignment(SwingConstants.CENTER);
		twoPlayersBtn.setContentAreaFilled(true);
		twoPlayersBtn.setEnabled(true);
		twoPlayersBtn.addActionListener(new ButtonActionListener());

		playAIBtn = new JButton("Play With Computer");
		playAIBtn.setFont(new Font("Sans Serif", Font.ITALIC, 14));
		playAIBtn.setHorizontalAlignment(SwingConstants.CENTER);
		playAIBtn.setContentAreaFilled(true);
		playAIBtn.setEnabled(true);
		playAIBtn.addActionListener(new ButtonActionListener());

		newGameBtn = new JButton("Start A New Game");
		newGameBtn.setFont(new Font("Sans Serif", Font.ITALIC, 14));
		newGameBtn.setHorizontalAlignment(SwingConstants.CENTER);
		newGameBtn.setContentAreaFilled(true);
		newGameBtn.setEnabled(true);
		newGameBtn.addActionListener(new ButtonActionListener());

		randomPlayerBtn = new JButton("Choose a Random Player");
		randomPlayerBtn.setFont(new Font("Sans Serif", Font.ITALIC, 14));
		randomPlayerBtn.setHorizontalAlignment(SwingConstants.CENTER);
		randomPlayerBtn.setContentAreaFilled(true);
		randomPlayerBtn.setEnabled(true);
		randomPlayerBtn.addActionListener(new ButtonActionListener());

		playerGoFirstBtn = new JButton("I will Go First");
		playerGoFirstBtn.setFont(new Font("Sans Serif", Font.ITALIC, 14));
		playerGoFirstBtn.setHorizontalAlignment(SwingConstants.CENTER);
		playerGoFirstBtn.setContentAreaFilled(true);
		playerGoFirstBtn.setEnabled(true);
		playerGoFirstBtn.addActionListener(new ButtonActionListener());

		compGoFirstBtn = new JButton("Computer Goes First");
		compGoFirstBtn.setFont(new Font("Sans Serif", Font.ITALIC, 14));
		compGoFirstBtn.setHorizontalAlignment(SwingConstants.CENTER);
		compGoFirstBtn.setContentAreaFilled(true);
		compGoFirstBtn.setEnabled(true);
		compGoFirstBtn.addActionListener(new ButtonActionListener());

		// Labels
		startScreen = new JLabel();
		startScreen.setHorizontalAlignment(SwingConstants.CENTER);
		loadBackgroundImage();		
		
		player1Lbl = new JLabel("Player 1 > Blue Chip");
		player1Lbl.setFont(new Font("Sans Serif", Font.BOLD, 12));
		player1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player1Lbl.setOpaque(true);
		player1Lbl.setBackground(Color.BLUE);
		player1Lbl.setForeground(Color.WHITE);
		player1Lbl.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		player2Lbl = new JLabel("Player 2 > Red Chip");
		player2Lbl.setFont(new Font("Sans Serif", Font.BOLD, 12));
		player2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player2Lbl.setOpaque(true);
		player2Lbl.setBackground(Color.RED);
		player2Lbl.setForeground(Color.WHITE);
		player2Lbl.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		AILbl = new JLabel("Computer > Green Chip");
		AILbl.setFont(new Font("Sans Serif", Font.BOLD, 12));
		AILbl.setHorizontalAlignment(SwingConstants.CENTER);
		AILbl.setOpaque(true);
		AILbl.setBackground(new Color(102, 255, 102));
		AILbl.setForeground(Color.BLACK);
		AILbl.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		startConnectFour = new ConnectFourPanel();

		// Set up the panels
		setUpPanel = new JPanel();
		setUpPanel.setBackground(Color.BLACK);
		setUpPanel.setLayout(new FlowLayout(5, 10, 2));
		setUpPanel.add(twoPlayersBtn);
		setUpPanel.add(playAIBtn);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(450, 480));
		setBackground(Color.BLACK);
		add(setUpPanel, BorderLayout.NORTH);
		setBackgroundImage(true);
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == twoPlayersBtn) {
				if(firstStart){
					setBackgroundImage(false);
					firstStart = false;
				}
				
				startConnectFour.setGameMode("MultiPlayerConnect4");
				twoPlayersBtn.setVisible(false);
				playAIBtn.setVisible(false);

				setUpPanel.remove(twoPlayersBtn);
				setUpPanel.remove(playAIBtn);
				setUpPanel.add(randomPlayerBtn);
				randomPlayerBtn.setVisible(true);

			}
			if (event.getSource() == playAIBtn) {
				if(firstStart){
					setBackgroundImage(false);
					firstStart = false;
				}
				
				startConnectFour.setGameMode("ComputerPlayer");
				twoPlayersBtn.setVisible(false);
				playAIBtn.setVisible(false);

				setUpPanel.remove(twoPlayersBtn);
				setUpPanel.remove(playAIBtn);

				setUpPanel.add(playerGoFirstBtn);
				setUpPanel.add(compGoFirstBtn);

			}
			if (event.getSource() == randomPlayerBtn) {
				int currentPlayer = startConnectFour.getRandomPlayer();
				JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " goes first");
				randomPlayerBtn.setVisible(false);
				setUpPanel.remove(randomPlayerBtn);

				if (currentPlayer == 1) {
					player1Lbl.setText("***Player 1 > Blue Chip");
				} else if (currentPlayer == 2) {
					player2Lbl.setText("***Player 2 > Red Chip");
				}
				setUpPanel.add(newGameBtn);
				setUpPanel.add(player1Lbl);
				setUpPanel.add(player2Lbl);

				startConnectFour.setBoardVisible(true);

			}
			if (event.getSource() == newGameBtn) {
				startConnectFour.gameReset();
				startConnectFour.setBoardVisible(false);

				setUpPanel.remove(newGameBtn);
				setUpPanel.remove(player1Lbl);
				setUpPanel.remove(player2Lbl);
				setUpPanel.remove(AILbl);

				setUpPanel.add(twoPlayersBtn);
				setUpPanel.add(playAIBtn);
				twoPlayersBtn.setVisible(true);
				playAIBtn.setVisible(true);
				playerGoFirstBtn.setVisible(true);
				compGoFirstBtn.setVisible(true);

				player1Lbl.setText("Player 1 > Blue Chip");
				player2Lbl.setText("Player 2 > Red Chip");

			}
			if (event.getSource() == playerGoFirstBtn) {
				playerGoFirstBtn.setVisible(false);
				compGoFirstBtn.setVisible(false);
				setUpPanel.remove(playerGoFirstBtn);
				setUpPanel.remove(compGoFirstBtn);

				setUpPanel.add(newGameBtn);
				setUpPanel.add(player1Lbl);
				setUpPanel.add(AILbl);
				startConnectFour.setBoardVisible(true);
				
				startConnectFour.setTurn(1); // 1 represents the human
			}
			if (event.getSource() == compGoFirstBtn) {
				playerGoFirstBtn.setVisible(false);
				compGoFirstBtn.setVisible(false);
				setUpPanel.remove(playerGoFirstBtn);
				setUpPanel.remove(compGoFirstBtn);

				setUpPanel.add(newGameBtn);
				setUpPanel.add(player1Lbl);
				setUpPanel.add(AILbl);
				startConnectFour.setBoardVisible(true);
				
				startConnectFour.setTurn(99); // 99 represent the computer
				startConnectFour.computersTurn();				
			}

		}
	}
	
	private void loadBackgroundImage(){
		try {
			Image background = ImageIO.read(getClass().getResource("/resources/startWallpaper.png"));
			startScreen.setIcon(new ImageIcon(background));
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Images Could Not Be Loaded!");
			System.exit(0); // terminates code
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something Went Wrong!");
			System.exit(0); // terminates code
		}
	}
	
	private void setBackgroundImage(boolean set){
		if(set){
			add(startScreen, BorderLayout.CENTER);
		}else if (!set){
			remove(startScreen);
			add(startConnectFour, BorderLayout.CENTER);
		}
	}
}
