package strategy.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;

@AllArgsConstructor
@Getter
public class AppConfig implements Config {

    private final String simulationType;
}
