package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaSpecificationExecutor<T>, JpaRepository<T, ID> {
}
