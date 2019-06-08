package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

//control.shift.o to remove unused imports

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daoImpl.BankAccountDaoImpl;
import com.fdmgroup.daoImpl.CustomerDaoImpl;
import com.fdmgroup.daoImpl.ProductDaoImpl;
import com.fdmgroup.daoImpl.SaleItemDaoImpl;
import com.fdmgroup.daoImpl.ShoppingBasketDaoImpl;
import com.fdmgroup.entity.BankAccount;
import com.fdmgroup.entity.Customer;
import com.fdmgroup.entity.Product;
import com.fdmgroup.entity.SaleItem;
import com.fdmgroup.entity.ShoppingBasket;
import com.fdmgroup.model.User;

@Controller
public class HomeController {

	/*
	 * // private Customer customer; // private CustomerDaoImpl customerDaoImpl
	 * = new CustomerDaoImpl();
	 */
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");

	Double accountBalance = 0.0d;
	BankAccount bankAccount;

	@RequestMapping(value = "pastOrders")
	public String pastOrdersHandler(Model model, HttpSession session) {

		// following finding username and customerNumber
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		int customerNumber = customers.get(0).getCustomerNumber();

		// get all shopping baskets
		ShoppingBasketDaoImpl shoppingBasketDaoImpl = new ShoppingBasketDaoImpl();
		List<ShoppingBasket> listOfBaskets = shoppingBasketDaoImpl.listOfBaskets();
		// System.out.println(listOfBaskets.size());

		// create a list of shopping basked which will hold up baskets related
		// with a specific logged user only

		List<ShoppingBasket> listOfBasketsOfaUser = new ArrayList<ShoppingBasket>();

		// iterate through shopping baskets
		Iterator<ShoppingBasket> basketIterator = listOfBaskets.iterator();
		while (basketIterator.hasNext()) {
			ShoppingBasket tempShoppingBasket = basketIterator.next();
			/*
			 * if(tempShoppingBasket.getCustomer().getCustomerNumber() !=
			 * customerNumber){ listOfBaskets.remove(tempShoppingBasket);
			 */
			if (tempShoppingBasket.getCustomer().getCustomerNumber() == customerNumber) {
				listOfBasketsOfaUser.add(tempShoppingBasket);
			}
		}
		// System.out.println(listOfBasketsOfaUser.size()); this works

		// now put listOfBasketsOfaUser into a model attribute for jsp display
		model.addAttribute("listBasketToJsp", listOfBasketsOfaUser);

		return "pastOrders";

		// write a method which will get all shopping baskets of the users as
		// per username/id
		// pass them as model to the jsp page pastOrders.jsp
		// display in tabular form the past orders
		//

	}

	@RequestMapping(value = "bankBalance2")
	public String bankAccountHandler(Model model, HttpSession session) {
		model.addAttribute("bankAccount", new BankAccount());

		///// this is added from another original method we require to refactor
		///// this code as
		BankAccountDaoImpl bankAccountDaoImpl = new BankAccountDaoImpl();
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		int customerNumber = customers.get(0).getCustomerNumber();
		//// System.out.println(customerNumber);

		bankAccount = bankAccountDaoImpl.getBankAccountByCustomerNumber(customerNumber);
		if (bankAccount != null)
			model.addAttribute("customerBankAccount", bankAccount);

		//////////////
		accountBalance = bankAccount.getAccountBalance();
		return "bankBalance2";
	}

	public boolean isNumeric(String str) {
		//return str.matches("^(?:(?:\\-{1})?\\d+(?:\\.{1}\\d+)?)$");
		return str.matches("^(?:(?:\\{1})?\\d+(?:\\.{1}\\d+)?)$");
	}

	@RequestMapping(value = "submitBankBalance2", method = POST)
	public String bankAccountSubmitHandler(Model model, HttpServletRequest request, HttpSession session) {

		BankAccountDaoImpl bankAccountDaoImpl = new BankAccountDaoImpl();
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		int customerNumber = customers.get(0).getCustomerNumber();

		String depositAmount = request.getParameter("depositAmount");
		//////////////// **********

		boolean isN = isNumeric(depositAmount);
		// System.out.println(isN);

		if (isN) {

			double deposit = Double.parseDouble(depositAmount);
			// BankAccount bankAccount = new BankAccount();

			// System.out.println(deposit);

			bankAccount = bankAccountDaoImpl.getBankAccountByCustomerNumber(customerNumber);
			accountBalance = bankAccount.getAccountBalance();

			Double currentBalance = accountBalance;
			// System.out.println(currentBalance);
			currentBalance += (deposit);
			// System.out.println(currentBalance);
			bankAccount.setAccountBalance(currentBalance);
			bankAccountDaoImpl.updateBankAccount(bankAccount);
			checkoutHandler(model, session);
			return "checkout";
		}

		else {
			return "bankBalanceWrong";
		}
		// return "listingShoppingBasket2";
		// return "listTheProducts";
	}

	@RequestMapping(value = "updateUser")
	public String updateCustomerHadler(Model model, HttpSession session) {

		model.addAttribute("user", new User());
		String username = (String) session.getAttribute("username");
		// System.out.println(username);

		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		model.addAttribute("listCustomers", customers);
		return "customer";
	}

	@RequestMapping(value = "bankBalance")
	public String bankBalanceHandler(Model model, HttpSession session) {

		BankAccountDaoImpl bankAccountDaoImpl = new BankAccountDaoImpl();
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		int customerNumber = customers.get(0).getCustomerNumber();
		System.out.println(customerNumber);

		BankAccount bankAccount = bankAccountDaoImpl.getBankAccountByCustomerNumber(customerNumber);
		if (bankAccount != null)
			model.addAttribute("customerBankAccount", bankAccount);
		return "bankBalance";
	}

	// working here !

