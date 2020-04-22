package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ChannelService;
import com.example.model.Channel;
@RestController
@RequestMapping("/channel")
public class ChannelConterller {
	@Autowired
	private ChannelService service;
	
	
	@GetMapping
	public List<Channel> getAllChannl(){
		List<Channel> results=service.getAllChannels();
		return results;
		
	}
	@GetMapping("/{id}")
	public Channel getChannel(@PathVariable String id) {
		System.out.println("获取频道id="+id);
		Channel c =service.getChannel(id);
		if(c!=null) {
			return c;
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteChannel(@PathVariable String id){
		System.out.print("即将删除频道id="+id);
		boolean result= service.deleteChannel(id);
		if(result) {
			return ResponseEntity.ok().body("删除成功");
		}else {
			return ResponseEntity.ok().body("删除失败");
		}
		
	}
	@PostMapping
	public Channel createChannel(@RequestBody Channel c) {
		System.out.println("即将添加频道，频道数据"+c);
		Channel saved=service.createChannel(c);
		return saved;
		
	}
	
	@PutMapping
	public Channel updateChannel(@RequestBody Channel c) {
		System.out.println("即将更新频道，频道数据"+c);
		Channel updated=service.updateChannel(c);
		return updated;
		
	}
	@GetMapping("/q/{quality}")
	public List<Channel> searchByQuality(@PathVariable String quality){
		return service.searchByQuality(quality);
	}
	@GetMapping("/t/{title}")
	public List<Channel> searchByTitle(@PathVariable String title){
		return service.searchByTitle(title);
	}
	@GetMapping("/cold")
	public List<Channel> getColdChannels(){
		return service.findColdChannels();
	}
	@GetMapping("/p/{page}")
	public List<Channel> getChannelsPage(@PathVariable int page){
		return service.findChannelsPage(page);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}