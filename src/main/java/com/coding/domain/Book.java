package com.coding.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * @author guanweiming
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "tab_book")
public class Book extends BaseDO {

    @ApiModelProperty("书名")
    private String name;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("出版社")
    private String press;

    @ApiModelProperty("ISBN")
    private String isbn;

    @ApiModelProperty("出版日期")
    private String pubDate;

    @ApiModelProperty("页码数")
    private Integer pages;


}