package hachi.flspringboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);


        Assert.assertEquals(dto.getName(), name);
        Assert.assertEquals(dto.getAmount(), amount);
        }
        }
