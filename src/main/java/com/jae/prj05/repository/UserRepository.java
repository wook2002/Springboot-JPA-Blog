package com.jae.prj05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jae.prj05.model.User;


// ������ IoC���� ��ü�� ������ �ֳ���? (=������ ����� �ǳ���?) => �׷��� DI��
// DAO (jsp�� ġ��)(Data Access Object)
// �ڵ����� bean���
@Repository  //  �ڵ����� bean��ϵ�, ��������
public interface UserRepository extends JpaRepository<User, Integer>{
//public interface UserRepository extends JpaRepository<T, ID>{

	
// -- extends JpaRepository (F3 or F4 ������ ���� ����)--
//	 => ������ �ִ� �͵� �� �� �� ����.
	
//	public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
//	...
//	@Override
//	List<T> findAll(); // UserTable ��� ����(���Ÿ��)
//	(~~Returns all instances of the type.~~)
// �� (�� ������)

//	public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
//	Iterable<T> findAll(Sort sort);  //������ ���� 
//	Page<T> findAll(Pageable pageable); //������ ����¡
// �� (�� ������)
	
//	public interface CrudRepository<T, ID> extends Repository<T, ID> {
//	<S extends T> S save(S entity);  
// (Saves a given entity.~�Ӷ�Ӷ�~) => // save() : insert, update �� �� ����
//	��� 



	
}
