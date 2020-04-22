package com.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Channel;

@Repository
public interface ChannelRepository extends MongoRepository<Channel, String> {
	public List<Channel> findByQuality(String q);
	public List<Channel> findByTitle(String t);
//	public List<Channel> findFristByQulity(String s);
//	List<Channel> findAll();
public List<Channel> findByCommentsNull();
}
/*
 * 
 *C:\Users\KOKODAYO>D:
   D:\>cd D:\spring\manggotibi\mongodb-win32-x86_64-2012plus-4.2.5\bin
  D:\spring\manggotibi\mongodb-win32-x86_64-2012plus-4.2.5\bin>mongod.exe --dupath="D:\spring\manggodate"

 * 
 * */
 