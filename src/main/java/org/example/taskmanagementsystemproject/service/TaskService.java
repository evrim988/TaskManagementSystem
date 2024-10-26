package org.example.taskmanagementsystemproject.service;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagementsystemproject.dto.request.task.TaskRequestDto;
import org.example.taskmanagementsystemproject.entity.Task;
import org.example.taskmanagementsystemproject.entity.User;
import org.example.taskmanagementsystemproject.entity.enums.TaskStatus;
import org.example.taskmanagementsystemproject.exception.ErrorType;
import org.example.taskmanagementsystemproject.exception.TaskManagementSystemException;
import org.example.taskmanagementsystemproject.mapper.TaskMappper;
import org.example.taskmanagementsystemproject.repository.TaskRepository;
import org.example.taskmanagementsystemproject.repository.UserRepository;
import org.example.taskmanagementsystemproject.utility.JwtManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final JwtManager jwtManager;
    private final UserRepository userRepository;

    /**
     * Kullanıcıya görev atama işlemi buradan yapılıyor.
     * Burada görev atayan kullanıcıyı token üzerinden alıyorum.
     * Token'ı doğrulayıp o taken'a ait kullanıcıya veri tabanına ekliyorum.
     * @param requestDto
     */
    public void createTask(TaskRequestDto requestDto) {
        //token doğrulama işlemi
        Optional<Long> optionalUserId = jwtManager.validateToken(requestDto.token());
        if (optionalUserId.isEmpty())
            throw new TaskManagementSystemException(ErrorType.INVALID_TOKEN);

        //tokena ait kullanıcı var mı yok mu kontrolü
        Optional<User> optionalUser = userRepository.findById(optionalUserId.get());
        if (optionalUser.isEmpty())
            throw new TaskManagementSystemException(ErrorType.USER_NOT_FOUND);

        //görev atanılan kullanıcı var mı yok mu buradan kontrol ediyorum.
        Optional<User> OptionalToUserId = userRepository.findById(requestDto.assignedToUserId());
        if (OptionalToUserId.isEmpty())
            throw new TaskManagementSystemException(ErrorType.ASSIGNED_TO_USER_NOTFOUND);

        Task task = TaskMappper.INSTANCE.fromTaskRequestDto(requestDto);
        task.setAssignedByUserId(optionalUser.get().getId());
        task.setTaskStatus(TaskStatus.PENDING);
        taskRepository.save(task);
    }

    /**
     * giriş yapan kullanıcının token'ı alarak
     * 64. satırdan itibaren token'ın döndüğü userId ye göre bir User bulmuş oldum.
     * O user a göre atanmış olan task ları görüntüleyen bir metotdur.
     * @param token
     * @return List<Task>
     */
    public List<Task> getMyTaskList(String token){
        Optional<Long> optionalUserId = jwtManager.validateToken(token);
        if (optionalUserId.isEmpty())
            throw new TaskManagementSystemException(ErrorType.INVALID_TOKEN);

        Optional<User> optionalUser = userRepository.findById(optionalUserId.get());
        if(optionalUser.isEmpty())
            throw new TaskManagementSystemException(ErrorType.USER_NOT_FOUND);

        List<Task> taskList = taskRepository.findAllByAssignedToUserId(optionalUser.get().getId());
        if (taskList.isEmpty())
            throw new TaskManagementSystemException(ErrorType.TASK_LIST_EMPTY);
        return taskList;
    }

    /**
     * Kullanıcılara atanan görevler burada listeleniyor.
     * @return List<Task>
     */
    public List<Task> findAllTask(){
        return taskRepository.findAll();
    }
}
