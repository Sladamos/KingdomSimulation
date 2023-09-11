package strategy.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.simulation.SimulationType;

@AllArgsConstructor
@Getter
public class AppConfig implements Config {

    private final SimulationType simulationType;
}
