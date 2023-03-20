package com.edu.product.dao;

import java.util.List;

import com.edu.common.util.ArrayCriteria;
import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;

public interface ProductDAO {
	
	public List<ProductDTO> productList() throws Exception;

	public List<ProductDTO> productTypeList(ArrayCriteria aCri) throws Exception;
	
	public int totalCount(ArrayCriteria aCri) throws Exception;
	
	public ProductDTO productDetail(String pdCode) throws Exception;
	
	public ImagesDTO getImagesName(String pdCode) throws Exception;
	
	public List<ProductDTO> productSearchList(SearchCriteria sCri) throws Exception;
	
	public int searchCount(SearchCriteria sCri) throws Exception;
	
	public String getProductCode(String product_type) throws Exception;
	
	public int productRegister(ProductDTO productDTO) throws Exception;
	
	public int imagesRegister(ImagesDTO imagesDTO) throws Exception;
	
	public int productDelete(String product_code) throws Exception;
	
	public int productUpdate(ProductDTO productDTO) throws Exception;
	
	public int imagesUpdate(ImagesDTO imagesDTO) throws Exception;
}
