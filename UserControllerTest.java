package com.example.task1.controller;


import com.example.task1.entity.GroupEntity;
import com.example.task1.entity.SubGroupEntity;
import com.example.task1.entity.UserEntity;
import com.example.task1.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    @Order(1)
    public void test_getAlll() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/user/get");

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());

    }

    @Test @Order(2)

    public void getByUserName() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/user/get/userName");

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }
    @Test @Order(3)

    public void getByUserNameAndEmailId() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/user/get/userName/emailId");

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test @Order(4)
    public void add() throws Exception {
        String uri = "/api/user/add";
        ObjectMapper mapper = new ObjectMapper();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        subGroups.add(new SubGroupEntity(1, "Amulya", 1));
        List<GroupEntity> groupEntityList = new ArrayList<>();
        groupEntityList.add(new GroupEntity(1, "Amulya", subGroups));
        UserEntity userEntity = new UserEntity("amulya@gmail.com", "username", 12345, groupEntityList);

        String result = mapper.writeValueAsString(userEntity);
        System.out.println(result);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(result)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }


    @Test @Order(5)
    public void updateuser() throws Exception {
        String uri = "/api/user/update/amulyaas@gmail.com";
        ObjectMapper mapper = new ObjectMapper();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        subGroups.add(new SubGroupEntity(1, "Amulya", 1));
        List<GroupEntity> groupEntityList = new ArrayList<>();
        groupEntityList.add(new GroupEntity(1, "Amulya", subGroups));
        UserEntity userEntity = new UserEntity("amulyaas@gmail.com", "username1", 123456, groupEntityList);

        String result = mapper.writeValueAsString(userEntity);
        System.out.println(result);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(result)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test @Order(6)
    public void updategroup() throws Exception {
        String uri = "/api/usergroup/update/1";
        ObjectMapper mapper = new ObjectMapper();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        subGroups.add(new SubGroupEntity(1, "Amulya", 1));
        List<GroupEntity> groupEntityList = new ArrayList<>();
        groupEntityList.add(new GroupEntity(1, "Amulya", subGroups));
        UserEntity userEntity = new UserEntity("amulyaas@gmail.com", "username1", 123456, groupEntityList);

        String result = mapper.writeValueAsString(userEntity);
        System.out.println(result);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(result)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }
    @Test @Order(7)
    public void updatesubgroup() throws Exception {
        String uri = "/api/usersubgroup/update/2";
        ObjectMapper mapper = new ObjectMapper();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        subGroups.add(new SubGroupEntity(2, "Amulya", 1));
        List<GroupEntity> groupEntityList = new ArrayList<>();
        groupEntityList.add(new GroupEntity(1, "Amulya", subGroups));
        UserEntity userEntity = new UserEntity("amulyaas@gmail.com", "username1", 123456, groupEntityList);

        String result = mapper.writeValueAsString(userEntity);
        System.out.println(result);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(result)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }
    @Test
    @Order(8)
    void delete() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/api/user/delete/emailId");

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }
}

