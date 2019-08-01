package kr.ac.hit.myapp.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Repository;

//@Repository
public class MemberDaoBatis implements MemberDao {

	@Resource
	private SqlSession sqlSession; //MyBatis의 본체

	@Override
	public int insert(memberVo vo) {
		// MyBatis를 사용해서 insert 쿼리문을 실행하도록 구현 (MyBatis 포함을 먼저 해야함)		
	/*	MyBatis를 사용하여 insert 쿼리문을 실행하도록 구현
		실행하려는 sql문에 맞는 SqlSession 객체의 메서드를 사용하여 실행*/
		//sql id가 같은 경우가 있기 때문에 매퍼파일의 매퍼 네임스페이스를 붙여줘야 합니다.
		return  sqlSession.insert("kr.ac.hit.myapp.member.MemberDao.insert" , vo);
	}

	@Override
	public List<memberVo> selectList() {
		
		return sqlSession.selectList("kr.ac.hit.myapp.member.MemberDao.selectList");
	}

	@Override
	public memberVo select(String memId) {
		return sqlSession.selectOne("실행할 sql문 id", memId);
	}

	@Override
	public int update(memberVo vo) {
		return sqlSession.update("실행할 sql문 id", vo);
	}

}
