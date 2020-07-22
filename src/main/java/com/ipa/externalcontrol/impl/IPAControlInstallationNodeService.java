package com.ipa.externalcontrol.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;

public class IPAControlInstallationNodeService extends ExternalControlInstallationNodeService
	implements SwingInstallationNodeService<IPAControlInstallationNodeContribution,
	IPAControlInstallationNodeView>{
	
	  @Override
	  public String getTitle(Locale locale) {
		    return "Kogrob Testbed 2 Self-Optimzing CPRs";
		  }
	  @Override
	  public IPAControlInstallationNodeView createView(ViewAPIProvider apiProvider) {
	    return new IPAControlInstallationNodeView();
	  }

	  @Override
	  public IPAControlInstallationNodeContribution createInstallationNode(
	      InstallationAPIProvider installationApiProvider, IPAControlInstallationNodeView view,
	      DataModel model, CreationContext context) {
	    return new IPAControlInstallationNodeContribution(installationApiProvider, view, model);
	  }
	  
}