	@RequestMapping(value = "paymentDone")
	public String paymentDoneHandler(Model model, HttpSession session) {
		checkoutHandler(model, session);
		bankAccountHandler(model, session);

		// model.addAttribute("youPaid", checkoutPrice);
		// but have to update in database as well !!!!

		if (accountBalance > checkoutPrice) {

			// calculating finalBalance after minus orderprice from balance
			Double balanceFinal = accountBalance - checkoutPrice;
			model.addAttribute("balance", balanceFinal);

			// update the current balance of the customer into the database
			BankAccountDaoImpl bankAccountDaoImpl = new BankAccountDaoImpl();

			String username = (String) session.getAttribute("username");
			CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
			List<Customer> customers = customerDaoImpl.getCusomerByName(username);
			int customerNumber = customers.get(0).getCustomerNumber();
			BankAccount bankAccount = new BankAccount();
			bankAccount = bankAccountDaoImpl.getBankAccountByCustomerNumber(customerNumber);

			bankAccount.setAccountBalance(balanceFinal);
			bankAccountDaoImpl.updateBankAccount(bankAccount);
			// updating into database is done

			// putting shopping basket final order into the database
			// int basketId = 100; //hard coded at the moment

			int basketId = new Random().nextInt(1000); // as have not used
														// sequence
														// into the table
			Double basketPrice = checkoutPrice;
			Double discountedBasketPrice = 0.0d;
			int discountPercentage = 0;
			Customer customer = customers.get(0);

			/// Map<Product, Integer> listOfAddedItems = new HashMap<Product,
			/// Integer>();
			// ShoppingBasket currentBasket = new ShoppingBasket(basketId,
			/// customer,
			/// basketPrice, )

			// should we create a new page showing nothing to pay if they click
			// on make payment before adding any items into the basket?

			if (basketPrice > 0) {
				ShoppingBasket currentBasket = new ShoppingBasket(basketId, customer, basketPrice, discountPercentage,
						discountedBasketPrice, basketProducts);

				// add shopping basket into database only if basketprice is more
				// than zero (that means basket is not empty)
				ShoppingBasketDaoImpl shoppingBasketDaoImpl = new ShoppingBasketDaoImpl();
				shoppingBasketDaoImpl.addShoppingBasket(currentBasket);
				String shippingAddress = customer.getAddress();
				model.addAttribute("shippingAddress", shippingAddress);

				return "paymentDone";
			} else
				return "paymentDoneEmpty";
		}

		else {

			/*
			 * Double moneyMoreNeeded = checkoutPrice - accountBalance;
			 * model.addAttribute("checkOutTotal", checkoutPrice);
			 * model.addAttribute("moneyMoreNeeded", moneyMoreNeeded); return
			 * "bankBalanceNotEnough"; // or another page!
			 */

			/// added this code to remove requirement of
			/// bankBalanceNotEnough.jsp page
			String errorMessage1 = "You May require to deposit (£) ";
			String errorMessage2 = "to complete this Order";
			String errorMessage = "Current Basket Total is (£) :";

			model.addAttribute("err", errorMessage);
			model.addAttribute("err1", errorMessage1);
			model.addAttribute("err2", errorMessage2);

			/*
			 * Map<Integer, String> mapErr = new HashMap<Integer, String>();
			 * mapErr.put(1, errorMessage); mapErr.put(2, errorMessage1);
			 * mapErr.put(3, errorMessage2);
			 * 
			 * model.addAllAttributes("errorMap", mapErr);
			 */
			///
			Double moneyMoreNeeded = checkoutPrice - accountBalance;
			model.addAttribute("checkOutTotal", checkoutPrice);
			model.addAttribute("moneyMoreNeeded", moneyMoreNeeded);
			return "bankBalance2"; // or another page!
		}

		/******
		 * now requires to udpate and create an order entry into the database as
		 * per customer basket //need the full basket object
		 * 
		 ******/

	}

