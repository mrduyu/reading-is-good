package com.getir.readingisgood.readingisgood;

import com.getir.readingisgood.readingisgood.authenticationservice.requests.JwtRequest;
import com.getir.readingisgood.readingisgood.bookservice.dataaccesslayer.interfaces.BookRepositoryDAL;
import com.getir.readingisgood.readingisgood.bookservice.requests.InsertBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.responses.InsertBookResponse;
import com.getir.readingisgood.readingisgood.bookservice.responses.ListBooksResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ReadingIsGoodApplicationTests extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void insertNewBook() throws Exception {
        String uri = "/books/insert";
        InsertBookRequest insertBookRequest = new InsertBookRequest();
        insertBookRequest.setAuthor("Emre Akar");
        insertBookRequest.setAmountPerItem(BigDecimal.valueOf(180));
        insertBookRequest.setName("Kara Kutu");
        insertBookRequest.setQuantity(100);

        String inputJson = super.mapToJson(insertBookRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void loginSuccess() throws Exception {
        String uri = "/authenticate";
        JwtRequest request = new JwtRequest("test","pwd");
        String inputJson = super.mapToJson(request);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void loginFail() throws Exception {
        String uri = "/authenticate";
        JwtRequest request = new JwtRequest("test","pwd2");
        String inputJson = super.mapToJson(request);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(inputJson)
                .accept(MediaType.TEXT_PLAIN_VALUE)).
                andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof Exception)).andReturn();
    }

}