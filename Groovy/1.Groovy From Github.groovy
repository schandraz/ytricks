import de.hybris.platform.scripting.engine.ScriptExecutable;

import de.hybris.platform.scripting.engine.ScriptExecutionResult;

final ScriptExecutable executable = scriptingLanguagesService.getExecutableByURI("https://raw.githubusercontent.com/schandraz/ytricks/master/Groovy/LogViewer.groovy);
 
// now we can execute script
final ScriptExecutionResult result = executable.execute();
 
// to obtain result of execution 
System.out.println(result.getScriptResult());
