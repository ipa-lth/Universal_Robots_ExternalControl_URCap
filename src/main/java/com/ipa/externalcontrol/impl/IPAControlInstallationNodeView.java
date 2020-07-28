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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class IPAControlInstallationNodeView extends ExternalControlInstallationNodeView 
 implements SwingInstallationNodeView<IPAControlInstallationNodeContribution>{

	  private JTextField textFieldIpaPort;
	  private JTextField textFieldIpaFile;
	  
	  public IPAControlInstallationNodeView() {
		  super();
	  }
	  
	  public void buildUI(
	      JPanel panel, final IPAControlInstallationNodeContribution contribution) {
	    panel.add(super.createIPBox(contribution));
	    panel.add(super.createPortBox(contribution));
	    panel.add(super.createNameBox(contribution));
	    panel.add(createIpaPortBox(contribution));
	    panel.add(createIpaFileBox(contribution));
	  }

	  
	  public void UpdateIpaPortTextField(String value) {
	  	textFieldIpaPort.setText(value);
	  }
		  
	  public void UpdateIpaFileTextField(String value) {
	  	textFieldIpaFile.setText(value);
	  }

	  private Box createIpaPortBox(final IPAControlInstallationNodeContribution contribution) {
		    Box box = Box.createVerticalBox();
		    // create port Label
		    JLabel label = new JLabel("IPA port: "); //pitasc port
		    box.add(label);
		    // create port Textfield
		    textFieldIpaPort = new JTextField(15);
		    textFieldIpaPort.setText(contribution.getIpaPort());//TODO
//		    textFieldPitascPort.setText("40404");
		    textFieldIpaPort.setFocusable(false);
		    textFieldIpaPort.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mousePressed(MouseEvent e) {
		        KeyboardTextInput keyboardInput = contribution.getInputForIpaPortTextField();
		        keyboardInput.show(textFieldIpaPort, contribution.getCallbackForIpaPortTextField());
		      }
		    });
		    box.add(textFieldIpaPort);
		    return box;
	}
		  
		  private Box createIpaFileBox(final IPAControlInstallationNodeContribution contribution) {
		    Box box = Box.createVerticalBox();
		    // create port Label
		    JLabel label = new JLabel("IPA config: "); //pitasc config
		    box.add(label);
		    // create port Textfield
		    textFieldIpaFile = new JTextField(15);
		    textFieldIpaFile.setText(contribution.getIpaFile());//TODO
//		    textFieldPitascFile.setText("default.xml");
		    textFieldIpaFile.setFocusable(false);
		    textFieldIpaFile.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mousePressed(MouseEvent e) {
		        KeyboardTextInput keyboardInput = contribution.getInputForIpaFileTextField();
		        keyboardInput.show(textFieldIpaFile, contribution.getCallbackForIpaFileTextField());
		      }
		    });
		    box.add(textFieldIpaFile);
		    return box;
		  }
}
