
package com.aem.www.samples.mbean;

import com.adobe.granite.jmx.annotation.Description;
import com.adobe.granite.jmx.annotation.Name;

import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;

@Description("ACS AEM Samples - Sample MBean")
public interface SampleMBean {

    @Description("Get the message")
    String getMessage();

    @Description("Set the message")
    void setMessage(@Name(value="New Message") String message);

    @Description("Clear history")
    void clearHistory();

    @Description("Get the message history in a tabular format")
    TabularDataSupport getHistory() throws OpenDataException;
}