package kr.ac.hit.myapp.member;

import java.io.File;
import java.util.List;

import kr.ac.hit.myapp.comm.MemSearchInfo;

public interface MemberService {
	public int insert(memberVo vo);
	
	public List<memberVo> selectList(MemSearchInfo info) ;
	
	public memberVo select(String memId) ;

	public int update(memberVo vo);
	
	public int delete(String memId);

	public memberVo selectLoginUser(memberVo vo);

	public File getImgFile(memberVo vo);

	public int selectCount(MemSearchInfo info);
}