	@RequestMapping(value = "checkout")
	public String checkoutHandler(Model model, HttpSession session) {

		synchronizeBaskets();
		// return "checkout";
		checkoutPrice = 0.0;
		// Set<Entry<Product, Integer>> entrySet = basketProducts.entrySet();
		Iterator<Entry<Product, Integer>> iterator = basketProducts.entrySet().iterator();
		// Iterator<Entry<Product, Integer>> iterator = entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Product, Integer> nextEntry = iterator.next();
			int quantity = nextEntry.getValue();
			Double price = nextEntry.getKey().getPrice();
			checkoutPrice += (quantity * price);
		}
		session.setAttribute("finalCheckoutPrice", checkoutPrice);
		model.addAttribute("listProductToJsp", basketProducts);
		return "checkout";
	}

	@RequestMapping(value = "logout")
	public String logoutHandler(HttpSession session) {
		session.invalidate();

		// emptied baskets and then test again
		basket.clear(); // = null;
		basketProducts.clear(); // = null; do not use null otherwise we will
								// have null pointer exception at the next login

		return "logout";
	}

	@RequestMapping(value = "listCustomers")
	/*
	 * // this is link on the page when // // // this is clicked on login //
	 * page // // button link
	 */ public String listUsersHandler(Model model) {
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		List<Customer> listOfCustomers = customerDao.listOfCustomers();
		model.addAttribute("listCustomersToJsp", listOfCustomers);
		return "listTheCustomers";

	}

	Map<Integer, Integer> basket = new HashMap<Integer, Integer>();
	Map<Product, Integer> basketProducts = new HashMap<Product, Integer>();
	double checkoutPrice = 0.0d;

	//// this is added into file to removeItemFromBasket

	@RequestMapping("removeItemFromBasket/{itemId}")
	public String removeItemFromBasketHandler(@PathVariable int itemId, Model model) {
		// *****as per EMAIL ******
		// intemId = itemId;

		// System.out.println(basket.size());
		if (basket.containsKey(itemId)) {
			// int quantity = basket.get(itemId);
			// basket.put(itemId, quantity - 1);
			basket.remove(itemId);
			basketProducts.clear();
			synchronizeBaskets();

		} else {
			// basket.put(itemId, 1);
			// synchronizeBaskets();
			return "productNotInList";
		}
		// return "productAdded";
		// listingShoppingBasketHandler(model);
		// System.out.println(basket.size());
		// System.out.println(basket.size());

		return "productRemoved";
	}

	@RequestMapping("addItemToBasket/{itemId}")
	public String additemToBasket(@PathVariable int itemId) {
		// *****as per EMAIL ******
		// intemId = itemId;
		if (basket.containsKey(itemId)) {
			int quantity = basket.get(itemId);
			basket.put(itemId, quantity + 1);
		} else {
			basket.put(itemId, 1);
		}
		return "productAdded";
	}

	int x = 0;

	@RequestMapping(value = "listShoppingBaskets2")
	public String listShoppingBaskets2Handler(Model model, HttpSession session)
	{
		synchronizeBaskets();
		model.addAttribute("listProductToJsp", basketProducts);
		session.setAttribute("finalCheckoutPrice", checkoutPrice);
		return "listingShoppingBasket2";
	}

	/*
	 * this method is being called at two places to fillup the basket and at the
	 * checkout place to display the items
	 */

	public void synchronizeBaskets() {

		ProductDaoImpl productDaoImpl = new ProductDaoImpl();
		for (Integer key : basket.keySet()) {
			Product product = productDaoImpl.getProduct(key);
			// checkoutPrice += product.getPrice();
			int quantity = basket.get(key);
			basketProducts.put(product, quantity);
			/*
			 * Double pri = product.getPrice(); checkoutPrice += (pri*quantity);
			 */
		}
		/*
		 * System.out.println("basket NN" + basket.size()); System.out.println(
		 * "another map" +basketProducts.size());
		 */
	}

	@RequestMapping(value = "listShoppingBaskets") // this is name of link when
													// // // this is called on
													// // / // login // page //
													// // button // link
	public String listingShoppingBasketHandler(Model model) {

		/* this works */
		ShoppingBasketDaoImpl shoppingBasketDaoImpl = new ShoppingBasketDaoImpl();
		List<ShoppingBasket> listOfBaskets = shoppingBasketDaoImpl.listOfBaskets();
		model.addAttribute("listBasketsToJsp", listOfBaskets);
		// System.out.println("list of basket: " + listOfBaskets.size());

		/* this works */
		String username = listOfBaskets.get(0).getCustomer().getCustomerName();
		model.addAttribute("username", username);

		// System.out.println(listOfBaskets.size());

		Map<Product, Integer> listOfAddedItems = listOfBaskets.get(0).getListOfAddedItems();
		model.addAttribute("listOfItems", listOfAddedItems);

		return "listingShoppingBasket";

	}

	@RequestMapping(value = "listProducts")
	public String listProductsHandler(Model model) {

		// EntityManager entityManager =
		// entityManagerFactory.createEntityManager();
		ProductDaoImpl productDao = new ProductDaoImpl();
		List<Product> listOfProducts = productDao.listOfProducts();
		model.addAttribute("listProductsToJsp", listOfProducts);
		return "listTheProducts";
	}

	@RequestMapping(value = "listItems") // link on the page must be called
											// this!
	public String listTheItemsHandler(Model model) {

		EntityManager entityManager = entityManagerFactory.createEntityManager(); // logic
																					// //
																					// //
																					// //
																					// in
																					// //
																					// controller

		SaleItemDaoImpl saleItemDao = new SaleItemDaoImpl(entityManager);
		List<SaleItem> listOfItems = saleItemDao.listTheItems();

		model.addAttribute("listAttribute", listOfItems);
		// we are forwarding listOfItems which came in from daoImpl to jsp
		// webpage as 'listAttribute'
		// model.addAttribute adding attribute/variable to jsp webpage as
		// 'listAttribute'

		return "listTheItems";
		/*
		 * // this will return listTheItems.jsp page when // user click on link
		 * stating listItems (which // is provided in value).
		 */ }

	@RequestMapping(value = "/")
	public String loginPageController(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "submitLogin", method = POST) /*
															 * * // form method
															 * * action // //
															 * name * must be //
															 * * 'submitLogin'
															 */

	public String loginSubmitHandler(Model model, User user, HttpServletRequest request, HttpSession session) {
		model.addAttribute("user",
				user); /**
						 * // taking information from the page // // submitted
						 */
		// request.setAttribute(arg0, arg1);
		String username = user.getUserName();
		String password = user.getPassword();
		boolean validCustomer = false;

		// return "login";

		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> custListFromDb = customerDaoImpl.getCusomerByName(username);

		for (Customer customer : custListFromDb) {

			// System.out.println(customer);
			if (customer.getUserName().equals(username) && customer.getPassword().equals(password)) {
				validCustomer = true;
			}
		}

		if (validCustomer) {
			session.setAttribute("username", username);
			// session.setAttribute("finalCheckoutPrice", checkoutPrice);
			return "welcome3";

		} else {
			request.setAttribute("error", "User name and/or password is not correct.");
			request.setAttribute("error1", "Please try again or Register.");
			return "login";
		}

	}

	@RequestMapping(value = "registration")
	public String registrationHandler(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = "submitRegistration", method = POST)
	public String registrationSubmitHandler(Model model, User user, HttpServletRequest request) {

		if (user.getFirstName().length() == 0) {
			request.setAttribute("error1", "First name is a required field and cannot be empty");
			return "registration";
		}

		if (user.getLastName().length() == 0) {
			request.setAttribute("error2", "Last name is a required field and cannot be empty");
			return "registration";
		}

		if (user.getUserName().length() == 0) {
			request.setAttribute("error3", "User name is a required field and cannot be empty");
			return "registration";
		}

		if (user.getPassword().length() == 0) {
			request.setAttribute("error4", "Password is a required field and cannot be empty");
			return "registration";
		}

		if (user.getConfirmPassword().length() == 0) {
			request.setAttribute("error5", "Confirm Password is a required field and cannot be empty");
			return "registration";
		}

		if (user.getHomeAddress().length() == 0) {
			request.setAttribute("error6", "Home address is a required field and cannot be empty");
			return "registration";
		}

		if (user.getEmailAddress().length() == 0) {
			request.setAttribute("error7", "Email is a required field and cannot be empty");
			return "registration";
		}
		
		if (!user.getEmailAddress().contains("@") || !user.getEmailAddress().contains(".")){
			request.setAttribute("error8", "Please provide valid email address");
			return "registration";
		}
			

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			request.setAttribute("error", "Password and Confirm Password should be same and cannot be empty");
			return "registration";
		}

		/*
		 * this.customerNumber = customerNumber; this.customerName =
		 * customerName; this.password = password; this.address = address;
		 * this.email = email; this.shipAddress = shipAddress; this.userName =
		 * userName;
		 */

		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> cusomerByName = customerDaoImpl.getCusomerByName(user.getUserName());
		if (cusomerByName.size() == 0) {
			Customer temp = new Customer();
			temp.setCustomerName(user.getFirstName());
			temp.setUserName(user.getUserName());
			temp.setCustomerNumber(new Random().nextInt(1000));

			// temp.setCustomerNumber(customerNumber);
			temp.setPassword(user.getPassword());
			temp.setAddress(user.getHomeAddress());
			temp.setShipAddress(user.getHomeAddress());

			temp.setEmail(user.getEmailAddress());

			customerDaoImpl.addCustomer(temp);
			
			// provide an account balance of 0 to new customers
			int bankNumber = new Random().nextInt(5000);
			BankAccount newCustomerBankAccount = new BankAccount(bankNumber, temp, 0.0);
			BankAccountDaoImpl bankAccountDaoImpl = new BankAccountDaoImpl();
			bankAccountDaoImpl.addBankAccount(newCustomerBankAccount);
			
			return "login";
		} else {
			request.setAttribute("error", "If you are already registered then please login");
			return "registration";
		}
	}

	@RequestMapping(value = "updateAllInOne")
	public String updateAllInOneHandler(Model model) {
		model.addAttribute("user", new User());
		return "updateAllInOne";
	}

	@RequestMapping(value = "submitUpdateAllInOne", method = POST)
	// we need to redirect this to updateD
	public String updateAllInOneSubmitHandler(Model model, User user, HttpServletRequest request, HttpSession session) {

		String updatedPassword = user.getPassword();
		String updatedConfirmedPassword = user.getConfirmPassword();
		String updatedEmail = user.getEmailAddress();
		String updatedHomeAddress = user.getHomeAddress();

		/*
		 * System.out.println(updatedPassword);
		 * System.out.println(updatedConfirmedPassword);
		 * System.out.println(updatedEmail);
		 * System.out.println(updatedHomeAddress);
		 */

		if (updatedPassword.length() == 0) {
			request.setAttribute("error1", "Updated password is a required field and cannot be empty");
			return "updateAllInOne";
		}

		if (updatedConfirmedPassword.length() == 0) {
			request.setAttribute("error2", "Confirmation of new Password is a required field and cannot be empty");
			return "updateAllInOne";
		}

		if (updatedHomeAddress.length() == 0) {
			request.setAttribute("error3", "Updated Home Address is a required field and cannot be empty");
			return "updateAllInOne";
		}

		if (updatedEmail.length() == 0) {
			request.setAttribute("error4", "Updated Email is a required field and cannot be empty");
			return "updateAllInOne";
		}

		if (!updatedPassword.equals(updatedConfirmedPassword)) {
			request.setAttribute("error", "Password and Confirmed password should be same and cannot be empty");
			return "updateAllInOne";
		}

		////////////////////// above on this works
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		Customer customer = customers.get(0);

		// Iterator<Customer> iterator = customers.iterator();
		if (customer != null) {
			customer.setPassword(updatedPassword);
			customer.setAddress(updatedHomeAddress);
			customer.setEmail(updatedEmail);
			customerDaoImpl.updateCustomer(customer);
		}

		/* System.out.println("updated!"); */
		return "login";
	}

	@RequestMapping(value = "updatePassword")
	public String updatePasswordHandler(Model model) {
		model.addAttribute("user", new User());
		return "updatePassword";
	}

	@RequestMapping(value = "submitUpdatePassword", method = POST)
	// we need to redirect this to updateD
	public String updatePasswordSubmitHandler(Model model, User user, HttpServletRequest request, HttpSession session) {

		String updatedPassword = user.getPassword();
		String updatedConfirmedPassword = user.getConfirmPassword();
		// String updatedEmail = user.getEmailAddress();
		// String updatedHomeAddress = user.getHomeAddress();

		/*
		 * System.out.println(updatedPassword);
		 * System.out.println(updatedConfirmedPassword);
		 * System.out.println(updatedEmail);
		 * System.out.println(updatedHomeAddress);
		 */

		if (updatedPassword.length() == 0) {
			request.setAttribute("error1", "Updated password is a required field and cannot be empty");
			return "updatePassword";
		}

		if (updatedConfirmedPassword.length() == 0) {
			request.setAttribute("error2", "Confirmation of new Password is a required field and cannot be empty");
			return "updatePassword";
		}
		/*
		 * if(updatedHomeAddress.length() == 0){ request.setAttribute("error3",
		 * "Updated Home Address is a required field and cannot be empty");
		 * return "updateAddress"; }
		 */

		/*
		 * if(updatedEmail.length() == 0){ request.setAttribute("error1",
		 * "Updated Email is a required field and cannot be empty"); return
		 * "updateEmail"; }
		 */

		if (!updatedPassword.equals(updatedConfirmedPassword)) {
			request.setAttribute("error", "Password and Confirmed password should be same and cannot be empty");
			return "updatePassword";
		}

		////////////////////// above on this works
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		Customer customer = customers.get(0);

		// Iterator<Customer> iterator = customers.iterator();
		if (customer != null) {
			customer.setPassword(updatedPassword);
			// customer.setAddress(updatedHomeAddress);
			// customer.setEmail(updatedEmail);
			customerDaoImpl.updateCustomer(customer);
		}

		System.out.println("updated!");
		return "login";
	}

	@RequestMapping(value = "updateEmail")
	public String updateEmailHandler(Model model) {
		model.addAttribute("user", new User());
		return "updateEmail";
	}

	@RequestMapping(value = "submitUpdateEmail", method = POST)
	// we need to redirect this to updateD
	public String updateEmailSubmitHandler(Model model, User user, HttpServletRequest request, HttpSession session) {

		/*
		 * String updatedPassword = user.getPassword(); String
		 * updatedConfirmedPassword = user.getConfirmPassword();
		 */
		String updatedEmail = user.getEmailAddress();
		// String updatedHomeAddress = user.getHomeAddress();

		/*
		 * System.out.println(updatedPassword);
		 * System.out.println(updatedConfirmedPassword);
		 * System.out.println(updatedEmail);
		 * System.out.println(updatedHomeAddress);
		 */

		/*
		 * if(updatedPassword.length() == 0){ request.setAttribute("error1",
		 * "Updated password is a required field and cannot be empty"); return
		 * "updateAddress"; }
		 * 
		 * if(updatedConfirmedPassword.length() == 0){
		 * request.setAttribute("error2",
		 * "Confirmation of new Password is a required field and cannot be empty"
		 * ); return "updateAddress"; }
		 * 
		 * if(updatedHomeAddress.length() == 0){ request.setAttribute("error3",
		 * "Updated Home Address is a required field and cannot be empty");
		 * return "updateAddress"; }
		 */

		if (updatedEmail.length() == 0) {
			request.setAttribute("error1", "Updated Email is a required field and cannot be empty");
			return "updateEmail";
		}

		/*
		 * if(!updatedPassword.equals(updatedConfirmedPassword)){
		 * request.setAttribute("error",
		 * "Password and Confirmed password should be same and cannot be empty"
		 * ); return "updateAddress"; }
		 */

		////////////////////// above on this works
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		Customer customer = customers.get(0);

		// Iterator<Customer> iterator = customers.iterator();
		if (customer != null) {
			/*
			 * customer.setPassword(updatedPassword);
			 * customer.setAddress(updatedHomeAddress);
			 */
			customer.setEmail(updatedEmail);
			customerDaoImpl.updateCustomer(customer);
		}

		/* System.out.println("updated!"); */
		return "login";
	}

	@RequestMapping(value = "updateAddress")
	public String updateAddressHandler(Model model) {
		model.addAttribute("user", new User());
		return "updateAddress";
	}

	@RequestMapping(value = "submitUpdateAddress", method = POST)
	// we need to redirect this to updateD
	public String updateAddressSubmitHandler(Model model, User user, HttpServletRequest request, HttpSession session) {

		/*
		 * String updatedPassword = user.getPassword(); String
		 * updatedConfirmedPassword = user.getConfirmPassword(); String
		 * updatedEmail = user.getEmailAddress();
		 */
		String updatedHomeAddress = user.getHomeAddress();

		/*
		 * System.out.println(updatedPassword);
		 * System.out.println(updatedConfirmedPassword);
		 * System.out.println(updatedEmail);
		 * System.out.println(updatedHomeAddress);
		 */

		/*
		 * if(updatedPassword.length() == 0){ request.setAttribute("error1",
		 * "Updated password is a required field and cannot be empty"); return
		 * "updateAddress"; }
		 * 
		 * if(updatedConfirmedPassword.length() == 0){
		 * request.setAttribute("error2",
		 * "Confirmation of new Password is a required field and cannot be empty"
		 * ); return "updateAddress"; }
		 */

		if (updatedHomeAddress.length() == 0) {
			request.setAttribute("error1", "Updated Home Address is a required field and cannot be empty");
			return "updateAddress";
		}

		/*
		 * if(updatedEmail.length() == 0){ request.setAttribute("error4",
		 * "Updated Email is a required field and cannot be empty"); return
		 * "updateAddress"; }
		 * 
		 * 
		 * if(!updatedPassword.equals(updatedConfirmedPassword)){
		 * request.setAttribute("error",
		 * "Password and Confirmed password should be same and cannot be empty"
		 * ); return "updateAddress"; }
		 */

		////////////////////// above on this works
		String username = (String) session.getAttribute("username");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> customers = customerDaoImpl.getCusomerByName(username);
		Customer customer = customers.get(0);

		// Iterator<Customer> iterator = customers.iterator();
		if (customer != null) {
			// customer.setPassword(updatedPassword);
			customer.setAddress(updatedHomeAddress);
			// customer.setEmail(updatedEmail);
			customerDaoImpl.updateCustomer(customer);
		}

		/* System.out.println("updated!"); */
		return "login";
	}
}

