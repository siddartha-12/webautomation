package powerautomate.codeless.services;

import org.springframework.stereotype.Service;
import powerautomate.codeless.Tasks.TestCaseExecution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TestCaseExecutionService {
    private final ExecutorService executorService;
    private static final Integer max_no_of_parallel_threads = 10;

    public TestCaseExecutionService() {
        this.executorService = Executors.newFixedThreadPool(max_no_of_parallel_threads);
    }
    public void executeCase(TestCaseExecution testCaseExecution){
        executorService.submit(testCaseExecution);
    }
}
