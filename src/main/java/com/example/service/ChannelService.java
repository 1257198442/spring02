package com.example.service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.dao.ChannelRepository;
import com.example.model.Channel;
import com.example.model.Comment;
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
//private List<Channel> channels;
//public ChannelService() {
////	channels = new ArrayList<>();
////	for (int i = 0;i<10;i++) {
////		Channel c=new Channel();
////		c.setId(i+1);
////		c.setTitle("����"+(i+1));
////		c.setUrl("http://www.cctv.com");
////		channels.add(c);	
////	}
//}
public List<Channel> getAllChannels() {
	return repo.findAll();
//			this.channels;
}
public Channel getChannel(String channelId) {
Optional<Channel>result=repo.findById(channelId);
if(result.isPresent()) {
	return result.get();
	}else {
		return null;
	}

}
//	Channel result=null;
//	for (Channel c:channels) {
////		if (c.getId()==channelId) {
////			result=c;
////			break;
////		}
//	}
//	return result;
//}
	public boolean deleteChannel(String channelId) {
		boolean result=true;
		repo.deleteById(channelId);
//		Channel c=getChannel(channelId);
//		ro
//		if(c!=null) {
//			channels.remove(c);
//			result=true;	
//		}
		return result;
	}
public Channel createChannel(Channel c) {
//	int newId = channels.get(channels.size()-1).getId()+1;
//	c.setId(newId);
//	channels.add(c);
//	return c;
	return repo.save(c);
	}
	public Channel updateChannel(Channel c) {
//		Channel toUpdate=getChannel(c.getId());
//		if (toUpdate !=null) {
//			toUpdate.setTitle(c.getTitle());
//			toUpdate.setQuality(c.getQuality());
//			toUpdate.setUrl(c.getUrl());
//			
//			
//		}
//		
//		return toUpdate;
//		return null;
		Channel saved=getChannel(c.getId());
		if(c.getTitle()!=null) {
			saved.setTitle(c.getTitle());	
		}
		if(c.getQuality()!=null) {
			saved.setQuality(c.getQuality());	
		}
		if(c.getUrl()!=null) {
			saved.setUrl(c.getUrl());	
		}
		if(c.getComments()!=null) {
			if(saved.getComments()!=null) {
			   saved.getComments().addAll(c.getComments());	
		}else {
			saved.setComments(c.getComments());
		}		
	}
		if(c.getCover()!=null) {
			saved.setComments(c.getComments());
			
		}
		return repo.save(saved);
	}
	public List<Channel> searchByQuality(String quality){
		return repo.findByQuality(quality);
		
	}
	public List<Channel> searchByTitle(String title){
		return repo.findByQuality(title);
		
	}
	public List<Channel> findColdChannels(){
	return repo.findByCommentsNull();
		
	}
	public List<Channel>findChannelsPage(int page){
		Page<Channel>p=repo.findAll(PageRequest.of(page,3));
		return p.toList();
	}
	
	public Channel addcomment(String channelId, Comment comment) {
		// TODO Auto-generated method stub
		Channel result=null;
		Channel saved=getChannel(channelId);
		if(null!=saved) {
			saved.getComments().add(comment);
			result=repo.save(saved);
		}
		return result;
		
	}
	public List<Comment> hotComment(String channelId) {
		// TODO Auto-generated method stub
		List<Comment> result=null;
		Channel saved=getChannel(channelId);
		if(saved!=null) {
		result=saved.getComments();
		result.sort(new Comparator<Comment>(){
				@Override
				public int compare(Comment o1,Comment o2) {
					int re=0;
					if(o1.getStar() < o2.getStar()) {
						re=1;
					}else if (o1.getStar() > o2.getStar()) {
						re=-1;
					}
					return re;
				}
				
			});
			if(result.size()>3) {
				result=result.subList(0, 3);
				
			}else {
				
			}
			}
			return result;
		}
}