/*
 * after adding any new page and link to that page will not work till the setup
 * is done withint homeController
 * 
 * /* useful notes
 * 
 * // UsersDao userDao = new UsersDao(); // User tempuser =
 * userDao.getUSer(username); // // // not a good practice to because if first
 * past is not null then second part will never be executed // // therefore
 * avoid // if(user != null && tempuser.getPassword().equals(password)){ //
 * return "loginsuccess"; // } // else // return "login";
 * 
 * //return "loginsuccess"; // must be the name of jsp page with case
 * sensitivity
 */

// firstly created an object to get the model sorted
// then created a daoImpl to get the methods sorted
// put and manage controller (get the items from database first into controller
// ==> then pass the list to jsp page ===> display list on jsp using tag)
// sort out jsp page

// code of registration comments

// make the user object to covert over from customer object
// return "registration";

// Customer customerNew = new Customer();
// user.getFirstName() = customer

// CustomerDaoImpl customerNew = new CustomerDaoImpl();
// Customer custTemp = new Customer();
// custTemp =
// customerNew.addCustomer(user);
// if (username.length() == 0 || password.length() == 0)

// code of login handler

// // List<Customer> listOfCustomers = customerFromDatabase.listOfCustomers();
//
// for (Customer customer : listOfCustomers) {
// if (customer.getCustomerName().equals(username) &&
// customer.getPassword().equals(password)) {
// validCustomer = true; // welcome page to be created
// }
// }

