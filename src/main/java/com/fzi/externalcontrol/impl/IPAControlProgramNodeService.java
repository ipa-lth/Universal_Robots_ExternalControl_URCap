package com.fzi.externalcontrol.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;

public class IPAControlProgramNodeService extends ExternalControlProgramNodeService{

	  @Override
	  public String getTitle(Locale locale) {
		    return "External Pitasc";
	  }
	  
	  @Override
	  public IPAControlProgramNodeView createView(ViewAPIProvider apiProvider) {
	    return new IPAControlProgramNodeView(apiProvider);
	  }

}
