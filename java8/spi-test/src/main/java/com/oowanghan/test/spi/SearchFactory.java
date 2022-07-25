package com.oowanghan.test.spi;

import com.oowanghan.spi.Search;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author WangHan
 * @Create 2022/4/23 1:18 下午
 */
public class SearchFactory {

    public static Search newSearch() {
        Search search = null;
        ServiceLoader<Search> serviceLoader = ServiceLoader.load(Search.class);
        Iterator<Search> searchs = serviceLoader.iterator();
        while (searchs.hasNext()) {
            search = searchs.next();
        }
        return search;
    }
}
