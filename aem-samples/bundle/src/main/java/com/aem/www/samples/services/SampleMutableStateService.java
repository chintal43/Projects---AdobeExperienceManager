

package com.aem.www.samples.services;

public interface SampleMutableStateService {

    void addToMap(final String key, final String val);

    String getFromMap(final String key);

    void addToList(final String val);

    int getListLength();

    void incremementCount();

    int getCount();
}
