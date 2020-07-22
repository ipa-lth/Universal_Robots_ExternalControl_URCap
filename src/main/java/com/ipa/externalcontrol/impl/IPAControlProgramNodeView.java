package com.ipa.externalcontrol.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;


public class IPAControlProgramNodeView extends ExternalControlProgramNodeView
	implements SwingProgramNodeView<IPAControlProgramNodeContribution> {
	
	  private JTextField jTextFieldApp = new JTextField(15);
	  private JTextField jTextFieldParams = new JTextField(15);

	  private JComboBox<String> weldjointType = new JComboBox<String>();
	  private JTextField weldingSpeed = new JTextField(15);

	  public IPAControlProgramNodeView(ViewAPIProvider apiProvider) {
		  super(apiProvider);
	  }

	  @Override
	  public void buildUI(
	      JPanel panel, ContributionProvider provider) {
			panel.setLayout(new GridBagLayout());
			GridBagConstraints baseConstraints = new GridBagConstraints();
			baseConstraints.fill = GridBagConstraints.HORIZONTAL;
			baseConstraints.anchor = GridBagConstraints.NORTH;
			baseConstraints.insets = new Insets(3,1,3,1);
			baseConstraints.weightx = 0.5;

			// Inputs
			// Description
			GridBagConstraints nodeDescriptionConstraints = (GridBagConstraints)baseConstraints.clone();
			nodeDescriptionConstraints.gridx = 0;
			nodeDescriptionConstraints.gridy = 0;
			nodeDescriptionConstraints.gridwidth = 2;
			nodeDescriptionConstraints.insets = new Insets(10,0,10,0);
	    super.infoLabel = new JLabel();
	    super.infoLabel.setFont(new Font("Serif", Font.BOLD, 14));
	    panel.add(infoLabel, nodeDescriptionConstraints);
	    
	    // IPA Logo
			GridBagConstraints logoConstraints = (GridBagConstraints)baseConstraints.clone();
			logoConstraints.gridx = 0;
			logoConstraints.gridy = 1;
			logoConstraints.gridwidth = 2;
			logoConstraints.insets = new Insets(10,0,10,0);
			logoConstraints.anchor = GridBagConstraints.EAST;
	    ImageIcon ipaIcon = new ImageIcon(getClass().getResource("/icons/IPA_logo_small.png"));
			JLabel ipaIconLabel = new JLabel(ipaIcon);
			ipaIconLabel.setVisible(true);
			panel.add(ipaIconLabel);
	    
			// Joint Type

			// Welding Joint Type 
			GridBagConstraints jointTypeComboBoxConstraints = (GridBagConstraints)baseConstraints.clone();
			jointTypeComboBoxConstraints.gridx = 0;
			jointTypeComboBoxConstraints.gridy = 2;
			jointTypeComboBoxConstraints.gridwidth = 1;
			jointTypeComboBoxConstraints.anchor = GridBagConstraints.WEST;
			panel.add(createWeldJointType(provider), jointTypeComboBoxConstraints);
			
			// Weld Speed
			GridBagConstraints weldSpeedTextFieldConstraints = (GridBagConstraints) baseConstraints.clone();
			weldSpeedTextFieldConstraints.gridx = 0;
			weldSpeedTextFieldConstraints.gridy = 3;
			weldSpeedTextFieldConstraints.anchor = GridBagConstraints.WEST;
			panel.add(createWeldingSpeed(provider), weldSpeedTextFieldConstraints);

//
//			JPanel inputPanel = new JPanel(new BorderLayout());
//			inputPanel.add(createHeightTextField(provider), BorderLayout.WEST);
//			inputPanel.add(new JLabel(" "), BorderLayout.CENTER);
//			inputPanel.add(createEnterValueLabel(), BorderLayout.EAST);
//
//			GridBagConstraints heightTextFieldConstraints = (GridBagConstraints)baseConstraints.clone();
//			heightTextFieldConstraints.gridx = 0;
//			heightTextFieldConstraints.gridy = 2;
//			heightTextFieldConstraints.anchor = GridBagConstraints.WEST;
//			heightTextFieldConstraints.fill = GridBagConstraints.NONE;
//			panel.add(inputPanel, heightTextFieldConstraints);
//
//			GridBagConstraints setInputConstraints = (GridBagConstraints)baseConstraints.clone();
//			setInputConstraints.gridx = 0;
//			setInputConstraints.gridy = 3;
//			setInputConstraints.insets = new Insets(20, 0, 5, 0);
//			panel.add(createSetInputsLabel(), setInputConstraints);
//
//			GridBagConstraints unitsComboConstraints = (GridBagConstraints)baseConstraints.clone();
//			unitsComboConstraints.gridx = 0;
//			unitsComboConstraints.gridy = 4;
//			unitsComboConstraints.anchor = GridBagConstraints.WEST;
//			unitsComboConstraints.fill = GridBagConstraints.NONE;
//			panel.add(createUnitsCombo(provider), unitsComboConstraints);		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
//
////	    panel.add(createInputApp(provider));
////	    panel.add(createInputParams(provider));
//			panel.add(createInputWeldingSpeed(provider));
//			panel.add(createInputStickout(provider));
//			panel.add(createInputWeldingAngle(provider));

			
	    /*
	    standardParamsPanel = new JPanel();
	    JLabel standardParamsLabel = new JLabel("Standard Parameters: ");
	    standardParamsLabel.setFont(new Font("Serif", Font.BOLD, 20));
	    standardParamsPanel.add(standardParamsLabel);
	    panel.add(standardParamsPanel);

	    advancedParamsPanel = new JPanel();
	    JLabel advancedParamsLabel = new JLabel("Advanced Parameters: ");
	    advancedParamsLabel.setFont(new Font("Serif", Font.BOLD, 20));
	    advancedParamsPanel.add(advancedParamsLabel);
	    panel.add(advancedParamsPanel);

	    createMaxLostPackages(
	        standardParamsPanel, provider, maxLostPackages_TF, "Max Nr. of lost pkg: ", "1000");
	    panel.add(createAdvancedParamBox(provider));

	    createGainServoJ(advancedParamsPanel, provider, gainServoj_TF, "Gain servoj: ", "0");
	     */
	  }
	  
	  
	  private Box createInputApp(final ContributionProvider<IPAControlProgramNodeContribution> provider) {
		    Box inputBox = Box.createHorizontalBox();
		    inputBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		    inputBox.add(new JLabel("App:       ")); // Hacky hack hack: Layout should be synced and not done with whitespaces...
//		    inputBox.add(createHorizontalSpacing());
		    jTextFieldApp.setFocusable(false);
//		    jTextFieldApp.setPreferredSize(style.getInputfieldSize());
//		    jTextFieldApp.setMaximumSize(jTextFieldApp.getPreferredSize());
		    jTextFieldApp.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mousePressed(MouseEvent e) {
		        KeyboardTextInput keyboardInput = provider.get().getInputForPitascAppTextField();
		        keyboardInput.show(jTextFieldApp, provider.get().getCallbackForPitascAppTextField());
		      }
		    });

		    inputBox.add(jTextFieldApp);
		    return inputBox;
		  }

		  public void UpdatePitascAppTextField(String value) {
		    jTextFieldApp.setText(value);
		  }
		  
		  private Box createInputParams(final ContributionProvider<IPAControlProgramNodeContribution> provider) {
		    Box inputBox = Box.createHorizontalBox();
		    inputBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		    inputBox.add(new JLabel("Params: "));
//		    inputBox.add(createHorizontalSpacing());

		    jTextFieldParams.setFocusable(false);
//		    jTextFieldApp.setPreferredSize(style.getInputfieldSize());
//		    jTextFieldParams.setMaximumSize(jTextFieldParams.getPreferredSize());
		    jTextFieldParams.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mousePressed(MouseEvent e) {
		        KeyboardTextInput keyboardInput = provider.get().getInputForPitascParamsTextField();
		        keyboardInput.show(jTextFieldParams, provider.get().getCallbackForPitascParamsTextField());
		      }
		    });

		    inputBox.add(jTextFieldParams);
		    return inputBox;
		  }
		  
		  //////////////////////////////////////////////////////////////////////////////////////////////
		  //////////////////////////////////////////////////////////////////////////////////////////////
		  //////////////////////////////////////////////////////////////////////////////////////////////
		  
		  
		  
		  private Box createWeldingSpeed(final ContributionProvider<IPAControlProgramNodeContribution> provider) {	  	
		  	Box inputBox = Box.createHorizontalBox();
		    inputBox.setAlignmentX(Component.LEFT_ALIGNMENT);
				JLabel weldSpeedLabel = new JLabel("Robotergeschwindigkeit (mm/min):  ");
				weldSpeedLabel.setFont(new Font("Serif", Font.BOLD, 14));
//				GridBagConstraints weldSpeedLabelConstraints = (GridBagConstraints) baseConstraints.clone();
//				weldSpeedLabelConstraints.gridx = 0;
//				weldSpeedLabelConstraints.gridy = 4;
//				weldSpeedLabelConstraints.anchor = GridBagConstraints.WEST;
//				weldSpeedLabelConstraints.fill = GridBagConstraints.NONE;
		    inputBox.add(weldSpeedLabel);

		    weldingSpeed.setFocusable(false);
		  	weldingSpeed.setFont(new Font("Serif", Font.BOLD, 14));
		  	weldingSpeed.setText("500");
//				GridBagConstraints weldSpeedTextFieldConstraints = (GridBagConstraints) baseConstraints.clone();
//				weldSpeedTextFieldConstraints.gridx = 0;
//				weldSpeedTextFieldConstraints.gridy = 5;
//				weldSpeedTextFieldConstraints.anchor = GridBagConstraints.WEST;
		    weldingSpeed.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mousePressed(MouseEvent e) {
		        KeyboardTextInput keyboardInput = provider.get().getInputForWeldingSpeedTextField();
		        keyboardInput.show(weldingSpeed, provider.get().getCallbackForWeldingSpeedTextField());
		      }
		    });
		    weldingSpeed.setText("500");

		    inputBox.add(weldingSpeed);
		    return inputBox;
		  }

		  public void UpdateWeldingSpeedTextField(String value) {
		  	weldingSpeed.setText(value);
		  }
		 
		  
		  private Box createWeldJointType(final ContributionProvider<IPAControlProgramNodeContribution> provider) {	  	
		  	Box inputBox = Box.createHorizontalBox();
		    inputBox.setAlignmentX(Component.LEFT_ALIGNMENT);
				JLabel jointTypeLabel = new JLabel("Typ der Schweißnaht: ");
				jointTypeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
//				GridBagConstraints jointTypeLabelConstraints = (GridBagConstraints)baseConstraints.clone();
//				jointTypeLabelConstraints.gridx = 0;
//				jointTypeLabelConstraints.gridy = 2;
//				jointTypeLabelConstraints.anchor = GridBagConstraints.WEST;
//				jointTypeLabelConstraints.fill = GridBagConstraints.NONE;
		    inputBox.add(jointTypeLabel);
				
				String joint_type_list[] = {"Kehlnaht", "Stumpfnaht"};
				weldjointType = new JComboBox<String>(joint_type_list);
				weldjointType.setFont(new Font("Serif", Font.BOLD, 14));
				weldjointType.setVisible(true);
//				GridBagConstraints jointTypeComboBoxConstraints = (GridBagConstraints)baseConstraints.clone();
//				jointTypeComboBoxConstraints.gridx = 0;
//				jointTypeComboBoxConstraints.gridy = 3;
//				jointTypeComboBoxConstraints.gridwidth = 1;
//				jointTypeLabelConstraints.anchor = GridBagConstraints.WEST;
		    inputBox.add(weldjointType);

		    weldjointType.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						provider.get().onWeldJointTypeSelection();
						weldjointType.setFont(new Font("Serif", Font.BOLD, 14));
					}
				});
		    inputBox.add(weldjointType);
		    return inputBox;
		  }

			public String getSelectedWeldJointType() {
				return (String) weldjointType.getSelectedItem();
			}
			
		  //////////////////////////////////////////////////////////////////////////////////////////////
		  //////////////////////////////////////////////////////////////////////////////////////////////
		  //////////////////////////////////////////////////////////////////////////////////////////////
		  
		  
		  public void UpdatePitascParamsTextField(String value) {
		    jTextFieldParams.setText(value);
		  }
		  
		  
		  @Override
		  public void updateInfoLabel(String host_ip, String custom_port) {
		    infoLabel.setText("<html><body>"
		    		+ "Automatisches Programmieren von Schweißnähten.  <br>"
		    		+ "Die Netzwerkkonfiguration befindet sich im \"Installation Tab\".  <br>"
		    		+ "Die folgenden Parameter müssen vor dem Start des Programms gesetzt werden:  "
		        + "<body><html>");
		  }
}
