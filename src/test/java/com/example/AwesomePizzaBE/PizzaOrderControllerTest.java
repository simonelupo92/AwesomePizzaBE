package com.example.AwesomePizzaBE;

import com.example.AwesomePizzaBE.dto.PizzaOrderDTO;
import com.example.AwesomePizzaBE.entity.OrderStatus;
import com.example.AwesomePizzaBE.exceptions.OrderNotFoundException;
import com.example.AwesomePizzaBE.iservice.PizzaOrderIService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PizzaOrderControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    private PizzaOrderDTO po,po1;

    @MockitoBean
    PizzaOrderIService orderService;

    @BeforeEach
    public void setUpEnv() throws ParseException {
        po = new PizzaOrderDTO();
        po.setId(1);
        po.setOrderCode("123");
        po.setStatus(OrderStatus.valueOf("PENDING"));
        po.setUpdatedIl(LocalDateTime.parse("2025-02-07T15:33:55.69105"));
        po.setCreatoIl(LocalDateTime.parse("2025-02-07T15:33:55.69105"));


        po1 = new PizzaOrderDTO();
        po1.setId(3);
        po1.setOrderCode("1234");
        po1.setStatus(OrderStatus.valueOf("IN_PROGRESS"));
        po1.setUpdatedIl(LocalDateTime.parse("2025-02-07T15:33:55.69105"));
        po1.setCreatoIl(LocalDateTime.parse("2025-02-07T15:33:55.69105"));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/orders/save").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testComplete() throws Exception {
        mockMvc.perform(post("/orders/1234/complete").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetOrderNotFound() throws Exception {
        when(orderService.getOrder("1124"))
                .thenThrow(new OrderNotFoundException());
        mockMvc.perform(get("/orders/getcode")
                        .param("orderCode", "1124").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetOrder() throws Exception {
        mockMvc.perform(get("/orders/getcode").param("orderCode","123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testNext() throws Exception {
        mockMvc.perform(post("/orders/nextorder").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