// code from handlener commented

// customerFromDatabase.getCustomer(CustomerNumber);

/*
 * List<Customer> listOfCustomers = customerDaoImpl.listOfCustomers(); // make
 * an iterator and see whether we can get data in that way or not
 * 
 * ListIterator<Customer> iterateOver = listOfCustomers.listIterator();
 * 
 * while(iterateOver.hasNext()){ customer = iterateOver.next();
 * System.out.println(customer); }
 */

// int customerId = customer.getCustomerNumber();
// String customerName = customer.getCustomerName();
//
// System.out.println(customerId + " + " + customerName);
/*
 * package com.fdmgroup.controllers;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import static org.springframework.web.bind.annotation.RequestMethod.*;
 * 
 * import java.util.Iterator; import java.util.List; import
 * java.util.ListIterator;
 * 
 * import javax.persistence.EntityManager; import
 * javax.persistence.EntityManagerFactory; import javax.persistence.Persistence;
 * import javax.persistence.Query;
 * 
 * import org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import com.fdmgroup.daoImpl.CustomerDaoImpl; import
 * com.fdmgroup.daoImpl.ProductDaoImpl; import
 * com.fdmgroup.daoImpl.SaleItemDaoImpl; import
 * com.fdmgroup.daoImpl.ShoppingBasketDaoImpl; import
 * com.fdmgroup.entity.Customer; import com.fdmgroup.entity.Product; import
 * com.fdmgroup.entity.SaleItem; import com.fdmgroup.entity.ShoppingBasket;
 * import com.fdmgroup.model.User;
 * 
 * @Controller public class HomeController {
 * 
 * // private Customer customer; // private CustomerDaoImpl customerDaoImpl =
 * new CustomerDaoImpl();
 * 
 * private EntityManagerFactory entityManagerFactory =
 * Persistence.createEntityManagerFactory("soloprojectjpa1");
 * 
 * @RequestMapping(value = "listCustomers") // this is link on the page when //
 * this is clicked on login page // button link public String
 * listUsersHandler(Model model) { CustomerDaoImpl customerDao = new
 * CustomerDaoImpl(); List<Customer> listOfCustomers =
 * customerDao.listOfCustomers(); model.addAttribute("listCustomersToJsp",
 * listOfCustomers); return "listTheCustomers";
 * 
 * }
 * 
 * @RequestMapping(value = "listShoppingBaskets") // this is name of link when
 * // this is called on login // page button link public String
 * listingShoppingBasketHandler(Model model) { ShoppingBasketDaoImpl
 * shoppingBasketDao = new ShoppingBasketDaoImpl(); // int basketId = 1; //
 * ShoppingBasket shoppingBasket = //
 * shoppingBasketDao.getShoppingBasket(basketId); // this will be done with
 * where clause at the next level
 * 
 * EntityManager entityManager = entityManagerFactory.createEntityManager();
 * String sqlJoinQuery =
 * "select ngs.BASKETID, ngc.CUSTOMER_NAME, ngsp.PRODUCT_ID, ngp.PRODUCT_NAME, ngsp.QUANTITY, ngs.BASKETPRICE, ngs.DISCOUNTPERCENTAGE, ngs.DISCOUNTEDBASKETPRICE "
 * +
 * "from NG_CUSTOMER ngc, ng_product ngp, ng_shoppingbasket ngs, ng_shoppingbasket_products ngsp "
 * +
 * "where ngc.CUSTOMERNUMBER = ngs.CUSTOMERNUMBER and ngs.BASKETID = ngsp.NG_SHOPPINGBASKET_BASKETID and ngsp.product_id = ngp.PRODUCT_ID"
 * ;
 * 
 * // String joinQuery="select ngs.BASKETID, ngc.CUSTOMER_NAME, //
 * ngsp.PRODUCT_ID, ngp.PRODUCT_NAME, ngsp.QUANTITY, ngs.BASKETPRICE, //
 * ngs.DISCOUNTPERCENTAGE, ngs.DISCOUNTEDBASKETPRICE from //
 * ng_shoppingbasket_products ngsp inner join ng_shoppingbasket ngs on //
 * ngs.BASKETID = ngsp.NG_SHOPPINGBASKET_BASKETID inner join NG_CUSTOMER // ngc
 * on ngc.CUSTOMERNUMBER = ngs.CUSTOMERNUMBER inner join ng_product // ngp on
 * ngsp.product_id = ngp.PRODUCT_ID"; // Query nativeQuery =
 * entityManager.createNativeQuery(joinQuery); Query nativeQuery =
 * entityManager.createNativeQuery(sqlJoinQuery); // List<Object[]>
 * listOfBaskets = nativeQuery.getResultList();
 * 
 * // List<ShoppingBasket> listOfBaskets = // shoppingBasketDao.listOfBaskets();
 * 
 * List<Object> listOfBaskets = nativeQuery.getResultList();
 * model.addAttribute("listBasketsToJsp", listOfBaskets); return
 * "listingShoppingBasket"; }
 * 
 * @RequestMapping(value = "listProducts") public String
 * listProductsHandler(Model model) {
 * 
 * // EntityManager entityManager = //
 * entityManagerFactory.createEntityManager(); ProductDaoImpl productDao = new
 * ProductDaoImpl(); List<Product> listOfProducts = productDao.listOfProducts();
 * model.addAttribute("listProductsToJsp", listOfProducts); return
 * "listTheProducts"; }
 * 
 * @RequestMapping(value = "listItems") // link on the page must be called //
 * this! public String listTheItemsHandler(Model model) {
 * 
 * EntityManager entityManager = entityManagerFactory.createEntityManager(); //
 * logic // in // controller SaleItemDaoImpl saleItemDao = new
 * SaleItemDaoImpl(entityManager); List<SaleItem> listOfItems =
 * saleItemDao.listTheItems();
 * 
 * model.addAttribute("listAttribute", listOfItems); // we are forwarding
 * listOfItems which came in from daoImpl to jsp // webpage as 'listAttribute'
 * // model.addAttribute adding attribute/variable to jsp webpage as //
 * 'listAttribute'
 * 
 * return "listTheItems"; // this will return listTheItems.jsp page when // user
 * click on link stating listItems (which // is provided in value). }
 * 
 * @RequestMapping(value = "/") public String loginPageController(Model model) {
 * model.addAttribute("user", new User()); return "login"; }
 * 
 * @RequestMapping(value = "submitLogin", method = POST) // form method action
 * // name must be // 'submitLogin' public String loginSubmitHandler(Model
 * model, User user) { model.addAttribute("user", user); // taking information
 * from the page // submitted
 * 
 * String username = user.getUserName(); String password = user.getPassword();
 * boolean validCustomer = false;
 * 
 * // return "login";
 * 
 * CustomerDaoImpl customerFromDatabase = new CustomerDaoImpl(); List<Customer>
 * listOfCustomers = customerFromDatabase.listOfCustomers();
 * 
 * for (Customer customer : listOfCustomers) { if
 * (customer.getCustomerName().equals(username) &&
 * customer.getPassword().equals(password)) { validCustomer = true; // welcome
 * page to be created } }
 * 
 * if(validCustomer) return "welcome"; else return "login";
 * 
 * 
 * // customerFromDatabase.getCustomer(CustomerNumber);
 * 
 * 
 * List<Customer> listOfCustomers = customerDaoImpl.listOfCustomers(); // make
 * an iterator and see whether we can get data in that way or not
 * 
 * ListIterator<Customer> iterateOver = listOfCustomers.listIterator();
 * 
 * while(iterateOver.hasNext()){ customer = iterateOver.next();
 * System.out.println(customer); }
 * 
 * 
 * // int customerId = customer.getCustomerNumber(); // String customerName =
 * customer.getCustomerName(); // // System.out.println(customerId + " + " +
 * customerName);
 * 
 * }
 * 
 * @RequestMapping(value = "registration") public String
 * registrationHandler(Model model) { model.addAttribute("user", new User());
 * return "registration"; }
 * 
 * @RequestMapping(value = "submitRegistration", method = POST) public String
 * registrationSubmitHandler(Model model, User user) {
 * 
 * // make the user object to covert over from customer object // return
 * "registration";
 * 
 * // Customer customerNew = new Customer(); // user.getFirstName() = customer
 * 
 * // CustomerDaoImpl customerNew = new CustomerDaoImpl(); // Customer custTemp
 * = new Customer(); // custTemp = // customerNew.addCustomer(user); // if
 * (username.length() == 0 || password.length() == 0)
 * 
 * if (user.getFirstName().length() == 0 || user.getLastName().length() == 0 ||
 * user.getEmailAddress().length() == 0 || user.getPassword().length() == 0 ||
 * user.getUserName().length() == 0 || user.getConfirmPassword().length() == 0)
 * { return "registration"; } return "login"; }
 * 
 * }
 * 
 * 
 * useful notes
 * 
 * // UsersDao userDao = new UsersDao(); // User tempuser =
 * userDao.getUSer(username); // // // not a good practice to because if first
 * past is not null then second part will never be executed // // therefore
 * avoid // if(user != null && tempuser.getPassword().equals(password)){ //
 * return "loginsuccess"; // } // else // return "login";
 * 
 * //return "loginsuccess"; // must be the name of jsp page with case
 * sensitivity
 * 
 * 
 * // firstly created an object to get the model sorted // then created a
 * daoImpl to get the methods sorted // put and manage controller (get the items
 * from database first into controller // ==> then pass the list to jsp page
 * ===> display list on jsp using tag) // sort out jsp page
 */

