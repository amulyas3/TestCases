package com.example.task1.serviceImpl;


import com.example.task1.entity.GroupEntity;
import com.example.task1.entity.SubGroupEntity;
import com.example.task1.entity.UserEntity;
import com.example.task1.repository.GroupRepo;
import com.example.task1.repository.SubGroupRepo;
import com.example.task1.repository.UserRepo;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {UserServiceImplTest.class})
public class UserServiceImplTest {

    @Mock
    UserRepo userRepo;

    @Mock
    GroupRepo groupRepo;
    @Mock
    SubGroupRepo subGroupRepo;

    @InjectMocks
    UserServiceImpl userService;



    @Test
    @Order(1)
    public void test_getAll() {
        List<UserEntity> userEntity = new ArrayList<>();
        {
            userEntity.add(new UserEntity("xyz@gmail.com", "user1", 987654321, new ArrayList<>()));
            userEntity.add(new UserEntity("abc@gmail.com", "user2", 1234567890, new ArrayList<>()));

            when(userRepo.findAll()).thenReturn(userEntity);
            assertEquals(2, userService.getAll().size());
        }
    }

    @Test
    @Order(2)
    public void test_getByUserName() {

        UserEntity userEntity = UserEntity.builder().emailId("xyz@gmail.com").userName("user1").phoneNumber(1234567890).build();
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity1 = UserEntity.builder().emailId("xyzabc@gmail.com").userName("user2").phoneNumber(1234567890).build();
        UserEntity userEntity2 = UserEntity.builder().emailId("xyzab@gmail.com").userName("user3").phoneNumber(1234567890).build();
        userEntities.add(userEntity1);
        userEntities.add(userEntity2);
        when(userRepo.findByUserName("user2")).thenReturn(userEntity);
        assertEquals("user2", userEntity1.getUserName());
        assertNotEquals("amulay@gamail.com", userService.getByUserName("user3"));
    }

    @Test
    @Order(3)
    public void test_getByUserNameAndEmailId() {

        List<UserEntity> userEntities = new ArrayList<>();
        {
            userEntities.add(UserEntity.builder().userName("abcd1").emailId("abcd1@gmail.com").phoneNumber(1234556667).groups(null).build());
            userEntities.add(UserEntity.builder().userName("abcd2").emailId("abcd2@gmail.com").build());
        }
        when(userRepo.findByUserNameAndEmailId("abcd2", "abcd2@gmail.com")).thenReturn(userEntities.get(1));
        assertEquals(
                "abcd2", userEntities.get(1).getUserName());
        assertEquals("abcd2", userService.getByUserNameAndEmailId("abcd2", "abcd2@gmail.com").getUserName());
    }

    @Test
    @Order(4)
    public void test_add() {
        List<GroupEntity> groups = new ArrayList<>();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        groups.add(new GroupEntity(1, "name", subGroups));
        UserEntity user = new UserEntity("user@gmail.com", "Amulya", 789023, groups);
        when(userRepo.save(user)).thenReturn(user);
        assertEquals("User is Added successfully", userService.add(user));
        assertEquals("Amulya", user.getUserName());
    }

    @Test
    @Order(5)
    public void test_Updateuser() {
        List<GroupEntity> groups = new ArrayList<>();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        {
            groups.add(new GroupEntity(1, "xyz", subGroups));
        }
        UserEntity user1 = new UserEntity("abc@gmail.com", "abc", 1234567890, groups);

        when(userRepo.findByEmailId("abc@gmail.com")).thenReturn(user1);
        user1.setUserName("xyz");
        when(userRepo.save(user1)).thenReturn(user1);
        assertEquals("xyz", userService.updateUser(user1, "abc@gmail.com").getUserName());

    }

    @Test
    @Order(6)
    public void test_UpdateGroup() {
        List<GroupEntity> groups = new ArrayList<>();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        {
            groups.add(new GroupEntity(1, "group1", subGroups));
        }
        GroupEntity user2 = new GroupEntity(1, "group1", subGroups);
        when(groupRepo.findById(1)).thenReturn(user2);
        user2.setGroupId(5);
        when(groupRepo.save(user2)).thenReturn(user2);
        assertEquals(5, userService.updateGroup(user2, 1).getGroupId());
    }

    @Test
    @Order(7)
    public void test_UpdateSubgroup() {
        List<SubGroupEntity> subGroups = new ArrayList<>();
        {
            subGroups.add(new SubGroupEntity(5, "subgroup", 3));
        }
        SubGroupEntity subGroup1=new SubGroupEntity();
       when(subGroupRepo.findById(5)).thenReturn(subGroup1);
       subGroup1.setSubgroupId(10);
       when(subGroupRepo.save(subGroup1)).thenReturn(subGroup1);
       assertEquals(10,userService.updateSubGroup(subGroup1,5).getSubgroupId());
    }

    @Test @Order(8)
    public void test_delete(){
//        List<SubGroupEntity> subGroups = new ArrayList<>();
//        {
//            subGroups.add(new SubGroupEntity(5, "subgroup", 3));
//        }
        List<GroupEntity> groups = new ArrayList<>();
        List<SubGroupEntity> subGroups = new ArrayList<>();
        {
            groups.add(new GroupEntity(1, "xyz", subGroups));
        }
        UserEntity user1 = new UserEntity("abc@gmail.com", "abc", 1234567890, groups);

        when(userRepo.findByEmailId("abc@gmail.com")).thenReturn(user1);

        userService.delete("abc@gmail.com");
//        verify(subGroupRepo,times(1)).delete();
    }
}