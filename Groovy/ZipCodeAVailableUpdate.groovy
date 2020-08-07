import de.hybris.platform.ordersplitting.model.WarehouseModel
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import com.electrolux.common.services.cronjob.ZZipCodesUpdateJobPerformable;
import com.electrolux.common.core.model.ZipCodesCronJobModel;
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.electrolux.common.core.model.ZipAvailableScheduleModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang.StringUtils;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.HashSet;

FlexibleSearchService flexibleSearchService = spring.getBean('flexibleSearchService');



JCoDestination destination = JCoDestinationManager.getDestination("ECC_RFC");

JCoFunction function  =destination.getRepository().getFunction("Z_SDSO_HYB_ZIP_DATA");

function.getImportParameterList().setValue("FI_RDC", "CA11");
WarehouseModel whm=getWarehouseByCode("CA11");
println function;
function.execute(destination);
println function
final JCoTable resultTable = function.getExportParameterList().getTable("FE_ZIPDATA");
println resultTable.getNumRows();
Set<ZipAvailableScheduleModel> set = new HashSet<ZipAvailableScheduleModel>();
for (int i = 0; i < resultTable.getNumRows(); i++)
						{
							resultTable.setRow(i);
							String pos = resultTable.getString("SHPPT");
							String zipcode = resultTable.getString("ZIPCODE");
							String leadTime = resultTable.getString("TRTIME");
							println (pos+"    "+zipcode+"   "+leadTime);
							ZipAvailableScheduleModel zipAvailableScheduleModel = findorCreate(zipcode);
							if (StringUtils.isNotEmpty(leadTime)) {
								zipAvailableScheduleModel.setLeadTime(Integer.parseInt(leadTime));
							}
							if (pos.equalsIgnoreCase(whm.getCode())) {
								zipAvailableScheduleModel.setWarehouse(whm);
							} 
							set.add(zipAvailableScheduleModel);
							modelService.save(zipAvailableScheduleModel);
						}
					
private ZipAvailableScheduleModel findorCreate(String zipcode)
	{
		ZipAvailableScheduleModel zipAvailableScheduleModel = findZipCode(zipcode);
		if (null == zipAvailableScheduleModel)
		{
			zipAvailableScheduleModel = modelService.create(ZipAvailableScheduleModel.class);
			zipAvailableScheduleModel.setCode(zipcode);

		}
		return zipAvailableScheduleModel;
	}
	
	public ZipAvailableScheduleModel findZipCode(String zipcode) {
		String query = "select {pk} from {ZipAvailableSchedule} where {code}= '" + zipcode + "'";
		final SearchResult<ZipAvailableScheduleModel> zipCodes = flexibleSearchService.search(query);
		if (!zipCodes.getResult().isEmpty())
		{
			return zipCodes.getResult().get(0);
		}
		return null;
	}
	
	public WarehouseModel getWarehouseByCode(final String warehouseCode)
	{
		final StringBuilder query = new StringBuilder();
		query.append("select {pk} FROM {Warehouse} WHERE {code} =?code");
		final Map<String, Object> attr = new HashMap<String, Object>(1);
		attr.put("code", warehouseCode);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
		searchQuery.getQueryParameters().putAll(attr);
		final SearchResult<WarehouseModel> searchResult = flexibleSearchService.search(searchQuery);
		return searchResult.getResult().get(0);
	}
