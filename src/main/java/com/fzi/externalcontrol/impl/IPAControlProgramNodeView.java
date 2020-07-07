package com.fzi.externalcontrol.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;


public class IPAControlProgramNodeView extends ExternalControlProgramNodeView {
	
	  private JTextField jTextFieldApp = new JTextField(15);
	  private JTextField jTextFieldParams = new JTextField(15);

	  public IPAControlProgramNodeView(ViewAPIProvider apiProvider) {
		  super(apiProvider);
	  }

	  @Override
	  public void buildUI(
	      JPanel panel, ContributionProvider provider) {
	    panel.setLayout(new GridLayout(0, 1));
	    super.infoLabel = new JLabel();
	    super.infoLabel.setFont(new Font("Serif", Font.BOLD, 14));
//	    infoLabel.setMinimumSize(new Dimension(500, 150));
	    panel.add(infoLabel);
	    JPanel subpanel = new JPanel();
	    subpanel.setLayout(new GridLayout(0, 1, 5, 5));
	    subpanel.add(createInputApp(provider));
	    subpanel.add(createInputParams(provider));

	    panel.add(subpanel);

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

		  public void UpdatePitascParamsTextField(String value) {
		    jTextFieldParams.setText(value);
		  }


		//  public static void main(String... args) {
		//  int margin = 8;
		//
		//  JPanel jPanel = new JPanel();
		//  jPanel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
		//  new ExternalControlProgramNodeView().buildUI(jPanel, null);
		//
		//  JFrame jFrame = new JFrame();
		//  jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//  jFrame.add(jPanel);
		//  jFrame.setPreferredSize(new Dimension(500, 450));
		//  jFrame.setVisible(true);
		//  jFrame.pack();
		//  }
}
