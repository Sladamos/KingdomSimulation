package strategy.military.infantry.warrior;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.military.InitMilitaryConfig;

@AllArgsConstructor
@Getter
public class InitWarriorsConfig implements InitMilitaryConfig {

    private final int numberOfUnits;

    private final int maxDamage;

    private final int maxDefense;
}
