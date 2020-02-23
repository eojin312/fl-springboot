package hachi.flspringboot.web.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class HelloRequestDto {
    private final String name;
    private final int amount;

    public HelloResponseDto toResponseDto() {
        return new HelloResponseDto(this.name, this.amount);
    }
}
