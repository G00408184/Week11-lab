package ie.atu.week8.projectexercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;



import java.util.Optional;


import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private ProductService productService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product(1L, "kettle", "boils",35);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        mockmvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("kettle"));
    }

    @Test
    void testCreateProduct() throws Exception{
        Product product = new Product(null, "kettle", "boils",35);
        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        ObjectMapper om = new ObjectMapper();
        String valueJson = om.writeValueAsString(product);

        mockmvc.perform(post("/products").contentType("application/json").content(valueJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("kettle"));

    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}