package com.ipa.externalcontrol.impl;

import java.util.Arrays;
import java.util.List;

import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.program.ProgramModel;
import com.ur.urcap.api.domain.program.nodes.ProgramNodeFactory;
import com.ur.urcap.api.domain.program.nodes.builtin.MoveNode;
import com.ur.urcap.api.domain.program.nodes.builtin.PopupNode;
import com.ur.urcap.api.domain.program.nodes.builtin.SetNode;
import com.ur.urcap.api.domain.program.structure.TreeNode;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;
import com.ur.urcap.api.domain.value.ValueFactoryProvider;
import com.ur.urcap.api.domain.value.simple.Length;
import com.ur.urcap.api.domain.value.simple.SimpleValueFactory;

public class IPAControlProgramNodeContribution extends ExternalControlProgramNodeContribution {

	  private final IPAControlProgramNodeView view;
	  private final ProgramAPIProvider apiProvider;
		private final ProgramModel programModel;
		private final ProgramNodeFactory programNodeFactory;
		private final ValueFactoryProvider valueFactoryProvider;

	  private final String PITASC_APP = "pitascapp";
	  private final String PITASC_DEFAULT_APP = "<undefined>";

	  private final String PITASC_PARAMS = "pitascparams";
	  private final String PITASC_DEFAULT_PARAMS = "";
	  
	  private final String WELDING_SPEED = "weldingspeed";
	  private final String WELDING_DEFAULT_SPEED = "500";
	  
	  private final String WELDING_JOINT_TYPE = "weldingjointtype";
	  private final String WELDING_DEFAULT_JOINT_TYPE = "Kehlnaht";
	  
	  public IPAControlProgramNodeContribution(
		      ProgramAPIProvider apiProvider, IPAControlProgramNodeView view, DataModel model, CreationContext context) {
		  super(apiProvider, view, model);
		  this.view = view;
		  this.apiProvider = apiProvider;
			this.programModel = programAPI.getProgramModel();
			this.programNodeFactory = apiProvider.getProgramAPI().getProgramModel().getProgramNodeFactory();
			this.valueFactoryProvider = apiProvider.getProgramAPI().getValueFactoryProvider();

			if (context.getNodeCreationType() == CreationContext.NodeCreationType.NEW) {
				initialize();
			}
	  }

	  @Override
	  public void openView() {
	  	super.openView();
	    view.UpdatePitascAppTextField(getPitascApp());
	    view.UpdatePitascParamsTextField(getPitascParams());
	    view.UpdateWeldingSpeedTextField(getWeldingSpeed());
	    initializeDropDownList();
	  }

		private void initializeDropDownList() {
			String[] jointTypes = new String[] { "Kehlnaht", "Stumpfnaht" };
			view.setWeldJointTypes(jointTypes);
			view.setSelectedWeldJointType(getWeldingJointType());
		}
	  
	  
	  @Override
	  public String getTitle() {
//	    return "pitasc: " + getPitascApp();
	  	return "IPA: KogRob";
	  }

	  @Override
	  public boolean isDefined() {
//	    return !model.get(PITASC_APP, PITASC_DEFAULT_APP).equals(PITASC_DEFAULT_APP);
	  	return !model.get(WELDING_JOINT_TYPE, WELDING_DEFAULT_JOINT_TYPE).equals(WELDING_JOINT_TYPE);
	  }

	  
	  @Override
	  public void generateScript(ScriptWriter writer) {
//	    getInstallation().getIpaCaller().appendNodeLines(writer, getPitascApp(), getPitascParams());
	  	getInstallation().getIpaCaller().appendNodeLines(writer, getWeldingJointType(), getWeldingSpeed());

	    String urScriptProgram = getInstallation().getUrScriptProgram();
	    String uniqueFunName = "fun_" + getInstallation().IncrementInstanceCounter() + "()";
	    writer.appendLine("def " + uniqueFunName + ":");
	    writer.appendRaw(urScriptProgram);
	    writer.appendLine("end");
	    writer.appendLine(uniqueFunName);

	    getInstallation().getIpaCaller().appendNodePostLines(writer);
	  }