// entityManager.NativeFieldKeySorterQuery(pString).getResultList();

/*
 * TypedQuery<Customer> sql = entityManager.createQuery(string, Customer.class);
 * List<Customer> resultList = sql.getResultList();
 */

// basketId
// https://www.objectdb.com/java/jpa/query/jpql/from#INNER_JOIN_

/*
 * String sqlJoinQuery =
 * "select ngs.BASKETID, ngc.CUSTOMER_NAME, ngsp.PRODUCT_ID, ngp.PRODUCT_NAME, ngsp.QUANTITY, ngs.BASKETPRICE, ngs.DISCOUNTPERCENTAGE, ngs.DISCOUNTEDBASKETPRICE "
 * +
 * "from NG_CUSTOMER ngc, ng_product ngp, ng_shoppingbasket ngs, ng_shoppingbasket_products ngsp "
 * +
 * "where ngc.CUSTOMERNUMBER = ngs.CUSTOMERNUMBER and ngs.BASKETID = ngsp.NG_SHOPPINGBASKET_BASKETID and ngsp.product_id = ngp.PRODUCT_ID"
 * ;
 * 
 * // String joinQuery="select ngs.BASKETID, ngc.CUSTOMER_NAME, //
 * ngsp.PRODUCT_ID, ngp.PRODUCT_NAME, ngsp.QUANTITY, ngs.BASKETPRICE, //
 * ngs.DISCOUNTPERCENTAGE, ngs.DISCOUNTEDBASKETPRICE from //
 * ng_shoppingbasket_products ngsp inner join ng_shoppingbasket ngs on //
 * ngs.BASKETID = ngsp.NG_SHOPPINGBASKET_BASKETID inner join NG_CUSTOMER // ngc
 * on ngc.CUSTOMERNUMBER = ngs.CUSTOMERNUMBER inner join ng_product // ngp on
 * ngsp.product_id = ngp.PRODUCT_ID"; // Query nativeQuery =
 * entityManager.createNativeQuery(joinQuery); Query nativeQuery =
 * entityManager.createNativeQuery(sqlJoinQuery); // List<Object[]>
 * listOfBaskets = nativeQuery.getResultList();
 * 
 * // List<ShoppingBasket> listOfBaskets = // shoppingBasketDao.listOfBaskets();
 * 
 * List<Object> listOfBaskets = nativeQuery.getResultList();
 */
