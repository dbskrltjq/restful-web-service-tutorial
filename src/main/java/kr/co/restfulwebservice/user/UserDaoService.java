package kr.co.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
	private static List<User> users = new ArrayList<>();		// DB용도로 사용
	
	private static int usersCount = 3;
	
	static {
		users.add(new User(1, "Kenneth", new Date(), "pass1", "701010-1111111"));
		users.add(new User(2, "Alice", new Date(), "pass2", "901010-1112222"));
		users.add(new User(3, "Elena", new Date(), "pass3", "801010-2211111"));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator(); 
		
		while(iterator.hasNext()) {	// 순차적으로 하나씩의 데이터를 가져온다.
			User user = iterator.next();
			
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;		// 해당 id가 존재하지 않는 경우
	}
	
	public User updateUser(User user) {
		User existUser = findOne(user.getId());
		
		if(existUser != null) {
			existUser.setName(user.getName());
			existUser.setJoinDate(user.getJoinDate());
			return user;
		}
		
		return null;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
}
