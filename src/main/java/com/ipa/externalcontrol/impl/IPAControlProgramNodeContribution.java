package com.ipa.externalcontrol.impl;

import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;
import com.ur.urcap.api.domain.value.simple.Length;

public class IPAControlProgramNodeContribution extends ExternalControlProgramNodeContribution {

	  private final IPAControlProgramNodeView view;
	  private final ProgramAPIProvider apiProvider;
	
	  private static final String PITASC_APP = "pitascapp";
	  private static final String PITASC_DEFAULT_APP = "<undefined>";

	  private static final String PITASC_PARAMS = "pitascparams";
	  private static final String PITASC_DEFAULT_PARAMS = "";
	  
	  private static final String WELDING_SPEED = "weldingspeed";
	  private static final String WELDING_DEFAULT_SPEED = "500";
	  
	  private static final String WELDING_JOINT_TYPE = "weldingjointtype";
	  private static final String WELDING_DEFAULT_JOINT_TYPE = "Kehlnaht";
	  
	  public IPAControlProgramNodeContribution(
		      ProgramAPIProvider apiProvider, IPAControlProgramNodeView view, DataModel model) {
		  super(apiProvider, view, model);
		  this.view = view;
		  this.apiProvider = apiProvider;
	  }

	  @Override
	  public void openView() {
	  	super.openView();
	    view.UpdatePitascAppTextField(getPitascApp());
	    view.UpdatePitascParamsTextField(getPitascParams());
	  }

	  @Override
	  public String getTitle() {
//	    return "pitasc: " + getPitascApp();
	  	return "IPA: KogRob";
	  }

	  @Override
	  public boolean isDefined() {
	    return !model.get(PITASC_APP, PITASC_DEFAULT_APP).equals(PITASC_DEFAULT_APP);
	  }

	  
	  @Override
	  public void generateScript(ScriptWriter writer) {
//	    getInstallation().getPitascCaller().appendNodeLines(writer, getPitascApp(), getPitascParams());
	  	getInstallation().getPitascCaller().appendNodeLines(writer, getWeldingJointType(), getWeldingSpeed());

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

	  
	  ////////////////////////////////////////////
	  // PITASC APP	  
	  ////////////////////////////////////////////
	  
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

	  ////////////////////////////////////////////
	  // PITASC PARAMS	  
	  ////////////////////////////////////////////
	  
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
	  
	  ////////////////////////////////////////////
	  // KOGROB JOINT TYPE	  
	  ////////////////////////////////////////////

	  public void setWeldingJointType(String params) {
	    if ("".equals(params)) {
	      resetToDefaultWeldingJointType();
	    } else {
	      model.set(WELDING_JOINT_TYPE, params);
	    }
	  }	  
	  public String getWeldingJointType() {
	    return model.get(WELDING_JOINT_TYPE, WELDING_DEFAULT_JOINT_TYPE);
	  }
	  
	  private void resetToDefaultWeldingJointType() {
	    model.set(WELDING_JOINT_TYPE, WELDING_DEFAULT_JOINT_TYPE);
	  }

		public void onWeldJointTypeSelection() {
			apiProvider.getProgramAPI().getUndoRedoManager().recordChanges(new UndoableChanges() {
				@Override
				public void executeChanges() {
					String jointType = view.getSelectedWeldJointType();
					model.set(WELDING_JOINT_TYPE, WELDING_DEFAULT_JOINT_TYPE);
					setWeldingJointType(jointType);
				}
			});
		}

	  ////////////////////////////////////////////
	  // KOGROB WELD SPEED
	  ////////////////////////////////////////////

	  public void setWeldingSpeed(String params) {
	    if ("".equals(params)) {
	      resetToDefaultWeldingSpeed();
	    } else {
	      model.set(WELDING_SPEED, params);
	    }
	  }

	  public String getWeldingSpeed() {
	    return model.get(WELDING_SPEED, WELDING_DEFAULT_SPEED);
	  }

	  private void resetToDefaultWeldingSpeed() {
	    model.set(WELDING_SPEED, WELDING_DEFAULT_SPEED);
	  }

	  public KeyboardTextInput getInputForWeldingSpeedTextField() {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    keyboInput.setInitialValue(getWeldingSpeed());
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForWeldingSpeedTextField() {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	        setWeldingSpeed(value);
	        view.UpdateWeldingSpeedTextField(value);
	      }
	    };
	  }
}
