package com.oowanghan.spib;

import com.oowanghan.spi.Search;

/**
 * @Author WangHan
 * @Create 2022/4/23 1:27 下午
 */
public class SearchB implements Search {
    @Override
    public String search(String keyword) {
        return keyword + " B";
    }
}
