package powerautomate.codeless.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import powerautomate.codeless.models.TestCase;
import powerautomate.codeless.requestmodels.TestcaseRequest;
import powerautomate.codeless.services.TestCaseService;

import java.util.List;
import java.util.UUID;

@RestController
public class TestCaseController {

    @Autowired
    TestCaseService testCaseService;

    @Operation(summary = "Add a testCase", description = "Returns a testcase id")
    @PostMapping("/testcases")
    public ResponseEntity addTestCase(@RequestBody TestcaseRequest testcaseRequest){
        TestCase testCase = TestCase.builder().id(UUID.randomUUID().toString())
                .steps(testcaseRequest.steps)
                .description(testcaseRequest.description)
                .monitorId(null)
                .build();
        String id = testCaseService.addCase(testCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @Operation(summary = "Get testcase", description = "Returns a testcase based on id")
    @GetMapping("/testcases/{id}")
    public ResponseEntity getTestCase(@PathVariable("id") String id){
        TestCase testCase = testCaseService.getCase(id);
        return ResponseEntity.status(HttpStatus.OK).body(testCase);
    }
    @Operation(summary = "Get all testcases", description = "Returns all testcases")
    @GetMapping("/testcases")
    public ResponseEntity getAllCases(){
        List<TestCase> testCaseList = testCaseService.getAllTestCases();
        return ResponseEntity.status(HttpStatus.OK).body(testCaseList);
    }

    @Operation(summary = "Delete testcase", description = "Delete a testcase based on id")
    @DeleteMapping("/testcases/{id}")
    public ResponseEntity deleteTestCase(@PathVariable("id") String id){
        TestCase testCase = testCaseService.deleteCase(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
    @Operation(summary = "Run testcase", description = "Run testcase based on id and returns monitor id")
    @PostMapping("/testcases/{id}/actions/run")
    public ResponseEntity runTestCase(@PathVariable("id") String id){
        String monitorId = testCaseService.runTestCase(id);
        return ResponseEntity.status(HttpStatus.OK).body(monitorId);
    }

    @Operation(summary = "Kill testcase", description = "kill a testcase")
    @PostMapping("/testcases/{id}/actions/kill")
    public ResponseEntity killTestCase(@PathVariable("id") String id){
        TestCase testCase = testCaseService.deleteCase(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @Operation(summary = "Kill testcase", description = "kill a testcase")
    @PostMapping("/testcases/{testcaseId}/runs/{runId}")
    public ResponseEntity getLogs(@PathVariable("testcaseId") String testcaseId, @PathVariable("runId") String runId){

        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

}
