import java.util.ArrayList;
import java.util.Random;

public class shop
{
    player owner;//owner reference
    int gold = 50;
    final int shopMax = 5;
    final int refreshCost = 2;
    ArrayList<pawn> shop;

    shop(player owner)
    {
        this.owner = owner;
        shop = new ArrayList<>();
    }

    void refreshShop()
    {
        if (gold >= refreshCost)
        {
            gold -= refreshCost;
            shop.clear();
            Random random = new Random();

            while (shop.size() < shopMax)
            {
                switch (random.nextInt(4) + 1)
                {
                    case 1 -> shop.add(new pawn1());
                    case 2 -> shop.add(new pawn2());
                    case 3 -> shop.add(new pawn3());
                    case 4 -> shop.add(new pawn4());
                    default -> System.out.println("RANDOM CLASS ERROR");
                }
            }
        }
    }

    void buyPawn(int shopIndex)
    {
        if (owner.playerBench.size() < owner.benchMax)
        {
            if (gold >= shop.get(shopIndex).currentCost)
            {
                owner.addPawn(shop.get(shopIndex));
                shop.remove(shopIndex);
            }
        }
    }

    void debugShop()
    {
        System.out.println("SHOP " + gold);
        for (pawn i : shop)
        {
            System.out.print(i.name + " ");
        }
        System.out.println();
    }
}
