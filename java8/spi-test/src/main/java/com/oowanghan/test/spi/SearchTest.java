package com.oowanghan.test.spi;

import com.oowanghan.spi.Search;

/**
 * @Author WangHan
 * @Create 2022/4/23 1:18 下午
 */
public class SearchTest {

    public static void main(String[] args) {
        Search search = SearchFactory.newSearch();
        System.out.println(search.search("java spi test"));
    }
}
