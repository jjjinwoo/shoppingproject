package com.edu.common.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.product.dao.ProductDAO;
import com.edu.product.dto.ImagesDTO;

@Controller
@RequestMapping(value="/image")
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Resource(name="uploadPath")
	String uploadPath; //c:/data/upload
	
	@Inject
	ProductDAO productDAO;
	
	//파일의 확장자를 이용해 mediaType을 가져오기 위한 변수
	private static Map<String, MediaType> mediaMap; //제공되는 미디어 타입
	static { //초기화 블록
		mediaMap = new HashMap<String, MediaType>();
		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	//상품코드 혹은 이미지 이름에 해당하는 파일 뿌려주기
	@RequestMapping(value="/displayImage", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage(String name) throws Exception {
		//logger.info("ImageController의 displayImgae 불러오기....");
		System.out.println("ImageController의 displayImage 불러오기.... 이름: " + name);
		
		//들어온 이름이 코드면 첫번째 이미지 이름으로 바꿔주고 확장자를 붙여준다.
		if(name.length() == 4) {
			name = name + "-1.jpg";
		}
		//System.out.println("파일 이름: " + name);
		
		String path;
		
		//이름 앞글자에 따라 경로를 지정해준다.
		if(name.substring(0, 1).equals("b")) {
			path = uploadPath + "/product/bottom/";
		} else if(name.substring(0, 1).equals("t")) {
			path = uploadPath + "/product/top/";
		} else if(name.substring(0, 1).equals("o")) {
			path = uploadPath + "/product/outer/";
		} else {
			path = uploadPath + "/product/acc/";
		}
		
		//System.out.println("파일에 따른 경로: " + path);
		
		ResponseEntity<byte[]> entity = null;
		InputStream inputStream = null;
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			
			inputStream = new FileInputStream(path + name);
			
			String extentionName = name.substring(name.lastIndexOf(".") + 1); //확장자
			//System.out.println("확장자: " + extentionName);
			MediaType mType = mediaMap.get(extentionName.toUpperCase()); //확장자로 미디어타입을 가져온다
			headers.setContentType(mType); //가져온 미디어 타입으로 세팅
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), headers, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(inputStream != null) inputStream.close();
		}
		
		return entity;
	}
	
	//넘어온 파일 UploadFileUtils 클래스의 메소드를 이용해 업로드하고, 상품코드로 이미지 데이터 저장하기
	@ResponseBody
	@RequestMapping(value="/uploadImage", method=RequestMethod.POST)
	public ResponseEntity<ImagesDTO> uploadImage(MultipartFile[] files, String product_code) throws Exception {
		System.out.println("ImageController의 uploadImage 불러오기.... 상품 코드: " + product_code + ", 파일이름: " + files[0].getOriginalFilename());
		
		//메소드를 이용해 이미지 업로드 후 ImagesDTO 타입을 받는다.
		ImagesDTO imagesDTO = UploadFileUtils.uploadImage(uploadPath, files, product_code);
		
		//imagesDTO에 상품 코드를 세팅한다.
		imagesDTO.setProduct_code(product_code);
		
		//imagesDTO로 데이터에 등록한다.
		int result = productDAO.imagesRegister(imagesDTO);
		if(result == 1) {
			System.out.println("db에 이미지 데이터 등록 성공");
		}
		
		return new ResponseEntity<ImagesDTO>(imagesDTO, HttpStatus.OK);
	}
	
	//이미지 삭제 하기
	@RequestMapping(value="/deleteImage", method=RequestMethod.POST)
	public void deleteImage(String product_code) throws Exception {
		System.out.println("ImageController의 deleteImage 불러오기.... 상품 코드: " + product_code);	
		
		//상품 코드에 해당하는 ImageDTO를 가져온다.(이미지 이름 데이터)
		ImagesDTO imagesDTO = productDAO.getImagesName(product_code);
		System.out.println("imagesDTO: " + imagesDTO);
		
		//상품 코드에 따라 경로를 세팅한다.
		String savedPath = UploadFileUtils.calculatePath(uploadPath, product_code);
		
		//반복문을 통해 해당 파일을 삭제한다.
		if(imagesDTO != null) {
			for(int i = 0; i < 5; i++) {
				if(imagesDTO.getImages01() != null) new File(savedPath + imagesDTO.getImages01()).delete();
				if(imagesDTO.getImages02() != null) new File(savedPath + imagesDTO.getImages02()).delete();
				if(imagesDTO.getImages03() != null) new File(savedPath + imagesDTO.getImages03()).delete();
				if(imagesDTO.getImages04() != null) new File(savedPath + imagesDTO.getImages04()).delete();
				if(imagesDTO.getImages05() != null) {
					new File(savedPath + imagesDTO.getImages05()).delete();
					System.out.println(savedPath + imagesDTO.getImages05() + " 파일 삭제함");
				}
			}
		}
		
	}	
	
	//상품 코드에 해당하는 이미지 파일을 삭제하고 
	//넘어온 파일 UploadFileUtils 클래스의 메소드를 이용해 업로드하고, 상품코드로 이미지 데이터 저장하기
	@ResponseBody
	@RequestMapping(value="/updateImage", method=RequestMethod.POST)
	public ResponseEntity<ImagesDTO> updateImage(MultipartFile[] files, String product_code) throws Exception {
		System.out.println("ImageController의 updateImage 처리하기.... 상품 코드: " + product_code + ", 파일이름: " + files[0].getOriginalFilename());
		
		//메소드를 이용해 해당 상품의 이미지 파일을 먼저 전부 삭제한다.
		deleteImage(product_code);
		
		//메소드를 이용해 이미지 업로드 후 ImagesDTO 타입을 받는다.
		ImagesDTO imagesDTO = UploadFileUtils.uploadImage(uploadPath, files, product_code);
		
		//imagesDTO에 상품 코드를 세팅한다.
		imagesDTO.setProduct_code(product_code);
		
		//imagesDTO로 데이터에 등록한다.
		int result = productDAO.imagesUpdate(imagesDTO);
		if(result == 1) {
			System.out.println("db에 이미지 데이터 등록 성공");
		}
		
		return new ResponseEntity<ImagesDTO>(imagesDTO, HttpStatus.OK);
	}
}
