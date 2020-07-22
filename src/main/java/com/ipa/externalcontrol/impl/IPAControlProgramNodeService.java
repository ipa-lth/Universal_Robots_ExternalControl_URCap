package com.ipa.externalcontrol.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class IPAControlProgramNodeService extends ExternalControlProgramNodeService
	implements SwingProgramNodeService<IPAControlProgramNodeContribution,
		IPAControlProgramNodeView> {


	  @Override
	  public String getTitle(Locale locale) {
		    return "KogRob Testbed 2 Self-Optimzing CPRs";
	  }
	  
	  @Override
	  public IPAControlProgramNodeView createView(ViewAPIProvider apiProvider) {
	    return new IPAControlProgramNodeView(apiProvider);
	  }
	  
	  @Override
	  public IPAControlProgramNodeContribution createNode(ProgramAPIProvider apiProvider,
	      IPAControlProgramNodeView view, DataModel model, CreationContext context) {
	    return new IPAControlProgramNodeContribution(apiProvider, view, model);
	  }
}
