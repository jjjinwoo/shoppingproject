package com.edu.product.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.common.util.ArrayCriteria;
import com.edu.common.util.Criteria;
import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;
import com.edu.product.service.ProductServiceImpl;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static String Namespace = "com.edu.product";
	
	@Override
	public List<ProductDTO> productList() throws Exception {
		logger.info("ProductDAOImpl의 productList 불러오기....");
		
		return sqlSession.selectList(Namespace + ".allList");
	}

	@Override
	public List<ProductDTO> productTypeList(ArrayCriteria aCri) throws Exception {
		logger.info("ProductDAOImpl의 productTypeList 불러오기....");
		
		return sqlSession.selectList(Namespace + ".typeList", aCri);
		
	}

	@Override
	public int totalCount(ArrayCriteria aCri) throws Exception {
		logger.info("ProductDAOImpl의 totalCount 불러오기....");
		
		return sqlSession.selectOne(Namespace + ".totalCount", aCri);
	}

	@Override
	public ProductDTO productDetail(String pdCode) throws Exception {
		System.out.println("ProductDAOImpl의 productDetail 불러오기....");
		
		return sqlSession.selectOne(Namespace + ".detail", pdCode);
	}

	@Override
	public ImagesDTO getImagesName(String pdCode) throws Exception {
		System.out.println("ProductDAOImpl의 getImagesName 불러오기....");
		
		return sqlSession.selectOne(Namespace + ".images", pdCode);
	}

	@Override
	public List<ProductDTO> productSearchList(SearchCriteria sCri) throws Exception {
		System.out.println("ProductDAOImpl의 productSearchList 불러오기....");
		
		return sqlSession.selectList(Namespace + ".searchList", sCri);
	}

	@Override
	public int searchCount(SearchCriteria sCri) throws Exception {
		System.out.println("ProductDAOImpl의 searchCounts 불러오기....");
		
		return sqlSession.selectOne(Namespace + ".searchCount", sCri);
	}

	@Override
	public String getProductCode(String product_type) throws Exception { //가장 최근 상품 코드 구하기
		System.out.println("ProductDAOImpl의 getProductCode 불러오기....");
		
		return sqlSession.selectOne(Namespace + ".getProductCode", product_type);
	}

	@Override
	public int productRegister(ProductDTO productDTO) throws Exception {
		System.out.println("ProductDAOImpl의 productRegister 불러오기....");
		
		return sqlSession.insert(Namespace + ".productRegister", productDTO);
	}

	@Override
	public int imagesRegister(ImagesDTO imagesDTO) throws Exception {
		System.out.println("ProductDAOImpl의 imagesRegister 불러오기....");
		
		return sqlSession.insert(Namespace + ".imagesRegister", imagesDTO);
	}
	
	@Override
	public int imagesUpdate(ImagesDTO imagesDTO) throws Exception {
		System.out.println("ProductDAOImpl의 imagesRegister 불러오기....");
		
		return sqlSession.insert(Namespace + ".imagesUpdate", imagesDTO);
	}

	@Override
	public int productDelete(String product_code) throws Exception {
		logger.info("ProductDAOImpl의 productDelete 불러오기....");
		
		return sqlSession.delete(Namespace + ".productDelete", product_code);
	}

	@Override
	public int productUpdate(ProductDTO productDTO) throws Exception {
		System.out.println("ProductDAOImpl의 productUpdate 불러오기....productDTO : " + productDTO);
		
		return sqlSession.update(Namespace + ".productUpdate", productDTO);
	}

}
