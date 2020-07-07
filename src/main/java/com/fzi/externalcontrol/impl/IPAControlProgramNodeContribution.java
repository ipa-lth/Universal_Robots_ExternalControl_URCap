package com.fzi.externalcontrol.impl;

import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class IPAControlProgramNodeContribution extends ExternalControlProgramNodeContribution {

	  private final IPAControlProgramNodeView view;
	
	  private static final String PITASC_APP = "pitascapp";
	  private static final String PITASC_DEFAULT_APP = "<undefined>";

	  private static final String PITASC_PARAMS = "pitascparams";
	  private static final String PITASC_DEFAULT_PARAMS = "";
	  
	  public IPAControlProgramNodeContribution(
		      ProgramAPIProvider apiProvider, IPAControlProgramNodeView view, DataModel model) {
		  super(apiProvider, view, model);
		  this.view = view;
	  }

	  @Override
	  public void openView() {
		super.openView();
	    view.UpdatePitascAppTextField(getPitascApp());
	    view.UpdatePitascParamsTextField(getPitascParams());
	  }

	  @Override
	  public String getTitle() {
	    return "pitasc: " + getPitascApp();
	  }

	  @Override
	  public boolean isDefined() {
	    return !model.get(PITASC_APP, PITASC_DEFAULT_APP).equals(PITASC_DEFAULT_APP);
	  }

	  
	  @Override
	  public void generateScript(ScriptWriter writer) {
	    getInstallation().getPitascCaller().appendNodeLines(writer, getPitascApp(), getPitascParams());

	    String urScriptProgram = getInstallation().getUrScriptProgram();
	    String uniqueFunName = "fun_" + getInstallation().IncrementInstanceCounter() + "()";
	    writer.appendLine("def " + uniqueFunName + ":");
	    writer.appendRaw(urScriptProgram);
	    writer.appendLine("end");
	    writer.appendLine(uniqueFunName);

	    getInstallation().getPitascCaller().appendNodePostLines(writer);
	  }

	  private IPAControlInstallationNodeContribution getInstallation() {
		    return programAPI.getInstallationNode(IPAControlInstallationNodeContribution.class);
		 }

	  // port helper functions
	  public void setPitascApp(String app) {
	    if ("".equals(app)) {
	      resetToDefaultPitascApp();
	    } else {
	      model.set(PITASC_APP, app);
	    }
	  }

	  public String getPitascApp() {
	    return model.get(PITASC_APP, PITASC_DEFAULT_APP);
	  }

	  private void resetToDefaultPitascApp() {
	    model.set(PITASC_APP, PITASC_DEFAULT_APP);
	  }
	  
	  public KeyboardTextInput getInputForPitascAppTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getPitascApp());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForPitascAppTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setPitascApp(value);
	        view.UpdatePitascAppTextField(value);
	      }
	    };
	  }

	  public void setPitascParams(String params) {
	    if ("".equals(params)) {
	      resetToDefaultPitascParams();
	    } else {
	      model.set(PITASC_PARAMS, params);
	    }
	  }

	  public String getPitascParams() {
	    return model.get(PITASC_PARAMS, PITASC_DEFAULT_PARAMS);
	  }

	  private void resetToDefaultPitascParams() {
	    model.set(PITASC_PARAMS, PITASC_DEFAULT_PARAMS);
	  }

	  public KeyboardTextInput getInputForPitascParamsTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getPitascParams());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForPitascParamsTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setPitascParams(value);
	        view.UpdatePitascParamsTextField(value);
	      }
	    };
	  }
}