// model.addAttribute("listBasketsToJsp", reus);

// queries

// int basketId = 1;
// ShoppingBasket shoppingBasket =
// shoppingBasketDao.getShoppingBasket(basketId);
// this will be done with where clause at the next level

/*
 * String string =
 * "select ngs.basketId, ngc.customerName, ngsp.Product_ID, ngp.productName, " +
 * "ngsp.Quantity, ngs.basketPrice, ngs.discountedBasketPrice, ngs.discountedBasketPrice "
 * +
 * "from ng_shoppingbasket_products ngsp join ngsp.ng_shoppingbasket ngs join ngs.NG_CUSTOMER ngc join ngc.ng_product ngp"
 * ;
 */

/*
 * String pString =
 * "select ngs.basketId, ngc.customerName, ngp.id, ngp.productName, ngsp.Quantity, ngs.basketPrice, ngs.discountedBasketPrice, ngs.discountedBasketPrice "
 * +
 * "from ng_shoppingbasket ngs join ng_shoppingbasket_products ngsp on  ngs.basketId = ngsp.basketId "
 * +
 * "join ng_customer ngc on ngc.CustomerNumber = ngs.CustomerNumber join  ng_product ngp on ngsp.Product_ID = ngp.id"
 * ;
 */

// List<Object[]> reus = entityManager.createQuery(pString).getResultList();

