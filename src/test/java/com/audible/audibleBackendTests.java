package com.audible;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.audible.controller.AudiobookController;
import com.audible.controller.AuthorController;
import com.audible.controller.CartController;
import com.audible.controller.CustomerController;
import com.audible.controller.LibraryController;
import com.audible.controller.OrderController;
import com.audible.controller.PaymentCardController;
import com.audible.controller.WishlistController;
import com.audible.dto.AudiobookDTO;
import com.audible.dto.AuthorDTO;
import com.audible.dto.CartDTO;
import com.audible.dto.CustomerDTO;
import com.audible.dto.LibraryDTO;
import com.audible.dto.OrderDTO;
import com.audible.dto.PaymentCardDTO;
import com.audible.dto.WishlistDTO;
import com.audible.service.AudiobookService;
import com.audible.service.AuthorService;
import com.audible.service.CartService;
import com.audible.service.CustomerService;
import com.audible.service.LibraryService;
import com.audible.service.OrderService;
import com.audible.service.PaymentCardService;
import com.audible.service.WishlistService;



@WebMvcTest({
        OrderController.class,
        LibraryController.class,
        AuthorController.class,
        AudiobookController.class,
        CartController.class,
        PaymentCardController.class,
        CustomerController.class,
        WishlistController.class
})



class Group16BackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private AudiobookService audiobookService;

    @MockBean
    private CartService cartService;

    @MockBean
    private PaymentCardService paymentService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private WishlistService wishlistService;



// ------------ Helper DTO builders ------------



    private CustomerDTO customer() {

        CustomerDTO c = new CustomerDTO();

        c.setCustomerId(1);
        c.setUsername("Prajwal");
        c.setName("Prajwal Hiremath");

        c.setEmail("praj005@gmail.com");

        c.setPassword("praj19@123");

        return c;

    }



    private WishlistDTO wishlist() {

        WishlistDTO d = new WishlistDTO();
        d.setWishlistId(5);
        d.setCustomerId(1);
        d.setAudioId(10);
        return d;

    }



    private CustomerController.ChangePasswordRequest changePwd() {

        CustomerController.ChangePasswordRequest r = new CustomerController.ChangePasswordRequest();
        r.setCustomerId(1);
        r.setOldPassword("Old@123");
        r.setNewPassword("New@123");

        return r;

    }



    private CustomerController.ForgotPasswordRequest forgotPwd() {

        CustomerController.ForgotPasswordRequest r = new CustomerController.ForgotPasswordRequest();
        r.setEmail("virat@example.com");
        r.setNewPassword("New@123");

        return r;
    }

    private OrderDTO order() {

        OrderDTO o = new OrderDTO();
        o.setOrderId(101);
        return o;

    }

    private LibraryDTO library() {

        LibraryDTO l = new LibraryDTO();
        l.setLibraryId(201);
        return l;

    }



    private AudiobookDTO audio() {

        AudiobookDTO a = new AudiobookDTO();

        a.setAudioId(401);
        a.setTitle("Get Happy");
        a.setPrice(BigDecimal.valueOf(249));

        return a;

    }

    private CartDTO cart() {

        CartDTO c = new CartDTO();
        c.setCartId(501);

        return c;

    }

    private PaymentCardDTO paymentCard() {

        PaymentCardDTO p = new PaymentCardDTO();
        p.setCardId(100);
        p.setCustomerId(1);
        p.setCardNumber("1111222233334444");
        p.setCardHolderName("Samarth");
        p.setExpiryDate(LocalDate.of(2030, 1, 1));
        p.setCvv("123");
        p.setCardType("VISA");

        return p;

    }



    private AuthorDTO author() {

        AuthorDTO a = new AuthorDTO();
        a.setAuthorId(7);
        a.setAuthorName("Hector Garcia");

        return a;

    }



