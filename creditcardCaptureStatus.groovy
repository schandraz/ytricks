import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
flexibleSearchService.search("select {p:pk} from {Order as p} where {p:orderType} in ('8796150825051','8796150759515','8796150792283','8796150857819') and {p:code} in ('7390005253','7390005251','7390005250','7390005208','7390005206','7390005203','7390005197','7390005144','7390005140','7390004721','7390004713','7390004693','7390004688','7390004466','7390004416','7390004389','7390004343','7390004297','7390004108','7390004106','7390004023','7390003960','7390003957','7390003954','7390003908','7390003873','7390003861','7390003846','7390003834','7390003815')").result.each {
	
  	if (it.getPaymentTransactions().size() > 0)
  {
    boolean isCaptured = false;
    for (PaymentTransactionModel paymentTransactionModel : it.getPaymentTransactions()) {
			if (paymentTransactionModel.getEntries().size() > 0 )
      		{
              for (PaymentTransactionEntryModel paymentTransactionEntryModel : paymentTransactionModel.getEntries()) {
                if (paymentTransactionEntryModel.getType() == PaymentTransactionType.CAPTURE ) {
                 isCaptured = true;
                }
              }
      		}
		}
    if (isCaptured)
    {
      println it.getCode() +" :" + "Captured"
    } else {
      println it.getCode() +" :" + "Not Captured"
    }
  }
 }
