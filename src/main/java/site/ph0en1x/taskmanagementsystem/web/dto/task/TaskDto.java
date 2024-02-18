package site.ph0en1x.taskmanagementsystem.web.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import site.ph0en1x.taskmanagementsystem.model.task.Priority;
import site.ph0en1x.taskmanagementsystem.model.task.Status;
import site.ph0en1x.taskmanagementsystem.validation.onCreate;
import site.ph0en1x.taskmanagementsystem.validation.onUpdate;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    @NotNull(message = "Id must be not null", groups = {onUpdate.class})
    private Long id;

    @NotNull(message = "Title must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Title must be less than 255 symbols", groups = {onCreate.class, onUpdate.class})
    private String title;
    private String description;

    private Status status;

    private Priority priority;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;
}
