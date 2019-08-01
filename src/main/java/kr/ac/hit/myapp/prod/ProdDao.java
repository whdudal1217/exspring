package kr.ac.hit.myapp.prod;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProdDao {

	int insert(ProdVo vo);

	List<ProdVo> selectList();

	ProdVo select(int prodNo);

	int update(ProdVo vo);

	int delete(int prodNo);
}
