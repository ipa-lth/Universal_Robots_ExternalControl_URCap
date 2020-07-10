package com.fzi.externalcontrol.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;

public class IPAControlInstallationNodeService extends ExternalControlInstallationNodeService {
	
	  @Override
	  public String getTitle(Locale locale) {
		    return "External Pitasc";
		  }
	  @Override
	  public IPAControlInstallationNodeView createView(ViewAPIProvider apiProvider) {
	    return new IPAControlInstallationNodeView();
	  }

	  @Override
	  public IPAControlInstallationNodeContribution createInstallationNode(
	      InstallationAPIProvider installationApiProvider, ExternalControlInstallationNodeView view,
	      DataModel model, CreationContext context) {
		IPAControlInstallationNodeView ipa_view = new IPAControlInstallationNodeView();
	    return new IPAControlInstallationNodeContribution(installationApiProvider, ipa_view, model);
	  }
	  
}
