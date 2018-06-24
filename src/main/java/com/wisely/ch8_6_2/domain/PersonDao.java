package com.wisely.ch8_6_2.domain;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.wisely.ch8_6_2.dao.Person;

@Repository
public class PersonDao {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate; //1-springboot已经配置了，在此处可以直接注入
	
	@Resource(name="stringRedisTemplate")
	ValueOperations<String,String> valOpsStr; //3-基于resource注入基于字符串的简单属性操作
	
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate; //2-springboot已经配置了，在此处可以直接注入
	
	@Resource(name="redisTemplate")
	ValueOperations<Object, Object> valOps; //4-基于resource注入基于对象的简单属性操作
	
	public void stringRedisTemplateDemo(){ //5-存字符串类型
		valOpsStr.set("xx", "yy");
	}
	
	
	public void save(Person person){ //6-存对象类型
		valOps.set(person.getId(),person);
	}
	
	public String getString(){//7-获得字符串
		return valOpsStr.get("xx");
	}
	
	public Person getPerson(){//8-获得对象
		return (Person) valOps.get("1");
	}

}
