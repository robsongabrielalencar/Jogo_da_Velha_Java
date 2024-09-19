
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class JogoDaVelhaGUI extends JFrame implements ActionListener {

	private JButton[][] buttons;
	private char currentPlayer;

	public JogoDaVelhaGUI() {
		currentPlayer = 'X';

		buttons = new JButton[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
				buttons[i][j].addActionListener(this);
				add(buttons[i][j]);
			}
		}

		setLayout(new GridLayout(3, 3));
		setTitle("Jogo da Velha");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		if (clickedButton.getText().equals("")) {
			clickedButton.setText(Character.toString(currentPlayer));
			clickedButton.setEnabled(false);
			if (verificarVitoria()) {
				JOptionPane.showMessageDialog(this, "Jogador " + currentPlayer + " Venceu!");
				reiniciarJogo();
			} else if (tabuleiroCompleto()) {
				JOptionPane.showMessageDialog(this, "Empate!");
				reiniciarJogo();
			} else {
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			}
		}
	}

	private boolean verificarVitoria() {
		for (int i = 0; i < 3; i++) {
			if (buttons[i][0].getText().equals(Character.toString(currentPlayer))
					&& buttons[i][1].getText().equals(Character.toString(currentPlayer))
					&& buttons[i][2].getText().equals(Character.toString(currentPlayer))) {
				return true;
			}
			if (buttons[0][i].getText().equals(Character.toString(currentPlayer))
					&& buttons[1][i].getText().equals(Character.toString(currentPlayer))
					&& buttons[2][i].getText().equals(Character.toString(currentPlayer))) {
				return true;
			}
		}
		if (buttons[0][0].getText().equals(Character.toString(currentPlayer))
				&& buttons[1][1].getText().equals(Character.toString(currentPlayer))
				&& buttons[2][2].getText().equals(Character.toString(currentPlayer))) {
			return true;
		}
		if (buttons[0][2].getText().equals(Character.toString(currentPlayer))
				&& buttons[1][1].getText().equals(Character.toString(currentPlayer))
				&& buttons[2][0].getText().equals(Character.toString(currentPlayer))) {
			return true;
		}
		return false;
	}

	private boolean tabuleiroCompleto() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (buttons[i][j].getText().equals("")) {
					return false;
				}
			}
		}
		return true;
	}

	private void reiniciarJogo() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j].setText("");
				buttons[i][j].setEnabled(true);
			}
		}
		currentPlayer = 'X';
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JogoDaVelhaGUI());
	}
}
