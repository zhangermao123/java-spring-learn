package com.zw;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.zw.model.Articlezw;
import com.zw.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DemoMongodbApplicationTests {
	@Autowired
	private ArticleRepository articleRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private Snowflake snowflake;

	@Test
	public void contextLoads() {
	}

	/**
	 * 测试新增
	 */
	@Test
	public void testSave() {
		Articlezw articlezw = Articlezw.builder()
							.id(snowflake.nextId())
							.title(RandomUtil.randomString(20))
							.content(RandomUtil.randomString(150))
							.createTime(DateUtil.date())
							.updateTime(DateUtil.date())
							.thumbUp(0L)
							.visits(0L).build();

		articleRepo.save(articlezw);
		log.info("[testSave]= {}", JSONUtil.toJsonStr(articlezw));
	}

	/**
	 * 测试新增列表
	 */
	@Test
	public void testSaveList() {
		List<Articlezw> articles = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			articles.add(new Articlezw(snowflake.nextId(), RandomUtil.randomString(20), RandomUtil.randomString(150), DateUtil.date(), DateUtil.date(), 0L, 0L));
		}
		articleRepo.saveAll(articles);

		log.info("【articles】= {}", JSONUtil.toJsonStr(articles.stream().map(Articlezw::getId).collect(Collectors.toList())));
	}


	@Test
	public void testQuery(){
		List<Articlezw> list = articleRepo.findAll();
		log.info("[testQuery] = {} ",JSONUtil.toJsonStr(list));
	}

	/**
	 * 测试分页排序查询
	 */
	@Test
	public void testPage() {
		Sort sort = Sort.by("thumbUp", "updateTime").descending();
		PageRequest pageRequest = PageRequest.of(0, 5, sort);
		Page<Articlezw> all = articleRepo.findAll(pageRequest);
		log.info("【总页数】= {}", all.getTotalPages());
		log.info("【总条数】= {}", all.getTotalElements());
		log.info("【当前页数据】= {}", JSONUtil.toJsonStr(all.getContent().stream().map(article -> "文章标题：" + article.getTitle() + "点赞数：" + article.getThumbUp() + "更新时间：" + article.getUpdateTime()).collect(Collectors.toList())));
	}


	/**
	 * 测试更新
	 */
	@Test
	public void testUpdate() {
		articleRepo.findById(1409820437889814528L).ifPresent(article -> {
			article.setTitle(article.getTitle() + "更新之后的标题");
			article.setUpdateTime(DateUtil.date());
			articleRepo.save(article);
			log.info("[testUpdate]////////////= {}", JSONUtil.toJsonStr(article));
		});
	}

	/**
	 * 测试点赞数、访客数，使用更优雅/高效的方式更新点赞、访客
	 */
	@Test
	public void testUpdate2() {
		Query query = new Query();
		//搜索语法
		query.addCriteria(Criteria.where("_id").is(1409817907097112576L));
		Update update = new Update();
		update.set("thumbUp", 1L);
		update.set("visits", 1L);
		update.set("title", "更新之后的标题");
		mongoTemplate.updateFirst(query, update, Articlezw.class);

		articleRepo.findById(1L).ifPresent(article -> log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article.getVisits()));
	}

	/**
	 * 测试根据标题模糊查询
	 */
	@Test
	public void testFindByTitleLike() {
		List<Articlezw> articles = articleRepo.findByTitleLike("更新");
		log.info("【articles】= {}", JSONUtil.toJsonStr(articles));
	}

	@Test
	public void testDeleteAll(){
		// 全部删除
		articleRepo.deleteAll();
	}
}
