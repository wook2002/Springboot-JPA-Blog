package com.jae.prj05.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jae.prj05.model.User;

//public interface UserRepository extends JpaRepository<T, ID>{
// 스프링 IoC에서 객체를 가지고 있나요? (=빈으로 등록이 되나요?) => 그래야 DI됨
// DAO (jsp로 치면)(Data Access Object)
// 자동으로 bean등록
@Repository  //  자동으로 bean등록됨, 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// SELECT * FROM user WHERE username = ?;
	Optional<User> findByUsername(String username);
	
}
// -- extends JpaRepository (F3 or F4 들어가보면 저거 있음)--
//	 => 들어가보면 있는 것들 다 쓸 수 있음.
	
//	public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
//	...
//	@Override
//	List<T> findAll(); // UserTable 모두 리턴(모든타입)
//	(~~Returns all instances of the type.~~)
// ↓ (또 들어가보면)

//	public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
//	Iterable<T> findAll(Sort sort);  //딱봐도 정렬 
//	Page<T> findAll(Pageable pageable); //딱봐도 페이징
// ↓ (또 들어가보면)
	
//	public interface CrudRepository<T, ID> extends Repository<T, ID> {
//	<S extends T> S save(S entity);  
// (Saves a given entity.~머라머라~) => // save() : insert, update 쓸 수 있음
//	등등 

	// "JPA Naming 쿼리" 전략----------
	// ex) 리턴타입 findByUsernameAndPassword();
	//  => SELECT * FROM user WHERE username = ? AND password = ?
	
	// m1) 쿼리가 간단할 때 
//	User findByUsernameAndPassword(String username, String password);
	// SELECT * FROM user WHERE username = username AND password = password
	
	// m2) 
//	@Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password);
	
//}