package junit.controller;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.ObjectMapper;
import org.budgetbuddy.communication.request.BudgetItemRequest;
import org.budgetbuddy.controller.BudgetController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BudgetController.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@ActiveProfiles("test")
public class BudgetControllerTest {
    //============================-Variables-=================================
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    //==============================-Tests-===================================

    //------------------------Controller-Not-Null-----------------------------
    @Test
    public void testBudgetControllerNotNull(){
        assertNotNull(this.mockMvc);
    }
    //------------------------Test-Budget-Homepage----------------------------

    //this method will test the /budget mapping from BudgetController.java
    @Test
    public void testBudget() throws Exception{
        this.mockMvc.perform(get("/budget/")
                        .with(csrf()))
                        //tests that status code is as expected
                        .andExpect(status().isOk());
    }
    //------------------------Test-Add-Budget-Item----------------------------
    @Test
    public void testAddBudgetItem() throws Exception {
        BudgetItemRequest budgetItemRequest = new BudgetItemRequest("food", 50, "grocery");
        this.mockMvc.perform(post("/budget/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(budgetItemRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Sucsess"));
    }
    //------------------------Test-Get-Budget-Item----------------------------
    @Test
    public void testGetBudget() throws Exception {
        this.mockMvc.perform(get("/budget/get")
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}