/*
 * if (user.getFirstName().length() == 0 || user.getLastName().length() == 0 ||
 * user.getEmailAddress().length() == 0 || user.getPassword().length() == 0 ||
 * user.getUserName().length() == 0 || user.getConfirmPassword().length() == 0)
 * { request.setAttribute("error",
 * "Any one of inputted item is zero length or email address is not valid" );
 * return "registration"; }
 */

/*
 * ShoppingBasketDaoImpl shoppingBasketDao = new ShoppingBasketDaoImpl();
 * EntityManager entityManager = entityManagerFactory.createEntityManager();
 * 
 * String pString =
 * "select ngs.BASKETID, ngc.CUSTOMER_NAME, ngp.PRODUCT_ID, ngp.PRODUCT_NAME, ngsp.Quantity, ngs.basketPrice, ngs.discountedBasketPrice, ngs.discountedBasketPrice "
 * +
 * "from ng_shoppingbasket ngs join ng_shoppingbasket_products ngsp on  ngs.BASKETID = ngsp.NG_SHOPPINGBASKET_BASKETID "
 * +
 * "join ng_customer ngc on ngc.CustomerNumber = ngs.CustomerNumber join  ng_product ngp on ngsp.Product_ID = ngp.PRODUCT_ID"
 * ;
 * 
 * List<Object[]> reus =
 * entityManager.createNativeQuery(pString).getResultList();
 * System.out.println(reus.size());
 */

// System.out.println(basket.entrySet());

/*
 * Set<Entry<Integer, Integer>> entrySetOfBasket = basket.entrySet();
 * model.addAttribute("listBasketsToJsp", entrySetOfBasket);
 */
/*
 * Set<Integer> keySet = basket.keySet(); Collection<Integer> values =
 * basket.values();
 */

/*
 * Set<Entry<Integer, Integer>> entrySet = basket.entrySet();
 * model.addAttribute("listBasketsToJsp", entrySet);
 */

// rather than above send the map to jsp and try it in that way

// System.out.println("list" + listOfAddedItems.size());

/*
 * Iterator<Integer> iterator = listOfAddedItems.values().iterator();
 * 
 * while(iterator.hasNext()){ //System.out.println(iterator.next());
 * model.addAttribute("quantity", iterator.next());
 * System.out.println(listOfAddedItems.keySet().); }
 * 
 * Iterator<Product> iterator2 = listOfAddedItems.keySet().iterator();
 * while(iterator2.hasNext()){ Product product = iterator2.next();
 * model.addAttribute("id", product.getId()); model.addAttribute("productname",
 * product.getProductName()); System.out.println(product.getId());
 * System.out.println(product.getProductName()); }
 */

/*
 * Set<Product> keySet = listOfBaskets.get(0).getListOfAddedItems().keySet();
 * System.out.println(keySet);
 */

// keySet.

// Set<Entry<Product, Integer>> entrySet =
// listOfBaskets.get(0).getListOfAddedItems().entrySet();
// entrySet.

// System.out.println(entrySet.toString());

/// ************code from listShoppingItems2 very important scribbles

/*
 * int itemId = 1; if (basket.containsKey(itemId)) { int quantity =
 * basket.get(itemId); basket.put(itemId, quantity + 1); } else {
 * basket.put(itemId, 1); }
 */

/*
 * ShoppingBasketDaoImpl shoppingBasketDaoImpl = new ShoppingBasketDaoImpl();
 * Set<Integer> keySet = basket.keySet();
 */

// ************** need to update as per new method
// so update the model attribute in a way that it passes map to jsp

/*
 * Set<Entry<Product, Integer>> entrySet = basketProducts.entrySet();
 * Iterator<Entry<Product, Integer>> iterator =
 * basketProducts.entrySet().iterator();
 * 
 * while(iterator.hasNext()){ Entry<Product, Integer> nextEntry =
 * iterator.next(); int quantity = nextEntry.getValue(); Double price =
 * nextEntry.getKey().getPrice(); checkoutPrice += (quantity*price);
 * System.out.println(nextEntry.getKey()); }
 */

// for (Integer value : map.values()) {
// System.out.println("Value = " + value);

// for (Product product: basketProducts.values())

/*
 * System.out.println(product.getProductName());
 * System.out.println(product.getPrice());
 */

// shoppingBasketDaoImpl.getShoppingBasket(basket)
// model.addAttribute("listBasketsToJsp", basket);

///// ***************

/****
 * //this not working and have to check with Adrian tomorrow the way around
 * 
 * @RequestMapping(value = "listShoppingBaskets22") public String
 *                       listShoppingBaskets22Handler(Model model, HttpSession
 *                       session)// , // @PathVariable // int // itemId) {
 *                       synchronizeBaskets2();
 *                       model.addAttribute("listProductToJsp", basketProducts);
 *                       session.setAttribute("finalCheckoutPrice",
 *                       checkoutPrice); return "listingShoppingBasket2"; }
 *                       public void synchronizeBaskets2(){
 * 
 *                       ProductDaoImpl productDaoImpl = new ProductDaoImpl();
 *                       for (Integer key : basket.keySet()) { Product product =
 *                       productDaoImpl.getProduct(key); // checkoutPrice +=
 *                       product.getPrice(); //int quantity = basket.get(key);
 *                       basketProducts.remove(product); /* Double pri =
 *                       product.getPrice(); checkoutPrice += (pri*quantity);
 */
// }
// System.out.println("basket" + basket.size());
// System.out.println("other" + basketProducts.size());
/////////