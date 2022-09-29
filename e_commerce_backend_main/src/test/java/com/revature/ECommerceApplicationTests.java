package com.revature;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.ProductService;
import com.revature.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ECommerceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@BeforeEach
	void setUp()
	{
		this.authService
				= new AuthService(this.userService,this.userRepository);
		this.userService
				= new UserService(this.userRepository);
		this.productService
				= new ProductService(this.productRepository);
	}

	/*










	AuthService and UserService Testing







	 */
	//David Charles Test
	@Test void getAllUsers()
	{
		List<User> actualResult = authService.findAll();
		assertThat(actualResult).isNotNull();
		System.out.println(actualResult);
	}

	//Mark Pearcy Test
	@Test
	void authServiceTestFindByCredentials() {
		User user = new User("mark@gmail.com","password", "firstName", "lastName", "address", false);
		userRepository.save(user);
		Optional<User> actualResult = authService.findByCredentials(user.getEmail(),user.getPassword());
		Optional<User> optionalUser = userService.findByCredentials("mark@gmail.com","password");
		System.out.println(optionalUser);
		System.out.println(optionalUser);
		assertThat(actualResult).isEqualTo(optionalUser);
		userRepository.delete(user); //clean up database
	}

	//Eric Curiel Test
	@Test
	void testRegisterUser() {
		User user = new User("eric@gmail.com","password", "firstName", "lastName", "address", false);
		userRepository.save(user);
		User actualResult = authService.register(user);
		User optionalUser = userService.save(user);
		System.out.println(optionalUser);
		System.out.println(actualResult);
		assertThat(actualResult).isEqualTo(optionalUser);
		userRepository.delete(user); //clean up database
	}

	//Isaiah Jordan Test
	@Test
	void testUpdateUserProfile(){
		User user = new User(5,"testuser@gmail.com","password", "TestTest", "UserUser", "123 Revature Lane", true);
		authService.updateUserProfile(user);
		Optional<User> actualResult = authService.findByCredentials(user.getEmail(),user.getPassword());
		System.out.println(actualResult);
		assertThat(actualResult.get().getFirstName()).isEqualTo("TestTest");
		assertThat(actualResult.get().getLastName()).isEqualTo("UserUser");
	}

	//Duy Nguyen Test
	@Test
	void testUpdateAdmin(){
		User user = new User(5,"testuser@gmail.com","password", "TestTest", "UserUser", "123 Revature Lane", true);
		int pk = authService.updateAdmin(user);
		boolean actualResult = pk > 0;
		assertThat(actualResult).isTrue();
	}

	//Chris Jurgens Test
	@Test
	void testResetPassword(){
		User user = new User(5,"testuser@gmail.com","password", "TestTest", "UserUser", "123 Revature Lane", true);
		int pk = authService.resetPassword(user);
		boolean actualResult = pk > 0;
		assertThat(actualResult).isTrue();
	}

	//Tyler Marquez Test
	@Test
	void userServiceTestFindByCredentials() {
		User user = new User("mark@gmail.com","password", "firstName", "lastName", "address", false);
		userRepository.save(user);
		Optional<User> optionalUser = authService.findByCredentials(user.getEmail(),user.getPassword());
		Optional<User> actualResult = userService.findByCredentials("mark@gmail.com","password");
		assertThat(actualResult).isEqualTo(optionalUser);
		userRepository.delete(user); //clean up database
	}

	//Peter Pottoroff Test
	@Test
	void testSave(){
		User user = new User("eric@gmail.com","password", "firstName", "lastName", "address", false);
		User actualResult = userService.save(user);
		System.out.println(actualResult);
		assertThat(actualResult).isNotNull();
		userRepository.delete(user); //clean up database
	}

	/*



	ProductService Testing



	 */

	//Charell Davis Test
	@Test
	void productServiceTestFindAll()
	{
		List<Product> actualResult = productService.findAll();
		assertThat(actualResult).isNotNull();
		System.out.println(actualResult);
	}

	//Li Villaranda Test
	@Test
	void productServiceTestFindById() {
		Optional<Product> actualResult = productService.findById(3);
		System.out.println(actualResult);
		assertThat(actualResult).isNotNull();
	}

	//Matthew Sinnemaki Test
	@Test
	void productServiceTestSave(){
		Product product = new Product(7, 47, "Sting T-Shirt", "https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png", "TeeShirt", "More Details");
		Product actualResult = productService.save(product);
		System.out.println(actualResult);
		assertThat(actualResult).isNotNull();
		productRepository.delete(product); //clean up database
	}

	//DeeAnne Freeman Test
	@Test
	void productServiceTestUpdateProduct(){
		Product product = new Product(7, 47, "Sting T-Shirt", "https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png", "TeeShirt", "Awesome T");
		productService.save(product);
		int pk = productRepository.updateProduct(product.getDescription(), product.getImage(), "More Awesome TShirt",
				product.getName(), product.getPrice(), product.getQuantity(), product.getId());
		boolean actualResult = pk > 0;
		assertThat(actualResult).isTrue();
		productRepository.delete(product); //clean up database
	}

	//Shane Bergeron Test
	@Test
	void productServiceGetProductByName() {
		List<Product> actualResult = productService.getProductByName("TeeShirt");
		System.out.println(actualResult);
		assertThat(actualResult).isNotNull();
	}

	//Braulio Calderon Test
	@Test
	void productServiceDelete() {
		Product product = new Product(7, 47, "Sting T-Shirt", "https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png", "TeeShirt", "Awesome T");
		productService.save(product);
		System.out.println(product);
		productService.delete(product.getId());
		Optional<Product> actualResult = productService.findById(product.getId());
		System.out.println(actualResult);
		assertThat(actualResult).isEmpty();
	}
}

