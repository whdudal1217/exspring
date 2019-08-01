package kr.ac.hit.myapp.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper //mybatis-spring 연동 모듈이 이 인터페이스를 찾아서 구현체를 생성하도록 표시
public interface MemberDao {
	public int insert(memberVo vo);
	public List<memberVo> selectList() ;
	public memberVo select(String memId) ;
	public int update(memberVo vo);
	public int delete(String memId);
	public memberVo selectLoginUser(memberVo vo);
}
