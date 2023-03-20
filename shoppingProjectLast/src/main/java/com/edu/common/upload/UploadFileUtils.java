package com.edu.common.upload;

import java.io.File;
import java.util.List;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.edu.product.dto.ImagesDTO;
import com.edu.product.service.ProductService;
import com.edu.product.service.ProductServiceImpl;

//업로드 관련 메소드의 클래스
public class UploadFileUtils {

	
	//이미지 업로드 총괄 메소드 (업로드 된 파일 이름 반환)
	public static ImagesDTO uploadImage(String uploadPath, MultipartFile[] files, String product_code) throws Exception {
		System.out.println("UploadFileUtils의 uploadImage 불러오기.... 첫번째 파일 이름: " + files[0].getOriginalFilename());		
		
		//ImagesDTO준비
		ImagesDTO imagesDTO = new ImagesDTO();
		
		//파일 변수에 필요한 경로를 준비한다.
		String savedPath = calculatePath(uploadPath, product_code);
		
		
		//배열로 들어온 파일을 반복문으로 업로드 한다.
		for(int i = 0; i < files.length; i++) {
			
			//파일 이름에 붙여줄 확장자를 구한다.
			String originalName = files[i].getOriginalFilename();
			String extentionName = originalName.substring(originalName.lastIndexOf(".") + 1); // "."다음부터 끝까지 잘라낸다
			
			//파일 변수에 필요한 파일 이름
			String savedName = product_code + "-" + (i + 1) + "." + extentionName;
			
			//경로와 이름으로 파일 변수를 먼저 만든다.
			File file = new File(savedPath, savedName);
			
			//넘겨받은 이미지 데이터를 바이트 형식으로 준비한다.
			byte[] bytes = files[i].getBytes();
			
			//파일 변수에 바이트를 붙여넣는다.
			FileCopyUtils.copy(bytes, file);
			
			//저장한 파일 이름을 imagesDTO에 순서대로 넣는다.
			if(i == 0) {
				imagesDTO.setImages01(savedName);				
			} else if(i == 1) {
				imagesDTO.setImages02(savedName);
			} else if(i == 2) {
				imagesDTO.setImages03(savedName);
			} else if(i == 3) {
				imagesDTO.setImages04(savedName);
			} else {
				imagesDTO.setImages05(savedName);
			}
			System.out.println("업로드 된 이미지 이름: " + savedName);
			
		}
		return imagesDTO;
	}
	
	//이미지 삭제 메소드
	public void deleteImage(String uploadPath, String product_code) throws Exception {
		System.out.println("UploadFileUtils의 deleteImage 불러오기.... 상품 코드: " + product_code);	
		
		//상품 코드에 해당하는 ImageList를 가져온다.(이미지 이름 데이터를 List 형태로 만든 것)
		ProductService productService = new ProductServiceImpl();
		List<String> imagesList = productService.getImagesName(product_code);
		System.out.println("imagesList: " + imagesList);
		
		//상품 코드에 따라 경로를 세팅한다.
		String savedPath = calculatePath(uploadPath, product_code);
		
		//반복문을 통해 해당 파일을 삭제한다.
		for(int i = 0; i < imagesList.size(); i++) {
			if(imagesList.get(i) != null) {
				new File(savedPath + imagesList.get(i)).delete();
				System.out.println(imagesList.get(i) + "파일 삭제함");
			}
		}
	}
	
	//---------------------------------------------------------
	//상품 코드를 이용해 경로를 계산하고 만드는 메소드(최종 경로 이름 반환)
	public static String calculatePath(String uploadPath, String product_code) {
		System.out.println("UploadFileUtils의 calculatePath() 시작....");
		
		//카테고리 경로
		String savedPath = "";
		
		//상품 코드 첫글자에 따른 경로 준비
		if(product_code.substring(0, 1).equals("t")) {
			savedPath  = uploadPath + "/product/top/";
		} else if(product_code.substring(0, 1).equals("b")) {
			savedPath  = uploadPath + "/product/bottom/";
		} else if(product_code.substring(0, 1).equals("o")) {
			savedPath  = uploadPath + "/product/outer/";
		} else {
			savedPath  = uploadPath + "/product/acc/";
		}
		
		//해당 경로가 이미 존재할 경우
		if(new File(savedPath).exists()) {
			System.out.println("이미 경로 있음");
			return savedPath;	//경로를 만들지 않고 경로 이름 반환
		} else { //경로가 없을 경우
			
			//경로를 만들어준다. mkdirs():지정한 경로 밑에 없는 폴더까지 만들어준다.
			new File(savedPath).mkdirs();
			System.out.println("경로 생성함");
			return savedPath;
			
		}
	}
}
