package com.ipa.externalcontrol.impl;

import com.ipa.externalcontrol.impl.Style;
import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardNumberInput;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public final class UIComponentFactory {

	private final Style style;
	
	public GridBagConstraints baseConstraints;

	
	public UIComponentFactory(Style style) {
		this.style = style;
		setGridBagBaseConstraints();
	}

	private void setGridBagBaseConstraints() {
		baseConstraints = new GridBagConstraints();
		baseConstraints.fill = GridBagConstraints.HORIZONTAL;
		baseConstraints.anchor = GridBagConstraints.WEST;
		baseConstraints.insets = new Insets(style.getSmallVerticalSpacing(),
				style.getSmallHorizontalSpacing(),
				style.getSmallVerticalSpacing(),
				style.getSmallHorizontalSpacing());
		baseConstraints.weightx = 0.5;		
	}
	
	public Box createInfoSection(String infoText) {
		Box infoBox = Box.createHorizontalBox();
		infoBox.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel infoLabel = new JLabel(infoText);
		infoBox.add(infoLabel);

		return infoBox;
	}

	public Box createTextInputBox(final String id,
			final String label,
			final JTextField inputField,
			final String value,
			final ContributionProvider<IPAControlProgramNodeContribution> provider) {
		
  	Box inputBox = Box.createHorizontalBox();
    inputBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel weldSpeedLabel = new JLabel(label);
		weldSpeedLabel.setFont(new Font("Serif", Font.BOLD, 14));
    inputBox.add(weldSpeedLabel);

    inputField.setFocusable(false);
    inputField.setFont(new Font("Serif", Font.BOLD, 14));
		inputField.setHorizontalAlignment(JTextField.LEFT);
    inputField.setText(value);
    inputField.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        KeyboardTextInput keyboardInput = provider.get().getInputForTextField(id);
        keyboardInput.show(inputField, provider.get().getCallbackForTextField(id));
      }
    });

    inputBox.add(inputField);
    return inputBox;
	}

	public Box createComboBox(final String id,
			final String label,
			final JComboBox<String> inputComboBox,
			final String[] values,
			final ContributionProvider<IPAControlProgramNodeContribution> provider) {
		
  	Box inputBox = Box.createHorizontalBox();
    inputBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel jointTypeLabel = new JLabel("Typ der Schweißnaht: ");
		jointTypeLabel.setFont(new Font("Serif", Font.BOLD, 14));
    inputBox.add(jointTypeLabel);
		
//		String joint_type_list[] = {"Kehlnaht", "Stumpfnaht"};
//		inputComboBox = new JComboBox<String>({"Kehlnaht", "Stumpfnaht"});
		inputComboBox.setFont(new Font("Serif", Font.BOLD, 14));
		inputComboBox.setVisible(true);
		inputBox.add(inputComboBox);

		inputComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				provider.get().onSelection("WELDING_JOINT_TYPE");
			}
			
		});
    inputBox.add(inputComboBox);
    return inputBox;
  }
}
