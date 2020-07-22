package com.ipa.externalcontrol.impl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class IPAControlInstallationNodeView extends ExternalControlInstallationNodeView 
 implements SwingInstallationNodeView<IPAControlInstallationNodeContribution>{

	  private JTextField textFieldPitascPort;
	  private JTextField textFieldPitascFile;
	  
	  public IPAControlInstallationNodeView() {
		  super();
	  }
	  
	  public void buildUI(
	      JPanel panel, final IPAControlInstallationNodeContribution contribution) {
	    panel.add(super.createIPBox(contribution));
	    panel.add(super.createPortBox(contribution));
	    panel.add(super.createNameBox(contribution));
	    panel.add(createPitascPortBox(contribution));
	    panel.add(createPitascFileBox(contribution));
	  }

	  
	  public void UpdatePitascPortTextField(String value) {
	  	textFieldPitascPort.setText(value);
	  }
		  
	  public void UpdatePitascFileTextField(String value) {
	  	textFieldPitascFile.setText(value);
	  }

	  private Box createPitascPortBox(final IPAControlInstallationNodeContribution contribution) {
		    Box box = Box.createVerticalBox();
		    // create port Label
		    JLabel label = new JLabel("IPA port: "); //pitasc
		    box.add(label);
		    // create port Textfield
		    textFieldPitascPort = new JTextField(15);
		    textFieldPitascPort.setText(contribution.getPitascPort());//TODO
//		    textFieldPitascPort.setText("40404");
		    textFieldPitascPort.setFocusable(false);
		    textFieldPitascPort.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mousePressed(MouseEvent e) {
		        KeyboardTextInput keyboardInput = contribution.getInputForPitascPortTextField();
		        keyboardInput.show(textFieldPitascPort, contribution.getCallbackForPitascPortTextField());
		      }
		    });
		    box.add(textFieldPitascPort);
		    return box;
	}
		  
		  private Box createPitascFileBox(final IPAControlInstallationNodeContribution contribution) {
		    Box box = Box.createVerticalBox();
		    // create port Label
		    JLabel label = new JLabel("IPA config: "); //pitasc
		    box.add(label);
		    // create port Textfield
		    textFieldPitascFile = new JTextField(15);
		    textFieldPitascFile.setText(contribution.getPitascFile());//TODO
//		    textFieldPitascFile.setText("default.xml");
		    textFieldPitascFile.setFocusable(false);
		    textFieldPitascFile.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mousePressed(MouseEvent e) {
		        KeyboardTextInput keyboardInput = contribution.getInputForPitascFileTextField();
		        keyboardInput.show(textFieldPitascFile, contribution.getCallbackForPitascFileTextField());
		      }
		    });
		    box.add(textFieldPitascFile);
		    return box;
		  }
}
