import de.hybris.platform.core.model.security.PrincipalGroupModel;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
int iCount = 0;
int nonCount = 0;
Predicate<PrincipalGroupModel> byStartingwithY = {principalGroupModel -> principalGroupModel.getUid().indexOf("y") == 0};
flexibleSearchService.search("select {p:pk} from {Order as p} where {p:site} in ('8796191360040') ").result.each {

 List<PrincipalGroupModel> groupsList =  it.getUser().getGroups().stream().collect(Collectors.toList())
final Set<PrincipalGroupModel> groups = new HashSet<>((groupsList != null ? groupsList : Collections.emptySet()));
 
  int size = groupsList.stream().filter(byStartingwithY).collect(Collectors.toList()).size();
  
  if (size > 0 )
  {
    iCount = iCount + 1;
    
  }
  else {
    nonCount = nonCount + 1;
    println it.getUser().getUid();
  }
 }

println "Electrolux orders" + iCount;

println "Non Electrolux orders" + nonCount;
