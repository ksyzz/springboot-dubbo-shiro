package com.example.demo.manager;

import com.example.common.domain.model.BaseModel;
import com.example.demo.repository.BaseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
@Component
public abstract class BaseManager<T extends BaseModel> {

    /**
     * 获取repository
     * @return
     */
    public abstract BaseRepository getRepository();

    /**
     * 添加一个实体
     * @param t
     */
    public void saveOne(T t){
        getRepository().save(t);
    }

    /**
     * 根据id查找
     * @param id
     */
    public T getOne(Long id){
        Optional<T> result = getRepository().findById(id);
        return result.orElse(null);
    }

    /**
     * 更新
     * @param t
     * @return
     */
    public void updateOne(T t){
        getRepository().save(t);
    }

    /**
     * 标记删除
     * @param t
     */
    public void deleteOne(T t){
        t.setDeleted(true);
        getRepository().save(t);
    }

    /**
     * 永久删除
     * @param t
     */
    public void deleteForever(T t){
        getRepository().delete(t);
    }
}
