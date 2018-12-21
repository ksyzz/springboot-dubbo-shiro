package com.example.common.domain.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author fengqian
 * @since <pre>2018/12/18</pre>
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "c_created", nullable = false)
    @CreatedDate
    private Date created;

    /**
     * 修改时间
     */
    @Column(name = "c_updated", nullable = false)
    @LastModifiedDate
    private Date updated;


    /**
     * 标记删除
     */
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;


}
