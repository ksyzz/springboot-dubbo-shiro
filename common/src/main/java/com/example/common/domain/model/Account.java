package com.example.common.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author fengqian
 * @since <pre>2018/12/18</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_account")
public class Account extends BaseModel {
    /**
     * 用户名
     */
    @Column(name = "c_username", nullable = false, unique = true)
    private String username;

    /**
     * 昵称
     */
    @Column(name = "c_name")
    private String name;

    /**
     * 密码
     */
    @Column(name = "c_password")
    private String password;
}
