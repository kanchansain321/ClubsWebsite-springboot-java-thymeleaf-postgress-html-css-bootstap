package com.kanchan.Mvc.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ClubDto {
    private Long id;
    @NotEmpty(message = "club title should not be empty")
    private String title;
    @NotEmpty(message ="photo link should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private LocalDateTime createdon;
    private LocalDateTime updatedOn;
    private List<EventDto> events;
}
