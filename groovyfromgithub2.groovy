import de.hybris.platform.scripting.engine.ScriptExecutable;

import de.hybris.platform.scripting.engine.ScriptExecutionResult;

final ScriptExecutable executable = scriptingLanguagesService.getExecutableByURI("https://raw.githubusercontent.com/SarathChandra84/GroovyScripts/master/RepairOrderconfirmation.groovy");
 
// now we can execute script
final ScriptExecutionResult result = executable.execute();
 
// to obtain result of execution 
System.out.println(result.getScriptResult());
