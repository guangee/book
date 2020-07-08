package com.coding.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Slf4j
@Data
public class DataResponse<T> {
    private long draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;
    private String error;
    private String referer;
    private boolean refresh;
    private String state;
    private String message;

    public DataResponse(Page<T> pageInfo, long draw) {
        this.draw = draw;
        recordsFiltered = pageInfo.getTotalElements();
        recordsTotal = pageInfo.getTotalElements();
        data = pageInfo.getContent();
    }

    public DataResponse(long total, List<T> data, long draw) {
        this.draw = draw;
        recordsFiltered = total;
        recordsTotal = total;
        this.data = data;
    }

    private DataResponse(String referer, boolean refresh, String state, String message) {
        this.referer = referer;
        this.refresh = refresh;
        this.state = state;
        this.message = message;
    }

    public long getDraw() {
        return draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public static <T> DataResponse<T> success(String referer) {
        return new DataResponse<>(referer, true, "success", "成功");
    }

    public static <T> DataResponse<T> error(String referer) {
        return new DataResponse<>(referer, false, "unsucess", "失败");
    }

    public static <T> DataResponse<T> successMessage(String referer, String message) {
        return new DataResponse<>(referer, true, "success", message);
    }
}
