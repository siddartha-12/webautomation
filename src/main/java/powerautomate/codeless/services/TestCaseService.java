package powerautomate.codeless.services;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerautomate.codeless.Tasks.TestCaseExecution;
import powerautomate.codeless.exceptions.CaseNotFound;
import powerautomate.codeless.exceptions.StepNotFound;
import powerautomate.codeless.models.*;
import powerautomate.codeless.providers.drivers.DateProvider;
import powerautomate.codeless.registeries.SimpleDateFormatRegistry;
import powerautomate.codeless.respositories.StepRepository;
import powerautomate.codeless.respositories.TestCaseResultsRepository;
import powerautomate.codeless.respositories.TestcaseRepository;
import powerautomate.codeless.respositories.TestcaseRunningMonitorRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Service
public class TestCaseService {

    @Autowired
    TestcaseRepository testcaseRepository;
    @Autowired
    TestCaseResultsRepository testCaseResultsRepository;
    @Autowired
    TestcaseRunningMonitorRepository testcaseRunningMonitorRepository;
    @Autowired
    StepService stepService;
    @Autowired
    TestCaseExecutionService testCaseExecutionService;
    private SimpleDateFormatRegistry simpleDateFormatRegistry;


    public String addCase(TestCase testCase){
        testcaseRepository.save(testCase);
        TestCaseResult testCaseResult = TestCaseResult.builder()
                .testCaseStatus(TestCaseStatus.NotRun)
                .testCaseId(testCase.getId())
                .lastPassed(null)
                .lastRun(null)
                .totalRuns(1)
                .totalRunsPassed(0)
                .build();
        testCaseResultsRepository.save(testCaseResult);
        return testCase.getId();
    }
    public TestCase getCase(String id){
         Optional<TestCase> testCase = testcaseRepository.findById(id);
         if (testCase.isEmpty()){
             throw  new CaseNotFound("Case Not Found");
         }
         return testCase.get();
    }

    public TestCase deleteCase(String id){
        TestCase testCase = null;
        try {
            testCase = getCase(id);
            testcaseRepository.deleteById(id);
            testCaseResultsRepository.deleteById(id);
        }
        catch (CaseNotFound caseNotFound){
            return null;
        }
        return testCase;
    }
    public List<TestCase> getAllTestCases(){
        return testcaseRepository.findAll();
    }
    public String runTestCase(String id){
        try {
            TestCase testCase = getCase(id);
            String monitorId = testCase.getMonitorId();
            if(monitorId!=null){
                return monitorId;
            }
            simpleDateFormatRegistry  = SimpleDateFormatRegistry.getInstance();
            String pattern = "dd/MM/yyyy HH:mm:ss";
            SimpleDateFormat simpleDateFormat = simpleDateFormatRegistry.get(pattern);
            if (simpleDateFormat == null){
                simpleDateFormat = new SimpleDateFormat(pattern);
                simpleDateFormatRegistry.add(pattern,simpleDateFormat);
            }
            TestCaseRunningMonitor testCaseRunningMonitor = TestCaseRunningMonitor.builder()
                    .testCaseId(id)
                    .runId(UUID.randomUUID().toString())
                    .testCaseRunningStatus(TestCaseRunningStatus.started)
                    .executedSteps(new ArrayList<>())
                    .createdTime(DateProvider.getInstance().getCurrentDate(simpleDateFormat))
                    .build();
            testCaseRunningMonitor = testcaseRunningMonitorRepository.save(testCaseRunningMonitor);
            testCase.setMonitorId(testCaseRunningMonitor.getRunId());
            testCase = testcaseRepository.save(testCase);

            WebDriver webDriver = new ChromeDriver();
            TestCaseExecution testCaseExecution = new TestCaseExecution(
                    testCase,
                    webDriver,
                    stepService,
                    testCaseResultsRepository,
                    testCaseRunningMonitor,
                    testcaseRunningMonitorRepository,
                    testcaseRepository);
            testCaseExecutionService.executeCase(testCaseExecution);
            return testCaseRunningMonitor.getRunId();
        }
        catch (CaseNotFound caseNotFound){
            return null;
        }
    }
}
