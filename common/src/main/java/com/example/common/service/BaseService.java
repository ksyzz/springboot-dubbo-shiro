package com.example.common.service;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
public interface BaseService<T> {
    /**
     * 添加一个实体
     * @param t
     */
    void saveOne(T t);

    /**
     * 根据id查找
     * @param id
     */
    T getOne(Long id);

    /**
     * 删除
     * @param t
     * @return
     */
    void updateOne(T t);

    /**
     * 删除
     * @param id
     */
    void deleteOne(Long id);
}
