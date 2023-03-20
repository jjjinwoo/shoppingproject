package com.edu.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.common.util.ArrayCriteria;
import com.edu.common.util.SearchCriteria;
import com.edu.product.dao.ProductDAO;
import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Inject
	ProductDAO productDAO;
	
	@Override
	public List<ProductDTO> productList() throws Exception {
		logger.info("ProductServiceImpl의 productList 불러오기....");
		
		return productDAO.productList();
	}
	
	@Override
	public List<ProductDTO> productTypeList(ArrayCriteria aCri) throws Exception {
		logger.info("ProductServiceImpl의 productTypeList 불러오기....");
		
		return productDAO.productTypeList(aCri);
	}
	
	@Override
	public int totalCount(ArrayCriteria aCri) throws Exception {
		logger.info("ProductServiceImpl의 totalCount 불러오기....");
		
		return productDAO.totalCount(aCri);
	}

	@Override
	public ProductDTO productDetail(String pdCode) throws Exception {
		System.out.println("ProductServiceImpl의 productDetail 불러오기....");
		
		return productDAO.productDetail(pdCode);
	}

	@Override
	public List<String> getImagesName(String pdCode) throws Exception {
		System.out.println("ProductServiceImpl의 getImagesName 불러오기....코드: " + pdCode);
		
		List<String> imagesList = new ArrayList<String>();
		
		ImagesDTO imagesDTO = productDAO.getImagesName(pdCode);
		
		if(imagesDTO.getImages01() != null) imagesList.add(imagesDTO.getImages01());
		if(imagesDTO.getImages02() != null) imagesList.add(imagesDTO.getImages02());
		if(imagesDTO.getImages03() != null) imagesList.add(imagesDTO.getImages03());
		if(imagesDTO.getImages04() != null) imagesList.add(imagesDTO.getImages04());
		if(imagesDTO.getImages05() != null) {
			System.out.println("5번째 사진이 null이 아님");
			imagesList.add(imagesDTO.getImages05());
		}
		
		return imagesList;
	}

	@Override
	public List<ProductDTO> productSearchList(SearchCriteria sCri) throws Exception {
		System.out.println("ProductServiceImpl의 productSearchList 불러오기....");
		
		return productDAO.productSearchList(sCri);
	}

	@Override
	public int searchCount(SearchCriteria sCri) throws Exception {
		System.out.println("ProductServiceImpl의 searchCount 불러오기....");
		
		return productDAO.searchCount(sCri);
	}

	@Override
	public String productRegister(ProductDTO productDTO) throws Exception {
		System.out.println("ProductServiceImpl의 productRegister 불러오기....");
		
		//dao를 통해 해당 타입의 가장 최근 상품 코드를 가져온다.
		String maxCode = productDAO.getProductCode(productDTO.getProduct_type());
		
		//최근 상품 코드를 이용해 새로운 코드를 만든다.
		String front = maxCode.substring(0, 1);
		String back = maxCode.substring(1);
		
		int Iback = Integer.parseInt(back) + 1;
		System.out.println(Iback);
		
		String product_code = "";
		
		if((int)(Math.log10(Iback)+1) == 1) {
			product_code = front + "00" + Integer.toString(Iback);
			System.out.println(product_code);
		} else if((int)(Math.log10(Iback)+1) == 2){
			product_code = front + "0" + Integer.toString(Iback);
			System.out.println(product_code);			
		} else {
			product_code = front + Integer.toString(Iback);
			System.out.println(product_code);				
		}
		
		//productDTO에 상품 코드를 세팅해준다.
		productDTO.setProduct_code(product_code);
		
		//dao를 통해 상품을 등록한다.
		int result = productDAO.productRegister(productDTO);
		
		if(result <= 0) { //실패하면
			System.out.println("상품 등록 실패");
			return "0"; 
		} else { //상품 등록에 성공하면
			return product_code; //상품 코드를 반환
		}
		
	}

	@Override
	public int productDelete(String product_code) throws Exception {
		System.out.println("ProductServiceImpl의 productDelete 불러오기....");
		
		return productDAO.productDelete(product_code);
	}

	@Override
	public String productUpdate(ProductDTO productDTO) throws Exception {
		System.out.println("ProductServiceImpl의 productUpdate 불러오기....");
		
		//등록에 성공하면 상품 코드를 반환해준다.
		if(productDAO.productUpdate(productDTO) == 1) {
			return productDTO.getProduct_code();
		} else {
			return "0";
		}
	}

}
