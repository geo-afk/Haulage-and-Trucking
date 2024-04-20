package gui.main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import gui.forgetpassword.ForgotPassword;
import gui.login.LoginPage;
import gui.welcomescreen.WelcomeScreen;
import constants.ConstantFunctions;
import constants.CustomButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel implements ActionListener {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public ForgotPassword forgotPasswordFrame;
	private GridBagConstraints constraints;
	private CustomButton loginButton;
	private LoginPage loginPage;
	private JDesktopPane pane;
	private WelcomeScreen welcomeScreen;

	public Options(int width, int height) {

		Border titleBorder = BorderFactory.createTitledBorder("Log in/sign up for an account.");
		Border beveledBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		setBorder(BorderFactory.createCompoundBorder(titleBorder, beveledBorder));
		setBackground(new Color(0x8B023332, true));
		setPreferredSize(new Dimension(width / 5, height));

		forgotPasswordFrame = new ForgotPassword();
		constraints = new GridBagConstraints();
		welcomeScreen = new WelcomeScreen();
		loginPage = new LoginPage();

		setLayout(new GridBagLayout());

		addLogoToPanel();
		loginButton();

		setVisible(true);
	}

	public void addLogoToPanel() {

		JLabel logoImg = ConstantFunctions.addLogoToPanel();

		constraints.anchor = GridBagConstraints.NORTH; // Anchor to the top
		constraints.insets = new Insets(0, 0, 250, 0); // Padding
		constraints.gridx = 0;
		constraints.gridy = 0;

		add(logoImg, constraints);

	}

	private void loginButton() {

		String path = ConstantFunctions.PHOTO_STRING + "login.png";
		loginButton = new CustomButton("Login", ConstantFunctions.resizeImage(path));
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(this);

		constraints.anchor = GridBagConstraints.SOUTH; // Anchor to the top
		constraints.insets = new Insets(0, 0, 20, 0); // Padding
		constraints.gridx = 0;
		constraints.gridy = 1;

		add(loginButton, constraints);
	}

	public void setDesktopPane(JDesktopPane pane) {
		this.pane = pane;
		this.pane.add(welcomeScreen);
		loginPage.getDesktopAndSignupPanel(pane);
		forgotPasswordFrame.initializeForNewAccount(pane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		welcomeScreen.dispose();
		this.pane.remove(welcomeScreen);

		if (e.getSource() == loginButton) {

			loginPage.setVisible(true);
			this.pane.add(loginPage);

		}

	}

}
