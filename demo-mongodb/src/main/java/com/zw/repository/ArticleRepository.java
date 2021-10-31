package com.zw.repository;

import com.zw.model.Articlezw;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.repository
 * @descripation TODO
 * @date 2021-06-29
 */
public interface ArticleRepository extends MongoRepository<Articlezw,Long> {

    //使用方法类似jpa，通过title查看数据
    List<Articlezw> findByTitleLike(String title);
}
