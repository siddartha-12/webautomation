package powerautomate.codeless.Tasks;

import org.openqa.selenium.WebDriver;
import powerautomate.codeless.elements.Element;
import powerautomate.codeless.exceptions.StepFailedException;
import powerautomate.codeless.exceptions.StepNotFound;
import powerautomate.codeless.models.*;
import powerautomate.codeless.providers.drivers.DateProvider;
import powerautomate.codeless.registeries.SimpleDateFormatRegistry;
import powerautomate.codeless.respositories.TestCaseResultsRepository;
import powerautomate.codeless.respositories.TestcaseRepository;
import powerautomate.codeless.respositories.TestcaseRunningMonitorRepository;
import powerautomate.codeless.services.StepService;

import java.text.SimpleDateFormat;
import java.util.Optional;

public class TestCaseExecution implements Runnable{
    private final TestCase testCase;
    private final WebDriver webDriver;
    private final StepService stepService;
    private final TestCaseResultsRepository testCaseResultsRepository;
    private final DateProvider dateProvider;
    private TestCaseRunningMonitor testCaseRunningMonitor;
    private final TestcaseRunningMonitorRepository testcaseRunningMonitorRepository;
    private final TestcaseRepository testcaseRepository;
    private static final int stepsUpdateInterval = 5;
    private int currentStep;
    private final TestCaseResult testCaseResult;
    private boolean testCasePassed;
    private SimpleDateFormat formatter;

    public TestCaseExecution(TestCase testCase, WebDriver webDriver,StepService stepService, TestCaseResultsRepository testCaseResultsRepository, TestCaseRunningMonitor testCaseRunningMonitor, TestcaseRunningMonitorRepository testcaseRunningMonitorRepository, TestcaseRepository testcaseRepository){
        this.testCase = testCase;
        this.webDriver = webDriver;
        this.stepService = stepService;
        this.testCaseResultsRepository = testCaseResultsRepository;
        this.testCaseRunningMonitor = testCaseRunningMonitor;
        this.testcaseRunningMonitorRepository = testcaseRunningMonitorRepository;
        this.testcaseRepository = testcaseRepository;
        dateProvider = DateProvider.getInstance();
        Optional<TestCaseResult> optionalTestCaseResult = testCaseResultsRepository.findById(testCase.getId());
        testCaseResult = optionalTestCaseResult.get();
        currentStep = 1;
        testCasePassed = true;
        formatter = SimpleDateFormatRegistry.getInstance().get("dd/MM/yyyy HH:mm:ss");
    }
    @Override
    public void run() {

        testCaseResult.setTotalRuns(testCaseResult.getTotalRuns()+1);
        testCaseResult.setLastRun(dateProvider.getCurrentDate(formatter));
        testCaseRunningMonitor.setTestCaseRunningStatus(TestCaseRunningStatus.running);
        testCaseRunningMonitor = testcaseRunningMonitorRepository.save(testCaseRunningMonitor);

        for(StepProperties stepProperties:testCase.getSteps()) {
            try {
                Step step = stepService.getStep(stepProperties.stepId);
                Element element = step.getElement();
                element.execute(webDriver);
                testCaseRunningMonitor.getExecutedSteps().add(step.getTitle());
                currentStep+=1;
                updateSteps();
            }
            catch(StepNotFound stepNotFound){
                testCasePassed = false;
                break;
            }
            catch(Exception exception){
                testCasePassed = false;
                break;
            }
        }
        finalUpdate();
    }
    private void updateSteps(){
        if(currentStep%stepsUpdateInterval == 0) {
            testCaseRunningMonitor = testcaseRunningMonitorRepository.save(testCaseRunningMonitor);
        }
    }

    private void finalUpdate(){
        if(testCasePassed) {
            testCaseResult.setLastPassed(dateProvider.getCurrentDate(formatter));
            testCaseResult.setTotalRunsPassed(testCaseResult.getTotalRunsPassed() + 1);
        }
        testCaseResult.setTestCaseStatus(testCasePassed?TestCaseStatus.Pass:TestCaseStatus.Fail);
        testCase.setMonitorId(null);
        testcaseRepository.save(testCase);
        testCaseResultsRepository.save(testCaseResult);
        testCaseRunningMonitor.setTestCaseRunningStatus(TestCaseRunningStatus.completed);
        testcaseRunningMonitorRepository.save(testCaseRunningMonitor);
    }
}
