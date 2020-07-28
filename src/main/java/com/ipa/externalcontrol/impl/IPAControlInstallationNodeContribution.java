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

import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;
import com.ipa.externalcontrol.impl.IPACaller;

public class IPAControlInstallationNodeContribution extends ExternalControlInstallationNodeContribution {

	  private final IPAControlInstallationNodeView view;

	  private int instanceCounter = 0; 

	  private IPACaller ipaCaller;
	  private static final String IPA_PORT_NR = "pitasc_port";
	  private static final String IPA_DEFAULT_PORT = "40404";
	  private static final String IPA_FILE = "pitasc_file";
	  private static final String IPA_DEFAULT_FILE = "default.xml";

	  public IPAControlInstallationNodeContribution(InstallationAPIProvider apiProvider,
		      IPAControlInstallationNodeView view, DataModel model) {
		  super(apiProvider, view, model);
		  this.view = view;
	  }
	  
	  public void generateScript(ScriptWriter writer) {
		  	super.generateScript(writer);
		  	
		    ipaCaller = new IPACaller(getHostIP(), getIpaPort(), "pitasc_xmlrpc");
		    ipaCaller.appendInstallationLines(writer, getIpaFile());
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
	  public void setIpaPort(String port) {
	    if ("".equals(port)) {
	      resetToDefaultIpaPort();
	    } else {
	      super.model.set(IPA_PORT_NR, port);
	    }
	  }

	  public String getIpaPort() {
	    return super.model.get(IPA_PORT_NR, IPA_DEFAULT_PORT);
	  }

	  private void resetToDefaultIpaPort() {
	    super.model.set(IPA_PORT_NR, IPA_DEFAULT_PORT);
	  }
	  
	  public KeyboardTextInput getInputForIpaPortTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getIpaPort());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForIpaPortTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setIpaPort(value);
	        view.UpdateIpaPortTextField(value);
	      }
	    };
	  }

	  // port helper functions
	  public void setIpaFile(String file) {
	    if ("".equals(file)) {
	      resetToDefaultIpaFile();
	    } else {
	      super.model.set(IPA_FILE, file);
	    }
	  }

	  public String getIpaFile() {
	    return super.model.get(IPA_FILE, IPA_DEFAULT_FILE);
	  }

	  private void resetToDefaultIpaFile() {
	    super.model.set(IPA_FILE, IPA_DEFAULT_FILE);
	  }
	  
	  public KeyboardTextInput getInputForIpaFileTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getIpaFile());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForIpaFileTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setIpaFile(value);
	        view.UpdateIpaFileTextField(value);
	      }
	    };
	  }
	  
	  public int IncrementInstanceCounter() {
		    return this.instanceCounter++;
		  }

		  public IPACaller getIpaCaller() {
			  return ipaCaller;
		  }
	  
}
