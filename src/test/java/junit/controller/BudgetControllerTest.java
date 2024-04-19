package junit.controller;

import org.budgetbuddy.controller.BudgetController;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetControllerTest {
    //============================-Variables-=================================
    BudgetController testBudgetController = new BudgetController();
    private MockMvc mockMvc;

    //----------------------------Test-Instantiation--------------------------------
    @Test
    public void testBudgetControllerNotNull(){
        assertNotNull(testBudgetController);
    }

    //----------------------------Test-Methods--------------------------------
    @Test
    public void testBudget() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("budget"));

    }

}
