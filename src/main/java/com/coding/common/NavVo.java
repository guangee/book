package com.coding.common;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author guanweiming
 */
@Data
public class NavVo implements Serializable {
    private String title;
    private String url;
    private List<NavVo> child = Lists.newArrayList();

    public NavVo(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
