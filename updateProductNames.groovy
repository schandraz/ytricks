import de.hybris.platform.core.model.product.ProductModel;
import java.util.Locale;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
Locale localeEn = new Locale("en");
flexibleSearchService.search("select {pk} from {Product as p} where {p:name[en]:ol} IS NULL and {p:catalogVersion} in ('8796224127577')").result.each {
 
 query = "select {pk} from {Product as p} where {p:code} ='" + it.getCode() + "' and {p:catalogVersion} in ('8796126118489')";
FlexibleSearchQuery fsquery = new FlexibleSearchQuery(query);
  ProductModel product = flexibleSearchService.searchUnique(fsquery);
  it.setName(product.getName(localeEn),localeEn);
 modelService.save(it);
 
 }
