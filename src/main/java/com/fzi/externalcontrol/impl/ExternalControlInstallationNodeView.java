//-- BEGIN LICENSE BLOCK ----------------------------------------------
// Copyright 2019 FZI Forschungszentrum Informatik
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//-- END LICENSE BLOCK ------------------------------------------------

//----------------------------------------------------------------------
/*!\file
*
* \author  Lea Steffen steffen@fzi.de
* \date    2019-04-18
*
*/
//----------------------------------------------------------------------

package com.fzi.externalcontrol.impl;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExternalControlInstallationNodeView
    implements SwingInstallationNodeView<ExternalControlInstallationNodeContribution> {
  private JTextField textFieldIP;
  private JTextField textFieldPort;
  private JTextField textFieldName;

  private JTextField textFieldPitascPort;
  private JTextField textFieldPitascFile;

  public ExternalControlInstallationNodeView() {}

  @Override
  public void buildUI(
      JPanel panel, final ExternalControlInstallationNodeContribution contribution) {
    panel.add(createIPBox(contribution));
//    panel.add(createSpacer(200));
    panel.add(createPortBox(contribution));
//    panel.add(createSpacer(200));
    panel.add(createNameBox(contribution));
//    panel.add(createSpacer(200));
    panel.add(createPitascPortBox(contribution));
//    panel.add(createSpacer(200));
    panel.add(createPitascFileBox(contribution));
  }

  public void UpdateIPTextField(String value) {
    textFieldIP.setText(value);
  }

  public void UpdatePortTextField(String value) {
    textFieldPort.setText(value);
  }

  public void UpdateNameTextField(String value) {
    textFieldName.setText(value);
  }

  public void UpdatePitascPortTextField(String value) {
    textFieldPitascPort.setText(value);
  }
  
  public void UpdatePitascFileTextField(String value) {
    textFieldPitascFile.setText(value);
  }
  
  private Box createIPBox(final ExternalControlInstallationNodeContribution contribution) {
    Box box = Box.createVerticalBox();
    // create IP Label
    JLabel label = new JLabel("Host IP: ");
    box.add(label);
    // create IP Textfield
    textFieldIP = new JTextField(15);
    textFieldIP.setText(contribution.getHostIP());//TODO
//    textFieldIP.setText("111.222.123.234");
    textFieldIP.setFocusable(false);
    textFieldIP.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        KeyboardTextInput keyboardInput = contribution.getInputForIPTextField();
        keyboardInput.show(textFieldIP, contribution.getCallbackForIPTextField());
      }
    });
    box.add(textFieldIP);
    return box;
  }

  private Box createPortBox(final ExternalControlInstallationNodeContribution contribution) {
    Box box = Box.createVerticalBox();
    // create port Label
    JLabel label = new JLabel("Custom port: ");
    box.add(label);
    // create port Textfield
    textFieldPort = new JTextField(15);
    textFieldPort.setText(contribution.getCustomPort());//TODO
//    textFieldPort.setText("50005");
    textFieldPort.setFocusable(false);
    textFieldPort.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        KeyboardTextInput keyboardInput = contribution.getInputForPortTextField();
        keyboardInput.show(textFieldPort, contribution.getCallbackForPortTextField());
      }
    });
    box.add(textFieldPort);
    return box;
  }

  private Box createNameBox(final ExternalControlInstallationNodeContribution contribution) {
    Box box = Box.createVerticalBox();
    // create name Label
    JLabel label = new JLabel("Host name");
    box.add(label);
    // create name Textfield
    textFieldName = new JTextField(15);
    textFieldName.setText(contribution.getName()); //TODO
//    textFieldName.setText("111.222.123.234");
    textFieldName.setFocusable(false);
    textFieldName.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        KeyboardTextInput keyboardInput = contribution.getInputForNameTextField();
        keyboardInput.show(textFieldPort, contribution.getCallbackForNameTextField());
      }
    });
    box.add(textFieldName);
    return box;
  }

  private Box createPitascPortBox(final ExternalControlInstallationNodeContribution contribution) {
    Box box = Box.createVerticalBox();
    // create port Label
    JLabel label = new JLabel("pitasc port: ");
    box.add(label);
    // create port Textfield
    textFieldPitascPort = new JTextField(15);
    textFieldPitascPort.setText(contribution.getPitascPort());//TODO
//    textFieldPitascPort.setText("40404");
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
  
  private Box createPitascFileBox(final ExternalControlInstallationNodeContribution contribution) {
    Box box = Box.createVerticalBox();
    // create port Label
    JLabel label = new JLabel("pitasc config: ");
    box.add(label);
    // create port Textfield
    textFieldPitascFile = new JTextField(15);
    textFieldPitascFile.setText(contribution.getPitascFile());//TODO
//    textFieldPitascFile.setText("default.xml");
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
  
  private Component createSpacer(int height) {
    return Box.createRigidArea(new Dimension(0, height));
  }

//  public static void main(String... args) {
//    int margin = 8;
//    Style style = new V3Style();
//    
//    JPanel jPanel = new JPanel();
//    jPanel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
//    new ExternalControlInstallationNodeView().buildUI(jPanel, null);
//    
//    JFrame jFrame = new JFrame();
//    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    jFrame.add(jPanel);
//    jFrame.setPreferredSize(new Dimension(500, 450));
//    jFrame.setVisible(true);
//    jFrame.pack();
//  }
  
}

