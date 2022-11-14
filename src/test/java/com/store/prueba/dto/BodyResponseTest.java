package com.store.prueba.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyResponseTest {

    BodyResponse bodyResponse;

    @BeforeEach
    void setup(){
        bodyResponse = new BodyResponse(200, "ok");
    }

    @Test
    void constructorTest(){

        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.getCode()>100);
        assertNotNull(bodyResponse.getMessage());
    }
    @Test
    void setTest(){

        bodyResponse.setCode(500);
        bodyResponse.setMessage("Internal server error");
        assertEquals(500, bodyResponse.getCode());
        assertEquals("Internal server error", bodyResponse.getMessage());
    }
}
