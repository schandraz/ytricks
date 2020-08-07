 import de.hybris.platform.processengine.BusinessProcessService;
 import de.hybris.platform.processengine.model.BusinessProcessModel;
 import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
 import de.hybris.platform.servicelayer.search.FlexibleSearchService;
 import de.hybris.platform.servicelayer.search.SearchResult;
 
 FlexibleSearchService flexibleSearchService = spring.getBean('flexibleSearchService');
 BusinessProcessService businessProcessService = spring.getBean('businessProcessService');
 
 StringBuilder query = new StringBuilder("select {pk} from {businessprocess} where {pk} in ('8798288543742', '8798223237118')");
         
 FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
 flexibleSearchService.search(fQuery).result.each {
     println it.getCode()
     businessProcessService.restartProcess(it, "sendOrderToErp");
     println 'completed.'
 }
