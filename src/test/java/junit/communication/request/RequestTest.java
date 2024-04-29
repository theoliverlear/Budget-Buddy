package junit.communication.request;
//=================================-Imports-==================================
import org.budgetbuddy.BudgetBuddyApplication;
import org.budgetbuddy.entity.user.SafePassword;
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.service.BudgetService;
import org.budgetbuddy.controller.BudgetController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BudgetBuddyApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@ActiveProfiles("test")
public class RequestTest {
    //============================-Variables-=================================
    @Autowired
    private MockMvc mockMvc;
    private MockHttpSession session = new MockHttpSession();
    private static final int NUMBER_OF_REQUESTS = 100;
    private static long startTime;
    User testUser = new User("testUser", new SafePassword("password"));

    @BeforeAll
    public static void startTime() {

    }

    @Test
    public void testRequest() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_REQUESTS);
        Callable<Integer> tasks = () -> {
            this.session.setAttribute("user", this.testUser);
            return this.mockMvc.perform(MockMvcRequestBuilders.post("/budget/")
                    .session(this.session))
                    .andReturn().getResponse().getStatus();
        };
        ArrayList<Future<Integer>> futureCalls = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_REQUESTS; i++) {
            Future<Integer> futureCall = executor.submit(tasks);
            futureCalls.add(futureCall);
        }
        startTime = System.currentTimeMillis();
        for (Future<Integer> futureCall : futureCalls) {
            int status = futureCall.get();
            assertEquals(200, status);
        }
        executor.shutdown();
    }

    @AfterAll
    public static void testRequestSpeed() {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time taken to process " + NUMBER_OF_REQUESTS + " requests: " + duration + " milliseconds");
        assertTrue(duration <= 1000);
    }
}
