package com.fzi.externalcontrol.impl;

import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class IPAControlInstallationNodeContribution extends ExternalControlInstallationNodeContribution {

	  private int instanceCounter = 0;

	  private PitascCaller pitascCaller;
	  private static final String PITASC_PORT_NR = "pitasc_port";
	  private static final String PITASC_DEFAULT_PORT = "40404";
	  private static final String PITASC_FILE = "pitasc_file";
	  private static final String PITASC_DEFAULT_FILE = "default.xml";

	  public IPAControlInstallationNodeContribution(InstallationAPIProvider apiProvider,
		      ExternalControlInstallationNodeView view, DataModel model) {
		  super(apiProvider, view, model);
	  }
	  
	  public void generateScript(ScriptWriter writer) {
		  	super.generateScript(writer);
		  
		    pitascCaller = new PitascCaller(getHostIP(), getPitascPort());
		    pitascCaller.appendInstallationLines(writer, getPitascFile());
	  }
	  
	  // port helper functions
	  public void setHostPort(String port) {
	    if ("".equals(port)) {
	      resetToDefaultPort();
	    } else {
	      super.model.set(PORT_NR, port);
	    }
	  }


	  private void resetToDefaultPort() {
	    super.model.set(PORT_NR, DEFAULT_PORT);
	  }

	  public KeyboardTextInput getInputForPortTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getCustomPort());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForPortTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setHostPort(value);
	        view.UpdatePortTextField(value);
	      }
	    };
	  }

	  // name helper functions
	  public void setName(String name) {
	    if ("".equals(name)) {
	      resetToDefaultName();
	    } else {
	      super.model.set(NAME, name);
	    }
	  }

	  public String getName() {
	    return super.model.get(NAME, DEFAULT_NAME);
	  }

	  private void resetToDefaultName() {
	    super.model.set(NAME, DEFAULT_NAME);
	  }

	  public KeyboardTextInput getInputForNameTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getName());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForNameTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setName(value);
	        view.UpdateNameTextField(value);
	      }
	    };
	  }

	  // port helper functions
	  public void setPitascPort(String port) {
	    if ("".equals(port)) {
	      resetToDefaultPitascPort();
	    } else {
	      super.model.set(PITASC_PORT_NR, port);
	    }
	  }

	  public String getPitascPort() {
	    return super.model.get(PITASC_PORT_NR, PITASC_DEFAULT_PORT);
	  }

	  private void resetToDefaultPitascPort() {
	    super.model.set(PITASC_PORT_NR, PITASC_DEFAULT_PORT);
	  }
	  
	  public KeyboardTextInput getInputForPitascPortTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getPitascPort());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForPitascPortTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setPitascPort(value);
	        view.UpdatePitascPortTextField(value);
	      }
	    };
	  }

	  // port helper functions
	  public void setPitascFile(String file) {
	    if ("".equals(file)) {
	      resetToDefaultPitascFile();
	    } else {
	      super.model.set(PITASC_FILE, file);
	    }
	  }

	  public String getPitascFile() {
	    return super.model.get(PITASC_FILE, PITASC_DEFAULT_FILE);
	  }

	  private void resetToDefaultPitascFile() {
	    super.model.set(PITASC_FILE, PITASC_DEFAULT_FILE);
	  }
	  
	  public KeyboardTextInput getInputForPitascFileTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getPitascFile());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForPitascFileTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setPitascFile(value);
	        view.UpdatePitascFileTextField(value);
	      }
	    };
	  }
	  
	  public int IncrementInstanceCounter() {
		    return this.instanceCounter++;
		  }

		  public PitascCaller getPitascCaller() {
			  return pitascCaller;
		  }
	  
}
