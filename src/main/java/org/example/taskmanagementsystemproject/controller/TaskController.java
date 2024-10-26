package org.example.taskmanagementsystemproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taskmanagementsystemproject.dto.request.task.TaskRequestDto;
import org.example.taskmanagementsystemproject.dto.response.BaseResponse;
import org.example.taskmanagementsystemproject.entity.Task;
import org.example.taskmanagementsystemproject.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create-task")
    public ResponseEntity<BaseResponse<Boolean>> createTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {
        taskService.createTask(taskRequestDto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .success(true)
                        .message("Görev oluşturma işlemi başarıyla tamamlandı.")
                        .code(200)
                        .data(true)
                .build());
    }

    @GetMapping("/my-task")
    public ResponseEntity<BaseResponse<List<Task>>> getMyTaskList(String token){
        return ResponseEntity.ok(BaseResponse.<List<Task>>builder()
                        .success(true)
                        .message("Görev listeniz başarılı şekilde listelendi...")
                        .code(200)
                        .data(taskService.getMyTaskList(token))
                .build());
    }


    @GetMapping("/find-all-task")
    public ResponseEntity<BaseResponse<List<Task>>> findAllTask(){
        return ResponseEntity.ok(BaseResponse.<List<Task>>builder()
                        .success(true)
                        .message("Görev listeleme işlemi başarılı...")
                        .code(200)
                        .data(taskService.findAllTask())
                .build());
    }
}
