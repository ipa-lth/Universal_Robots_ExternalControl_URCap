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
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class IPAControlProgramNodeService extends ExternalControlProgramNodeService
	implements SwingProgramNodeService<IPAControlProgramNodeContribution,
		IPAControlProgramNodeView> {


	  @Override
	  public String getTitle(Locale locale) {
//	  	return "External Pitasc"; //project_pitasc
		    return "KogRob Testbed 2 Self-Optimzing CPRs"; //project_kogrob
	  }
	  
	  @Override
	  public IPAControlProgramNodeView createView(ViewAPIProvider apiProvider) {
	    return new IPAControlProgramNodeView(apiProvider);
	  }
	  
	  @Override
	  public IPAControlProgramNodeContribution createNode(ProgramAPIProvider apiProvider,
	      IPAControlProgramNodeView view, DataModel model, CreationContext context) {
	    return new IPAControlProgramNodeContribution(apiProvider, view, model, context);
	  }
}
