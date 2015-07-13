package data;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.DemoOrder;
import model.DemoOrderItem;
import myTools.DBUtil;

public class DemoOrderDB 
{
	public static boolean insertOrder(DemoOrder order)
	{
		BigDecimal total = new BigDecimal(0);
		BigDecimal quantity = new BigDecimal(0);
		BigDecimal price = new BigDecimal(0);
		BigDecimal linePrice = new BigDecimal(0);
		BigDecimal testPrice = new BigDecimal(0);
		
		Date date = new Date();
		
		for (DemoOrderItem oi : order.getDemoOrderItems())
		{
			quantity = oi.getQuantity();
			price = oi.getDemoProductInfo().getListPrice();
			linePrice = price.multiply(quantity);
			oi.setUnitPrice(price);
			testPrice = oi.getUnitPrice();
			//oi.setUnitPrice(oi.getQuantity().multiply(oi.getProduct().getListPrice()));
			total = total.add(oi.getQuantity().multiply(oi.getDemoProductInfo().getListPrice()));
		}
		
		order.setOrderTotal(total);
		order.setOrderTimestamp(date);
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try
		{
			trans.begin();
			em.persist(order);
			trans.commit();
			return true;
		}
		catch (Exception e)
		{
			trans.rollback();
			System.out.println("A problem occurred while inserting the order: " + e);
			return false;
		}
		finally
		{
			em.close();
		}
		
	}
	
	public static BigDecimal presetOrderTotal(DemoOrder order)
	{
		BigDecimal total = new BigDecimal(0);
		BigDecimal quantity = new BigDecimal(0);
		BigDecimal price = new BigDecimal(0);
		BigDecimal linePrice = new BigDecimal(0);
		BigDecimal testPrice = new BigDecimal(0);
		
		for (DemoOrderItem oi : order.getDemoOrderItems())
		{
			quantity = oi.getQuantity();
			price = oi.getDemoProductInfo().getListPrice();
			linePrice = price.multiply(quantity);
			oi.setUnitPrice(price);
			testPrice = oi.getUnitPrice();
			//oi.setUnitPrice(oi.getQuantity().multiply(oi.getProduct().getListPrice()));
			total = total.add(oi.getQuantity().multiply(oi.getDemoProductInfo().getListPrice()));
		}
		
		order.setOrderTotal(total);
		return total;
	}
	
	public static long insertOrderReturnId(DemoOrder order)
	{
		BigDecimal total = new BigDecimal(0);
		BigDecimal quantity = new BigDecimal(0);
		BigDecimal price = new BigDecimal(0);
		BigDecimal linePrice = new BigDecimal(0);
		BigDecimal testPrice = new BigDecimal(0);
		long id = 0;
		
		Date date = new Date();
		
		for (DemoOrderItem oi : order.getDemoOrderItems())
		{
			quantity = oi.getQuantity();
			price = oi.getDemoProductInfo().getListPrice();
			linePrice = price.multiply(quantity);
			oi.setUnitPrice(price);
			testPrice = oi.getUnitPrice();
			//oi.setUnitPrice(oi.getQuantity().multiply(oi.getProduct().getListPrice()));
			total = total.add(oi.getQuantity().multiply(oi.getDemoProductInfo().getListPrice()));
		}
		
		order.setOrderTotal(total);
		order.setOrderTimestamp(date);
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try
		{
			trans.begin();
			em.persist(order);
			em.flush();
			id = order.getOrderId();
			trans.commit();
			return id;
		}
		catch (Exception e)
		{
			trans.rollback();
			System.out.println("A problem occurred while inserting the order: " + e);
			return -1;
		}
		finally
		{
			em.close();
		}	
	}
	
	public static DemoOrder getOrderById(long orderId)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		try 
		{
			DemoOrder order = em.find(DemoOrder.class, orderId);
			return order;
		}
		catch (Exception e)
		{
			System.out.println("An error occurred in the getOrderById method: " + e);
			return null;
		}
		finally
		{
			em.close();
		}
	}
}
