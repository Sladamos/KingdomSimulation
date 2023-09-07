package strategy.item.military.infantry.warrior;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.item.military.infantry.InfantryConfig;


@AllArgsConstructor
@Getter
public class WarriorConfig implements InfantryConfig {

    private final int damage;

    private final int defense;

    private final int health;
}
