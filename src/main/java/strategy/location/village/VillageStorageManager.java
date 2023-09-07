package strategy.location.village;

import strategy.item.food.Honey;
import strategy.item.food.Milk;
import strategy.item.plant.Wheat;
import strategy.item.wood.Mahogany;
import strategy.storage.OneItemStorage;

public interface VillageStorageManager {
    OneItemStorage<Milk> getMilkStorage();
    OneItemStorage<Honey> getHoneyStorage();
    OneItemStorage<Wheat> getWheatStorage();
    OneItemStorage<Mahogany> getMahoganyStorage();
}
