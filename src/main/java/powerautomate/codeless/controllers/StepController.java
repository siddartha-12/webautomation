package powerautomate.codeless.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import powerautomate.codeless.models.Step;
import powerautomate.codeless.requestmodels.StepRequest;
import powerautomate.codeless.services.StepService;

import java.util.List;
import java.util.UUID;

@RestController
public class StepController {
    @Autowired
    StepService stepService;

    @Operation(summary = "Add a step", description = "Returns a step id")
    @PostMapping("/steps")
    public ResponseEntity addStep(@Valid @RequestBody StepRequest stepRequest){
        Step step = Step.builder().title(stepRequest.getTitle()).Value(stepRequest.getValue())
                .id(UUID.randomUUID().toString())
                .element(null)
                .build();
        String id = stepService.addStep(step);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @Operation(summary = "Get step", description = "Returns a step details as per id")
    @GetMapping("/steps/{id}")
    public ResponseEntity getStep(@PathVariable("id") String id){
        Step step = stepService.getStep(id);
        return ResponseEntity.status(HttpStatus.OK).body(step);
    }
    @Operation(summary = "Delete step", description = "Delete a step as per id")
    @DeleteMapping("/steps/{id}")
    public ResponseEntity deleteStep(@PathVariable("id") String id){
        String deletedId = stepService.deleteStep(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedId);
    }

    @Operation(summary = "Get all steps", description = "Returns all steps")
    @GetMapping("/steps")
    public ResponseEntity getAllSteps(){
        List<Step> steps = stepService.getAllSteps();
        return ResponseEntity.status(HttpStatus.OK).body(steps);
    }

    @Operation(summary = "Update step", description = "Returns a updated step based on id")
    @PatchMapping("/steps/{id}")
    public ResponseEntity updateStep(@PathVariable("id") String id,
                                     @Valid @RequestBody  StepRequest stepRequest){
        Step step = Step.builder().title(stepRequest.getTitle()).Value(stepRequest.getValue())
                .id(id)
                .build();
        Step updatedStep = stepService.updateStep(step);
        return ResponseEntity.status(HttpStatus.OK).body(updatedStep);
    }
}