// --------------------CUSTOMERAPI----------------------



    @Test
    @DisplayName("POST /api/customers/register → 201 Created (happy path)")

    void registerCustomer_valid() throws Exception {

        CustomerDTO req = new CustomerDTO();
        req.setUsername("Prajwal");
        req.setName("Prajwal Hiremath");
        req.setEmail("prajwal005@gmail.com");
        req.setPassword("prajwal@123");

        CustomerDTO res = new CustomerDTO();
        res.setCustomerId(1);
        res.setUsername("prajwal");
        res.setName("");
        res.setEmail("sravya005@gmail.com");



        Mockito.when(customerService.registerCustomer(Mockito.any(CustomerDTO.class))).thenReturn(res);

        mockMvc.perform(post("/api/customers/register").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(1)).andExpect(jsonPath("$.username").value("sravya"))
                .andExpect(jsonPath("$.email").value("sravya005@gmail.com"));

    }



    @Test
    @DisplayName("POST /api/customers/login → 200 OK")

    void login_valid() throws Exception {

        CustomerDTO req = new CustomerDTO();
        req.setUsername("sravya");
        req.setPassword("srav19@123");

        Mockito.when(customerService.login("sravya", "srav19@123")).thenReturn(customer());
        mockMvc.perform(post("/api/customers/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1));

    }



    @Test
    @DisplayName("GET /api/customers/{id} → 200 OK")

    void getById_valid() throws Exception {

        Mockito.when(customerService.getCustomerById(1)).thenReturn(customer());
        mockMvc.perform(get("/api/customers/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1));

    }

    @Test
    @DisplayName("GET /api/customers → 200 OK")

    void getAll_valid() throws Exception {

        Mockito.when(customerService.getAllCustomers()).thenReturn(List.of(customer()));
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));



    }

    @Test
    @DisplayName("PUT /api/customers/change-password → 200 OK")

    void changePassword_valid() throws Exception {

        var req = changePwd();
        mockMvc.perform(put("/api/customers/change-password").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))).andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Password changed successfully"));

    }



    @Test
    @DisplayName("PUT /api/customers/forgot-password → 200 OK")

    void forgotPassword_valid() throws Exception {

        var req = forgotPwd();
        mockMvc.perform(put("/api/customers/forgot-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Password reset successfully"));

    }



    @Test
    @DisplayName("GET /api/customers/username/{username} → 200 OK")

    void getEmailByUsername_valid() throws Exception {
        Mockito.when(customerService.getEmailByUsername("virat18"))
                .thenReturn("virat@example.com");



        mockMvc.perform(get("/api/customers/username/virat18"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("virat@example.com"));



    }



// -------------------- PAYMENT API --------------------



    @Test
    @DisplayName("POST /api/payment-cards → 201 Created")
    void addPaymentCard_valid() throws Exception {
        PaymentCardDTO req = paymentCard();
        req.setCardId(null); // incoming request should not have an id
        when(paymentService.addPaymentCard(any(PaymentCardDTO.class)))
                .thenReturn(paymentCard());

        mockMvc.perform(post("/api/payment-cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cardId").value(100));

    }



    @Test
    @DisplayName("GET /api/payment-cards/customer/{customerId} → 200 OK")

    void getPaymentCardsByCustomer_valid() throws Exception {

        when(paymentService.getPaymentCardsByCustomerId(1))
                .thenReturn(List.of(paymentCard()));

        mockMvc.perform(get("/api/payment-cards/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cardId").value(100));

    }



    @Test
    @DisplayName("DELETE /api/payment-cards/{cardId} → No Content")

    void deletePaymentCard_valid() throws Exception {

        doNothing().when(paymentService).deletePaymentCard(100);
        mockMvc.perform(delete("/api/payment-cards/100"))
                .andExpect(status().isNoContent());

        Mockito.verify(paymentService).deletePaymentCard(100);

    }



// --------------------- LIBRARY API ---------------------



    @Test
    @DisplayName("GET /api/library/{customerId} → 200 OK")

    void getLibraryByCustomerId_shouldReturnList() throws Exception {

        Mockito.when(libraryService.getLibraryByCustomerId(2))
                .thenReturn(List.of(library()));

        mockMvc.perform(get("/api/library/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].libraryId").value(201));

    }



    @Test
    @DisplayName("DELETE /api/library/{customerId}/{audioId} → No Content")

    void removeFromLibrary_shouldReturnNoContent() throws Exception {

        doNothing().when(libraryService).removeFromLibrary(2, 401);

        mockMvc.perform(delete("/api/library/2/401"))
                .andExpect(status().isNoContent());

    }

// --------------------- ORDER API ---------------------

    @Test
    @DisplayName("GET /api/orders/{customerId} -> 200 OK with list")

    void getOrdersByCustomerId_returnsListOk() throws Exception {

        when(orderService.getOrdersByCustomerId(5)).thenReturn(List.of(order()));
        mockMvc.perform(get("/api/orders/5"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(101));

    }

    @Test
    @DisplayName("GET /api/orders/{customerId} -> 500 when service throws runtime exception")

    void getOrdersByCustomerId_serviceThrowsRuntimeException_returns500() throws Exception {

        when(orderService.getOrdersByCustomerId(6)).thenThrow(new RuntimeException("unexpected"));
        mockMvc.perform(get("/api/orders/6"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorCode").exists())
                .andExpect(jsonPath("$.errorMessage").exists());

    }

    @Test
    @DisplayName("POST /api/orders/{customerId}/place → 201 Created")

    void placeOrder_shouldReturnCreated() throws Exception {

        Mockito.when(orderService.placeOrder(1, "Credit Card")).thenReturn(order());

        mockMvc.perform(post("/api/orders/1/place")
                        .param("paymentMethod", "Credit Card")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(101));

    }


// --------------------- AUTHOR API ---------------------

    @Test
    @DisplayName("GET /api/authors/{authorId} → 200 OK")

    void getAuthorById_shouldReturnOk() throws Exception {

        Mockito.when(authorService.getAuthorById(7))
                .thenReturn(author());

        mockMvc.perform(get("/api/authors/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorId").value(7));

    }



    @Test
    @DisplayName("POST /api/authors → 201 Created")

    void addAuthor_valid() throws Exception {

        AuthorDTO req = new AuthorDTO();
        req.setAuthorName("Hector Garcia");
        when(authorService.addAuthor(any(AuthorDTO.class))).thenReturn(author());
        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.authorId").value(7));

    }

    @Test
    @DisplayName("GET /api/authors → 200 OK")
    void getAllAuthors_shouldReturnList() throws Exception {

        Mockito.when(authorService.getAllAuthors())
                .thenReturn(List.of(author(), author()));

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }



// --------------------- AUDIOBOOK API ---------------------



    @Test
    @DisplayName("POST /api/audiobooks → 201 Created")

    void addAudiobook_shouldReturnCreated() throws Exception {

        AudiobookDTO req = new AudiobookDTO();

        req.setAudioId(400);
        req.setTitle("New Audio");
        req.setPrice(BigDecimal.valueOf(250));

        Mockito.when(audiobookService.addAudiobook(any(AudiobookDTO.class)))
                .thenReturn(audio());

        mockMvc.perform(post("/api/audiobooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.audioId").value(401));

    }

    @Test
    @DisplayName("GET /api/audiobooks/{audioId} → 200 OK")

    void getAudiobookById_shouldReturnOk() throws Exception {

        Mockito.when(audiobookService.getAudiobookById(401))
                .thenReturn(audio());
        mockMvc.perform(get("/api/audiobooks/401"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.audioId").value(401));
    }



    @Test
    @DisplayName("GET /api/audiobooks/search?title=... → 200 OK")

    void searchAudiobooks_shouldReturnList() throws Exception {

        Mockito.when(audiobookService.searchAudiobooks("Get Happy"))
                .thenReturn(List.of(audio()));

        mockMvc.perform(get("/api/audiobooks/search")
                        .param("title", "Get Happy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].audioId").value(401));



    }



    @Test
    @DisplayName("GET /api/audiobooks/author/{authorId} → 200 OK")

    void getAudiobooksByAuthor_shouldReturnList() throws Exception {

        Mockito.when(audiobookService.getAudiobooksByAuthor(301))
                .thenReturn(List.of(audio()));

        mockMvc.perform(get("/api/audiobooks/author/301"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].audioId").value(401));

    }



// --------------------- CART API ---------------------

    @Test
    @DisplayName("GET /api/carts/{customerId} → 200 OK")

    void getCartByCustomerId_shouldReturnOk() throws Exception {

        Mockito.when(cartService.getCartByCustomerId(3))
                .thenReturn(cart());

        mockMvc.perform(get("/api/carts/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value(501));

    }

    @Test
    @DisplayName("POST /api/carts/{customerId}/add/{audioId} → 200 OK")

    void addToCart_shouldReturnOk() throws Exception {

        Mockito.when(cartService.addToCart(3, 401))
                .thenReturn(cart());

        mockMvc.perform(post("/api/carts/3/add/401"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value(501));

    }



    @Test
    @DisplayName("DELETE /api/carts/{customerId}/remove/{audioId} → 200 OK")

    void removeFromCart_shouldReturnOk() throws Exception {

        Mockito.when(cartService.removeFromCart(3, 401))
                .thenReturn(cart());

        mockMvc.perform(delete("/api/carts/3/remove/401"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value(501));

    }

    @Test
    @DisplayName("DELETE /api/carts/{customerId}/clear → 204 No Content")
    void clearCart_shouldReturnNoContent() throws Exception {
        doNothing().when(cartService).clearCart(3);

        mockMvc.perform(delete("/api/carts/3/clear"))
                .andExpect(status().isNoContent());

    }



//-------------------------------WISHLIST API--------------------------------------

    @Test
    @DisplayName("POST /api/wishlist/{customerId}/{audioId} -> 200 OK (Added to wishlist)")

    void addToWishlist_returnsOk() throws Exception {

        doNothing().when(wishlistService).addToWishlist(1, 10);

        mockMvc.perform(post("/api/wishlist/1/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Added to wishlist"));

        Mockito.verify(wishlistService, Mockito.times(1)).addToWishlist(1, 10);

    }



    @Test
    @DisplayName("DELETE /api/wishlist/{customerId}/{audioId} -> 200 OK (Removed from wishlist)")

    void removeFromWishlist_returnsOk() throws Exception {

        doNothing().when(wishlistService).removeFromWishlist(2, 20);
        mockMvc.perform(delete("/api/wishlist/2/20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Removed from wishlist"));
        Mockito.verify(wishlistService, Mockito.times(1)).removeFromWishlist(2, 20);

    }

    @Test
    @DisplayName("GET /api/wishlist/{customerId} -> 200 OK (returns list)")

    void getWishlist_returnsList() throws Exception {

        WishlistDTO dto = wishlist();
        when(wishlistService.getWishlist(1)).thenReturn(List.of(dto));
        mockMvc.perform(get("/api/wishlist/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].wishlistId")
                        .value(5));
        Mockito.verify(wishlistService, Mockito.times(1)).getWishlist(1);
    }

}