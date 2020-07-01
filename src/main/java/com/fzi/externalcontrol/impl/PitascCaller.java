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

//----------------------------------------------------------------------
/*!\file
 *
 * \author  Lorenz Halt lth@ipa.fhg.de
 * \date    2020-06-08
 *
 */
//----------------------------------------------------------------------
package com.fzi.externalcontrol.impl;

import com.ur.urcap.api.domain.script.ScriptWriter;

public class PitascCaller {
  private static final String XMLRPC_VARIABLE = "pitasc_xmlrpc";
  // custom IP
  private final String hostIp;
  // custom port
  private final int portNr;
  private int instanceCnt = -1;
  /*
   * Default constructor
   */
  public PitascCaller(String hostIp, String portNr) {
    //this.programName = name + "():\n";
    this.hostIp = hostIp;
    this.portNr = Integer.parseInt(portNr);
  }

  /**
   * Append URScript line to the ScriptCommand
   * @param command is the line to be appended
   */
  public void appendInstallationLines(ScriptWriter writer, String file) {
    //TODO: Disable this call with some checkbox
    writer.assign(XMLRPC_VARIABLE, "rpc_factory(\"xmlrpc\", \"http://"+hostIp+":"+portNr+"/RPC2\")");
    // Call "load_file" only once at startup. Alternative: do another urcap to load the stuff and remove installation tab content
    writer.assign("load_resp", XMLRPC_VARIABLE + ".load_file(\"" + file + "\")");
  }

  public void appendNodeLines(ScriptWriter writer, String app, String param) {
    this.instanceCnt++;
    writer.appendLine("thread startApp_" + this.instanceCnt + "():");
    writer.appendLine("  textmsg(\"Start pitasc app thread.\")");
        writer.assign("  app_resp", XMLRPC_VARIABLE + ".start_app(\"" + app + "\",\"" + param + "\")");
    writer.appendLine("  textmsg(\"Stopped pitasc app thread.\")");
    writer.appendLine("  textmsg(app_resp)");
    //TODO: read return value and "blocking popup" if it is not "succeeded"
    writer.appendLine("  if app_resp != \"succeeded\":");
    writer.appendLine("    popup(app_resp, \"Continue?\", False, True, blocking=True)");
    writer.appendLine("  end");
    writer.appendLine("end");
    writer.appendLine("threadApp_" + this.instanceCnt +" = run startApp_" + this.instanceCnt +"()");
  }

  public void appendNodePostLines(ScriptWriter writer) {
    writer.appendLine("join threadApp_" + this.instanceCnt);
  }

}
