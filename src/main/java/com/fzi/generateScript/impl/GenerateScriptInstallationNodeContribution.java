package com.fzi.generateScript.impl;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.InstallationAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputFactory;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;





public class GenerateScriptInstallationNodeContribution implements InstallationNodeContribution {

	private static final String HOST_IP= "ip";
	private static final String DEFAULT_VALUE= "default";
	private DataModel model;
	private final GenerateScriptInstallationNodeView view;
	private final InstallationAPI installationAPI;
	private final KeyboardInputFactory keyboardFactory;
	

	public GenerateScriptInstallationNodeContribution(InstallationAPIProvider apiProvider, GenerateScriptInstallationNodeView view, DataModel model) {
		this.installationAPI = apiProvider.getInstallationAPI();
		this.keyboardFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getKeyboardInputFactory();
		this.model = model;
		this.view = view;
	}



	@Override
	public void openView() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	public boolean isDefined() {
		return !getHostIP().isEmpty();
	}


	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
	}

	
	public void setHostIP(String ip) {
		if ("".equals(ip)) {
			resetToDefaultValue();
		} else {
			model.set(HOST_IP, ip);
		}
	}
	
	public String getHostIP() {
		return model.get(HOST_IP, DEFAULT_VALUE);
	}
	
	private void resetToDefaultValue() {
		//view.setHostIP(DEFAULT_VALUE);
		model.set(HOST_IP, DEFAULT_VALUE);
	}
	
	public KeyboardTextInput getInputForTextField() {
		KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
		keyboInput.setInitialValue(getHostIP());
		return keyboInput;
	}
	
	public KeyboardInputCallback<String> getCallbackForTextField() {
		return new KeyboardInputCallback<String>() {
			@Override
			public void onOk(String value) {
				System.out.println("Om pressed: " + value );
				setHostIP(value);
				System.out.println("getHostIP: " + getHostIP());
			}
		};
	}

}
