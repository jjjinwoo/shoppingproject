package com.edu.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.product.dto.CartDTO;
import com.edu.product.dto.OrderDTO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static String Namespage = "com.edu.product";
	
	@Override
	public List<CartDTO> cartList(String userID) throws Exception {
		System.out.println("OrderDAOImpl의 cartList()....");
		
		return sqlSession.selectList(Namespage + ".cartList", userID);
	}

	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		System.out.println("OrderDAOImpl의 addCart()....");
		
		return sqlSession.insert(Namespage + ".addCart", cartDTO);
	}

	@Override
	public CartDTO cartNumList(int cartNum) throws Exception {
		System.out.println("OrderDAOImpl의 cartNumList().... cartNum: " + cartNum);
		
		return sqlSession.selectOne(Namespage + ".cartNumList", cartNum);
	}

	@Override
	public int getCartNum(CartDTO cartDTO) throws Exception {
		System.out.println("OrderDAOImpl의 getCartNum()....");
		
		return sqlSession.selectOne(Namespage + ".getCartNum", cartDTO);
	}

	@Override
	public int cartCount(CartDTO cartDTO) throws Exception {
		System.out.println("OrderDAOImpl의 cartCount()....");
		
		return sqlSession.selectOne(Namespage + ".cartCount", cartDTO);
	}

	@Override
	public int cartDelete(int cartNum) throws Exception {
		System.out.println("OrderDAOImpl의 cartDelete()....");
		
		return sqlSession.delete(Namespage + ".cartDelete", cartNum);
	}
	
	@Override
	public int cartDeleteByCode(String product_code) throws Exception {
		System.out.println("OrderDAOImpl의 cartDeleteByCode()....");
		
		return sqlSession.delete(Namespage + ".cartDeleteByCode", product_code);
	}
	
	@Override
	public int orderDateCount(String date) throws Exception {
		System.out.println("OrderDAOImpl의 orderDateCount()....");
		
		return sqlSession.selectOne(Namespage + ".orderDateCount", date);
	}

	@Override
	public long getMaxOrderNum() throws Exception {
		System.out.println("OrderDAOImpl의 getMaxOrderNum()....");
		
		return sqlSession.selectOne(Namespage + ".getMaxOrderNum");
	}
	
	@Override
	public int addOrder(OrderDTO orderDTO) throws Exception {
		System.out.println("OrderDAOImpl의 addOrder()....");
		
		return sqlSession.insert(Namespage + ".addOrder", orderDTO);
		
	}
	
	@Override
	public int addOrderProduct(OrderDTO orderDTO) throws Exception {
		System.out.println("OrderDAOImpl의 addOrderProduct()....");
		
		return sqlSession.insert(Namespage + ".addOrderProduct", orderDTO);
	}
	
	@Override
	public OrderDTO getOrderDTO(long order_num) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDTO()....");
		
		return sqlSession.selectOne(Namespage + ".getOrderDTO", order_num);
	}
	
	@Override
	public OrderDTO getOrderDetailDTO(OrderDTO orderDTO) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDetailDTO()....");
		
		return sqlSession.selectOne(Namespage + ".getOrderDetailDTO", orderDTO);
	}
	
	@Override
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDetail()....");
		
		
		return sqlSession.selectList(Namespage + ".getOrderDTODetail", order_num);
	}

	@Override
	public List<Long> getOrderNum(String userID) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderNum()....");
		
		return sqlSession.selectList(Namespage + ".getOrderNum", userID);
	}

	@Override
	public List<OrderDTO> getOrderList(List<Long> orderNumList) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderList()....");
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		
		//반복문을 통해 orderDTO를 가지고 와서 리스트에 담는다.
		for(int i = 0; i < orderNumList.size(); i++) {
			OrderDTO orderDTO = sqlSession.selectOne(Namespage + ".getOrderDTO", orderNumList.get(i));
			orderList.add(i, orderDTO);
		}
		
		return orderList;
	}

	@Override
	public int cartUpdate(CartDTO cartDTO) throws Exception {
		System.out.println("OrderDAOImpl의 cartUpdate()....");
		
		return sqlSession.update(Namespage + ".cartUpdate", cartDTO);
	}

	@Override
	public List<OrderDTO> getOrderDetailById(String userID) throws Exception {
		System.out.println("OrderDAOImpl의 getOrderDetailById()....");
		
		return sqlSession.selectList(Namespage + ".getOrderDetailById", userID);
	}

	@Override
	public List<OrderDTO> orderTotalList() throws Exception {
		System.out.println("OrderDAOImpl의 orderTotalList()....");
		
		return sqlSession.selectList(Namespage + ".orderTotalList");
	}

	@Override
	public List<Long> monthBill(String month) throws Exception {
		System.out.println("OrderDAOImpl의 monthBill()....");
		
		return sqlSession.selectList(Namespage + ".monthBill", month);
	}

	@Override
	public Long ageMonthBill(HashMap<String, String> dataList) throws Exception {
		System.out.println("OrderDAOImpl의 ageMonthBill()....");
		
		return sqlSession.selectOne(Namespage + ".ageMonthBill", dataList);
	}

}

