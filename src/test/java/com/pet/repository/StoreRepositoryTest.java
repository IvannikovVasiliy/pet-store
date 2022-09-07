package com.pet.repository;

import com.pet.entity.City;
import com.pet.entity.StoreEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

//    @Test
//    void existsByNameTest() {
//        //assertTrue(!storeRepository.findAll().iterator().hasNext());
//        StoreEntity store = new StoreEntity(UUID.randomUUID(), "Apple", new City());
//        storeRepository.save(store);
//        assertTrue(storeRepository.existsByName(store.getName()));
//    }

    @Test
    void findAllTest() {
        assertEquals(storeRepository.findAll().size(), 0);
    }
}