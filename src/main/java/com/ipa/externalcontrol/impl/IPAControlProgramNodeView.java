// -- BEGIN LICENSE BLOCK ----------------------------------------------
// Copyright 2020 Fraunhofer IPA
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// -- END LICENSE BLOCK ------------------------------------------------

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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
	  
	  private UIComponentFactory uiComponentFactory;

	  public IPAControlProgramNodeView(ViewAPIProvider apiProvider) {
		  super(apiProvider);
		  uiComponentFactory = new UIComponentFactory(new V5Style());
	  }

	  @Override
	  public void buildUI(
	      JPanel panel, ContributionProvider provider) {
			panel.setLayout(new GridBagLayout());

			// Inputs
			// Description
			GridBagConstraints nodeDescriptionConstraints = (GridBagConstraints) uiComponentFactory.baseConstraints.clone();
			nodeDescriptionConstraints.gridx = 0;
			nodeDescriptionConstraints.gridy = 0;
			nodeDescriptionConstraints.gridwidth = 2;
	    super.infoLabel = new JLabel();
	    super.infoLabel.setFont(new Font("Serif", Font.BOLD, 14));
	    panel.add(infoLabel, nodeDescriptionConstraints);
	    
	    // IPA Logo
			GridBagConstraints logoConstraints = (GridBagConstraints) uiComponentFactory.baseConstraints.clone();
			logoConstraints.gridx = 0;
			logoConstraints.gridy = 1;
			logoConstraints.gridwidth = 2;
			logoConstraints.anchor = GridBagConstraints.EAST;
	    ImageIcon ipaIcon = new ImageIcon(getClass().getResource("/icons/IPA_logo_small.png"));
			JLabel ipaIconLabel = new JLabel(ipaIcon);
			ipaIconLabel.setVisible(true);
			panel.add(ipaIconLabel);
	    
			
			//Kogrob //project_kogrob
			// Welding Joint Type 
			GridBagConstraints jointTypeComboBoxConstraints = (GridBagConstraints) uiComponentFactory.baseConstraints.clone();
			jointTypeComboBoxConstraints.gridx = 0;
			jointTypeComboBoxConstraints.gridy = 2;
			panel.add(uiComponentFactory.createComboBox("WELDING_JOINT_TYPE", "Typ der Schweißnaht: ", weldjointType, new String[]{"Kehlnaht", "Stumpfnaht"}, provider),
					jointTypeComboBoxConstraints);
			
			// Weld Speed
			GridBagConstraints weldSpeedTextFieldConstraints = (GridBagConstraints) uiComponentFactory.baseConstraints.clone();
			weldSpeedTextFieldConstraints.gridx = 0;
			weldSpeedTextFieldConstraints.gridy = 3;
			panel.add(uiComponentFactory.createTextInputBox("WELDING_SPEED", "Robotergeschwindigkeit (mm/min): ", weldingSpeed, "500", provider),
					weldSpeedTextFieldConstraints);

			// Pitasc //project_pitasc
//			GridBagConstraints pitascAppConstraints = (GridBagConstraints) uiComponentFactory.baseConstraints.clone();
//			pitascAppConstraints.gridx = 0;
//			pitascAppConstraints.gridy = 4;
//			panel.add(uiComponentFactory.createTextInputBox("PITASC_APP", "App:       ", jTextFieldApp, "testapp", provider),
//					pitascAppConstraints);
//				
//			GridBagConstraints pitascParamsConstraints = (GridBagConstraints) uiComponentFactory.baseConstraints.clone();
//			pitascParamsConstraints.gridx = 0;
//			pitascParamsConstraints.gridy = 5;
//				panel.add(uiComponentFactory.createTextInputBox("PITASC_PARAMS", "Params: ", jTextFieldParams, "testparam", provider),
//						pitascParamsConstraints);
	  }
	  
	  //////////////////////////////////////////////////////////////////////////////////////////////
	  // PITASC
	  //////////////////////////////////////////////////////////////////////////////////////////////
	  
	  public void UpdatePitascAppTextField(String value) {
	  	jTextFieldApp.setText(value);
		}
		  
		public void UpdatePitascParamsTextField(String value) {
		  jTextFieldParams.setText(value);
		}
		  		  
		  
		//////////////////////////////////////////////////////////////////////////////////////////////
		// KOGROB
		//////////////////////////////////////////////////////////////////////////////////////////////
		  
		  
		public void UpdateWeldingSpeedTextField(String value) {
			weldingSpeed.setText(value);
		}		  		 
		  
		public void setWeldJointTypes(String[] jointTypes) {
			weldjointType.removeAll();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			for(String jointType : jointTypes){
				model.addElement(jointType);
			}
			weldjointType.setModel(model);
			weldjointType.updateUI();
		}
				
		public String getSelectedWeldJointType() {
			return (String) weldjointType.getSelectedItem();
		}
			
		public void setSelectedWeldJointType(String unit) {
			weldjointType.setSelectedItem(unit);
		}
	
	  //////////////////////////////////////////////////////////////////////////////////////////////
	  //////////////////////////////////////////////////////////////////////////////////////////////
	  //////////////////////////////////////////////////////////////////////////////////////////////
		  
		  		  
		@Override
		public void updateInfoLabel(String host_ip, String custom_port) {
		 	// KogRob //project_kogrob
		  infoLabel.setText("<html><body>"
				+ "Automatisches Programmieren von Schweißnähten.  <br>"
		 		+ "Die Netzwerkkonfiguration befindet sich im \"Installation Tab\".  <br>"
		 		+ "Die folgenden Parameter müssen vor dem Start des Programms gesetzt werden:  "
		    + "<body><html>");
		    
		  // Pitasc //project_pitasc
//		  infoLabel.setText("<html><body>"
//		  	+ "Pitasc Pitasc Pitasc.  <br>"
//		    + "Pitasc Pitasc Pitasc.  <br>"
//		    + "Pitasc Pitasc Pitasc.  "
//		    + "<body><html>");

		}
}
