package com.hemeijia.apigate.web.comtroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hemeijia.apigate.ApiGateApplicationTests;
import com.hemeijia.apigate.persistence.dao.UserRepository;
import com.hemeijia.apigate.persistence.entity.User;
import com.hemeijia.apigate.web.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIntegrationTests extends ApiGateApplicationTests {

  @Autowired
  UserRepository userRepository;

  @BeforeAll
  public void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @Test
  @Order(1)
  public void loginCustomerWhenNoUser() throws Exception {
    mvc.perform(
        post(apiBasePath + "/login")
            .param("username", "18600000000")
            .param("password", "071RZNVJ1tmJP2020xVJ1H7VVJ1RZNVs")
    )
        .andDo(print())
        .andExpect(status().isUnauthorized())
    ;
  }

  @Test
  @Order(3)
  public void loginCustomer() throws Exception {
    registerValidCustomer();
    mvc.perform(
        post(apiBasePath + "/login")
            .param("username", "18600000000")
            .param("password", "071RZNVJ1tmJP2020xVJ1H7VVJ1RZNVs")
    )
        .andDo(print())
        .andExpect(status().isOk())
    ;
  }

  @Test
  @Order(2)
  public void registerValidCustomer() throws Exception {
    String username = "18600000000";
    registerUser(
        username,
        "071RZNVJ1tmJP2020xVJ1H7VVJ1RZNVs",
        "18600000000",
        "xxx"
    )
        .andDo(print())
        .andExpect(status().isCreated())
    ;
    User user = userRepository.findByUsername(username).get();
    assertNotNull(user);
    assertNotNull(user.getCreatedAt());
    assertNotNull(user.getUpdatedAt());
  }

  @Test
  @Order(3)
  @WithMockUser(username = "18600000000", password = "071RZNVJ1tmJP2020xVJ1H7VVJ1RZNVs")
  public void getExistUser() throws Exception {
////    registerValidCustomer();
//    loginCustomer();
    mvc.perform(
        get(apiBasePath + "/users")
    )
        .andDo(print())
        .andExpect(status().isForbidden())
//        .andExpect(jsonPath("$.data.username").value("18600000000"))
//        .andExpect(jsonPath("$.data.phoneNumber").value("18600000000"))
//        .andExpect(jsonPath("$.data.member").value(false))
    ;
  }

  private ResultActions registerUser(String username, String password, String phoneNumber,
      String name)
      throws Exception {
    UserDto dto = new UserDto();
    dto.setUsername(username);
    dto.setPassword(password);
    dto.setName(name);
    dto.setPhoneNumber(phoneNumber);
    return mvc.perform(
        post(apiBasePath + "/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto))
    );
  }
}
