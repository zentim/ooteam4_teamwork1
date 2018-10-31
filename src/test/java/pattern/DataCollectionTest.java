package test.java.pattern;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Product;
import main.java.model.bean.Segment;
import main.java.model.bean.Brand;
import main.java.model.bean.Category;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.SegmentDAO;
import main.java.model.dao.BrandDAO;
import main.java.model.dao.CategoryDAO;
import main.java.pattern.composite.*;
/**
 * 
 * Test Composite Pattern and Iterator Pattern
 * (Need to connect mysql)
 *
 */
public class DataCollectionTest {
	public static SegmentDAO segmentdao = new SegmentDAO();
	public static Segment segment;
	public static int segmentId;
	
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category;
	public static int categoryId;
	
	public static BrandDAO branddao = new BrandDAO();
	public static Brand brand;
	public static int brandId;

	public static ProductDAO productdao = new ProductDAO();
	public static Product product;
	public static int productId;

	@BeforeClass
	public static void testAdd() {
		// create segment
		segment = new Segment();
		segment.setName("XXX Segment");
		segmentId = segmentdao.add(segment);

		// create category
		category = new Category();
		category.setName("OOO Category");
		category.setSegment(segmentdao.get(segmentId));
		categoryId = categorydao.add(category);

		// create brand
		brand = new Brand();
		brand.setName("OOXX Brand");
		brand.setCategory(categorydao.get(categoryId));
		brandId = branddao.add(brand);

		System.out.println("Test Start...");

		// create product
		product = new Product();
		product.setName("OXOX Product");
		product.setInventory(5);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setBrand(branddao.get(brandId));
		productId = productdao.add(product);
	}
	
	@Test
	public void testCompositePattern() {
		System.out.println("=== testCompositePattern");
		Component productComponent = productdao.get(productId);
		Component brandComponent = branddao.get(brandId);
		Component categoryComponent = categorydao.get(categoryId);
		Component segmentComponent = segmentdao.get(segmentId);
		
		brandComponent.add(productComponent);
		categoryComponent.add(brandComponent);
		segmentComponent.add(categoryComponent);
		segmentComponent.operation();
	}

	@AfterClass
	public static void testDelete() {
		// delete product
		productdao.delete(productId);

		System.out.println();
		System.out.println("Test End...");

		// delete brand
		branddao.delete(brandId);
		
		// delete category
		categorydao.delete(categoryId);
		
		// delete segment
		segmentdao.delete(segmentId);
	}

}
