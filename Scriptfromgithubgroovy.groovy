import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import de.hybris.platform.scripting.engine.content.ScriptContent;
import de.hybris.platform.scripting.engine.ScriptExecutable;
import de.hybris.platform.scripting.engine.content.impl.ResourceScriptContent;
import de.hybris.platform.scripting.engine.ScriptExecutionResult;
final Resource resource = new UrlResource("https://raw.githubusercontent.com/SarathChandra84/GroovyScripts/master/RepairOrderconfirmation.groovy");

// Let's assume we have scriptingLanguagesService injected by the Spring
final ScriptContent scriptContent = new ResourceScriptContent(resource);
final ScriptExecutable executable = scriptingLanguagesService.getExecutableByContent(scriptContent);
 
// now we can execute script
final ScriptExecutionResult result = executable.execute();
 
// to obtain result of execution 
System.out.println(result.getScriptResult());
