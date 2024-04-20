package junit.controller;

import org.budgetbuddy.controller.BudgetController;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetControllerTest {
    //============================-Variables-=================================

    BudgetController testBudgetController = new BudgetController();

    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(testBudgetController).build();
    }

    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testBudgetControllerNotNull(){
        assertNotNull(testBudgetController);
    }

    //----------------------------Test-Methods--------------------------------
    @Test
    public void testBudget() throws Exception{
        mockMvc.perform(post("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("budget"));

    }
    @Test
    public void testAdd() throws Exception{
        mockMvc.perform(get("/add")
                .param("food", "50"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sucsess"));

    }

}
