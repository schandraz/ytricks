 import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
flexibleSearchService = spring.getBean("flexibleSearchService");
// Create the email
email = de.hybris.platform.util.mail.MailUtils.getPreConfiguredEmail()
email.addTo('sarath.chandra@electrolux.com')
email.addTo('venkatesh.saini@electrolux.com')

email.subject = 'Order Totals Per Day'
StringBuffer buffer = new StringBuffer();
buffer.append("Total" + "\t" + "Total Count" +"\t"+ "Date" + "\n");
buffer.append("-----------------------------US B2B----------------------------" + "\n");
query = "SELECT FORMAT(SUM({O.totalprice}), 'C'),FORMAT({O:date},'MM/dd/yyyy'),count(*) FROM {Order AS O} WHERE {O:site} = '8796093088808' GROUP BY FORMAT({O:date},'MM/dd/yyyy') ORDER BY FORMAT({O:date},'MM/dd/yyyy') DESC";

FlexibleSearchQuery fsquery = new FlexibleSearchQuery(query);
fsquery.setResultClassList(Arrays.asList(String.class, String.class,String.class));
 SearchResult<List<Object>> result = flexibleSearchService.search(fsquery);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Date date = new Date();
        String todate = dateFormat.format(date);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date todate1 = cal.getTime();
 
 for (List<Object> row : result.getResult()) {
     String orderTotal = row.get(0);
     String orderDate= row.get(1);
     String orderCount = row.get(2);
   Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(orderDate); 
   if (date1.compareTo(todate1) > 0) {
     
   buffer.append(orderTotal + "\t" + orderCount +"\t"+ orderDate + "\n");
   }
 }
buffer.append("---------------------CA B2B------------------------------------" + "\n");
query = "SELECT FORMAT(SUM({O.totalprice}), 'C'),FORMAT({O:date},'MM/dd/yyyy'),count(*) FROM {Order AS O}  WHERE {O:site} = '8796191360040' GROUP BY FORMAT({O:date},'MM/dd/yyyy') ORDER BY FORMAT({O:date},'MM/dd/yyyy') DESC";

fsquery = new FlexibleSearchQuery(query);
fsquery.setResultClassList(Arrays.asList(String.class, String.class,String.class));
result = flexibleSearchService.search(fsquery);
 
 for (List<Object> row : result.getResult()) {
     String orderTotal = row.get(0);
     String orderDate= row.get(1);
     String orderCount = row.get(2);
   Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(orderDate); 
   if (date1.compareTo(todate1) > 0) {
     
   buffer.append(orderTotal + "\t" + orderCount +"\t"+ orderDate + "\n");
   }
 }
buffer.append("---------------------US B2C ------------------------------------" + "\n");
query = "SELECT FORMAT(SUM({O.totalprice}), 'C'),FORMAT({O:date},'MM/dd/yyyy'),count(*) FROM {Order AS O}  WHERE {O:site} = '8796093056040' GROUP BY FORMAT({O:date},'MM/dd/yyyy') ORDER BY FORMAT({O:date},'MM/dd/yyyy') DESC";

fsquery = new FlexibleSearchQuery(query);
fsquery.setResultClassList(Arrays.asList(String.class, String.class,String.class));
result = flexibleSearchService.search(fsquery);
 
 for (List<Object> row : result.getResult()) {
     String orderTotal = row.get(0);
     String orderDate= row.get(1);
     String orderCount = row.get(2);
   Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(orderDate); 
   if (date1.compareTo(todate1) > 0) {
     
   buffer.append(orderTotal + "\t" + orderCount +"\t"+ orderDate + "\n");
   }
 }
 buffer.append("---------------------ESA------------------------------------" + "\n");
query = "SELECT FORMAT(SUM({O.totalprice}), 'C'),FORMAT({O:date},'MM/dd/yyyy'),count(*) FROM {Order AS O}  WHERE {O:site} = '8796158592040' GROUP BY FORMAT({O:date},'MM/dd/yyyy') ORDER BY FORMAT({O:date},'MM/dd/yyyy') DESC";

fsquery = new FlexibleSearchQuery(query);
fsquery.setResultClassList(Arrays.asList(String.class, String.class,String.class));
result = flexibleSearchService.search(fsquery);
 
 for (List<Object> row : result.getResult()) {
     String orderTotal = row.get(0);
     String orderDate= row.get(1);
     String orderCount = row.get(2);
   Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(orderDate); 
   if (date1.compareTo(todate1) > 0) {
     
   buffer.append(orderTotal + "\t" + orderCount +"\t"+ orderDate + "\n");
   }
 }
 buffer.append("---------------------Total Orders------------------------------------" + "\n");
query = "SELECT FORMAT(SUM({O.totalprice}), 'C'),FORMAT({O:date},'MM/dd/yyyy'),count(*) FROM {Order AS O}  GROUP BY FORMAT({O:date},'MM/dd/yyyy') ORDER BY FORMAT({O:date},'MM/dd/yyyy') DESC";

fsquery = new FlexibleSearchQuery(query);
fsquery.setResultClassList(Arrays.asList(String.class, String.class,String.class));
result = flexibleSearchService.search(fsquery);
 
 for (List<Object> row : result.getResult()) {
     String orderTotal = row.get(0);
     String orderDate= row.get(1);
     String orderCount = row.get(2);
   Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(orderDate); 
   if (date1.compareTo(todate1) > 0) {
     
   buffer.append(orderTotal + "\t" + orderCount +"\t"+ orderDate + "\n");
   }
 } 
buffer.append("---------------------------------------------------------" + "\n");
query1 = "SELECT FORMAT(SUM({O.totalprice}), 'C'),count(*) FROM {Order AS O} ";

FlexibleSearchQuery query1 = new FlexibleSearchQuery(query1);
query1.setResultClassList(Arrays.asList(String.class, String.class));
 SearchResult<List<Object>> result1 = flexibleSearchService.search(query1);
for (List<Object> row1 : result1.getResult()) {
     String orderTotal1 = row1.get(0);
     String orderCount1 = row1.get(1);
   buffer.append(orderTotal1 + "\t" + orderCount1);
 }

email.msg = buffer.toString();
// Send the email
email.send()
