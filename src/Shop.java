import java.util.ArrayList;
import java.util.Random;

public class Shop
{
    Player owner;//owner reference
    int gold = 50;
    final int shopMax = 5;
    final int refreshCost = 2;
    ArrayList<Pawn> shop;

    Shop(Player owner)
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
                    case 1 -> shop.add(new Pawn1());
                    case 2 -> shop.add(new Pawn2());
                    case 3 -> shop.add(new Pawn3());
                    case 4 -> shop.add(new Pawn4());
                    default -> System.out.println("RANDOM CLASS ERROR");
                }
            }
        }
    }

    void buyPawn(Pawn pawn)
    {
        if (owner.playerBench.size() < owner.benchMax)
        {
            if (gold >= pawn.currentCost)
            {
                gold-=pawn.currentCost;
                owner.addPawn(pawn);
                shop.remove(pawn);

            }
        }
    }

    void sellPawn(Pawn pawn)
    {
        gold += pawn.currentCost;
        if(owner.playerBoard.contains(pawn))
        {
            owner.playerBoard.remove(pawn);
        }
        else
        {
            owner.playerBench.remove(pawn);
        }
    }

    void debugShop()
    {
        System.out.println("SHOP " + gold);
        for (Pawn i : shop)
        {
            System.out.print(i.name + " ");
        }
        System.out.println();
    }
}
