package strategy.item.military;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;

@AllArgsConstructor
@Getter
public class GeneralConfig implements Config {
    private final int happinessDamageModificator;
}