	  private IPAControlInstallationNodeContribution getInstallation() {
		    return programAPI.getInstallationNode(IPAControlInstallationNodeContribution.class);
		 }

	  
		private void initialize() {
			//TODO
		}
	  
	  
	  ////////////////////////////////////////////
	  // PITASC APP	  
	  ////////////////////////////////////////////
	  
	  public void setPitascApp(String app) {
	    if ("".equals(app)) {
	    	model.set(PITASC_APP, PITASC_DEFAULT_APP);
	    } else {
	      model.set(PITASC_APP, app);
	    }
	  }

	  public String getPitascApp() {
	    return model.get(PITASC_APP, PITASC_DEFAULT_APP);
	  }

	  ////////////////////////////////////////////
	  // PITASC PARAMS	  
	  ////////////////////////////////////////////
	  
	  public void setPitascParams(String params) {
	    if ("".equals(params)) {
	    	model.set(PITASC_PARAMS, PITASC_DEFAULT_PARAMS);
	    } else {
	      model.set(PITASC_PARAMS, params);
	    }
	  }

	  public String getPitascParams() {
	    return model.get(PITASC_PARAMS, PITASC_DEFAULT_PARAMS);
	  }
	  
	  ////////////////////////////////////////////
	  // KOGROB JOINT TYPE	  
	  ////////////////////////////////////////////

	  public String getWeldingJointType() {
	    return model.get(WELDING_JOINT_TYPE, WELDING_DEFAULT_JOINT_TYPE);
	  }
	  
	  ////////////////////////////////////////////
	  // KOGROB WELD SPEED
	  ////////////////////////////////////////////

	  public void setWeldingSpeed(String params) {
	    if ("".equals(params)) {
	    	model.set(WELDING_SPEED, WELDING_DEFAULT_SPEED);
	    } else {
	      model.set(WELDING_SPEED, params);
	    }
	  }

	  public String getWeldingSpeed() {
	    return model.get(WELDING_SPEED, WELDING_DEFAULT_SPEED);
	  }

	  ////////////////////////////////////////////
	  // GENERIC
	  ////////////////////////////////////////////

	  public KeyboardTextInput getInputForTextField(final String id) {
	    KeyboardTextInput keyboInput = keyboardFactory.createStringKeyboardInput();
	    
	    switch (id) {
	    case "WELDING_SPEED":
		    keyboInput.setInitialValue(getWeldingSpeed());
		    break;
	    case "PITASC_PARAMS":
	    	keyboInput.setInitialValue(getPitascParams());
	    	break;
	    case "PITASC_APP":
	    	keyboInput.setInitialValue(getPitascApp());
	    	break;
	    default:
	    	keyboInput.setInitialValue("");
	    }
	    return keyboInput;
	  }

	  public KeyboardInputCallback<String> getCallbackForTextField(final String id) {
	    return new KeyboardInputCallback<String>() {
	      @Override
	      public void onOk(String value) {
	      	switch (id) {
	      	case "WELDING_SPEED":
		        setWeldingSpeed(value);
		        view.UpdateWeldingSpeedTextField(value);
		        break;
	  	    case "PITASC_PARAMS":
		        setPitascParams(value);
		        view.UpdatePitascParamsTextField(value);
	  	    	break;
	  	    case "PITASC_APP":
		        setPitascApp(value);
		        view.UpdatePitascAppTextField(value);
	  	    	break;
	  	    default:
	  	    	break;
	      	}
	      }
	    };
	  }
	  
		public void onSelection(final String id) {
			apiProvider.getProgramAPI().getUndoRedoManager().recordChanges(new UndoableChanges() {
				@Override
				public void executeChanges() {
					switch (id) {
					case "WELDING_JOINT_TYPE":
						String jointType = view.getSelectedWeldJointType();
						model.set(WELDING_JOINT_TYPE, jointType);
						break;
					default:
						break;
					}
				}
			});
		}


}
