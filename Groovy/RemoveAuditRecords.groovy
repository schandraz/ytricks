import de.hybris.platform.persistence.audit.gateway.WriteAuditGateway;
import de.hybris.platform.core.Registry;
String[] types = ["abstractcontactinfo","address","b2bunit","basesite","cart","cartentry","comment","consent","consenttemplate","country","csagentgroup","csticket","currency","customerreview","employee","language","order","orderentry","paymentinfo","paymentmode","principalgrouprelation","product","quote","quoteentry","region","title","unit","user","usergroup","userpasswordchangeaudit"];
WriteAuditGateway writeAuditGateway = Registry.getApplicationContext().getBean("writeAuditGateway", WriteAuditGateway.class);
for (final String type : types)
{
  writeAuditGateway.removeAuditRecordsForType(type);
  println "Removed ----- "+type;
}