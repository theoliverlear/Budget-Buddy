package junit.communication.request;
//=================================-Imports-==================================
import org.budgetbuddy.BudgetBuddyApplication;
import org.budgetbuddy.service.BudgetService;
import org.budgetbuddy.controller.BudgetController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;




@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BudgetController.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@ActiveProfiles("test")
public class RequestTest {
    //============================-Variables-=================================
    @Autowired
    private MockMvc mockMvc;

    private static final int NUMBER_OF_REQUESTS = 100;
    private static long startTime;

    @BeforeAll
    public static void startTime() {
        startTime = System.currentTimeMillis();
    }

    @RepeatedTest(NUMBER_OF_REQUESTS)
    public void testRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/budget/"))
                .andExpect(status().isOk());
    }

    @AfterAll
    public static void testRequestSpeed() {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assert duration <= 1000 : "Took longer than 1 second to process " + NUMBER_OF_REQUESTS + " requests";
    }
}
