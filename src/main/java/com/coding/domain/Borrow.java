package com.coding.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * @author guanweiming
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "tab_borrow")
public class Borrow extends BaseDO {

    @ApiModelProperty("状态 0-已借阅 1-已还书")
    private Integer status;


    @OneToOne
    private Book book;

    @OneToOne
    private User user;


    @CreationTimestamp
    @ApiModelProperty("借书时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime borrowTime;


    @ApiModelProperty("还书时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnTime;
}