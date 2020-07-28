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
