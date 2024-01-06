import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PianoController {
	 private SoundManager sm = new SoundManager();
	 private JFrame parentFrame;
	 @FXML
	 private Label volumeLabel,octaveLabel;
	 
	
	public PianoController(JFrame parentFrame) {
		super();
		this.parentFrame = parentFrame;
	}
	
	@FXML
	void btnAPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green; -fx-border-color: Black");
		sm.noteOn(0);
	}
	
	@FXML
	void btnAReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: White; -fx-border-color: Black");
		sm.noteOff(0);
	}
	
	@FXML
	void btnSPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green; -fx-border-color: Black");
		sm.noteOn(2);
	}
	
	@FXML
	void btnSReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: White; -fx-border-color: Black");
		sm.noteOff(2);
	}
	
	@FXML
	void btnDPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green; -fx-border-color: Black");
		sm.noteOn(4);
	}
	
	@FXML
	void btnDReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: White; -fx-border-color: Black");
		sm.noteOff(4);
	}
	
	@FXML
	void btnFPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green; -fx-border-color: Black");
		sm.noteOn(5);
	}
	
	@FXML
	void btnFReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: White; -fx-border-color: Black");
		sm.noteOff(5);
	}
	
	@FXML
	void btnGPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green; -fx-border-color: Black");
		sm.noteOn(7);
	}
	
	@FXML
	void btnGReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: White; -fx-border-color: Black");
		sm.noteOff(7);
	}
	
	@FXML
	void btnHPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green; -fx-border-color: Black");
		sm.noteOn(9);
	}
	
	@FXML
	void btnHReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: White; -fx-border-color: Black");
		sm.noteOff(9);
	}
	
	@FXML
	void btnJPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green; -fx-border-color: Black");
		sm.noteOn(11);
	}
	
	@FXML
	void btnJReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: White; -fx-border-color: Black");
		sm.noteOff(11);
	}
	
	@FXML
	void btnWPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green");
		sm.noteOn(1);
	}
	
	@FXML
	void btnWReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Black");
		sm.noteOff(1);
	}
	
	@FXML
	void btnEPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green");
		sm.noteOn(3);
	}
	
	@FXML
	void btnEReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Black");
		sm.noteOff(3);
	}
	
	@FXML
	void btnTPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green");
		sm.noteOn(6);
	}
	
	@FXML
	void btnTReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Black");
		sm.noteOff(6);
	}
	
	@FXML
	void btnYPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green");
		sm.noteOn(8);
	}
	
	@FXML
	void btnYReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Black");
		sm.noteOff(8);
	}
	
	@FXML
	void btnUPressed(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Green");
		sm.noteOn(10);
	}
	
	@FXML
	void btnUReleased(MouseEvent e) {
		((Button) e.getSource()).setStyle("-fx-background-color: Black");
		sm.noteOff(10);
	}
	
	@FXML
	private void showHelpDialog() {
        JOptionPane.showMessageDialog(parentFrame,
            "Virtual Piano Help:\n" +
            "Use the keyboard keys ASDFGHJ for white piano keys.\n" +
            "Use the keyboard keys WE TYU for black piano keys.\n" +
            "Press 0-8 to change octaves, +/- to adjust the current octave.\n" +
            "Press the keys to play notes.",
            "Help", JOptionPane.INFORMATION_MESSAGE);
    }
	
	@FXML
	void volumeUpPressed(ActionEvent e) {
		sm.increaseVolume(10);
		volumeLabel.setText(sm.getVolume() + "");
	}
	
	@FXML
	void volumeDownPressed(ActionEvent e) {
		sm.increaseVolume(-10);
		volumeLabel.setText(sm.getVolume() + "");
	}
	
	@FXML
	void octaveUpPressed(ActionEvent e) {
		sm.increaseOctave(1);
		octaveLabel.setText(sm.getOctave() + "");
	}
	
	@FXML
	void octaveDownPressed(ActionEvent e) {
		sm.increaseOctave(-1);
		octaveLabel.setText(sm.getOctave() + "");
	}
}
